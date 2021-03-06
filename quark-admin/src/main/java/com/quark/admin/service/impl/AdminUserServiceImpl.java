package com.quark.admin.service.impl;

import com.google.common.collect.Lists;
import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.RoleService;
import com.quark.admin.utils.PasswordHelper;
import com.quark.common.base.BaseServiceImpl;
import com.quark.common.dao.AdminUserDao;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Role;
import com.quark.common.enums.impl.UserStatus;
import com.quark.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUserDao, AdminUser> implements AdminUserService {

    @Autowired
    private RoleService roleService;

    @Override
    public AdminUser findByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Page<AdminUser> findByPage(AdminUser adminUser, int pageIndex, int pageSize) {
        PageRequest pageable = new PageRequest(pageIndex, pageSize);

        Specification<AdminUser> specification = new Specification<AdminUser>() {

            @Override
            public Predicate toPredicate(Root<AdminUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Integer> $id = root.get("id");
                Path<String> $username = root.get("username");
                Path<Integer> $enable = root.get("enable");

                ArrayList<Predicate> list = Lists.newArrayList();
                if (CommonUtils.notEmpty(adminUser.getId())) {
                    list.add(criteriaBuilder.equal($id, adminUser.getId()));
                }
                if (CommonUtils.notEmpty(adminUser.getEnable())){
                    list.add(criteriaBuilder.equal($enable, adminUser.getEnable()));
                }
                if (adminUser.getUsername() != null){
                    list.add(criteriaBuilder.like($username, "%" + adminUser.getUsername() + "%"));
                }

                Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
                return predicate;
            }
        };

        Page<AdminUser> page = repository.findAll(specification, pageable);

        return page;
    }

    @Override
    public void saveAdmin(AdminUser entity) {
        entity.setEnable(UserStatus.ENABLE.getCode());
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(entity);
        save(entity);
    }

    @Override
    public void saveAdminRoles(Integer uid, Integer[] roles) {
        AdminUser adminUser = findOne(uid);
        if (roles == null) {
            adminUser.setRoles(new HashSet<>());
        } else {
            Set<Role> roleSet = roleService.findAll(Arrays.asList(roles)).stream().collect(toSet());
            adminUser.setRoles(roleSet);
        }
        save(adminUser);
    }

    @Override
    public void saveAdminEnable(Integer[] ids) {

        List<AdminUser> all = findAll(Arrays.asList(ids));
        for (AdminUser user : all) {
            if (user.getEnable() == 1) {
                user.setEnable(0);
            } else {
                user.setEnable(1);
            }
        }
        save(all);
    }
}
