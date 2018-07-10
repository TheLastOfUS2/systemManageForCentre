package com.systemManage.pojo.dto;

import com.systemManage.pojo.base.CommonAccount;

public class CommonAccountDto extends CommonAccount {
    private static final long serialVersionUID = 1L;
    
    /**
     * 角色名称
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
     * 基本信息ID
     */
    private String baseInfoId;
    
    /**
     * 基本信息名称
     */
    private String baseInfoName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRolePower() {
        return rolePower;
    }

    public void setRolePower(String rolePower) {
        this.rolePower = rolePower;
    }

    public String getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public String getBaseInfoName() {
        return baseInfoName;
    }

    public void setBaseInfoName(String baseInfoName) {
        this.baseInfoName = baseInfoName;
    } 
    
}