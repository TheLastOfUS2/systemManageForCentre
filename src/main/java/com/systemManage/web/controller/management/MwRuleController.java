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
import com.systemManage.pojo.base.MwRule;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwRuleService;

/**
 * 类     名:MwRuleController.java
 * 作     用:规章制度管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:55:50
 */
@Controller
@RequestMapping("/rule")
public class MwRuleController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwRuleService mwRuleService; // 规章制度逻辑层
    
     /**
      * 方法名: toRuleList
      * 描述: 跳转到规章制度列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:57:58
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toRuleList")
    public String toRuleList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwRuleList";
    }
        
     /**
      * 方法名: ruleList
      * 描述: 规章制度信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:58:16
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/ruleList")
    @ResponseBody
    public Object ruleList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("ruleDel", request.getParameter("ruleDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("ruleTitle").equals(getName)){
        		 criteria.put("ruleTitle2", getValue);
        	}else if(("ruleTime").equals(getName)) {
        		criteria.put("ruleTime2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" rule_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwRuleService.countByExample(criteria);
        List<MwRule> mwRule=mwRuleService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwRule);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
     /**
      * 方法名: ruleView
      * 描述: 查看规章制度信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:59:47
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/ruleView")
    @ResponseBody
    public Object ruleView(HttpServletRequest request, HttpServletResponse response){
        MwRule mwRule = null;
        String ruleId = request.getParameter("ruleId");
        if(StringUtil.isNotBlank(ruleId)){
            mwRule = mwRuleService.selectByPrimaryKey(ruleId);
        }
        return mwRule;
    }
    
     /**
      * 方法名: saveOrUpRule
      * 描述: 新增/编辑规章制度信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午12:00:35
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpRule")
    public void saveOrUpRule(MwRule mwRule,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwRuleService.insertSelective(mwRule,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteRule
     * 描述: 批量删除规章制度
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午12:00:55
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteRule")
    public void deleteRule(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String ruleDel=request.getParameter("ruleDel");
        //返回多个id
        String ruleId=request.getParameter("ruleId");
        if(StringUtil.isNotBlank(ruleId) && StringUtil.isNotBlank("ruleDel")) {
            String[] strRuleId = ruleId.split(",");
            for(int i=0;i<strRuleId.length;i++){
                MwRule mwRule=mwRuleService.selectByPrimaryKey(strRuleId[i]);
                if(mwRule != null) {
                    mwRuleService.updateByPrimaryKey(mwRule,ruleDel);
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
