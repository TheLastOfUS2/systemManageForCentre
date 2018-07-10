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
import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmConference;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;

/**
 * 类     名:AmConferenceController.java
 * 作     用:学术会议管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月14日 下午16:41:50
 */
@Controller
@RequestMapping("/conference")
public class AmConferenceController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmConferenceService amConferenceService; // 学术会议
    
     /**
      * 方法名: toConferenceList
      * 描述: 跳转到学术会议列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午16:42:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toConferenceList")
    public String toConferenceList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/amConferenceList";
    }
        
     /**
      * 方法名: conferenceList
      * 描述: 学术会议信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午16:43:35
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/conferenceList")
    @ResponseBody
    public Object conferenceList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("conferenceDel", request.getParameter("conferenceDel"));
        //责任人姓名
        String conferenceNameId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(conferenceNameId)){
        	criteria.put("conferenceNameId", conferenceNameId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("conferenceTitle").equals(getName)){
        		 criteria.put("conferenceTitle2", getValue);
        	}else if(("conferenceAddress").equals(getName)) {
        		criteria.put("conferenceAddress2", getValue);
        	}else if(("conferenceStartTime").equals(getName)) {
        		criteria.put("conferenceStartTime2", getValue);
        	}else if(("conferenceEndTime").equals(getName)) {
        		criteria.put("conferenceEndTime2", getValue);
        	}else if(("conferenceChairman").equals(getName)) {
        		criteria.put("conferenceChairman2", getValue);
        	}else if(("conferenceImplementChairman").equals(getName)) {
        		criteria.put("conferenceImplementChairman2", getValue);
        	}else if(("conferenceName").equals(getName)) {
                criteria.put("conferenceName2", getValue);
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
            	criteria.setOrderByClause(" CONVERT("+sort+"  USING gbk) "+order);
            }else{
            	criteria.setOrderByClause(" conference_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amConferenceService.countByExample(criteria);
        List<AmConference> amConference=amConferenceService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amConference);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("conferenceTitle").equals(name)){
    		name="conference_title";  
    	}else if(("conferenceNature").equals(name)){
    		name="conference_nature";  
    	}else if(("conferenceChairman").equals(name)){
    		name="conference_chairman";  
    	}else if(("conferenceImplementChairman").equals(name)){
    		name="conference_implement_chairman";  
    	}else if(("conferenceName").equals(name)){
    		name="conference_name";  
    	}else if(("conferenceAddress").equals(name)){
    		name="conference_address";  
    	}else if(("conferenceStartTime").equals(name)){
    		name="conference_address";  
    	}else if(("conferenceEndTime").equals(name)){
    		name="conference_end_time";  
    	}else if(("conferenceType").equals(name)){
    		name="conference_type";  
    	}
    	return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("conferenceType").equals(name)){
            if(("国内").equals(value)){
               value="0"; 
            }else if(("国外").equals(value)){
                value="1"; 
            }
        }else if(("conferenceNature").equals(name)){
            if(("自办").equals(value)){
                value="0"; 
             }else if(("参会").equals(value)){
                 value="1"; 
             }
         }
        return value;
    }
     /**
      * 方法名: conferenceView
      * 描述: 查看学术会议信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午16:44:35
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/conferenceView")
    @ResponseBody
    public Object conferenceView(HttpServletRequest request, HttpServletResponse response){
        AmConference amConference = null;
        String conferenceId = request.getParameter("conferenceId");
        if(StringUtil.isNotBlank(conferenceId)){
            amConference = amConferenceService.selectByPrimaryKey(conferenceId);
        }
        return amConference;
    }
    
     /**
      * 方法名: saveOrUpConference
      * 描述: 新增/编辑学术会议信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午16:47:25
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpConference")
    public void saveOrUpConference(AmConference amConference,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amConferenceService.insertSelective(amConference,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteConference
     * 描述: 批量删除学术会议
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午16:57:23
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteConference")
    public void deleteConference(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String conferenceDel=request.getParameter("conferenceDel");
        //返回多个id
        String conferenceId=request.getParameter("conferenceId");
        if(StringUtil.isNotBlank(conferenceId) && StringUtil.isNotBlank(conferenceDel)) {
            String[] strConferenceId = conferenceId.split(",");
            for(int i=0;i<strConferenceId.length;i++){
                AmConference amConference=amConferenceService.selectByPrimaryKey(strConferenceId[i]);
                if(amConference != null) {
                    amConferenceService.updateByPrimaryKey(amConference,conferenceDel);
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
