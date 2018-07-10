package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsOtherEntrust;
import com.systemManage.pojo.base.PcTeaching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CsOtherEntrustService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(CsOtherEntrust csOtherEntrust, HttpServletRequest request);
    
    int countByExample(Criteria example);

    CsOtherEntrust selectByPrimaryKey(String entrustId);

    List<CsOtherEntrust> selectByExample(Criteria example);

    int deleteByPrimaryKey(String entrustId);

    int updateByPrimaryKeySelective(CsOtherEntrust record);

    int updateByPrimaryKey(CsOtherEntrust record,String entrustDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CsOtherEntrust record, Criteria example);

    int updateByExample(CsOtherEntrust record, Criteria example);

    int insert(CsOtherEntrust record);

    int insertSelective(CsOtherEntrust record);
}