package com.systemManage.service;

import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CaCentralActivityService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(CaCentralActivity caCentralActivity, HttpServletRequest request);
    
    int countByExample(Criteria example);

    CaCentralActivity selectByPrimaryKey(String activityId);

    List<CaCentralActivity> selectByExample(Criteria example);

    int deleteByPrimaryKey(String activityId);

    int updateByPrimaryKeySelective(CaCentralActivity record);

    int updateByPrimaryKey(CaCentralActivity record,String activityDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CaCentralActivity record, Criteria example);

    int updateByExample(CaCentralActivity record, Criteria example);

    int insert(CaCentralActivity record);

    int insertSelective(CaCentralActivity record);
}