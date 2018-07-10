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
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.EpReport;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.EpReportService;

/**
 * 类     名:EpReportController.java
 * 作     用:各类统计报表及其他资料管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:48:10
 */
@Controller
@RequestMapping("/report")
public class EpReportController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private EpReportService epReportService; // 报表
    
     /**
      * 方法名: toReportList
      * 描述: 跳转到报表列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:51:00
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toReportList")
    public String toReportList(HttpServletRequest request, HttpServletResponse response){
        String reportType= request.getParameter("reportType");
        request.setAttribute("reportType", reportType);
        return "achievement/epReportList";
    }
        
     /**
      * 方法名: reportList
      * 描述: 报表信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:51:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/reportList")
    @ResponseBody
    public Object reportList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("reportType", request.getParameter("reportType"));
        criteria.put("reportDel", request.getParameter("reportDel"));
        //责任人姓名
        String reportSubmitterId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(reportSubmitterId)){
        	criteria.put("reportSubmitterId", reportSubmitterId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("reportTitle").equals(getName)){
        		 criteria.put("reportTitle2", getValue);
        	}else if(("reportDept").equals(getName)) {
        		criteria.put("reportDept2", getValue);
        	}else if(("reportSubmitter").equals(getName)) {
        		criteria.put("reportSubmitter2", getValue);
        	}else if(("reportTime").equals(getName)) {
        		criteria.put("reportTime2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" report_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = epReportService.countByExample(criteria);
        List<EpReport> epReport=epReportService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", epReport);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
  
     /**
      * 方法名: reportView
      * 描述: 查看报表信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:52:13
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/reportView")
    @ResponseBody
    public Object reportView(HttpServletRequest request, HttpServletResponse response){
        EpReport epReport = null;
        String reportId = request.getParameter("reportId");
        if(StringUtil.isNotBlank(reportId)){
            epReport = epReportService.selectByPrimaryKey(reportId);
        }
        return epReport;
    }
    
     /**
      * 方法名: saveOrUpReport
      * 描述: 新增/编辑报表信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:53:21
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpReport")
    public void saveOrUpReport(EpReport epReport,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = epReportService.insertSelective(epReport,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteReport
     * 描述: 批量删除特色活动
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午17:10:52
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteReport")
    public void deleteReport(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String reportDel=request.getParameter("reportDel");
        //返回多个id
        String reportId=request.getParameter("reportId");
        if(StringUtil.isNotBlank(reportId) && StringUtil.isNotBlank(reportDel)) {
            String[] strReportId = reportId.split(",");
            for(int i=0;i<strReportId.length;i++){
                EpReport epReport=epReportService.selectByPrimaryKey(strReportId[i]);
                if(epReport != null) {
                    epReportService.updateByPrimaryKey(epReport,reportDel);
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
