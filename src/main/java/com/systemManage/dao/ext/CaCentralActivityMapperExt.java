package com.systemManage.dao.ext;

import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CaCentralActivityMapperExt {
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
    List<CaCentralActivity> selectByExample(Criteria example);
}