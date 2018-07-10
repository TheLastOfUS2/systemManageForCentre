package com.systemManage.web.controller.achievement;

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

import com.systemManage.common.utils.JsonUtils;
import com.systemManage.common.utils.PageUtil;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.FcCooperation;
import com.systemManage.service.FcCooperationService;

/**
 * 类     名:FcCooperationController.java
 * 作     用:对外合作管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:33:14
 */
@Controller
@RequestMapping("/cooperation")
public class FcCooperationController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private FcCooperationService fcCooperationService; // 对外合作
    
     /**
      * 方法名: toCooperationList
      * 描述: 跳转到对外合作列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:35:54
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toCooperationList")
    public String toCooperationList(HttpServletRequest request, HttpServletResponse response){
        String cooperationType=request.getParameter("cooperationType");
        request.setAttribute("cooperationType", cooperationType);
        return "achievement/fcCooperationList";
    }
        
     /**
      * 方法名: cooperationList
      * 描述: 对外合作信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:36:21
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/cooperationList")
    @ResponseBody
    public Object cooperationList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("cooperationType", request.getParameter("cooperationType"));
        criteria.put("cooperationDel", request.getParameter("cooperationDel"));
        //责任人姓名
        String cooperationPersonLiableId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(cooperationPersonLiableId)){
        	criteria.put("cooperationPersonLiableId", cooperationPersonLiableId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("cooperationProjectName").equals(getName)){
        		 criteria.put("cooperationProjectName2", getValue);
        	}else if(("cooperationCompany").equals(getName)) {
                criteria.put("cooperationCompany2", getValue);
            }else if(("cooperationPersonLiable").equals(getName)) {
                criteria.put("cooperationPersonLiable2", getValue);
            }else if(("cooperationPerson").equals(getName)) {
        		criteria.put("cooperationPerson2", getValue);
        	}else if(("cooperationTime").equals(getName)) {
        		criteria.put("cooperationTime2", getValue);
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
            	criteria.setOrderByClause(" CONVERT("+sort+"  USING gbk) "+order);
            }else{
            	criteria.setOrderByClause(" cooperation_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = fcCooperationService.countByExample(criteria);
        List<FcCooperation> fcCooperation=fcCooperationService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", fcCooperation);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("cooperationProjectName").equals(name)){
    		name="cooperation_project_name";  
    	}else if(("cooperationCompany").equals(name)){
    		name="cooperation_company";  
    	}else if(("cooperationTime").equals(name)){
    		name="cooperation_time";  
    	}else if(("cooperationPersonLiable").equals(name)){
    		name="cooperation_person_liable";  
    	}else if(("cooperationPerson").equals(name)){
    		name="cooperation_person";  
    	}else if(("cooperationPersonCompany").equals(name)){
    		name="cooperation_person_company";  
    	}
    	return name;
    }
     /**
      * 方法名: cooperationView
      * 描述: 查看对外合作信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:39:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/cooperationView")
    @ResponseBody
    public Object cooperationView(HttpServletRequest request, HttpServletResponse response){
        FcCooperation fcCooperation = null;
        String cooperationId = request.getParameter("cooperationId");
        if(StringUtil.isNotBlank(cooperationId)){
            fcCooperation = fcCooperationService.selectByPrimaryKey(cooperationId);
        }
        return fcCooperation;
    }
    
     /**
      * 方法名: saveOrUpCooperation
      * 描述: 新增/编辑对外合作信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午16:55:40
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpCooperation")
    public void saveOrUpCooperation(FcCooperation fcCooperation,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = fcCooperationService.insertSelective(fcCooperation,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteCooperation
     * 描述: 批量删除对外合作
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午16:56:52
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteCooperation")
    public void deleteCooperation(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String cooperationDel=request.getParameter("cooperationDel");
        //返回多个id
        String cooperationId=request.getParameter("cooperationId");
        if(StringUtil.isNotBlank(cooperationId) && StringUtil.isNotBlank(cooperationDel)) {
            String[] strCooperationId = cooperationId.split(",");
            for(int i=0;i<strCooperationId.length;i++){
                FcCooperation fcCooperation=fcCooperationService.selectByPrimaryKey(strCooperationId[i]);
                if(fcCooperation != null) {
                    fcCooperationService.updateByPrimaryKey(fcCooperation,cooperationDel);
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
