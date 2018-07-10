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
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.PcTeachingService;

/**
 * 类     名:PcTeachingController.java
 * 作     用:授课信息管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 上午11:06:28
 */
@Controller
@RequestMapping("/teaching")
public class PcTeachingController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private PcTeachingService pcTeachingService; // 授课信息
    
     /**
      * 方法名: toTeachingList
      * 描述: 跳转到授课信息列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 上午11:58:17
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toTeachingList")
    public String toTeachingList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/pcTeachingList";
    }
        
     /**
      * 方法名: teachingList
      * 描述: 授课信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 上午11:59:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/teachingList")
    @ResponseBody
    public Object teachingList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("teachingDel", request.getParameter("teachingDel"));
        //责任人姓名
        String teachingNameId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(teachingNameId)){
        	criteria.put("teachingNameId", teachingNameId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("teachingName").equals(getName)){
        		 criteria.put("teachingName2", getValue);
        	}else if(("teachingTitle").equals(getName)) {
        		criteria.put("teachingTitle2", getValue);
        	}else if(("teachingHour").equals(getName)) {
        		criteria.put("teachingHour2", getValue);
        	}else if(("teachingYear").equals(getName)) {
        		criteria.put("teachingYear2", getValue);
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
            	criteria.setOrderByClause(" teaching_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = pcTeachingService.countByExample(criteria);
        List<PcTeaching> pcTeaching=pcTeachingService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", pcTeaching);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("teachingTitle").equals(name)){
    		name="teaching_title";  
    	}else if(("teachingYear").equals(name)){
    		name="teaching_title";  
    	}else if(("teachingName").equals(name)){
    		name="teaching_name";  
    	}else if(("teachingTerm").equals(name)){
    		name="teaching_term";  
    	}else if(("teachingType").equals(name)){
    		name="teaching_type";  
    	}else if(("teachingHour").equals(name)){
    		name="teaching_type";  
    	}
    	return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("teachingTerm").equals(name)){
            if(("第一学期").equals(value)){
               value="0"; 
            }else if(("第二学期").equals(value)){
                value="1"; 
            }
        }
        if(("teachingType").equals(name)){
            if(("博士").equals(value)){
               value="0"; 
            }else if(("硕士").equals(value)){
                value="1"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: teachingView
      * 描述: 查看授课信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 上午12:00:28
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/teachingView")
    @ResponseBody
    public Object teachingView(HttpServletRequest request, HttpServletResponse response){
        PcTeaching pcTeaching = null;
        String teachingId = request.getParameter("teachingId");
        if(StringUtil.isNotBlank(teachingId)){
            pcTeaching = pcTeachingService.selectByPrimaryKey(teachingId);
        }
        return pcTeaching;
    }
    
     /**
      * 方法名: saveOrUpTeaching
      * 描述: 新增/编辑授课信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:00:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpTeaching")
    public void saveOrUpTeaching(PcTeaching pcTeaching,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = pcTeachingService.insertSelective(pcTeaching,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteTeaching
     * 描述: 批量删除授课信息
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午13:01:25
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteTeaching")
    public void deleteTeaching(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String teachingDel=request.getParameter("teachingDel");
        //返回多个id
        String teachingId=request.getParameter("teachingId");
        if(StringUtil.isNotBlank(teachingId) && StringUtil.isNotBlank(teachingDel)) {
            String[] strTeachingId = teachingId.split(",");
            for(int i=0;i<strTeachingId.length;i++){
                PcTeaching pcTeaching=pcTeachingService.selectByPrimaryKey(strTeachingId[i]);
                if(pcTeaching != null) {
                    pcTeachingService.updateByPrimaryKey(pcTeaching,teachingDel);
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
