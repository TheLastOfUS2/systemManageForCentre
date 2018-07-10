package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class CommonAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号id
     */
    private String accountId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 用户名
     */
    private String accountName;

    /**
     * 用户密码
     */
    private String accountPassWord;
    
    /**
     * 账号使用状态（0.未使用1.已使用）
     */
    private String accountStatus;

	/**
     * 是否删除（0.未删除1.已删除）
     */
    private String accountDel;

    /**
     * 上传时间
     */
    private Date accountCreateTime;
    
    /**
     * 账号对应角色类型（仅对于领导角色有用,当领导切换为科研人员时，该字段为1；对于其他角色没有用处）
     */
    private String accountRoleType;

    /**
     * @return 账号id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
     *            账号id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return 角色id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId 
     *            角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return 用户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName 
     *            用户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return 用户密码
     */
    public String getAccountPassWord() {
        return accountPassWord;
    }

    /**
     * @param accountPassWord 
     *            用户密码
     */
    public void setAccountPassWord(String accountPassWord) {
        this.accountPassWord = accountPassWord;
    }

    /**
     * @return 是否删除（0.未删除1.已删除）
     */
    public String getAccountDel() {
        return accountDel;
    }

    /**
     * @param accountDel 
     *            是否删除（0.未删除1.已删除）
     */
    public void setAccountDel(String accountDel) {
        this.accountDel = accountDel;
    }

    /**
     * @return 上传时间
     */
    public Date getAccountCreateTime() {
        return accountCreateTime;
    }

    /**
     * @param accountCreateTime 
     *            上传时间
     */
    public void setAccountCreateTime(Date accountCreateTime) {
        this.accountCreateTime = accountCreateTime;
    }
    
    public String getAccountStatus() {
  		return accountStatus;
  	}

  	public void setAccountStatus(String accountStatus) {
  		this.accountStatus = accountStatus;
  	}
  	
  	 public String getAccountRoleType() {
         return accountRoleType;
     }

     public void setAccountRoleType(String accountRoleType) {
         this.accountRoleType = accountRoleType;
     }

}