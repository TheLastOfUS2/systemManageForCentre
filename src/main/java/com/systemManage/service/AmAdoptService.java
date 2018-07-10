package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmPrize;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;

public interface AmAdoptService {
    
    /**
     * 根据ID查询
     */
    AmAdoptDto selectByPrimaryKeyExt(String adoptId);
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmAdoptDto amAdoptDto, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmAdopt selectByPrimaryKey(String adoptId);

    List<AmAdoptDto> selectByExample(Criteria example);

    int deleteByPrimaryKey(String adoptId);

    int updateByPrimaryKeySelective(AmAdopt record);

    int updateByPrimaryKey(AmAdopt record,String adoptDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmAdopt record, Criteria example);

    int updateByExample(AmAdopt record, Criteria example);

    int insert(AmAdopt record);

    int insertSelective(AmAdopt record);
}