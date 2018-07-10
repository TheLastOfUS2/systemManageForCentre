package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.PcTeaching;

public interface PcTeachingService {
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(PcTeaching pcTeaching, HttpServletRequest request);
    
    int countByExample(Criteria example);

    PcTeaching selectByPrimaryKey(String teachingId);

    List<PcTeaching> selectByExample(Criteria example);

    int deleteByPrimaryKey(String teachingId);

    int updateByPrimaryKeySelective(PcTeaching record);

    int updateByPrimaryKey(PcTeaching record,String teachingDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(PcTeaching record, Criteria example);

    int updateByExample(PcTeaching record, Criteria example);

    int insert(PcTeaching record);

    int insertSelective(PcTeaching record);
}