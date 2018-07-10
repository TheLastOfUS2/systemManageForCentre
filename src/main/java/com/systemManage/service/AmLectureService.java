package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.Criteria;

public interface AmLectureService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmLecture amLecture, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmLecture selectByPrimaryKey(String lectureId);

    List<AmLecture> selectByExample(Criteria example);

    int deleteByPrimaryKey(String lectureId);

    int updateByPrimaryKeySelective(AmLecture record);

    int updateByPrimaryKey(AmLecture record,String lectureDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmLecture record, Criteria example);

    int updateByExample(AmLecture record, Criteria example);

    int insert(AmLecture record);

    int insertSelective(AmLecture record);
}