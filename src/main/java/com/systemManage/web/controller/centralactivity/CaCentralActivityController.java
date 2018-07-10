package com.systemManage.web.controller.centralactivity;

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
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.CaCentralActivity;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.CaCentralActivityService;

/**
 * 类     名:CaCentralActivityController.java
 * 作     用:中心各项活动管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:16:21
 */
@Controller
@RequestMapping("/centralActivity")
public class CaCentralActivityController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private CaCentralActivityService caCentralActivityService; // 中心各项活动逻辑层
    
     /**
      * 方法名: toCentralActivityList
      * 描述: 跳转到中心各项活动列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:17:04
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toCentralActivityList")
    public String toCentralActivityList(HttpServletRequest request, HttpServletResponse response){
        String activityType=request.getParameter("activityType");
        request.setAttribute("activityType", activityType);
        return "centralactivity/caCentralActivityList";
    }
        
     /**
      * 方法名: centralActivityList
      * 描述: 中心各项活动信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:18:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/centralActivityList")
    @ResponseBody
    public Object centralActivityList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("activityType",  request.getParameter("activityType"));
        criteria.put("activityDel", request.getParameter("activityDel"));
     // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("activityTheme").equals(getName)){
        		 criteria.put("activityTheme2", getValue);
        	}else if(("activityTime").equals(getName)) {
        		criteria.put("activityTime2", getValue);
        	}else if(("activityAddress").equals(getName)) {
        		criteria.put("activityAddress2", getValue);
        	}else if(("activityHost").equals(getName)) {
        		criteria.put("activityHost2", getValue);
        	}else if(("activitySpeaker").equals(getName)) {
        		criteria.put("activitySpeaker2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" activity_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = caCentralActivityService.countByExample(criteria);
        List<CaCentralActivity> caCentralActivity=caCentralActivityService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", caCentralActivity);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
     /**
      * 方法名: centralActivityView
      * 描述: 查看中心各项活动信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:20:03
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/centralActivityView")
    @ResponseBody
    public Object centralActivityView(HttpServletRequest request, HttpServletResponse response){
        CaCentralActivity caCentralActivity = null;
        String activityId = request.getParameter("activityId");
        if(StringUtil.isNotBlank(activityId)){
            caCentralActivity = caCentralActivityService.selectByPrimaryKey(activityId);
        }
        return caCentralActivity;
    }
    
     /**
      * 方法名: saveOrUpCentralActivity
      * 描述: 新增/编辑中心各项活动信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:21:13
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpCentralActivity")
    public void saveOrUpCentralActivity(CaCentralActivity caCentralActivity,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = caCentralActivityService.insertSelective(caCentralActivity,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteCentralActivity
     * 描述: 批量删除中心各项活动
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午11:21:52
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteCentralActivity")
    public void deleteCentralActivity(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
      //判断是删除还是恢复
        String activityDel=request.getParameter("activityDel");
        //返回多个id
        String activityId=request.getParameter("activityId");
        if(StringUtil.isNotBlank(activityId) && StringUtil.isNotBlank(activityDel)) {
            String[] strActivityId = activityId.split(",");
            for(int i=0;i<strActivityId.length;i++){
                CaCentralActivity caCentralActivity=caCentralActivityService.selectByPrimaryKey(strActivityId[i]);
                if(caCentralActivity != null) {
                    caCentralActivityService.updateByPrimaryKey(caCentralActivity,activityDel);
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
