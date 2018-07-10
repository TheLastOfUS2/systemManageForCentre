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
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.base.EpPublicity;
import com.systemManage.service.EpPublicityService;

/**
 * 类     名:EpPublicityController.java
 * 作     用:对外宣传管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:40:28
 */
@Controller
@RequestMapping("/publicity")
public class EpPublicityController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private EpPublicityService epPublicityService; // 对外宣传
    
     /**
      * 方法名: toPublicityList
      * 描述: 跳转到对外宣传列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:42:00
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toPublicityList")
    public String toPublicityList(HttpServletRequest request, HttpServletResponse response){
       String publicityType= request.getParameter("publicityType");
       request.setAttribute("publicityType", publicityType);
        return "achievement/epPublicityList";
    }
        
     /**
      * 方法名: publicityList
      * 描述: 对外宣传信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:44:48
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/publicityList")
    @ResponseBody
    public Object publicityList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("publicityType", request.getParameter("publicityType"));
        criteria.put("publicityDel", request.getParameter("publicityDel"));
        //责任人姓名
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
        	criteria.put("publicityIntervieweeId", request.getParameter("baseInfoId"));
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("publicityTheme").equals(getName)){
        		 criteria.put("publicityTheme2", getValue);
        	}else if(("publicityTitle").equals(getName)) {
        		criteria.put("publicityTitle2", getValue);
        	}else if(("publicityInterviewee").equals(getName)) {
        		criteria.put("publicityInterviewee2", getValue);
        	}else if(("publicityCompany").equals(getName)) {
                criteria.put("publicityCompany2", getValue);
            }else if(("publicityTime").equals(getName)) {
        		criteria.put("publicityTime2", getValue);
        	}else if(("publicityPosition").equals(getName)) {
        		criteria.put("publicityPosition2", getValue);
        	}else if(("publicityVolume").equals(getName)) {
        		criteria.put("publicityVolume2", getValue);
        	}else if(("publicityChannel").equals(getName)) {
        		criteria.put("publicityChannel2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" publicity_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = epPublicityService.countByExample(criteria);
        List<EpPublicity> epPublicity=epPublicityService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", epPublicity);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    
     /**
      * 方法名: publicityView
      * 描述: 查看对外宣传信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:45:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/publicityView")
    @ResponseBody
    public Object publicityView(HttpServletRequest request, HttpServletResponse response){
        EpPublicity epPublicity = null;
        String publicityId = request.getParameter("publicityId");
        if(StringUtil.isNotBlank(publicityId)){
            epPublicity = epPublicityService.selectByPrimaryKey(publicityId);
        }
        return epPublicity;
    }
    
     /**
      * 方法名: saveOrUpPublicity
      * 描述: 新增/编辑对外宣传信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:46:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpPublicity")
    public void saveOrUpPublicity(EpPublicity epPublicity,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = epPublicityService.insertSelective(epPublicity,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deletePublicity
     * 描述: 批量删除对外宣传
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午13:46:40
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deletePublicity")
    public void deletePublicity(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String publicityDel=request.getParameter("publicityDel");
        //返回多个id
        String publicityId=request.getParameter("publicityId");
        if(StringUtil.isNotBlank(publicityId) && StringUtil.isNotBlank(publicityDel)) {
            String[] strPublicityId = publicityId.split(",");
            for(int i=0;i<strPublicityId.length;i++){
                EpPublicity epPublicity=epPublicityService.selectByPrimaryKey(strPublicityId[i]);
                if(epPublicity != null) {
                    epPublicityService.updateByPrimaryKey(epPublicity,publicityDel);
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
