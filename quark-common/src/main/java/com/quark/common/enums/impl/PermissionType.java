package com.quark.common.enums.impl;

import com.quark.common.enums.IDictEnum;

public enum PermissionType implements IDictEnum {
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    private Integer code;

    private String name;

    PermissionType(Integer code, String name) {
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
