package com.systemManage.web.controller.achievement;

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
import com.systemManage.pojo.base.AmProject;
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmProjectDto;
import com.systemManage.service.AmProjectService;
import com.systemManage.service.BiBaseInfoService;

/**
 * 类     名:BaseInfoController.java
 * 作     用:基本信息管理
 * 作     者:张金秋
 * 日     期:2017 2017年5月24日 下午14:54:8
 */
@Controller
@RequestMapping("/project")
public class AmProjectController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmProjectService amProjectService; // 项目业务层
    
    @Autowired
    private BiBaseInfoService baseInfoService;//基本信息逻辑层
    
     /**
      * 方法名: toProjectList
      * 描述: 跳转到项目列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:22:39
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toProjectList")
    public String toProjectList(HttpServletRequest request, HttpServletResponse response){
        
    	//责任人id
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            request.setAttribute("baseInfoId", baseInfoId);
        }
        
        // 全文检索
        //责任人姓名
        String qBaseInfoName=request.getParameter("qBaseInfoName");
        if(StringUtil.isNotBlank(qBaseInfoName)){
            request.setAttribute("qBaseInfoName", qBaseInfoName);
        }
        //项目类型（0.横向/1.纵向/2.国际合作项目）
        String projectType=request.getParameter("projectType");
        request.setAttribute("projectType", projectType);
        //责任人类别
        String baseInfoType=request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
            request.setAttribute("baseInfoType", baseInfoType);
        }
        //责任人职称
        String baseInfoPositionalTitles=request.getParameter("baseInfoPositionalTitles");
        if(StringUtil.isNotBlank(baseInfoPositionalTitles)){
            request.setAttribute("baseInfoPositionalTitles", baseInfoPositionalTitles);
        }
        //责任人级别
        String baseInfoLevel=request.getParameter("baseInfoLevel");
        if(StringUtil.isNotBlank(baseInfoLevel)){
            request.setAttribute("baseInfoLevel", baseInfoLevel);
        }
        
        //责任人年龄区间
        String baseInfoStartAge=request.getParameter("baseInfoStartAge");
        if(StringUtil.isNotBlank(baseInfoStartAge)){
        	 request.setAttribute("baseInfStartAge", baseInfoStartAge);
        }
        String baseInfoEndAge=request.getParameter("baseInfoEndAge");
        if(StringUtil.isNotBlank(baseInfoEndAge)){
        	 request.setAttribute("baseInfoEndAge", baseInfoEndAge);
        }
        //成果/项目名称
        String projectName=request.getParameter("projectName");
        if(StringUtil.isNotBlank(projectName)){
            request.setAttribute("projectName", projectName);
        }
        //成果时间区间
        String projectStartTime=request.getParameter("projectStartTime");
        if(StringUtil.isNotBlank(projectStartTime)){
        	 request.setAttribute("projectStartTime", projectStartTime);
        }
        String projectEndTime=request.getParameter("projectEndTime");
        if(StringUtil.isNotBlank(projectEndTime)){
        	 request.setAttribute("projectEndTime", projectEndTime);
        }
        return "achievement/amProjectList";
    }
        
     /**
      * 方法名: projectList
      * 描述: 项目信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:23:46
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/projectList")
    @ResponseBody
    public Object projectList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("projectType", request.getParameter("projectType"));
        criteria.put("projectDel", request.getParameter("projectDel"));
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            criteria.put("baseInfoId",baseInfoId);
        }
       
        //全文检索数据
        //责任人姓名
        String qBaseInfoName=request.getParameter("qBaseInfoName");
        if(StringUtil.isNotBlank(qBaseInfoName)){
            criteria.put("baseInfoName3",qBaseInfoName);
        }
        //责任人类别
        String baseInfoType=request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
        	criteria.put("baseInfoType3", request.getParameter("baseInfoType"));
        }
        //责任人职称
        String baseInfoPositionalTitles=request.getParameter("baseInfoPositionalTitles");
        if(StringUtil.isNotBlank(baseInfoPositionalTitles)){
        	criteria.put("baseInfoPositionalTitles3", request.getParameter("baseInfoPositionalTitles"));
        }
        
        //责任人级别
        String baseInfoLevel=request.getParameter("baseInfoLevel");
        if(StringUtil.isNotBlank(baseInfoLevel)){
        	criteria.put("baseInfoLevel3", request.getParameter("baseInfoLevel"));
        }
        
        //责任人年龄区间
        String baseInfoStartAge=request.getParameter("baseInfoStartAge");
        if(StringUtil.isNotBlank(baseInfoStartAge)){
        	criteria.put("baseInfoStartAge", request.getParameter("baseInfoStartAge"));
        }
        String baseInfoEndAge=request.getParameter("baseInfoEndAge");
        if(StringUtil.isNotBlank(baseInfoEndAge)){
        	criteria.put("baseInfoEndAge", request.getParameter("baseInfoEndAge"));
        }
        
        //成果/项目名称
        String projectName=request.getParameter("projectName");
        if(StringUtil.isNotBlank(projectName)){
        	criteria.put("projectName3", request.getParameter("projectName"));
        }
        
        //成果时间区间
        String projectStartTime=request.getParameter("projectStartTime");
        if(StringUtil.isNotBlank(projectStartTime)){
        	criteria.put("projectStartTime", request.getParameter("projectStartTime"));
        }
        String projectEndTime=request.getParameter("projectEndTime");
        if(StringUtil.isNotBlank(projectEndTime)){
        	criteria.put("projectEndTime", request.getParameter("projectEndTime"));
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("projectName").equals(getName)){
        		 criteria.put("projectName2", getValue);
        	}else if(("projectNumber").equals(getName)) {
        		criteria.put("projectNumber2", getValue);
        	}else if(("projectTime").equals(getName)) {
        		criteria.put("projectTime2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            } else{
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
            	criteria.setOrderByClause(" project_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amProjectService.countByExample(criteria);
        List<AmProjectDto> amProject=amProjectService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amProject);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("projectName").equals(name)){
    		name="project_name";  
    	}else if(("projectNumber").equals(name)){
    		name="project_number";  
    	}else if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("projectCooperativeUnit").equals(name)){
    		name="project_cooperative_unit";  
    	}else if(("projectTime").equals(name)){
    		name="project_time";  
    	}else if(("projectStatus").equals(name)){
    		name="project_status";  
    	}
    	return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("projectStatus").equals(name)){
            if(("在研").equals(value)){
               value="0"; 
            }else if(("结项").equals(value)){
                value="1"; 
            }else if(("延期（已申请）").equals(value)){
                value="2"; 
            }else {
                value="3"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: baseInfoView
      * 描述: 查看基本信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:28
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/projectView")
    @ResponseBody
    public Object projectView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        AmProjectDto amProjectDto = null;
        String path = request.getParameter("path");
        if(StringUtil.isNotBlank(path) && ("addProject").equals(path)){
           
        }else{
            String projectId = request.getParameter("projectId");
            if(StringUtil.isNotBlank(projectId)){
                amProjectDto = amProjectService.selectByPrimaryKeyExt(projectId);
            }
        }
        return amProjectDto;
    }
    
     /**
      * 方法名: saveOrUpProject
      * 描述: 新增/编辑项目信息
      * 参数: @param amProject
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpProject")
    public void saveOrUpProject(AmProjectDto amProjectDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amProjectService.insertSelective(amProjectDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteProject
     * 描述: 批量删除项目
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:25:06
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteProject")
    public void deleteProject(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String projectDel=request.getParameter("projectDel");
        //返回多个id
        String projectId=request.getParameter("projectId");
        if(StringUtil.isNotBlank(projectId) && StringUtil.isNotBlank(projectDel)) {
            String[] strProjectId = projectId.split(",");
            for(int i=0;i<strProjectId.length;i++){
                AmProject amProject=amProjectService.selectByPrimaryKey(strProjectId[i]);
                if(amProject != null) {
                    amProjectService.updateByPrimaryKey(amProject,projectDel);
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
