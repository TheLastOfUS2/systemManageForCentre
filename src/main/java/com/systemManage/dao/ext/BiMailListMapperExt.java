package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiMailListDto;

public interface BiMailListMapperExt {
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<BiMailListDto> selectByExample(Criteria example);
    
    /**
     * 根据条件查询基本信息和通讯信息
     */
    BiMailListDto selectByPrimaryKeyExt(String mailListId);
    
    /**
     * 根据条件查询基本信息和通讯信息
     */
    BiMailListDto selectBaseInfoIdExt(String baseInfoId);
    
    /**
     * 根据条件查询基本信息和通讯信息
     */
    BiMailListDto selectByMailListIdExt(String mailListId);
    /*
     * 根据账号id,查询基本信息和通讯信息
     */
    BiMailListDto selectByAccountId(String accountId);
    

}