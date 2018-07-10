package com.systemManage.web.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fr.function.UUID;
import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.CommonAccountService;

/**
 * 类     名:AccountController.java
 * 作     用:权限管理
 * 作     者:张金秋
 * 日     期:2017 2017年7月17日 下午22:42:10
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private CommonAccountService commonAccountService; // 账号业务层
    
    /**
     * 方法名: toAccountList
     * 描述: 跳转到账号列表
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月17日 下午11:04:09
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
    @RequestMapping("/toAccountList")
    public String toAccountList(HttpServletRequest request, HttpServletResponse response){
        return "common/accountList";
    }
        
    @RequestMapping("/accountList")
    @ResponseBody
    public Object accountList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("accountDel", request.getParameter("accountDel"));
        
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("accountName").equals(getName)){
        		 criteria.put("accountName2", getValue);
        	}else if(("accountPassWord").equals(getName)) {
        		criteria.put("accountPassWord2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            }else{
        		getValue = getValue(getName, getValue);
                criteria.put(getName, getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" account_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = commonAccountService.countByExample(criteria);
        List<CommonAccountDto> commonAccount=commonAccountService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", commonAccount);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("roleName").equals(name)){
            if(("管理员").equals(value)){
               value="0"; 
            }else if(("领导").equals(value)){
                value="1"; 
            }else if(("行政").equals(value)){
                value="2"; 
            }else if(("科研人员").equals(value)){
                value="3"; 
            }else if(("博士后").equals(value)){
                value="4"; 
            }else if(("博士").equals(value)){
                value="5"; 
            }else if(("硕士").equals(value)){
                value="6"; 
            }else if(("其他人员").equals(value)){
                value="7"; 
            }
        }else  if(("accountStatus").equals(name)){
            if(("未使用").equals(value)){
                value="0"; 
             }else {
                 value="1"; 
             }
        }
        return value;
    }
    
    /**
     * 方法名: accountView
     * 描述: 查询账号信息
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月17日 下午11:12:41
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: Object
     */
    @RequestMapping("/accountView")
    @ResponseBody
    public Object accountView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        CommonAccount commonAccount = null;
        String accountId = request.getParameter("accountId");
        if(StringUtil.isNotBlank(accountId)){
            commonAccount = commonAccountService.selectByPrimaryKey(accountId);
        }
        return commonAccount;
    }
    
     /**
      * 方法名: saveOrUpAccount
      * 描述: 保存或更新账户信息
      * 参数: @param amOpusDto
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年7月17日 下午11:13:17
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpAccount")
    public void saveOrUpAccount(CommonAccount commonAccount,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = commonAccountService.insertSelective(commonAccount,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    /**
     * 方法名: deleteAccount
     * 描述: 批量删除账户
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月17日 下午11:14:55
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteAccount")
    public void deleteAccount(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        // 已被使用的账号个数
        int count=0;
        //判断是删除还是恢复
        String accountDel=request.getParameter("accountDel");
        //返回多个id
        String accountId=request.getParameter("accountId");
        if(StringUtil.isNotBlank(accountId) && StringUtil.isNotBlank(accountDel)) {
            String[] strAccountId = accountId.split(",");
            for(int i=0;i<strAccountId.length;i++){
            	CommonAccount commonAccount=commonAccountService.selectByPrimaryKey(strAccountId[i]);
                if(commonAccount != null) {
                	if(StringUtil.isNotBlank(commonAccount.getAccountStatus()) && ("0").equals(commonAccount.getAccountStatus())){
                		commonAccountService.updateByPrimaryKey(commonAccount,accountDel);
                	}else{
                		count++;
                	}
                    json = JsonUtils.setSuccess();
                    json.put("text", "操作成功");
                }else {
                    json = JsonUtils.setError();
                    json.put("text", "操作失败");
                }
            }
        }else {
            json = JsonUtils.setError();
            json.put("text", "系统异常, 请刷新后重试");
        }
        json.put("count", count);
        JsonUtils.outJsonString(json.toString(), response);
    }
    
    /**
     * 方法名: accountNames
     * 描述: 根据角色查询未被分配的账号信息
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月27日 下午11:21:59
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: Object
     */
    @RequestMapping("/accountNames")
    @ResponseBody
    public Object baseInfoNames(HttpServletRequest request, HttpServletResponse response){
        criteria.clear();
        criteria.put("roleName", request.getParameter("roleName"));
        List<CommonAccountDto> accountList = commonAccountService.selectAcccountName(criteria);
        return accountList;
    }
    
    /**
     * 方法名: editPassWord
     * 描述: 更改账户信息
     * 参数: @param amOpusDto
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月17日 下午11:13:17
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
   @RequestMapping("/updatePassWord")
   public void updatePassWord(HttpServletRequest request, HttpServletResponse response){
	   // 根据账号id查询账户信息
	   JSONObject result=null;
	   String accountId=request.getParameter("accountId");
	   if(StringUtil.isNotBlank(accountId)){
		   CommonAccount commonAccount=commonAccountService.selectByPrimaryKey(accountId);
		   if(commonAccount!=null){
			   result = commonAccountService.updatePassWord(commonAccount,request);
		   }
	   }
       JsonUtils.outJsonString(result.toString(), response);
   }
   
   /**
    * 方法名: roleChange
    * 描述: 领导角色转换（领导和科研人员角色相互转换）
    * 参数: @param amOpusDto
    * 参数: @param request
    * 参数: @param response     
    * 创建人: Zhang JinQiu 
    * 创建时间: 2017年7月17日 下午11:13:17
    * 版本号: v1.0   
    * 抛出异常:
    * 返回类型: void
    */
  @RequestMapping("/roleChange")
  public void roleChange(CommonAccount commonAccount,HttpServletRequest request, HttpServletResponse response){
      JSONObject result=null;
      CommonAccount ca=null;
      if(commonAccount!=null && StringUtil.isNotBlank(commonAccount.getAccountId())){
          ca = commonAccountService.selectByPrimaryKey(commonAccount.getAccountId());
          if(ca!=null){
              result = commonAccountService.updateRoleChange(ca,request);
          }
      }
      JsonUtils.outJsonString(result.toString(), response);
  }
}
