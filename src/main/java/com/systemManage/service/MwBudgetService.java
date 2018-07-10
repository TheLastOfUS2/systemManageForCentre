package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwBudget;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwBudgetService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwBudget mwBudget, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwBudget selectByPrimaryKey(String budgetId);

    List<MwBudget> selectByExample(Criteria example);

    int deleteByPrimaryKey(String budgetId);

    int updateByPrimaryKeySelective(MwBudget record);

    int updateByPrimaryKey(MwBudget record,String budgetDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwBudget record, Criteria example);

    int updateByExample(MwBudget record, Criteria example);

    int insert(MwBudget record);

    int insertSelective(MwBudget record);
}