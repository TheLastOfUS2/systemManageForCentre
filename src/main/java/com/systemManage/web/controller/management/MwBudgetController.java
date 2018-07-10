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
import com.systemManage.pojo.base.MwBudget;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwBudgetService;

/**
 * 类     名:MwBudgetController.java
 * 作     用:年度预算管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:23:57
 */
@Controller
@RequestMapping("/budget")
public class MwBudgetController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwBudgetService mwBudgetService; // 年度预算逻辑层
    
     /**
      * 方法名: toBudgetList
      * 描述: 跳转到年度预算列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:25:23
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toBudgetList")
    public String toBudgetList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwBudgetList";
    }
        
     /**
      * 方法名: budgetList
      * 描述: 年度预算信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:26:39
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/budgetList")
    @ResponseBody
    public Object budgetList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("budgetDel", request.getParameter("budgetDel"));
     // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("budgetYear").equals(getName)){
        		 criteria.put("budgetYear2", getValue);
        	}else if(("budgetTime").equals(getName)) {
        		criteria.put("budgetTime2", getValue);
        	}else if(("budgetTitle").equals(getName)) {
        		criteria.put("budgetTitle2", getValue);
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
            criteria.setOrderByClause(" budget_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwBudgetService.countByExample(criteria);
        List<MwBudget> mwBudget=mwBudgetService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwBudget);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("budgetCategory").equals(name)){
            if(("学术会议").equals(value)){
               value="0"; 
            }else if(("数据库建设").equals(value)){
                value="1"; 
            }else if(("学术期刊").equals(value)){
                value="2"; 
            }else if(("网站建设").equals(value)){
                value="3"; 
            }else if(("图书资料").equals(value)){
                value="4"; 
            }else if(("其他").equals(value)){
                value="5"; 
            }
        }
        if(("budgetFundSource").equals(name)){
            if(("教育部").equals(value)){
               value="0"; 
            }else if(("省部委").equals(value)){
                value="1"; 
            }else if(("学校拨款").equals(value)){
                value="2"; 
            }else if(("其他来源").equals(value)){
                value="3"; 
            }
        }
        return value;
    }
     /**
      * 方法名: budgetView
      * 描述: 查看年度预算信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:29:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/budgetView")
    @ResponseBody
    public Object budgetView(HttpServletRequest request, HttpServletResponse response){
        MwBudget mwBudget = null;
        String budgetId = request.getParameter("budgetId");
        if(StringUtil.isNotBlank(budgetId)){
            mwBudget = mwBudgetService.selectByPrimaryKey(budgetId);
        }
        return mwBudget;
    }
    
     /**
      * 方法名: saveOrUpBudget
      * 描述: 新增/编辑年度预算信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 下午13:23:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpBudget")
    public void saveOrUpBudget(MwBudget mwBudget,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwBudgetService.insertSelective(mwBudget,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteBudget
     * 描述: 批量删除特色活动
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 下午13:24:23
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteBudget")
    public void deleteBudget(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
      //判断是删除还是恢复
        String budgetDel=request.getParameter("budgetDel");
        //返回多个id
        String budgetId=request.getParameter("budgetId");
        if(StringUtil.isNotBlank(budgetId) && StringUtil.isNotBlank(budgetDel)) {
            String[] strBudgetId = budgetId.split(",");
            for(int i=0;i<strBudgetId.length;i++){
                MwBudget mwBudget=mwBudgetService.selectByPrimaryKey(strBudgetId[i]);
                if(mwBudget != null) {
                    mwBudgetService.updateByPrimaryKey(mwBudget,budgetDel);
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
