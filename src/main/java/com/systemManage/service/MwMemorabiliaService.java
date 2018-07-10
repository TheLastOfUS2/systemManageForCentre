package com.systemManage.service;

import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.MwMemorabilia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface MwMemorabiliaService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(MwMemorabilia mwMemorabilia, HttpServletRequest request);
    
    int countByExample(Criteria example);

    MwMemorabilia selectByPrimaryKey(String memorabiliaId);

    List<MwMemorabilia> selectByExample(Criteria example);

    int deleteByPrimaryKey(String memorabiliaId);

    int updateByPrimaryKeySelective(MwMemorabilia record);

    int updateByPrimaryKey(MwMemorabilia record,String memorabiliaDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(MwMemorabilia record, Criteria example);

    int updateByExample(MwMemorabilia record, Criteria example);

    int insert(MwMemorabilia record);

    int insertSelective(MwMemorabilia record);
}