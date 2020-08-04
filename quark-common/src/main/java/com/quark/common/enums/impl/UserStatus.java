package com.quark.common.enums.impl;

import com.quark.common.enums.IDictEnum;

/**
 * 用户状态
 * @author zhangds
 * @date 2020/7/31 16:04
 */
public enum UserStatus implements IDictEnum {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private Integer code;

    private String name;

    UserStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
