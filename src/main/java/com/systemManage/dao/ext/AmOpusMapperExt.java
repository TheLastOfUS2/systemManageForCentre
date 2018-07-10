package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;

public interface AmOpusMapperExt {
    /**
     * 根据项目ID查询
     */
    AmOpusDto selectByPrimaryKeyExt(String opusId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<AmOpusDto> selectByExample(Criteria example);
}