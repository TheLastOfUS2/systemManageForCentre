package com.systemManage.web.controller.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.systemManage.pojo.base.BiBaseInfo;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.pojo.dto.BiBaseInfoDto;
import com.systemManage.pojo.dto.BiMailListDto;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.BiBaseInfoService;
import com.systemManage.service.BiMailListService;

/**
 * 类     名:BaseInfoController.java
 * 作     用:基本信息管理
 * 作     者:张金秋
 * 日     期:2017 2017年5月24日 下午14:54:8
 */
@Controller
@RequestMapping("/baseInfo")
public class BiBaseInfoController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private BiBaseInfoService baseInfoService;//基本信息逻辑层
    @Autowired
    private BiMailListService biMailListService;//通讯录逻辑层
    
    
     /**
      * 方法名: toBaseInfoList
      * 描述: 跳转到基本信息列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:22:39
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toBaseInfoList")
    public String toBaseInfoList(HttpServletRequest request, HttpServletResponse response){
        String baseInfoType=request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
            request.setAttribute("baseInfoType", baseInfoType);
        }
        return "baseinfo/baseInfoList";
    }
    
    /**
     * 方法名: toBaseInfoView
     * 描述: 跳转到基本信息明细
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:22:39
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: String
     */
    @RequestMapping("/toBaseInfoView")
    public String toBaseInfoView(HttpServletRequest request, HttpServletResponse response){
        String baseInfoType = request.getParameter("baseInfoType");
        if(StringUtil.isNotBlank(baseInfoType)){
            request.setAttribute("baseInfoType", baseInfoType);
        }
        return "baseinfo/baseInfoView";
    }
        
     /**
      * 方法名: baseInfoList
      * 描述: 基本信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:23:46
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/baseInfoList")
    @ResponseBody
    public Object baseInfoList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("baseInfoType", request.getParameter("baseInfoType"));
        criteria.put("baseInfoDel", request.getParameter("baseInfoDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("baseInfoName").equals(getName)){
        		 criteria.put("baseInfoName2", getValue);
        	}else if(("baseInfoBirthDate").equals(getName)) {
        		criteria.put("baseInfoBirthDate2", getValue);
        	}else if(("baseInfoSpecialty").equals(getName)) {
        		criteria.put("baseInfoSpecialty2", getValue);
        	}else if(("baseInfoPost").equals(getName)) {
        		criteria.put("baseInfoPost2", getValue);
        	}else if(("baseInfoStartWorkTime").equals(getName)) {
        		criteria.put("baseInfoStartWorkTime2", getValue);
        	}else if(("baseInfoStartTime").equals(getName)) {
                criteria.put("baseInfoStartTime2", getValue);
            } else if(("baseInfoEndTime").equals(getName)) {
                criteria.put("baseInfoEndTime2", getValue);
            } else if(("baseInfoMentor").equals(getName)) {
                criteria.put("baseInfoMentor2", getValue);   
            }else if(("baseInfoExternalAppellation").equals(getName)) {
                criteria.put("baseInfoExternalAppellation2", getValue);   
            }else if(("abroadEndTime").equals(getName)) {
            	// 出国日期
            	// 判断传来的日期的长度
            	if(getValue!=null && getValue.length()!=0){
            		 criteria.put("abroadEndTime2", getValue);
                     criteria.put("abroadEndTimeLen",getValue.length());
            	}
            } else{
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
            	criteria.setOrderByClause(" base_create_time desc");
            }
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = baseInfoService.countByExample(criteria);
        List<BiBaseInfoDto> biBaseInfoList=baseInfoService.selectByExample(criteria);
        
        // 根据角色判断，如果是领导或者行政人员，判断其是否已添加过基本信息，如果已添加过基本信息，页面新增按钮不让点击
        HttpSession session = request.getSession();
        CommonAccountDto cad=(CommonAccountDto) session.getAttribute("accountInfo");
        if(cad!=null && (("1").equals(cad.getRoleName()) || ("2").equals(cad.getRoleName()))){
        	 BiBaseInfo BiBaseInfo=baseInfoService.selectByAccountId(cad.getAccountId());
        	 if(BiBaseInfo!=null){
        		 jsonMap.put("accountId", BiBaseInfo.getAccountId());//存在
        	 }else{
        		 jsonMap.put("accountId", "0");//不存在
        	 }
        }else{
        	jsonMap.put("accountId", "0");//不存在
        }
        // 返回的数据
        jsonMap.put("rows", biBaseInfoList);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    // 页面列表页字段排序
    public String getName(String name){
    	if(("baseInfoName").equals(name)){
    		name="base_info_name";  
    	}else if(("accountName").equals(name)){
    		name="account_name";  
    	}else if(("baseInfoSex").equals(name)){
    		name="base_info_sex";  
    	}else if(("baseInfoBirthDate").equals(name)){
    		name="base_info_birth_date";  
    	}else if(("baseInfoEducation").equals(name)){
    		name="base_info_education";  
    	}else if(("baseInfoPositionalTitles").equals(name)){
    		name="base_info_positional_titles";  
    	}else if(("baseInfoLevel").equals(name)){
    		name="base_info_level";  
    	}else if(("baseInfoIfDoctorTutor").equals(name)){
    		name="base_info_if_doctor_tutor";  
    	}else if(("baseInfoIfMasterTutor").equals(name)){
    		name="base_info_if_master_tutor";  
    	}else if(("baseInfoStartWorkTime").equals(name)){
    		name="base_info_start_work_time";  
    	}else if(("baseInfoMentor").equals(name)){
    		name="base_info_mentor";  
    	}else if(("baseInfoStartTime").equals(name)){
    		name="base_info_start_time";  
    	}else if(("baseInfoEndTime").equals(name)){
    		name="base_info_end_time";  
    	}else if(("baseInfoExternalAppellation").equals(name)){
    		name="base_info_external_appellation";  
    	}else if(("baseInfoEngageStartTime").equals(name)){
    		name="base_info_engage_start_time";  
    	}else if(("baseInfoEngageEndTime").equals(name)){
    		name="base_info_engage_end_time";  
    	}
    	return name;
    }
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("baseInfoSex").equals(name)){
            if(("女").equals(value)){
               value="0"; 
            }else{
                value="1";
            }
        }else if(("baseInfoEducation").equals(name)){
            if(("博士").equals(value)){
                value="0"; 
            }else if(("硕士").equals(value)){
                value="1"; 
            }else{
                value="2";
            }
        }else if(("baseInfoDegree").equals(name)){
            if(("博士").equals(value)){
                value="0"; 
            }else if(("硕士").equals(value)){
                value="1"; 
            }else{
                value="2";
            }
        }else if(("baseInfoPositionalTitles").equals(name)){
            if(("研究员").equals(value)){
                value="0"; 
            }else if(("副研究员").equals(value)){
                value="1"; 
            }else if(("助理研究员").equals(value)){
                value="2"; 
            }else if(("教授").equals(value)){
                value="3"; 
            }else if(("副教授").equals(value)){
                value="4"; 
            }else if(("讲师").equals(value)){
                value="5"; 
            }else{
                value="6";
            }
        }else if(("baseInfoLevel").equals(name)){
            if(("高级").equals(value)){
                value="0"; 
            }else if(("副高").equals(value)){
                value="1"; 
            }else if(("中级").equals(value)){
                value="2"; 
            }else{
                value="3";
            }
        }
        return value;
    }
    
    
     /**
      * 方法名: baseInfoView
      * 描述: 查看基本信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:28
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/baseInfoView")
    @ResponseBody
    public Object baseInfoView(HttpServletRequest request, HttpServletResponse response){
        BiBaseInfoDto biBaseInfoDto = null;
        String baseInfoId = request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId)){
            biBaseInfoDto = baseInfoService.selectByPrimaryKey(baseInfoId);
        }
        return biBaseInfoDto;
    }
    
     /**
      * 方法名: saveOrUpBaseInfo
      * 描述: 新增/编辑基本信息
      * 参数: @param biBaseInfo
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月6日 上午10:24:55
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpBaseInfo")
    public void saveOrUpBaseInfo(BiBaseInfoDto biBaseInfoDto,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = baseInfoService.insertSelective(biBaseInfoDto,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteBaseInfo
     * 描述: 批量删除基本信息
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:25:06
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteBaseInfo")
    public void deleteBaseInfo(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        // 已绑定通讯录个数
        int count=0;
        //判断是删除还是恢复
        String baseInfoDel=request.getParameter("baseInfoDel");
        //返回多个id
        String baseInfoId=request.getParameter("baseInfoId");
        if(StringUtil.isNotBlank(baseInfoId) && StringUtil.isNotBlank(baseInfoDel)) {
            String[] strBaseInfoId = baseInfoId.split(",");
            for(int i=0;i<strBaseInfoId.length;i++){
                BiBaseInfo biBaseInfo=baseInfoService.selectByPrimaryKey(strBaseInfoId[i]);
                if(biBaseInfo != null) {
                	//根据基本信息id,查询是否已绑定了通讯录,如果已绑定,则不允许删除（需先删除通讯录,再删除基本信息）
                    BiMailListDto biMailListDto =biMailListService.selectByBaseInfoIdExt(biBaseInfo.getBaseInfoId());
                	if(biMailListDto!=null && biMailListDto.getBaseInfoId()!=null){
                		count++;
                	}else{
                		  baseInfoService.updateByPrimaryKey(biBaseInfo,baseInfoDel);
                	}
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
        json.put("count", count);
        JsonUtils.outJsonString(json.toString(), response);
    }
    
    
    /**
     * 方法名: baseInfoNames
     * 描述: 根据人员类型查询人员名称信息
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月27日 下午11:21:59
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: Object
     */
    @RequestMapping("/baseInfoNames")
    @ResponseBody
    public Object baseInfoNames(HttpServletRequest request, HttpServletResponse response){
        criteria.clear();
        criteria.put("baseInfoDel",0);
        criteria.put("baseInfoType", request.getParameter("baseInfoType"));
        List<BiBaseInfo> biBaseInfo = baseInfoService.selectBaseInfoName(criteria);
        return biBaseInfo;
    }
}
