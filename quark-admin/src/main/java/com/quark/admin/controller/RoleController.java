package com.quark.admin.controller;

import com.quark.admin.service.RoleService;
import com.quark.common.base.BaseController;
import com.quark.common.dto.PageResult;
import com.quark.common.dto.QuarkResult;
import com.quark.common.entity.Permission;
import com.quark.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public PageResult getAll(String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int pageSize) {
        int pageIndex = start / pageSize;
        Page<Role> page = roleService.findByPage(pageIndex, pageSize);
        PageResult<List<Role>> result = new PageResult<>(draw, page.getTotalElements(), page.getTotalElements(), page.getContent());
        return result;
    }

    @GetMapping("/rolesWithSelected/{uid}")
    public QuarkResult rolesWithSelected(@PathVariable Integer uid) {
        QuarkResult result = roleService.findRolesAndSelected(uid);
        return result;
    }

    @PostMapping("/add")
    public QuarkResult add(@RequestBody Role role) {
        QuarkResult result = restProcessor(() -> {
            roleService.save(role);
            return QuarkResult.ok();
        });
        return result;
    }

    @PostMapping("/delete")
    public QuarkResult delete(@RequestParam(value = "ids[]") Role[] ids){
        QuarkResult result = restProcessor(() -> {
            roleService.deleteInBatch(Arrays.asList(ids));
            return QuarkResult.ok();
        });

        return result;
    }

    @PostMapping("/saveRolePermission")
    public QuarkResult saveRolePermission(Integer roleid, @RequestParam(value = "pers[]") Permission[] pers){

        QuarkResult result = restProcessor(() -> {
            roleService.saveRolePermission(roleid, pers);
            return QuarkResult.ok();
        });

        return result;
    }

}
