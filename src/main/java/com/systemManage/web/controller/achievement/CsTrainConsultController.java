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
import com.systemManage.pojo.base.CsTrainConsult;
import com.systemManage.pojo.base.PcTeaching;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.CsTrainConsultService;
import com.systemManage.service.PcTeachingService;

/**
 * 类     名:CsTrainConsultController.java
 * 作     用:培训咨询管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:14:28
 */
@Controller
@RequestMapping("/consult")
public class CsTrainConsultController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private CsTrainConsultService csTrainConsultService; // 培训咨询
    
     /**
      * 方法名: toConsultList
      * 描述: 跳转到培训咨询列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:17:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toConsultList")
    public String toConsultList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/csTrainConsultList";
    }
        
     /**
      * 方法名: consultList
      * 描述: 培训咨询列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:18:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/consultList")
    @ResponseBody
    public Object consultList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("consultDel", request.getParameter("consultDel"));
        //责任人姓名
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
        	criteria.put("consultNameId", request.getParameter("baseInfoId"));
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("consultTitle").equals(getName)){
        		 criteria.put("consultTitle2", getValue);
        	}else if(("consultName").equals(getName)) {
                criteria.put("consultName2", getValue);
            }else if(("consultTarget").equals(getName)) {
                criteria.put("consultTarget2", getValue);
            }else if(("consultStartTime").equals(getName)) {
        		criteria.put("consultStartTime2", getValue);
        	}else if(("consultEndTime").equals(getName)) {
        		criteria.put("consultEndTime2", getValue);
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
            	criteria.setOrderByClause(" consult_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = csTrainConsultService.countByExample(criteria);
        List<CsTrainConsult> csTrainConsult=csTrainConsultService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", csTrainConsult);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("consultTitle").equals(name)){
    		name="consult_title";  
    	}else if(("consultName").equals(name)){
    		name="consult_name";  
    	}else if(("consultTarget").equals(name)){
    		name="consult_target";  
    	}else if(("consultStartTime").equals(name)){
    		name="consult_target";  
    	}else if(("consultEndTime").equals(name)){
    		name="consult_end_time";  
    	}else if(("consultEntrustCompany").equals(name)){
    		name="consult_entrust_company";  
    	}else if(("consultCount").equals(name)){
    		name="consult_count";  
    	}else if(("consultType").equals(name)){
    		name="consult_type";  
    	}
    	return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("consultType").equals(name)){
            if(("自办").equals(value)){
               value="0"; 
            }else if(("委托").equals(value)){
                value="1"; 
            }
        }
        return value;
    }
     /**
      * 方法名: consultView
      * 描述: 查看培训咨询信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:18:43
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/consultView")
    @ResponseBody
    public Object consultView(HttpServletRequest request, HttpServletResponse response){
        CsTrainConsult csTrainConsult = null;
        String consultId = request.getParameter("consultId");
        if(StringUtil.isNotBlank(consultId)){
            csTrainConsult = csTrainConsultService.selectByPrimaryKey(consultId);
        }
        return csTrainConsult;
    }
    
     /**
      * 方法名: saveOrUpConsult
      * 描述: 新增/编辑培训咨询信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:19:21
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpConsult")
    public void saveOrUpConsult(CsTrainConsult csTrainConsult,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = csTrainConsultService.insertSelective(csTrainConsult,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteConsult
     * 描述: 批量删除培训咨询
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午13:01:25
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteConsult")
    public void deleteConsult(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String consultDel=request.getParameter("consultDel");
        //返回多个id
        String consultId=request.getParameter("consultId");
        if(StringUtil.isNotBlank(consultId) && StringUtil.isNotBlank(consultDel)) {
            String[] strTconsultId = consultId.split(",");
            for(int i=0;i<strTconsultId.length;i++){
                CsTrainConsult csTrainConsult=csTrainConsultService.selectByPrimaryKey(strTconsultId[i]);
                if(csTrainConsult != null) {
                    csTrainConsultService.updateByPrimaryKey(csTrainConsult,consultDel);
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
