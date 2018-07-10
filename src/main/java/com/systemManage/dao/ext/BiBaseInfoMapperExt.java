package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiBaseInfoDto;

public interface BiBaseInfoMapperExt {
    
    /**
     * 根据条件查询记录集
     */
    List<BiBaseInfo> selectBaseInfoName(Criteria example);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<BiBaseInfoDto> selectByExample(Criteria example);
    
    /**
     * 根据主键查询记录
     */
    BiBaseInfoDto selectByPrimaryKey(String baseInfoId);
    
    /**
     *  根据账号ID查询
     */
    BiBaseInfo selectByAccountId(String accountId);
}