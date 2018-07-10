package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.FcCooperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface FcCooperationService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(FcCooperation fcCooperation, HttpServletRequest request);
    
    int countByExample(Criteria example);

    FcCooperation selectByPrimaryKey(String cooperationId);

    List<FcCooperation> selectByExample(Criteria example);

    int deleteByPrimaryKey(String cooperationId);

    int updateByPrimaryKeySelective(FcCooperation record);

    int updateByPrimaryKey(FcCooperation record,String cooperationDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(FcCooperation record, Criteria example);

    int updateByExample(FcCooperation record, Criteria example);

    int insert(FcCooperation record);

    int insertSelective(FcCooperation record);
}