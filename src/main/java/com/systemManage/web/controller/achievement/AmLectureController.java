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
import com.systemManage.pojo.base.AmLecture;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;

/**
 * 类     名:AmLectureController.java
 * 作     用:报告讲座管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月14日 下午16:59:28
 */
@Controller
@RequestMapping("/lecture")
public class AmLectureController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmLectureService amLectureService; // 报告讲座
    
     /**
      * 方法名: toLectureList
      * 描述: 跳转到报告讲座列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:00:30
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toLectureList")
    public String toLectureList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/amLectureList";
    }
        
     /**
      * 方法名: lectureList
      * 描述: 报告讲座信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:01:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/lectureList")
    @ResponseBody
    public Object lectureList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("lectureDel", request.getParameter("lectureDel"));
        //责任人姓名
        String lectureSpeakerId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(lectureSpeakerId)){
        	criteria.put("lectureSpeakerId", lectureSpeakerId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("lectureTheme").equals(getName)){
        		 criteria.put("lectureTheme2", getValue);
        	}else if(("lectureSpeaker").equals(getName)) {
        		criteria.put("lectureSpeaker2", getValue);
        	}else if(("lectureTime").equals(getName)) {
        		criteria.put("lectureTime2", getValue);
        	}else if(("lectureAddress").equals(getName)) {
        		criteria.put("lectureAddress2", getValue);
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
            	criteria.setOrderByClause(" lecture_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amLectureService.countByExample(criteria);
        List<AmLecture> amLecture=amLectureService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amLecture);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("lectureTheme").equals(name)){
    		name="lecture_theme";  
    	}else if(("lectureSpeaker").equals(name)){
    		name="lecture_speaker";  
    	}else if(("lectureTime").equals(name)){
    		name="lecture_time";  
    	}else if(("lectureAddress").equals(name)){
    		name="lecture_address";  
    	}
    	return name;
    }
     /**
      * 方法名: lectureView
      * 描述: 查看报告讲座信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:02:39
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/lectureView")
    @ResponseBody
    public Object lectureView(HttpServletRequest request, HttpServletResponse response){
        AmLecture amLecture = null;
        String lectureId = request.getParameter("lectureId");
        if(StringUtil.isNotBlank(lectureId)){
            amLecture = amLectureService.selectByPrimaryKey(lectureId);
        }
        return amLecture;
    }
    
     /**
      * 方法名: saveOrUpLecture
      * 描述: 新增/编辑报告讲座信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午17:03:25
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpLecture")
    public void saveOrUpLecture(AmLecture amLecture,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amLectureService.insertSelective(amLecture,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteLecture
     * 描述: 批量删除报告讲座
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午17:04:10
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteLecture")
    public void deleteLecture(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String lectureDel=request.getParameter("lectureDel");
        //返回多个id
        String lectureId=request.getParameter("lectureId");
        if(StringUtil.isNotBlank(lectureId) && StringUtil.isNotBlank(lectureDel)) {
            String[] strLectureId = lectureId.split(",");
            for(int i=0;i<strLectureId.length;i++){
                AmLecture amLecture=amLectureService.selectByPrimaryKey(strLectureId[i]);
                if(amLecture != null) {
                    amLectureService.updateByPrimaryKey(amLecture,lectureDel);
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
