package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.CommonAccountDto;

public interface CommonAccountMapperExt {
	/**
     * 根据项目ID查询
     */
    CommonAccount selectByPrimaryKeyExt(String accountId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<CommonAccountDto> selectByExample(Criteria example);
    
    /**
     * 根据条件查询记录集
     */
    List<CommonAccountDto> selectByAccount(Criteria example);
    
    /**
     * 根据条件查询记录集
     */
    List<CommonAccountDto> selectAcccountName(Criteria example);
    
    CommonAccountDto selectCommonAcccount(String accountId);
    
    
}