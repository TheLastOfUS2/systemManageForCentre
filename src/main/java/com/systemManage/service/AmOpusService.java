package com.systemManage.service;
import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.AmProject;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface AmOpusService {
    
    /**
     * 根据著作id,查询著作和基本信息
     */
    AmOpusDto selectByPrimaryKeyExt(String opusId);
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmOpusDto amOpusDto, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmOpus selectByPrimaryKey(String opusId);

    List<AmOpusDto> selectByExample(Criteria example);

    int deleteByPrimaryKey(String opusId);

    int updateByPrimaryKeySelective(AmOpus record);

    int updateByPrimaryKey(AmOpus record,String opusDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmOpus record, Criteria example);

    int updateByExample(AmOpus record, Criteria example);

    int insert(AmOpus record);

    int insertSelective(AmOpus record);
}