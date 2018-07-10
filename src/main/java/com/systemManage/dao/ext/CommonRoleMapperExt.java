package com.systemManage.dao.ext;

import com.systemManage.pojo.base.CommonRole;

public interface CommonRoleMapperExt {
	/**
     * 查询角色id
     */
    CommonRole selectByRoleNameExt(String roleName);
}