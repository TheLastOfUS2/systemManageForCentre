package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwFund;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwFundService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwFund mwFund, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwFund selectByPrimaryKey(String fundId);

    List<MwFund> selectByExample(Criteria example);

    int deleteByPrimaryKey(String fundId);

    int updateByPrimaryKeySelective(MwFund record);

    int updateByPrimaryKey(MwFund record,String fundDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwFund record, Criteria example);

    int updateByExample(MwFund record, Criteria example);

    int insert(MwFund record);

    int insertSelective(MwFund record);
}