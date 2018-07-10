package com.systemManage.service;

import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.Criteria;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface AmActivityService {
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmActivity amActivity, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmActivity selectByPrimaryKey(String activityId);

    List<AmActivity> selectByExample(Criteria example);

    int deleteByPrimaryKey(String activityId);

    int updateByPrimaryKeySelective(AmActivity record);

    int updateByPrimaryKey(AmActivity record,String activityDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmActivity record, Criteria example);

    int updateByExample(AmActivity record, Criteria example);

    int insert(AmActivity record);

    int insertSelective(AmActivity record);
}