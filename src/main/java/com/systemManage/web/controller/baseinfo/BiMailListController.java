package com.systemManage.web.controller.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.common.utils.WebUtils;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.BiMailList;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiBaseInfoDto;
import com.systemManage.pojo.dto.BiMailListDto;
import com.systemManage.service.BiBaseInfoService;
import com.systemManage.service.BiMailListService;

/**
 * 类     名:BaseInfoController.java
 * 作     用:基本信息管理
 * 作     者:张金秋
 * 日     期:2017 2017年5月24日 下午14:54:8
 */
@Controller
@RequestMapping("/baseInfo")
public class BiMailListController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private BiMailListService biMailListService; // 通讯信息逻辑层
    @Autowired
    private BiBaseInfoService baseInfoService; // 基本信息逻辑层
    
     /**
      * 方法名: tobiMailList
      * 描述: 通讯录列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 下午2:12:14
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toBiMailList")
    public String toMiMailList(HttpServletRequest request, HttpServletResponse response){
        String baseInfoType=request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
            request.setAttribute("baseInfoType", baseInfoType);
        }
        return "baseinfo/biMailList";
    }
    
    /**
     * 方法名: toMailView
     * 描述: 跳转到基本信息明细
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:22:39
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
   @RequestMapping("/toMailView")
   public String toMailView(HttpServletRequest request, HttpServletResponse response){
       String baseInfoType = request.getParameter("baseInfoType");
       if(StringUtil.isNotBlank(baseInfoType)){
           request.setAttribute("baseInfoType", baseInfoType);
       }
       // 获取当前登录用户信息
       //CommonAccountDto accountInfo = (CommonAccountDto) request.getSession().getAttribute("accountInfo");
       return "baseinfo/biMailView";
       /*if(accountInfo != null){
       }else {
          // TODO - 重定向到登录窗口页面, 调用其他网站链接
           return "redirect:/system/toLogin";
       }*/
   }
        
     /**
      * 方法名: biMailList
      * 描述: 基本信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:23:46
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/biMailList")
    @ResponseBody
    public Object biMailList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("baseInfoType", request.getParameter("baseInfoType"));
        criteria.put("mailListDel", request.getParameter("mailListDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("baseInfoName").equals(getName)){
        		 criteria.put("baseInfoName2", getValue);
        	}else if(("mailListPhone").equals(getName)) {
        		criteria.put("mailListPhone2", getValue);
        	}else if(("mailListPhone1").equals(getName)) {
        		criteria.put("mailListPhone12", getValue);
        	}else if(("mailListOph").equals(getName)) {
        		criteria.put("mailListOph2", getValue);
        	}else if(("mailListStudio").equals(getName)) {
        		criteria.put("mailListStudio2", getValue);
        	}else if(("mailListEmail").equals(getName)) {
        		criteria.put("mailListEmail2", getValue);
        	}else if(("mailListAddress").equals(getName)) {
        		criteria.put("mailListAddress2", getValue);
        	}else if(("mailListCompany").equals(getName)) {
                criteria.put("mailListCompany2", getValue);
            }else if(("baseInfoPost").equals(getName)) {
                criteria.put("baseInfoPost2", getValue);
            }else if(("baseInfoStartTime").equals(getName)) {
                criteria.put("baseInfoStartTime2", getValue);
            }else if(("baseInfoEndTime").equals(getName)) {
                criteria.put("baseInfoEndTime2", getValue);
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
            // 获取sort：排序列字段名、order：排序方式，可以是 'asc' 或者 'desc'，默认值是 'asc'。
            String sort=request.getParameter("sort");
            String order=request.getParameter("order");
            if(StringUtil.isNotBlank(sort) && StringUtil.isNotBlank(order)){
            	sort=getName(sort);
            	criteria.setOrderByClause("CONVERT("+sort+"  USING gbk) "+order);
            }else{
            	 criteria.setOrderByClause(" mail_list_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = biMailListService.countByExample(criteria);
        List<BiMailListDto> BiMailListExt=biMailListService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", BiMailListExt);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("mailListPhone").equals(name)){
    		name="mail_list_phone";  
    	}else if(("mailListPhone1").equals(name)){
    		name="mail_list_phone1";  
    	}else if(("mailListOph").equals(name)){
    		name="mail_list_oph";  
    	}else if(("mailListStudio").equals(name)){
    		name="mail_list_studio";  
    	}else if(("baseInfoPositionalTitles").equals(name)){
    		name="base_info_positional_titles";  
    	}else if(("baseInfoPost").equals(name)){
    		name="base_info_post";  
    	}else if(("baseInfoStartTime").equals(name)){
    		name="base_info_start_time";  
    	}else if(("baseInfoEndTime").equals(name)){
    		name="base_info_end_time";  
    	}else if(("mailListEmail").equals(name)){
    		name="mail_list_email";  
    	}else if(("mailListAddress").equals(name)){
    		name="mail_list_address";  
    	}
    	return name;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("baseInfoPositionalTitles").equals(name)){
            if(("研究员").equals(value)){
                value="0"; 
            }else if(("副研究员").equals(value)){
                value="1"; 
            }else if(("助理研究员").equals(value)){
                value="2"; 
            }else if(("教授").equals(value)){
                value="3"; 
            }else if(("副教授").equals(value)){
                value="4"; 
            }else if(("讲师").equals(value)){
                value="5"; 
            }else{
                value="6";
            }
        }
        return value;
    }
    
     /**
      * 方法名: biMailListView
      * 描述: 查看通讯录信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:28
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/biMailListView")
    @ResponseBody
    public Object biMailListView(HttpServletRequest request, HttpServletResponse response){
        String path = request.getParameter("path");
        BiMailListDto biMailListDto = null;
        if(StringUtil.isNotBlank(path)){
        	// 科研人员、其他人员通讯信息明细页面 mailListView
            if(("mailListView").equals(path)){
                String accountId = request.getParameter("accountId");
                if(StringUtil.isNotBlank(accountId)){
                	biMailListDto=biMailListService.selectByAccountId(accountId);
                }
            }else{
            	// 编辑页面查询通讯信息 editMailList
                String mailListId = request.getParameter("mailListId");
                if(StringUtil.isNotBlank(mailListId)){
                	biMailListDto = biMailListService.selectByMailListId(mailListId);
                } 
            }
        }
        return biMailListDto; 
    }
    
     /**
      * 方法名: saveOrUpMailList
      * 描述: 新增/编辑基本信息
      * 参数: @param biMailList
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpMailList")
    public void saveOrUpMailList(BiMailListDto biMailListDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = biMailListService.insertSelective(biMailListDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    /**
     * 方法名: deleteMailList
     * 描述: 批量删除通讯录
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:25:06
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteMailList")
    public void deleteBaseInfo(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String mailListDel=request.getParameter("mailListDel");
        //返回多个id
        String mailListId=request.getParameter("mailListId");
        if(StringUtil.isNotBlank(mailListId) && StringUtil.isNotBlank(mailListDel)) {
            String[] strMailListId = mailListId.split(",");
            for(int i=0;i<strMailListId.length;i++){
                BiMailList biMailList=biMailListService.selectByPrimaryKey(strMailListId[i]);
                if(biMailList != null ) {
                    biMailListService.updateByPrimaryKey(biMailList,mailListDel);
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
        JsonUtils.outJsonString(json.toString(), response);
    }
}
