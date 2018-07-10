package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiBaseInfoDto;

public interface BiBaseInfoService {
	
    
    /**
     * 查询人员名称
     * @param request
     * @return
     */
    List<BiBaseInfo> selectBaseInfoName(Criteria example);
    
	/**
	 * 保存不为空的数据
	 * @param request
	 * @return
	 */
	JSONObject insertSelective(BiBaseInfoDto biBaseInfoExt, HttpServletRequest request);
	
	/**
     * 获取数据总条数
     * @param request
     * @return
     */
	int countByExample(Criteria example);
	
	/**
     * 查询列表数据
     * @param request
     * @return
     */
    List<BiBaseInfoDto> selectByExample(Criteria example);
	
    /**
     * 根据ID查询
     * @param request
     * @return
     */
    BiBaseInfoDto selectByPrimaryKey(String baseInfoId);
    
    /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    int insertSelective(BiBaseInfo record);
    
    /**
     * 更新数据
     * @param request
     * @return
     */
	int updateByPrimaryKey(BiBaseInfo record,String baseInfoDel);
	
	/**
     * 根据账号ID查询
     * @param request
     * @return
     */
	BiBaseInfo selectByAccountId(String accountId);
	
    int deleteByPrimaryKey(String baseInfoId);

    int updateByPrimaryKeySelective(BiBaseInfo record);

   

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiBaseInfo record, Criteria example);

    int updateByExample(BiBaseInfo record, Criteria example);

    int insert(BiBaseInfo record);

   
}