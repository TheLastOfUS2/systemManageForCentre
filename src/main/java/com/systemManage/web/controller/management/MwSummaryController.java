package com.systemManage.web.controller.management;

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
import com.systemManage.pojo.base.MwSummary;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwSummaryService;

/**
 * 类     名:MwSummaryController.java
 * 作     用:基地总结管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:38:12
 */
@Controller
@RequestMapping("/summary")
public class MwSummaryController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwSummaryService mwSummaryService; // 基地总结逻辑层
    
     /**
      * 方法名: toSummaryList
      * 描述: 跳转到基地总结列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:40:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toSummaryList")
    public String toSummaryList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwSummaryList";
    }
        
     /**
      * 方法名: summaryList
      * 描述: 基地总结信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:40:38
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/summaryList")
    @ResponseBody
    public Object summaryList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("summaryDel", request.getParameter("summaryDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("summaryTitle").equals(getName)){
        		 criteria.put("summaryTitle2", getValue);
        	}else if(("summaryTime").equals(getName)) {
        		criteria.put("summaryTime2", getValue);
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
            criteria.setOrderByClause(" summary_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwSummaryService.countByExample(criteria);
        List<MwSummary> mwSummary=mwSummaryService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwSummary);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("summaryCycle").equals(name)){
            if(("年度总结").equals(value)){
               value="0"; 
            }else if(("十二五总结").equals(value)){
                value="1"; 
            }else if(("十一五总结").equals(value)){
                value="2"; 
            }else if(("十五总结").equals(value)){
                value="3"; 
            }else{
                value="99"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: summaryView
      * 描述: 查看基地总结信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:42:05
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/summaryView")
    @ResponseBody
    public Object summaryView(HttpServletRequest request, HttpServletResponse response){
        MwSummary mwSummary = null;
        String summaryId = request.getParameter("summaryId");
        if(StringUtil.isNotBlank(summaryId)){
            mwSummary = mwSummaryService.selectByPrimaryKey(summaryId);
        }
        return mwSummary;
    }
    
     /**
      * 方法名: saveOrUpSummary
      * 描述: 新增/编辑基地总结信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:43:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpSummary")
    public void saveOrUpSummary(MwSummary mwSummary,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwSummaryService.insertSelective(mwSummary,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteSummary
     * 描述: 批量删除基地总结
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午11:43:38
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteSummary")
    public void deleteSummary(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String summaryDel=request.getParameter("summaryDel");
        //返回多个idsummaryDel
        String summaryId=request.getParameter("summaryId");
        if(StringUtil.isNotBlank(summaryId) && StringUtil.isNotBlank(summaryDel)) {
            String[] strSummaryId = summaryId.split(",");
            for(int i=0;i<strSummaryId.length;i++){
                MwSummary mwSummary=mwSummaryService.selectByPrimaryKey(strSummaryId[i]);
                if(mwSummary != null) {
                    mwSummaryService.updateByPrimaryKey(mwSummary,summaryDel);
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
