package com.systemManage.service;

import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.Criteria;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface AmConferenceService {
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmConference amConference, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmConference selectByPrimaryKey(String conferenceId);

    List<AmConference> selectByExample(Criteria example);

    int deleteByPrimaryKey(String conferenceId);

    int updateByPrimaryKeySelective(AmConference record);

    int updateByPrimaryKey(AmConference record,String amConference);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmConference record, Criteria example);

    int updateByExample(AmConference record, Criteria example);

    int insert(AmConference record);

    int insertSelective(AmConference record);
}