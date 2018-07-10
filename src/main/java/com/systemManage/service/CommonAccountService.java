package com.systemManage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.CommonAccountDto;

public interface CommonAccountService {
	
	 /**
     * 保存不为空的数据
     * @param request
     * @return
     */
    JSONObject insertSelective(CommonAccount commonAccount, HttpServletRequest request);
    
    /**
     * 查询账号信息
     * @param request
     * @return
     */
    List<CommonAccountDto> selectAcccountName(Criteria example);
    
    /**
     * 更改账号密码
     * @param request
     * @return
     */
    JSONObject updatePassWord(CommonAccount commonAccount, HttpServletRequest request);
    
    /**
     * 领导角色转换
     * @param request
     * @return
     */
    JSONObject updateRoleChange(CommonAccount commonAccount, HttpServletRequest request);
    
    int updateByPrimaryKey(CommonAccount commonAccount,String accountDel);
    
    List<CommonAccountDto> selectByExample(Criteria example);
    
    List<CommonAccountDto> selectByAccount(Criteria example);
     
    int countByExample(Criteria example);

    CommonAccount selectByPrimaryKey(String accountId);

    int deleteByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(CommonAccount record);

    int deleteByExample(Criteria example);

    int updateByExampleSelective(CommonAccount record, Criteria example);

    int updateByExample(CommonAccount record, Criteria example);

    int insert(CommonAccount record);

    int insertSelective(CommonAccount record);
}