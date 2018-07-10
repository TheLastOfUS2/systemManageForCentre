package com.systemManage.service;

import com.systemManage.pojo.base.BiMailList;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiMailListDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public interface BiMailListService {
    
    /*
     * 查询通讯录列表数
     */
    int countByExample(Criteria example);
    
    /*
     * 查询通讯录列表
     */
    List<BiMailListDto> selectByExample(Criteria example);
    
    /*
     * 根据ID查询
     */
    BiMailList selectByPrimaryKey(String mailListId);
    
    /*
     * 根据ID查询
     */
    BiMailListDto selectByPrimaryKeyExt(String mailListId);
    
    /*
     * 根据基本信息id查询
     */
    BiMailListDto selectByBaseInfoIdExt(String baseInfoId);
    
    /*
     * 根据通讯录id查询
     */
    BiMailListDto selectByMailListId(String baseInfoId);

    /*
     * 保存不为空数据
     */
    JSONObject insertSelective(BiMailListDto biMailListExt, HttpServletRequest request);
    
    /*
     * 根据账号id,查询基本信息和通讯信息
     */
    BiMailListDto selectByAccountId(String accountId);
    
    int insertSelective(BiMailList record);
    
    int deleteByPrimaryKey(String mailListId);

    int updateByPrimaryKeySelective(BiMailList record);

    int updateByPrimaryKey(BiMailList record,String mailListDel);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(BiMailList record, Criteria example);

    int updateByExample(BiMailList record, Criteria example);

    int insert(BiMailList record);

 
}