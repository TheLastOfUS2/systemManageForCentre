package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwPlan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwPlanService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwPlan mwPlan, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwPlan selectByPrimaryKey(String planId);

    List<MwPlan> selectByExample(Criteria example);

    int deleteByPrimaryKey(String planId);

    int updateByPrimaryKeySelective(MwPlan record);

    int updateByPrimaryKey(MwPlan record,String planDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwPlan record, Criteria example);

    int updateByExample(MwPlan record, Criteria example);

    int insert(MwPlan record);

    int insertSelective(MwPlan record);
}