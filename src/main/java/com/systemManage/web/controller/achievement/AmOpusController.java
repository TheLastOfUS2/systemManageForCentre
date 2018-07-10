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
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.service.AmOpusService;

/**
 * 类     名:AmOpusController.java
 * 作     用:著作管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月13日 下午11:19:10
 */
@Controller
@RequestMapping("/opus")
public class AmOpusController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmOpusService amOpusService; // 著作业务层
    
     /**
      * 方法名: toOpusList
      * 描述: 跳转到著作列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 上午11:20:9
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toOpusList")
    public String toOpusList(HttpServletRequest request, HttpServletResponse response){
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
        return "achievement/amOpusList";
    }
        
     /**
      * 方法名: opusList
      * 描述: 著作信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 上午11:21:11
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/opusList")
    @ResponseBody
    public Object opusList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("opusDel", request.getParameter("opusDel"));
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
        	if(("opusName").equals(getName)){
        		 criteria.put("opusName2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            }else if(("opusPublishTime").equals(getName)) {
        		criteria.put("opusPublishTime2", getValue);
        	}else if(("opusPublishCompany").equals(getName)) {
        		criteria.put("opusPublishCompany2", getValue);
        	}else if(("opusSupportTopic").equals(getName)) {
        		criteria.put("opusSupportTopic2", getValue);
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
            	criteria.setOrderByClause("CONVERT("+sort+"  USING gbk) "+order);
            }else{
            	 criteria.setOrderByClause(" opus_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amOpusService.countByExample(criteria);
        List<AmOpusDto> amOpus=amOpusService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amOpus);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("opusName").equals(name)){
    		name="opus_name";  
    	}else if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("opusPublishTime").equals(name)){
    		name="opus_publish_time";  
    	}else if(("opusPublishCompany").equals(name)){
    		name="opus_publish_company";  
    	}else if(("opusSupportTopic").equals(name)){
    		name="opus_support_topic";  
    	}else if(("opusLanguages").equals(name)){
    		name="opus_languages";  
    	}else if(("opusType").equals(name)){
    		name="opus_type";  
    	}
    	return name;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("opusType").equals(name)){
            if(("专著").equals(value)){
               value="0"; 
            }else if(("编著").equals(value)){
                value="1"; 
            }else if(("译著").equals(value)){
                value="2"; 
            }else if(("教材").equals(value)){
                value="3"; 
            }else if(("工具书").equals(value)){
                value="4"; 
            }else if(("普及行出版物").equals(value)){
                value="5"; 
            }
        }
        if(("opusLanguages").equals(name)){
            if(("中文").equals(value)){
               value="0"; 
            }else if(("英文").equals(value)){
                value="1"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: opusView
      * 描述: 查看著作信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 上午11:22:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/opusView")
    @ResponseBody
    public Object opusView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        AmOpusDto amOpusDto = null;
        String path = request.getParameter("path");
        if(StringUtil.isNotBlank(path) && ("addOpus").equals(path)){
           
        }else{
            String opusId = request.getParameter("opusId");
            if(StringUtil.isNotBlank(opusId)){
                amOpusDto = amOpusService.selectByPrimaryKeyExt(opusId);
            }
        }
        return amOpusDto;
    }
    
     /**
      * 方法名: saveOrUpOpus
      * 描述: 新增/编辑著作信息
      * 参数: @param amOpus
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月13日 上午11:33:24
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpOpus")
    public void saveOrUpOpus(AmOpusDto amOpusDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amOpusService.insertSelective(amOpusDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteOpus
     * 描述: 批量删除著作
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月13日 上午11:36:35
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteOpus")
    public void deleteOpus(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String opusDel=request.getParameter("opusDel");
        //返回多个id
        String opusId=request.getParameter("opusId");
        if(StringUtil.isNotBlank(opusId) && StringUtil.isNotBlank(opusDel)) {
            String[] strOpusId = opusId.split(",");
            for(int i=0;i<strOpusId.length;i++){
                AmOpus amOpus=amOpusService.selectByPrimaryKey(strOpusId[i]);
                if(amOpus != null) {
                    amOpusService.updateByPrimaryKey(amOpus,opusDel);
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
