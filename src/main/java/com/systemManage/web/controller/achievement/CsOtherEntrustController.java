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
import com.systemManage.pojo.base.CsOtherEntrust;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.CsOtherEntrustService;
import com.systemManage.service.CsTrainConsultService;
import com.systemManage.service.PcTeachingService;

/**
 * 类     名:CsOtherEntrustController.java
 * 作     用:特其他委托色活动管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:20:05
 */
@Controller
@RequestMapping("/entrust")
public class CsOtherEntrustController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private CsOtherEntrustService csOtherEntrustService; // 其他委托
    
     /**
      * 方法名: toEntrustList
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
    @RequestMapping("/toEntrustList")
    public String toEntrustList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/csOtherEntrustList";
    }
        
     /**
      * 方法名: entrustList
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
    @RequestMapping("/entrustList")
    @ResponseBody
    public Object entrustList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("entrustDel", request.getParameter("entrustDel"));
        //责任人姓名
        String entrustUndertakerId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(entrustUndertakerId)){
        	criteria.put("entrustUndertakerId",entrustUndertakerId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("entrustUndertaker").equals(getName)){
        		 criteria.put("entrustUndertaker2", getValue);
        	}else if(("entrustCompany").equals(getName)) {
        		criteria.put("entrustCompany2", getValue);
        	}else if(("entrustTime").equals(getName)) {
        		criteria.put("entrustTime2", getValue);
        	}else if(("entrustTask").equals(getName)) {
        		criteria.put("entrustTask2", getValue);
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
            	criteria.setOrderByClause(" entrust_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = csOtherEntrustService.countByExample(criteria);
        List<CsOtherEntrust> csOtherEntrust=csOtherEntrustService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", csOtherEntrust);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("entrustTask").equals(name)){
    		name="entrust_task";  
    	}else if(("entrustUndertaker").equals(name)){
    		name="entrust_undertaker";  
    	}else if(("entrustTime").equals(name)){
    		name="entrust_time";  
    	}else if(("entrustCompany").equals(name)){
    		name="entrust_company";  
    	}
    	return name;
    }
     /**
      * 方法名: entrustView
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
    @RequestMapping("/entrustView")
    @ResponseBody
    public Object entrustView(HttpServletRequest request, HttpServletResponse response){
        CsOtherEntrust csOtherEntrust = null;
        String entrustId = request.getParameter("entrustId");
        if(StringUtil.isNotBlank(entrustId)){
            csOtherEntrust = csOtherEntrustService.selectByPrimaryKey(entrustId);
        }
        return csOtherEntrust;
    }
    
     /**
      * 方法名: saveOrUpEntrust
      * 描述: 新增/编辑特色活动信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:00:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpEntrust")
    public void saveOrUpEntrust(CsOtherEntrust csOtherEntrust,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = csOtherEntrustService.insertSelective(csOtherEntrust,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteEntrust
     * 描述: 批量删除授课信息
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午13:01:25
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteEntrust")
    public void deleteEntrust(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
      //判断是删除还是恢复
        String entrustDel=request.getParameter("entrustDel");
        //返回多个id
        String entrustId=request.getParameter("entrustId");
        if(StringUtil.isNotBlank(entrustId) && StringUtil.isNotBlank(entrustDel)) {
            String[] strEntrustId = entrustId.split(",");
            for(int i=0;i<strEntrustId.length;i++){
                CsOtherEntrust csOtherEntrust=csOtherEntrustService.selectByPrimaryKey(strEntrustId[i]);
                if(csOtherEntrust != null) {
                    csOtherEntrustService.updateByPrimaryKey(csOtherEntrust,entrustDel);
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
