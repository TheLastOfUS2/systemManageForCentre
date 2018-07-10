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
import com.systemManage.pojo.base.MwFund;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwFundService;

/**
 * 类     名:MwFundController.java
 * 作     用:资助经费管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午11:30:58
 */
@Controller
@RequestMapping("/fund")
public class MwFundController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwFundService mwFundService; // 资助经费逻辑层
    
     /**
      * 方法名: toFundList
      * 描述: 跳转到资助经费列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:32:16
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toFundList")
    public String toFundList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwFundList";
    }
        
     /**
      * 方法名: fundList
      * 描述: 资助经费信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:33:48
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/fundList")
    @ResponseBody
    public Object fundList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("fundDel", request.getParameter("fundDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("fundTitle").equals(getName)){
        		 criteria.put("fundTitle2", getValue);
        	}else if(("fundTime").equals(getName)) {
        		criteria.put("fundTime2", getValue);
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
            criteria.setOrderByClause(" fund_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwFundService.countByExample(criteria);
        List<MwFund> mwFund=mwFundService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwFund);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    //根据值获取数据库对应字段的值
    public String getValue(String name ,String value){
        if(("fundType").equals(name)){
            if(("设备").equals(value)){
               value="0"; 
            }else if(("会议").equals(value)){
                value="1"; 
            }else if(("图书").equals(value)){
                value="2"; 
            }else if(("项目").equals(value)){
                value="3"; 
            }else if(("办公").equals(value)){
                value="4"; 
            }else if(("其他").equals(value)){
                value="5"; 
            }
        }
        if(("fundSource").equals(name)){
            if(("教育部").equals(value)){
               value="0"; 
            }else if(("其他部委").equals(value)){
                value="1"; 
            }else if(("省市政府").equals(value)){
                value="2"; 
            }else if(("学校").equals(value)){
                value="3"; 
            }else if(("企事业单位").equals(value)){
                value="4"; 
            }else if(("社会捐助").equals(value)){
                value="5"; 
            }
        }
        return value;
    }
     /**
      * 方法名: fundView
      * 描述: 查看资助经费信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:35:22
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/fundView")
    @ResponseBody
    public Object fundView(HttpServletRequest request, HttpServletResponse response){
        MwFund mwFund = null;
        String fundId = request.getParameter("fundId");
        if(StringUtil.isNotBlank(fundId)){
            mwFund = mwFundService.selectByPrimaryKey(fundId);
        }
        return mwFund;
    }
    
     /**
      * 方法名: saveOrUpFund
      * 描述: 新增/编辑资助经费信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午11:36:21
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpFund")
    public void saveOrUpFund(MwFund mwFund,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwFundService.insertSelective(mwFund,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteFund
     * 描述: 批量删除资助经费
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午11:36:50
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteFund")
    public void deleteFund(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
      //判断是删除还是恢复
        String fundDel=request.getParameter("fundDel");
        //返回多个id
        String fundId=request.getParameter("fundId");
        if(StringUtil.isNotBlank(fundId) && StringUtil.isNotBlank(fundDel)) {
            String[] strFundId = fundId.split(",");
            for(int i=0;i<strFundId.length;i++){
                MwFund mwFund=mwFundService.selectByPrimaryKey(strFundId[i]);
                if(mwFund != null) {
                    mwFundService.updateByPrimaryKey(mwFund,fundDel);
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
