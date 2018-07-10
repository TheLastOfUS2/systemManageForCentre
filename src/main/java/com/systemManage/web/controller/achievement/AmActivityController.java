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
import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;

/**
 * 类     名:AmActivityController.java
 * 作     用:特色活动管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月14日 下午17:06:28
 */
@Controller
@RequestMapping("/activity")
public class AmActivityController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmActivityService amActivityService; // 特色活动
    
     /**
      * 方法名: toActivityList
      * 描述: 跳转到特色活动列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:07:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toActivityList")
    public String toActivityList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/amActivityList";
    }
        
     /**
      * 方法名: activityList
      * 描述: 特色活动信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:07:48
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/activityList")
    @ResponseBody
    public Object activityList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("activityDel", request.getParameter("activityDel"));
        //责任人姓名
        String activitySpeakerId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(activitySpeakerId)){
        	criteria.put("activitySpeakerId", activitySpeakerId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("activityName").equals(getName)){
        		 criteria.put("activityName2", getValue);
        	}else if(("activityTheme").equals(getName)) {
        		criteria.put("activityTheme2", getValue);
        	}else if(("activityTime").equals(getName)) {
        		criteria.put("activityTime2", getValue);
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
            // 获取sort：排序列字段名、order：排序方式，可以是 'asc' 或者 'desc'，默认值是 'asc'。
            String sort=request.getParameter("sort");
            String order=request.getParameter("order");
            if(StringUtil.isNotBlank(sort) && StringUtil.isNotBlank(order)){
            	sort=getName(sort);
            	criteria.setOrderByClause(" CONVERT("+sort+"  USING gbk) "+order);
            }else{
            	criteria.setOrderByClause(" activity_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amActivityService.countByExample(criteria);
        List<AmActivity> amActivity=amActivityService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amActivity);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("activityName").equals(name)){
    		name="activity_name";  
    	}else if(("activityTheme").equals(name)){
    		name="activity_theme";  
    	}else if(("activitySpeaker").equals(name)){
    		name="activity_speaker";  
    	}else if(("activityTime").equals(name)){
    		name="activity_time";  
    	}
    	return name;
    }
     /**
      * 方法名: activityView
      * 描述: 查看特色活动信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:08:31
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/activityView")
    @ResponseBody
    public Object activityView(HttpServletRequest request, HttpServletResponse response){
        AmActivity amActivity = null;
        String adoptId = request.getParameter("activityId");
        if(StringUtil.isNotBlank(adoptId)){
            amActivity = amActivityService.selectByPrimaryKey(adoptId);
        }
        return amActivity;
    }
    
     /**
      * 方法名: saveOrUpActivity
      * 描述: 新增/编辑特色活动信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:09:25
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpActivity")
    public void saveOrUpActivity(AmActivity amActivity,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amActivityService.insertSelective(amActivity,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteActivity
     * 描述: 批量删除特色活动
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午17:10:52
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteActivity")
    public void deleteActivity(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String activityDel=request.getParameter("activityDel");
        //返回多个id
        String activityId=request.getParameter("activityId");
        if(StringUtil.isNotBlank(activityId) && StringUtil.isNotBlank(activityDel)) {
            String[] strActivityId = activityId.split(",");
            for(int i=0;i<strActivityId.length;i++){
                AmActivity amActivity=amActivityService.selectByPrimaryKey(strActivityId[i]);
                if(amActivity != null) {
                    amActivityService.updateByPrimaryKey(amActivity,activityDel);
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
