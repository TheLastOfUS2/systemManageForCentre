package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.AmPrize;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmPrizeDto;

public interface AmPrizeMapperExt {
    /**
     * 根据项目ID查询
     */
    AmPrizeDto selectByPrimaryKeyExt(String prizeId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<AmPrizeDto> selectByExample(Criteria example);
}