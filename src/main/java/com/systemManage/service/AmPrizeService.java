package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.AmPrize;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmPrizeDto;

public interface AmPrizeService {
    
    /**
     * 根据ID查询
     */
    AmPrizeDto selectByPrimaryKeyExt(String prizeId);
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(AmPrizeDto amPrizeDto, HttpServletRequest request);
    
    int countByExample(Criteria example);

    AmPrize selectByPrimaryKey(String prizeId);

    List<AmPrizeDto> selectByExample(Criteria example);

    int deleteByPrimaryKey(String prizeId);

    int updateByPrimaryKeySelective(AmPrize record);

    int updateByPrimaryKey(AmPrize record,String amPrize);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(AmPrize record, Criteria example);

    int updateByExample(AmPrize record, Criteria example);

    int insert(AmPrize record);

    int insertSelective(AmPrize record);
}