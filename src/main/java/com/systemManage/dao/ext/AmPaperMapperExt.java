package com.systemManage.dao.ext;

import java.util.List;

import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmPaperDto;

public interface AmPaperMapperExt {
    /**
     * 根据项目ID查询
     */
    AmPaperDto selectByPrimaryKeyExt(String paperId);
    
    /**
     * 根据条件查询记录总数
     */
    int countByExample(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<AmPaperDto> selectByExample(Criteria example);
}