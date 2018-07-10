package com.systemManage.web.controller.common;

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
import com.systemManage.pojo.base.CommonAccount;
import com.systemManage.pojo.base.CommonRole;
import com.systemManage.pojo.base.Criteria;
import com.systemManage.service.CommonAccountService;
import com.systemManage.service.CommonRoleService;

/**
 * 类     名:RoleController.java
 * 作     用:权限管理
 * 作     者:张金秋
 * 日     期:2017 2017年7月17日 下午22:42:10
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //查询条件
    Criteria criteria = new Criteria();
        
    @Autowired
    private CommonRoleService commonRoleService;//角色业务层
    
    /**
     * 方法名: toRoleList
     * 描述: 跳转到角色列表
     * 参数: @param request
     * 参数: @param response
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年7月17日 下午11:12:41
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: Object
     */
    @RequestMapping("/toRoleList")
    public String toRoleList(HttpServletRequest request, HttpServletResponse response){
        return "common/roleList";
    }
        
    @RequestMapping("/roleList")
    @ResponseBody
    public Object roleList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("roleDel", request.getParameter("roleDel"));
        
        // 页面检索的name值
        String getName=request.getParameter("getName");
        // 页面检索输入的value值
        String getValue=request.getParameter("getValue");
        
        // 获取当前分页页号和每页显示条数
        int page = PageUtil.getPageOrRows(request.getParameter("page"));
        int rows = PageUtil.getPageOrRows(request.getParameter("rows"));
        if(page != 0 && rows != 0){
            criteria.setMysqlOffset(PageUtil.getStartRecord(page, rows));
            criteria.setMysqlLength(rows);
        }
        Map<String,Object> jsonMap = new HashMap<String,Object>();
        int total = commonRoleService.countByExample(criteria);
        List<CommonRole> commonRole=commonRoleService.selectByExample(criteria);
        // 返回的数据
        jsonMap.put("rows", commonRole);
        // 总条数
        jsonMap.put("total", total);
        return jsonMap;
    }
    
    @RequestMapping("/roleTypeList")
    @ResponseBody
    public Object roleTypeList(HttpServletRequest request, HttpServletResponse response) {
        criteria.clear();
        criteria.put("roleDel", request.getParameter("roleDel"));
        List<CommonRole> commonRole=commonRoleService.selectByExample(criteria);
        return commonRole;
    }
}
