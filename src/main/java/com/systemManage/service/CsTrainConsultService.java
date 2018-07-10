package com.systemManage.service;

import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.PcTeaching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface CsTrainConsultService {
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(CsTrainConsult csTrainConsult, HttpServletRequest request);
    
    int countByExample(Criteria example);

    CsTrainConsult selectByPrimaryKey(String consultId);

    List<CsTrainConsult> selectByExample(Criteria example);

    int deleteByPrimaryKey(String consultId);

    int updateByPrimaryKeySelective(CsTrainConsult record);

    int updateByPrimaryKey(CsTrainConsult record,String consultDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CsTrainConsult record, Criteria example);

    int updateByExample(CsTrainConsult record, Criteria example);

    int insert(CsTrainConsult record);

    int insertSelective(CsTrainConsult record);
}