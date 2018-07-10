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
import com.systemManage.pojo.base.MwMemorabilia;
import com.systemManage.pojo.dto.AmAdoptDto;
import com.systemManage.service.AmActivityService;
import com.systemManage.service.AmAdoptService;
import com.systemManage.service.AmConferenceService;
import com.systemManage.service.AmLectureService;
import com.systemManage.service.MwMemorabiliaService;

/**
 * 类     名:MwMemorabiliaController.java
 * 作     用:基地大事记管理
 * 作     者:张金秋
 * 日     期:2017 2017年6月16日 上午12:01:46
 */
@Controller
@RequestMapping("/memorabilia")
public class MwMemorabiliaController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private MwMemorabiliaService mwMemorabiliaService; // 基地大事记逻辑层
    
     /**
      * 方法名: toMemorabiliaList
      * 描述: 跳转到基地大事记列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午12:04:20
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toMemorabiliaList")
    public String toMemorabiliaList(HttpServletRequest request, HttpServletResponse response){
        return "management/mwMemorabiliaList";
    }
        
     /**
      * 方法名: memorabiliaList
      * 描述: 基地大事记信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午12:04:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/memorabiliaList")
    @ResponseBody
    public Object memorabiliaList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("memorabiliaDel", request.getParameter("memorabiliaDel"));
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("memorabiliaTheme").equals(getName)){
        		 criteria.put("memorabiliaTheme2", getValue);
        	}else if(("memorabiliaTime").equals(getName)) {
        		criteria.put("memorabiliaTime2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" memorabilia_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = mwMemorabiliaService.countByExample(criteria);
        List<MwMemorabilia> mwMemorabilia=mwMemorabiliaService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", mwMemorabilia);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
   
     /**
      * 方法名: memorabiliaView
      * 描述: 查看基地大事记信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午12:05:58
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/memorabiliaView")
    @ResponseBody
    public Object memorabiliaView(HttpServletRequest request, HttpServletResponse response){
        MwMemorabilia mwMemorabilia = null;
        String memorabiliaId = request.getParameter("memorabiliaId");
        if(StringUtil.isNotBlank(memorabiliaId)){
            mwMemorabilia = mwMemorabiliaService.selectByPrimaryKey(memorabiliaId);
        }
        return mwMemorabilia;
    }
    
     /**
      * 方法名: saveOrUpMemorabilia
      * 描述: 新增/编辑基地大事记信息
      * 参数: @param amPrize
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年6月16日 上午12:06:50
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpMemorabilia")
    public void saveOrUpMemorabilia(MwMemorabilia mwMemorabilia,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = mwMemorabiliaService.insertSelective(mwMemorabilia,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteMemorabilia
     * 描述: 批量删除基地大事记
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月16日 上午12:07:08
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteMemorabilia")
    public void deleteMemorabilia(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
       //判断是删除还是恢复
        String memorabiliaDel=request.getParameter("memorabiliaDel");
        //返回多个id
        String memorabiliaId=request.getParameter("memorabiliaId");
        if(StringUtil.isNotBlank(memorabiliaId) && StringUtil.isNotBlank(memorabiliaDel)) {
            String[] strMemorabiliaId = memorabiliaId.split(",");
            for(int i=0;i<strMemorabiliaId.length;i++){
                MwMemorabilia mwMemorabilia=mwMemorabiliaService.selectByPrimaryKey(strMemorabiliaId[i]);
                if(mwMemorabilia != null) {
                    mwMemorabiliaService.updateByPrimaryKey(mwMemorabilia,memorabiliaDel);
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
