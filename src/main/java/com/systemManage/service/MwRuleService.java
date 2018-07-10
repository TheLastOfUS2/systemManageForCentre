package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwRule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwRuleService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwRule mwRule, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwRule selectByPrimaryKey(String ruleId);

    List<MwRule> selectByExample(Criteria example);

    int deleteByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(MwRule record);

    int updateByPrimaryKey(MwRule record,String ruleDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwRule record, Criteria example);

    int updateByExample(MwRule record, Criteria example);

    int insert(MwRule record);

    int insertSelective(MwRule record);
}