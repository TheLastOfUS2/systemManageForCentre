package com.systemManage.web.controller.sharefile;

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
import com.systemManage.pojo.base.SfShareFile;
import com.systemManage.pojo.dto.AmOpusDto;
import com.systemManage.service.AmOpusService;
import com.systemManage.service.SfShareFileService;

/**
 * 类     名:SfShareFileController.java
 * 作     用:共享文件管理
 * 作     者:张金秋
 * 日     期:2017 2017年7月18日 下午15:07:10
 */
@Controller
@RequestMapping("/shareFile")
public class SfShareFileController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private SfShareFileService sfShareFileService; // 共享文件业务层
    
     /**
      * 方法名: toShareFileList
      * 描述: 跳转到共享文件列表页
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年7月18日 下午15:09:9
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: String
      */
    @RequestMapping("/toShareFileList")
    public String toShareFileList(HttpServletRequest request, HttpServletResponse response){
    	 String shareFileType=request.getParameter("shareFileType");
         if(StringUtil.isNotBlank(shareFileType)){
             request.setAttribute("shareFileType", shareFileType);
         }
        return "sharefile/sfShareFileList";
    }
        
     /**
      * 方法名: shareFileList
      * 描述: 文件共享信息列表
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年7月18日 下午15:09:11
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/shareFileList")
    @ResponseBody
    public Object shareFileList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("shareFileDel", request.getParameter("shareFileDel"));
        criteria.put("shareFileType", request.getParameter("shareFileType"));
      
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        if(StringUtil.isNotBlank(getValue)){
        	if(("shareFileName").equals(getName)){
        		 criteria.put("shareFileName2", getValue);
        	}else if(("shareFileSubmitter").equals(getName)) {
        		criteria.put("shareFileSubmitter2", getValue);
        	}
        }
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
            criteria.setOrderByClause(" share_file_create_time desc");
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = sfShareFileService.countByExample(criteria);
        List<SfShareFile> sfShareFile=sfShareFileService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", sfShareFile);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
     /**
      * 方法名: shareFileView
      * 描述: 查看共享文件信息
      * 参数: @param request
      * 参数: @param response
      * 参数: @return     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年7月18日 下午15:16:45
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: Object
      */
    @RequestMapping("/shareFileView")
    @ResponseBody
    public Object shareFileView(HttpServletRequest request, HttpServletResponse response){
        //如果是科研人员:获取姓名 作为第一负责人,如果是其他,则第一负责人可选
        SfShareFile sfShareFile = null;
        String shareFileId = request.getParameter("shareFileId");
        if(StringUtil.isNotBlank(shareFileId)){
        	sfShareFile = sfShareFileService.selectByPrimaryKey(shareFileId);
        }
        return sfShareFile;
    }
    
     /**
      * 方法名: saveOrUpShareFile
      * 描述: 新增/编辑共享文件信息
      * 参数: @param amOpus
      * 参数: @param request
      * 参数: @param response     
      * 创建人: Zhang JinQiu 
      * 创建时间: 2017年7月18日 下午15:25:24
      * 版本号: v1.0   
      * 抛出异常:
      * 返回类型: void
      */
    @RequestMapping("/saveOrUpShareFile")
    public void saveOrUpShareFile(SfShareFile sfShareFile,HttpServletRequest request, HttpServletResponse response){
        JSONObject result = sfShareFileService.insertSelective(sfShareFile,request);
        JsonUtils.outJsonString(result.toString(), response);
    }
    
    
    /**
     * 方法名: deleteShareFile
     * 描述: 批量删除共享文件
     * 参数: @param request
     * 参数: @param response     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月18日 下午15:26:35
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: void
     */
    @RequestMapping("/deleteShareFile")
    public void deleteShareFile(HttpServletRequest request, HttpServletResponse response){
        JSONObject json = null;
        //判断是删除还是恢复
        String shareFileDel=request.getParameter("shareFileDel");
        //返回多个id
        String shareFileId=request.getParameter("shareFileId");
        if(StringUtil.isNotBlank(shareFileId) && StringUtil.isNotBlank(shareFileDel)) {
            String[] strShareFilesId = shareFileId.split(",");
            for(int i=0;i<strShareFilesId.length;i++){
            	SfShareFile sfShareFile=sfShareFileService.selectByPrimaryKey(strShareFilesId[i]);
                if(sfShareFile != null) {
                	sfShareFileService.updateByPrimaryKey(sfShareFile,shareFileDel);
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
