package com.systemManage.pojo.base;

import java.io.Serializable;

public class CommonRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色类型(0,管理员1.领导2.行政3.科研人员4.博士后5.博士6.硕士7.其他人员)
     */
    private String roleName;

    /**
     * 角色对应权限
     */
    private String rolePower;

    /**
     * @return 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
     *            角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 角色类型(0,管理员1.领导2.行政3.科研人员4.博士后5.博士6.硕士7.其他人员)
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName 
     *            角色类型(0,管理员1.领导2.行政3.科研人员4.博士后5.博士6.硕士7.其他人员)
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return 角色对应权限
     */
    public String getRolePower() {
        return rolePower;
    }

    /**
     * @param rolePower 
     *            角色对应权限
     */
    public void setRolePower(String rolePower) {
        this.rolePower = rolePower;
    }
}