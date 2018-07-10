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
import com.systemManage.pojo.base.PcTrain;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.PcTrainService;

/**
 * 类     名:PcTrainController.java
 * 作     用:培训深造管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月15日 下午13:03:28
 */
@Controller
@RequestMapping("/train")
public class PcTrainController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private PcTrainService pcTrainService; // 培训深造
    
     /**
      * 方法名: toTrainList
      * 描述: 跳转到培训深造列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:04:44
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toTrainList")
    public String toTrainList(HttpServletRequest request, HttpServletResponse response){
        return "achievement/pcTrainList";
    }
        
     /**
      * 方法名: trainList
      * 描述: 培训深造信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:05:48
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/trainList")
    @ResponseBody
    public Object trainList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("trainDel", request.getParameter("trainDel"));
        //责任人姓名
        String trainNameId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(trainNameId)){
        	criteria.put("trainNameId", trainNameId);
        }
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("trainName").equals(getName)){
        		 criteria.put("trainName2", getValue);
        	}else if(("trainCompany").equals(getName)) {
        		criteria.put("trainCompany2", getValue);
        	}else if(("trainTime").equals(getName)) {
        		criteria.put("trainTime2", getValue);
        	}else if(("trainAddress").equals(getName)) {
        		criteria.put("trainAddress2", getValue);
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
            	criteria.setOrderByClause(" train_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = pcTrainService.countByExample(criteria);
        List<PcTrain> pcTrain=pcTrainService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", pcTrain);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("trainName").equals(name)){
    		name="train_name";  
    	}else if(("trainCompany").equals(name)){
    		name="train_company";  
    	}else if(("trainTime").equals(name)){
    		name="train_time";  
    	}else if(("trainAddress").equals(name)){
    		name="train_address";  
    	}
    	return name;
    }
     /**
      * 方法名: trainView
      * 描述: 查看培训深造信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:06:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/trainView")
    @ResponseBody
    public Object trainView(HttpServletRequest request, HttpServletResponse response){
        PcTrain pcTrain = null;
        String trainId = request.getParameter("trainId");
        if(StringUtil.isNotBlank(trainId)){
            pcTrain = pcTrainService.selectByPrimaryKey(trainId);
        }
        return pcTrain;
    }
    
     /**
      * 方法名: saveOrUpTrain
      * 描述: 新增/编辑培训深造信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月15日 下午13:07:30
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpTrain")
    public void saveOrUpTrain(PcTrain pcTrain,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = pcTrainService.insertSelective(pcTrain,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteTrain
     * 描述: 批量删除培训深造
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月15日 下午13:09:31
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteTrain")
    public void deleteTrain(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复 
        String trainDel=request.getParameter("trainDel");
        //返回多个id
        String trainId=request.getParameter("trainId");
        if(StringUtil.isNotBlank(trainId) && StringUtil.isNotBlank(trainDel)) {
            String[] strTrainId = trainId.split(",");
            for(int i=0;i<strTrainId.length;i++){
                PcTrain pcTrain=pcTrainService.selectByPrimaryKey(strTrainId[i]);
                if(pcTrain != null) {
                    pcTrainService.updateByPrimaryKey(pcTrain,trainDel);
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
