package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmPaperDto;

public interface AmPaperService {
    
    /**
     * 根据ID查询
     */
    AmPaperDto selectByPrimaryKeyExt(String paperId);
    
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmPaperDto amPaperDto, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmPaper selectByPrimaryKey(String paperId);

    List<AmPaperDto> selectByExample(Criteria example);

    int deleteByPrimaryKey(String paperId);

    int updateByPrimaryKeySelective(AmPaper record);

    int updateByPrimaryKey(AmPaper record,String paperDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmPaper record, Criteria example);

    int updateByExample(AmPaper record, Criteria example);

    int insert(AmPaper record);

    int insertSelective(AmPaper record);
}