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
import com.systemManage.pojo.base.AmOpus;
import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.pojo.dto.AmPaperDto;
import com.systemManage.service.AmOpusService;
import com.systemManage.service.AmPaperService;

/**
 * 类     名:AmPaperController.java
 * 作     用:论文管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月13日 下午13:28:45
 */
@Controller
@RequestMapping("/paper")
public class AmPaperController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmPaperService amPaperService; // 论文业务层
    
     /**
      * 方法名: toPaperList
      * 描述: 跳转到著作列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 下午13:29:25
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toPaperList")
    public String toPaperList(HttpServletRequest request, HttpServletResponse response){
        //责任人id
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            request.setAttribute("baseInfoId", baseInfoId);
        }
        
        //责任人id
        String path=request.getParameter("path");
        if(StringUtil.isNotBlank(path)){
            request.setAttribute("path", path);
        }
        
        // 全文检索
        //责任人id
        String qBaseInfoName=request.getParameter("qBaseInfoName");
        if(StringUtil.isNotBlank(qBaseInfoName)){
            request.setAttribute("qBaseInfoName", qBaseInfoName);
        }
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
        return "achievement/amPaperList";
    }
        
     /**
      * 方法名: paperList
      * 描述: 著作信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 下午13:30:15
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/paperList")
    @ResponseBody
    public Object paperList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("paperDel", request.getParameter("paperDel"));
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
        //论文类型
        String paperType=request.getParameter("paperType");
        if(StringUtil.isNotBlank(getValue)){
        	if(("paperTitle").equals(getName)){
        		 criteria.put("paperTitle2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            }else if(("paperPublishJournal").equals(getName)) {
        		criteria.put("paperPublishJournal2", getValue);
        	}else if(("paperVolumeNumber").equals(getName)) {
        		criteria.put("paperVolumeNumber2", getValue);
        	}else if(("paperPublishTime").equals(getName)) {
        		criteria.put("paperPublishTime2", getValue);
        	}else if(("paperSupportTopic").equals(getName)) {
        		criteria.put("paperSupportTopic2", getValue);
        	}else if(("paperName").equals(getName)) {
        		criteria.put("paperName2", getValue);
        	}else if(("paperAddress").equals(getName)) {
        		criteria.put("paperAddress2", getValue);
        	}else if(("paperPlate").equals(getName)) {
        		criteria.put("paperPlate2", getValue);
        	}else{
        		  getValue = getValue(getName, getValue);
                  criteria.put(getName, getValue);
        	}
        }
        if(StringUtil.isNotBlank(paperType)){
        	 criteria.put("paperType", paperType);
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
            	 criteria.setOrderByClause(" paper_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amPaperService.countByExample(criteria);
        List<AmPaperDto> amPaper=amPaperService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amPaper);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("paperTitle").equals(name)){
    		name="paper_title";  
    	}else if(("paperName").equals(name)){
    		name="paper_name";  
    	}else if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("paperPeriodicalType").equals(name)){
    		name="paper_periodical_type";  
    	}else if(("paperPublishJournal").equals(name)){
    		name="paper_publish_journal";  
    	}else if(("paperVolumeNumber").equals(name)){
    		name="paper_volume_number";  
    	}else if(("paperSupportTopic").equals(name)){
    		name="paper_support_topic";  
    	}else if(("paperPublishTime").equals(name)){
    		name="paper_publish_time";  
    	}else if(("paperAddress").equals(name)){
    		name="paper_address";  
    	}else if(("paperPlate").equals(name)){
    		name="paper_plate";  
    	}else if(("paperIfTheory").equals(name)){
    		name="paper_if_theory";  
    	}
    	return name;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("paperPeriodicalType").equals(name)){
            if(("期刊论文").equals(value)){
               value="0"; 
            }else if(("会议论文").equals(value)){
                value="1"; 
            }else if(("报纸文章").equals(value)){
                value="2";
            }
        }else if(("paperIfTheory").equals(name)) {
        	 if(("否").equals(value)){
                 value="0"; 
              }else if(("是").equals(value)){
                  value="1"; 
              }
    	}
        return value;
    }
     /**
      * 方法名: paperView
      * 描述: 查看著作信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 下午13:31:40
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/paperView")
    @ResponseBody
    public Object paperView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        AmPaperDto amPaperDto = null;
        String path = request.getParameter("path");
        if(StringUtil.isNotBlank(path) && ("addPaper").equals(path)){
           
        }else{
            String paperId = request.getParameter("paperId");
            if(StringUtil.isNotBlank(paperId)){
                amPaperDto = amPaperService.selectByPrimaryKeyExt(paperId);
            }
        }
        return amPaperDto;
    }
    
     /**
      * 方法名: saveOrUpPaper
      * 描述: 新增/编辑论文信息
      * 参数: @param amPaper
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 下午11:40:24
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpPaper")
    public void saveOrUpPaper(AmPaperDto amPaperDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amPaperService.insertSelective(amPaperDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deletePaper
     * 描述: 批量删除论文
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月13日 下午13:44:45
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deletePaper")
    public void deletePaper(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String paperDel=request.getParameter("paperDel");
        //返回多个id
        String paperId=request.getParameter("paperId");
        if(StringUtil.isNotBlank(paperId) && StringUtil.isNotBlank(paperDel)) {
            String[] strPaperId = paperId.split(",");
            for(int i=0;i<strPaperId.length;i++){
                AmPaper amPaper=amPaperService.selectByPrimaryKey(strPaperId[i]);
                if(amPaper != null) {
                    amPaperService.updateByPrimaryKey(amPaper,paperDel);
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
