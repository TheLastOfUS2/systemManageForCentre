package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.PcTrain;

public interface PcTrainService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(PcTrain pcTrain, HttpServletRequest request);
    
    int countByExample(Criteria example);

    PcTrain selectByPrimaryKey(String trainId);

    List<PcTrain> selectByExample(Criteria example);

    int deleteByPrimaryKey(String trainId);

    int updateByPrimaryKeySelective(PcTrain record);

    int updateByPrimaryKey(PcTrain record,String pcTrain);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(PcTrain record, Criteria example);

    int updateByExample(PcTrain record, Criteria example);

    int insert(PcTrain record);

    int insertSelective(PcTrain record);
}