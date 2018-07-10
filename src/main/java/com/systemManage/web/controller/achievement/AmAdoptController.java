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
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmAdoptService;

/**
 * 类     名:AmPrizeController.java
 * 作     用:获奖管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月14日 下午14:04:50
 */
@Controller
@RequestMapping("/adopt")
public class AmAdoptController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmAdoptService amAdoptService; // 采纳业务层
    
     /**
      * 方法名: toAdoptList
      * 描述: 跳转到采纳列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:34:44
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toAdoptList")
    public String toAdoptList(HttpServletRequest request, HttpServletResponse response){
    	//责任人id
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            request.setAttribute("baseInfoId", baseInfoId);
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
        return "achievement/amAdoptList";
    }
        
     /**
      * 方法名: AdoptList
      * 描述: 采纳信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:35:23
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/adoptList")
    @ResponseBody
    public Object adoptList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("adoptDel", request.getParameter("adoptDel"));
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
        if(StringUtil.isNotBlank(getValue)){
        	if(("adoptName").equals(getName)){
        		 criteria.put("adoptName2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            }else if(("adoptSubmitTime").equals(getName)) {
        		criteria.put("adoptSubmitTime2", getValue);
        	}else if(("adoptTime").equals(getName)) {
        		criteria.put("adoptTime2", getValue);
        	}else if(("adoptCompany").equals(getName)) {
        		criteria.put("adoptCompany2", getValue);
        	}else if(("adoptPublication").equals(getName)) {
        		criteria.put("adoptPublication2", getValue);
        	}else if(("adoptSupportTopic").equals(getName)) {
        		criteria.put("adoptSupportTopic2", getValue);
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
            	 criteria.setOrderByClause(" a.adopt_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amAdoptService.countByExample(criteria);
        List<AmAdoptDto> amAdopt=amAdoptService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amAdopt);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("adoptName").equals(name)){
    		name="adopt_name";  
    	}else if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("adoptWay").equals(name)){
    		name="adopt_way";  
    	}else if(("adoptSubmitTime").equals(name)){
    		name="adopt_submit_time";  
    	}else if(("adoptPublication").equals(name)){
    		name="adopt_publication";  
    	}else if(("adoptCompany").equals(name)){
    		name="adopt_company";  
    	}else if(("adoptTime").equals(name)){
    		name="adopt_time";  
    	}else if(("adoptSupportTopic").equals(name)){
    		name="adopt_support_topic";  
    	}
    	return name;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("adoptWay").equals(name)){
            if(("采纳").equals(value)){
               value="0"; 
            }else if(("批示").equals(value)){
                value="1"; 
            }else if(("刊发").equals(value)){
                value="2"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: adoptView
      * 描述: 查看采纳信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:36:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/adoptView")
    @ResponseBody
    public Object adoptView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        AmAdoptDto amAdoptDto = null;
        String path = request.getParameter("path");
        if(StringUtil.isNotBlank(path) && ("addAdopt").equals(path)){
           
        }else{
            String adoptId = request.getParameter("adoptId");
            if(StringUtil.isNotBlank(adoptId)){
                amAdoptDto = amAdoptService.selectByPrimaryKeyExt(adoptId);
            }
        }
        return amAdoptDto;
    }
    
     /**
      * 方法名: saveOrUpAdopt
      * 描述: 新增/编辑采纳信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:38:14
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpAdopt")
    public void saveOrUpAdopt(AmAdoptDto amAdoptDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amAdoptService.insertSelective(amAdoptDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteAdopt
     * 描述: 批量删除采纳
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午14:38:40
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteAdopt")
    public void deleteAdopt(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String adoptDel=request.getParameter("adoptDel");
        //返回多个id
        String adoptId=request.getParameter("adoptId");
        if(StringUtil.isNotBlank(adoptId) && StringUtil.isNotBlank(adoptDel)) {
            String[] strAdoptId = adoptId.split(",");
            for(int i=0;i<strAdoptId.length;i++){
                AmAdopt amAdopt=amAdoptService.selectByPrimaryKey(strAdoptId[i]);
                if(amAdopt != null) {
                    amAdoptService.updateByPrimaryKey(amAdopt,adoptDel);
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
