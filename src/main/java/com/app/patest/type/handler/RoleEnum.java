package com.app.patest.type.handler;

/**
 * @ClassName: RoleEnum
 * @Author: Yangyang
 * @Date: 2019/6/14
 * @Description: TODO
 */
public enum  RoleEnum {
    STUDENT(0,"学生"),
    TEACHER(1,"教师"),
    ADMIN(2,"管理员");

    private int roleCode;
    private String roleName;

    public static RoleEnum getInstance(int roleCode){
        for (RoleEnum r:values()){
            if (r.getRoleCode() == roleCode){
                return r;
            }
        }
        return null;
    }

    RoleEnum(int roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }
}
