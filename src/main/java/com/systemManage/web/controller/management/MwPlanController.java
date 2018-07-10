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
import com.systemManage.pojo.base.MwPlan;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwPlanService;

/**
 * 类     名:MwPlanController.java
 * 作     用:基地规划管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:44:10
 */
@Controller
@RequestMapping("/plan")
public class MwPlanController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwPlanService mwPlanService; // 基地规划逻辑层
    
     /**
      * 方法名: toPlanList
      * 描述: 跳转到基地规划列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:46:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toPlanList")
    public String toPlanList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwPlanList";
    }
        
     /**
      * 方法名: planList
      * 描述: 基地规划信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:46:56
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/planList")
    @ResponseBody
    public Object planList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("planDel", request.getParameter("planDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("planTitle").equals(getName)){
        		 criteria.put("planTitle2", getValue);
        	}else if(("planTime").equals(getName)) {
        		criteria.put("planTime2", getValue);
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
            criteria.setOrderByClause(" plan_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwPlanService.countByExample(criteria);
        List<MwPlan> mwPlan=mwPlanService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwPlan);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("planCycle").equals(name)){
            if(("年度规划").equals(value)){
               value="0"; 
            }else if(("十三五规划").equals(value)){
                value="1"; 
            }else if(("十二五规划").equals(value)){
                value="2"; 
            }else if(("十一五规划").equals(value)){
                value="3"; 
            }else if(("十五规划").equals(value)){
                value="4"; 
            }
        }
        return value;
    }
     /**
      * 方法名: planView
      * 描述: 查看基地规划信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:48:13
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/planView")
    @ResponseBody
    public Object planView(HttpServletRequest request, HttpServletResponse response){
        MwPlan mwPlan = null;
        String planId = request.getParameter("planId");
        if(StringUtil.isNotBlank(planId)){
            mwPlan = mwPlanService.selectByPrimaryKey(planId);
        }
        return mwPlan;
    }
    
     /**
      * 方法名: saveOrUpPlan
      * 描述: 新增/编辑基地规划信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:49:11
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpPlan")
    public void saveOrUpPlan(MwPlan mwPlan,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwPlanService.insertSelective(mwPlan,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deletePlan
     * 描述: 批量删除基地规划
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午11:49:29
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deletePlan")
    public void deletePlan(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
      //判断是删除还是恢复
        String planDel=request.getParameter("planDel");
        //返回多个id
        String planId=request.getParameter("planId");
        if(StringUtil.isNotBlank(planId) && StringUtil.isNotBlank(planDel)) {
            String[] strPlanId = planId.split(",");
            for(int i=0;i<strPlanId.length;i++){
                MwPlan mwPlan=mwPlanService.selectByPrimaryKey(strPlanId[i]);
                if(mwPlan != null) {
                    mwPlanService.updateByPrimaryKey(mwPlan,planDel);
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
