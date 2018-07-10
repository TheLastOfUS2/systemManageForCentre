package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwSummary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwSummaryService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwSummary MwSummary, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwSummary selectByPrimaryKey(String summaryId);

    List<MwSummary> selectByExample(Criteria example);

    int deleteByPrimaryKey(String summaryId);

    int updateByPrimaryKeySelective(MwSummary record);

    int updateByPrimaryKey(MwSummary record,String summaryDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwSummary record, Criteria example);

    int updateByExample(MwSummary record, Criteria example);

    int insert(MwSummary record);

    int insertSelective(MwSummary record);
}