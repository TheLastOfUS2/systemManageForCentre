package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.EpPublicity;

public interface EpPublicityService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(EpPublicity epPublicity, HttpServletRequest request);
    
    int countByExample(Criteria example);

    EpPublicity selectByPrimaryKey(String publicityId);

    List<EpPublicity> selectByExample(Criteria example);

    int deleteByPrimaryKey(String publicityId);

    int updateByPrimaryKeySelective(EpPublicity record);

    int updateByPrimaryKey(EpPublicity record,String publicityDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(EpPublicity record, Criteria example);

    int updateByExample(EpPublicity record, Criteria example);

    int insert(EpPublicity record);

    int insertSelective(EpPublicity record);
}