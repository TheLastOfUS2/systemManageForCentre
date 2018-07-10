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
import com.systemManage.pojo.base.AmPaper;
import com.systemManage.pojo.base.AmPrize;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.AmPaperDto;
import com.systemManage.pojo.dto.AmPrizeDto;
import com.systemManage.service.AmPaperService;
import com.systemManage.service.AmPrizeService;

/**
 * 类     名:AmPrizeController.java
 * 作     用:获奖管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月14日 下午14:04:50
 */
@Controller
@RequestMapping("/prize")
public class AmPrizeController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private AmPrizeService amPrizeService; // 获奖业务层
    
     /**
      * 方法名: toPrizeList
      * 描述: 跳转到获奖列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:05:02
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toPrizeList")
    public String toPrizeList(HttpServletRequest request, HttpServletResponse response){
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
        return "achievement/amPrizeList";
    }
        
     /**
      * 方法名: prizeList
      * 描述: 获奖信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:07:09
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/prizeList")
    @ResponseBody
    public Object prizeList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("prizeDel", request.getParameter("prizeDel"));
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
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("prizeTitle").equals(getName)){
        		 criteria.put("prizeTitle2", getValue);
        	}else if(("baseInfoName").equals(getName)) {
                criteria.put("baseInfoName2", getValue);
            }else if(("prizeAchievement").equals(getName)) {
        		criteria.put("prizeAchievement2", getValue);
        	}else if(("prizeAwardCompany").equals(getName)) {
        		criteria.put("prizeAwardCompany2", getValue);
        	}else if(("prizeWinningTime").equals(getName)) {
        		criteria.put("prizeWinningTime2", getValue);
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
            	 criteria.setOrderByClause(" prize_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = amPrizeService.countByExample(criteria);
        List<AmPrizeDto> amPrize=amPrizeService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", amPrize);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("prizeTitle").equals(name)){
    		name="prize_title";  
    	}else if(("prizeAchievement").equals(name)){
    		name="prize_achievement";  
    	}else if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("prizeWay").equals(name)){
    		name="prize_way";  
    	}else if(("prizeWinningTime").equals(name)){
    		name="prize_winning_time";  
    	}else if(("prizeGrade").equals(name)){
    		name="prize_grade";  
    	}else if(("prizeLevel").equals(name)){
    		name="prize_level";  
    	}else if(("prizeAwardCompany").equals(name)){
    		name="prize_award_company";  
    	}
    	return name;
    }
    
   //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("prizeWay").equals(name)){
            if(("著作").equals(value)){
               value="0"; 
            }else if(("论文").equals(value)){
                value="1";
            }else if(("咨询报告").equals(value)){
                value="2";
            }else if(("研究报告").equals(value)){
                value="3";
            }else if(("音像软件").equals(value)){
                value="4";
            }else if(("工具书或参考书").equals(value)){
                value="5";
            }else if(("其他").equals(value)){
                value="6";
            }
        }else if(("prizeGrade").equals(name)){
            if(("一").equals(value)){
                value="0"; 
            }else if(("二").equals(value)){
                value="1"; 
            }else if(("三").equals(value)){
                value="2"; 
            }else if(("优秀").equals(value)){
                value="3"; 
            }else if(("其他").equals(value)){
                value="4"; 
            }
        }else if(("prizeLevel").equals(name)){
            if(("国家").equals(value)){
                value="0"; 
            }else if(("部级").equals(value)){
                value="1"; 
            }else if(("省级").equals(value)){
                value="2"; 
            }else if(("省级以下").equals(value)){
                value="3"; 
            }
        }
        return value;
    }
    
     /**
      * 方法名: prizeView
      * 描述: 查看获奖信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:26:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/prizeView")
    @ResponseBody
    public Object prizeView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        AmPrizeDto amPrizeDto = null;
        String path = request.getParameter("path");
        if(StringUtil.isNotBlank(path) && ("addPrize").equals(path)){
           
        }else{
            String prizeId = request.getParameter("prizeId");
            if(StringUtil.isNotBlank(prizeId)){
                amPrizeDto = amPrizeService.selectByPrimaryKeyExt(prizeId);
            }
        }
        return amPrizeDto;
    }
    
     /**
      * 方法名: saveOrUpPrize
      * 描述: 新增/编辑获奖信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月14日 下午14:27:10
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpPrize")
    public void saveOrUpPrize(AmPrizeDto amPrizeDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = amPrizeService.insertSelective(amPrizeDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deletePrize
     * 描述: 批量删除获奖
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月14日 下午14:32:16
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deletePrize")
    public void deletePrize(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String prizeDel=request.getParameter("prizeDel");
        //返回多个id
        String prizeId=request.getParameter("prizeId");
        if(StringUtil.isNotBlank(prizeId) && StringUtil.isNotBlank(prizeDel)) {
            String[] strPrizeId = prizeId.split(",");
            for(int i=0;i<strPrizeId.length;i++){
                AmPrize amPrize=amPrizeService.selectByPrimaryKey(strPrizeId[i]);
                if(amPrize != null) {
                    amPrizeService.updateByPrimaryKey(amPrize,prizeDel);
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
