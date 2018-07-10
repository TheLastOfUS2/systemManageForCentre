package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;

public interface AmAdoptMapperExt {
    /**
     * 根据项目ID查询
     */
    AmAdoptDto selectByPrimaryKeyExt(String adoptId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<AmAdoptDto> selectByExample(Criteria example);
}