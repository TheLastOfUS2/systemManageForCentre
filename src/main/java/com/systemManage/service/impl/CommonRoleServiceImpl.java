package com.systemManage.service.impl;

import com.systemManage.dao.base.CommonRoleMapper;
import com.systemManage.dao.ext.CommonRoleMapperExt;
import com.systemManage.pojo.base.CommonRole;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.CommonRoleService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonRoleServiceImpl implements CommonRoleService {
    @Autowired
    private CommonRoleMapper commonRoleMapper;
    @Autowired
    private CommonRoleMapperExt commonRoleMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(CommonRoleServiceImpl.class);
    
    /**
     * 查询角色id
     */
    public CommonRole selectByRoleNameExt(String roleName){
    	 return this.commonRoleMapperExt.selectByRoleNameExt(roleName);
    }
    
    public int countByExample(Criteria example) {
        int count = this.commonRoleMapper.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }

    public CommonRole selectByPrimaryKey(String roleId) {
        return this.commonRoleMapper.selectByPrimaryKey(roleId);
    }

    public List<CommonRole> selectByExample(Criteria example) {
        return this.commonRoleMapper.selectByExample(example);
    }

    public int deleteByPrimaryKey(String roleId) {
        return this.commonRoleMapper.deleteByPrimaryKey(roleId);
    }

    public int updateByPrimaryKeySelective(CommonRole record) {
        return this.commonRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(CommonRole record) {
        return this.commonRoleMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(Criteria example) {
        return this.commonRoleMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CommonRole record, Criteria example) {
        return this.commonRoleMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CommonRole record, Criteria example) {
        return this.commonRoleMapper.updateByExample(record, example);
    }

    public int insert(CommonRole record) {
        return this.commonRoleMapper.insert(record);
    }

    public int insertSelective(CommonRole record) {
        return this.commonRoleMapper.insertSelective(record);
    }
}