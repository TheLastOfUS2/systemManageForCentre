package com.systemManage.service;

import com.systemManage.pojo.base.AmProject;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmProjectDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface AmProjectService {
    
    /**
     * 根据项目id查询项目信息
     */
    AmProjectDto selectByPrimaryKeyExt(String projectId);
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmProjectDto amProjectDto, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmProject selectByPrimaryKey(String projectId);

    List<AmProjectDto> selectByExample(Criteria example);

    int deleteByPrimaryKey(String projectId);

    int updateByPrimaryKeySelective(AmProject record);

    int updateByPrimaryKey(AmProject record,String projectDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmProject record, Criteria example);

    int updateByExample(AmProject record, Criteria example);

    int insert(AmProject record);

    int insertSelective(AmProject record);
}