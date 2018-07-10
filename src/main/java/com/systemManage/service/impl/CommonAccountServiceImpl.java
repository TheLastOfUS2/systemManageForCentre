package com.systemManage.service.impl;

import com.systemManage.common.utils.CollectionUtils;
import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.dao.base.CommonAccountMapper;
import com.systemManage.dao.ext.CommonAccountMapperExt;
import com.systemManage.dao.ext.CommonRoleMapperExt;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.CommonRole;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.CommonAccountService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonAccountServiceImpl implements CommonAccountService {
    @Autowired
    private CommonAccountMapper commonAccountMapper;
    @Autowired
    private CommonAccountMapperExt commonAccountMapperExt;
    @Autowired
    private CommonRoleMapperExt commonRoleMapperExt;
    
    //查询条件
    Criteria criteria = new Criteria();

    private static final Logger logger = LoggerFactory.getLogger(CommonAccountServiceImpl.class);
    
    @Override
    public JSONObject insertSelective(CommonAccount commonAccount, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        if(commonAccount!=null){
            String accountId=commonAccount.getAccountId();
            if (StringUtil.isEmpty(accountId)) {
                //设置主键
            	commonAccount.setAccountId(StringUtil.getUUID());
                //设置新增时间
            	commonAccount.setAccountCreateTime(new Date());
                //设置删除状态（0.未删除1.已删除）
            	commonAccount.setAccountDel("0");
            	// 设置账号使用状态（0.未使用1.已使用）
            	commonAccount.setAccountStatus("0");
            }
            // 判断用户名是否重复
            criteria.clear();
            if(StringUtil.isNotBlank(commonAccount.getAccountName())){
                criteria.put("accountName", commonAccount.getAccountName());
                List<CommonAccountDto> caList=commonAccountMapperExt.selectByExample(criteria);
                if(caList!=null && caList.size()>0){
                    if (StringUtil.isNotBlank(accountId)) {
                        if(!caList.get(0).getAccountId().equals(accountId)){
                            result = JsonUtils.setError();  
                            result.put("text", "该用户名已存在");
                            return result;
                        }
                    }else{
                        result = JsonUtils.setError();  
                        result.put("text", "该用户名已存在");
                        return result;
                    }
                 
                }
            }
            //保存基本信息
            if (StringUtil.isEmpty(accountId)) {
                res = this.commonAccountMapper.insertSelective(commonAccount);
            }else{
                res = this.commonAccountMapper.updateByPrimaryKeySelective(commonAccount);
            }
        }
        if(res > 0){
            result = JsonUtils.setSuccess();    
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    @Override
    public JSONObject updatePassWord(CommonAccount commonAccount, HttpServletRequest request) {
        int res = 0;
        int res2 = 0;
        JSONObject result = null;
        String accountId=request.getParameter("accountId");
        if(commonAccount!=null){
			   String oldPassWord=request.getParameter("oldPassWord");
			   // 判断原密码是否正确
			   if(oldPassWord.equals(commonAccount.getAccountPassWord())){
				   // 判断新密码和确认新密码是否一致
				   String accountPassword=request.getParameter("accountPassword");
				   String accountPassword2=request.getParameter("accountPassword2");
				   if(accountPassword.equals(accountPassword2)){
					   // 更新密码
					   commonAccount.setAccountPassWord(accountPassword);
					   res = this.commonAccountMapper.updateByPrimaryKeySelective(commonAccount);
				   }else{
					   res2=1;
				   }
			   }else{
			       res2=2;
			   }
        }
        if(res > 0){
        	// 更改session中的账号密码
            CommonAccountDto accountInfo = commonAccountMapperExt.selectCommonAcccount(accountId);
            if(accountInfo!=null) {
                // 向Session中存储用户信息
                HttpSession session = request.getSession();
                session.setAttribute("accountInfo", accountInfo);
            }
            result = JsonUtils.setSuccess();    
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            if(res2==1){
                result.put("text", "操作失败:两次输入密码不一致"); 
            }else if(res2==2){
                result.put("text", "操作失败:原密码不正确"); 
            }else{
                result.put("text", "操作失败");
            }
        }
        return result;
    }
    
    
    @Override
    public JSONObject updateRoleChange(CommonAccount commonAccount, HttpServletRequest request) {
        int res = 0;
        JSONObject result = null;
        String accountRoleType=request.getParameter("accountRoleType");
        String accountId=request.getParameter("accountId");
        // 根据accountRoleType(1.领导3.科研人员) 查询角色表，获取角色id
        String roleName="";
        if(("0").equals(accountRoleType)){
        	roleName="1";
        }else{
        	roleName="3";
        }
        CommonRole commonRole =	commonRoleMapperExt.selectByRoleNameExt(roleName);
        if(commonRole!=null && StringUtil.isNotBlank(commonRole.getRoleId())){
    	   commonAccount.setRoleId(commonRole.getRoleId());
           commonAccount.setAccountRoleType(accountRoleType);
           res = this.commonAccountMapper.updateByPrimaryKeySelective(commonAccount);
        }
        if(res > 0){
            // 更改session中的角色id和账号角色类型
            CommonAccountDto accountInfo = commonAccountMapperExt.selectCommonAcccount(accountId);
            if(accountInfo!=null) {
                // 向Session中存储用户信息
                HttpSession session = request.getSession();
                session.setAttribute("accountInfo", accountInfo);
            }
            result = JsonUtils.setSuccess(); 
            result.put("text", "操作成功");
        }else{
            result = JsonUtils.setError();
            result.put("text", "操作失败");
        }
        return result;
    }
    
    public List<CommonAccountDto> selectAcccountName(Criteria example) {
        return this.commonAccountMapperExt.selectAcccountName(example);
    }
    
    public int countByExample(Criteria example) {
        int count = this.commonAccountMapperExt.countByExample(example);
        logger.debug("count: {}", count);
        return count;
    }
    
    public int updateByPrimaryKey(CommonAccount commonAccount,String accountDel) {
    	 // baseInfoDel=0:恢复;baseInfoDel=1:删除;baseInfoDel=2:彻底删除
        if(!("2").equals(accountDel)){
            //更改数据状态
        	commonAccount.setAccountDel(accountDel);
            return this.commonAccountMapper.updateByPrimaryKey(commonAccount);
        }else{
            //从数据库中删除
            return this.commonAccountMapper.deleteByPrimaryKey(commonAccount.getAccountId());
        }
    }

    public List<CommonAccountDto> selectByExample(Criteria example) {
        return this.commonAccountMapperExt.selectByExample(example);
    }
    
    public List<CommonAccountDto> selectByAccount(Criteria example) {
        return this.commonAccountMapperExt.selectByAccount(example);
    }

    public int deleteByPrimaryKey(String accountId) {
        return this.commonAccountMapper.deleteByPrimaryKey(accountId);
    }

    public int updateByPrimaryKeySelective(CommonAccount record) {
        return this.commonAccountMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByExample(Criteria example) {
        return this.commonAccountMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(CommonAccount record, Criteria example) {
        return this.commonAccountMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(CommonAccount record, Criteria example) {
        return this.commonAccountMapper.updateByExample(record, example);
    }

    public int insert(CommonAccount record) {
        return this.commonAccountMapper.insert(record);
    }

    public int insertSelective(CommonAccount record) {
        return this.commonAccountMapper.insertSelective(record);
    }

	public CommonAccount selectByPrimaryKey(String accountId) {
		return commonAccountMapper.selectByPrimaryKey(accountId);
	}
}