package com.systemManage.service;

import com.systemManage.pojo.base.CommonRole;
import com.systemManage.pojo.base.Criteria;

import java.util.List;

public interface CommonRoleService {
	
	/**
     * 查询角色id
     */
    CommonRole selectByRoleNameExt(String roleName);
    
    int countByExample(Criteria example);

    CommonRole selectByPrimaryKey(String roleId);

    List<CommonRole> selectByExample(Criteria example);

    int deleteByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(CommonRole record);

    int updateByPrimaryKey(CommonRole record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CommonRole record, Criteria example);

    int updateByExample(CommonRole record, Criteria example);

    int insert(CommonRole record);

    int insertSelective(CommonRole record);
}