package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.EpReport;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface EpReportService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(EpReport epReport, HttpServletRequest request);
    
    int countByExample(Criteria example);

    EpReport selectByPrimaryKey(String reportId);

    List<EpReport> selectByExample(Criteria example);

    int deleteByPrimaryKey(String reportId);

    int updateByPrimaryKeySelective(EpReport record);

    int updateByPrimaryKey(EpReport record,String reportDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(EpReport record, Criteria example);

    int updateByExample(EpReport record, Criteria example);

    int insert(EpReport record);

    int insertSelective(EpReport record);
}