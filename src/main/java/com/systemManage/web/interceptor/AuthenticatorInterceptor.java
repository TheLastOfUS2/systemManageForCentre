package com.systemManage.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.systemManage.common.utils.PermissionUtils;
import com.systemManage.common.utils.StringUtil;
import com.systemManage.common.utils.WebUtils;
import com.systemManage.pojo.dto.CommonAccountDto;
import com.systemManage.service.CommonAccountService;

public class AuthenticatorInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonAccountService accountService;
	
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception) throws Exception {
    	//System.out.println("afterCompletion.................");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView) throws Exception {
    	//System.out.println("postHandle.................");
    }

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
	    // 从Session中获取项目路径
        HttpSession session = request.getSession();
        String path = (String) session.getAttribute("basePath");
        if(StringUtils.isBlank(path)) {
            //获取项目基础路径
            String basePath = WebUtils.getBasePath(request, response);
            //供页面和后台引用项目路径使用
            session.setAttribute("basePath", basePath);
        }
        //获取当前请求的url
        String requestMappingName = WebUtils.getRequestMappingName(request, response);
        if(requestMappingName.startsWith("resources") || requestMappingName.startsWith("upload")){
            return true;
        }else{
            //过滤掉系统默认的url
            if(PermissionUtils.getSystemUrl().contains(requestMappingName)){
                return true;
            }
            //获取当前登录用户信息
            CommonAccountDto account = (CommonAccountDto) session.getAttribute("accountInfo");
            //判断用户是否登录
            if(account == null){
                // 如果是ajax请求则直接返回false
            	if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            		return false;
                }else {
            	    // Session超时, 重定向到登录窗口页面, 调用其他网站链接
                    logger.info("操作失败, 用户名未登录！");
                    WebUtils.toLogin(response);
                    return false;
                }
            }else{
                //如果访问路径以ajax开始，则通过
                if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            		return true;
                }
                // 如果访问来自进入首页, 则通过
                if(requestMappingName.contains("toIndexManage") ) {
                	return true;
                }
                // 获取当前请求的参数
                String queryString = request.getQueryString();
                if(StringUtil.isNotBlank(queryString) && queryString.contains("menuid")) {
                    queryString = queryString.substring(queryString.indexOf("menuid")+7, queryString.indexOf("menuid")+10);
                    // 判断当前登录用户对应的权限是否包含该访问链接, 包含则允许访问, 否则重定向到登录页
                    if(!account.getRolePower().contains(","+queryString+",")) {
                        // 不包含参数则重定向到登录页
                        logger.info("操作失败, 用户无权限访问该链接, 访问路径：" + requestMappingName);
                        WebUtils.toLogin(response);
                        return false;
                    }
                }else {
                    // 不包含参数则重定向到登录页
                    logger.info("操作失败, 用户访问链接没有携带对应的菜单ID条件！访问路径：" + requestMappingName);
                    WebUtils.toLogin(response);
                    return false;
                }
            }
        }
	    return true;
    }
	
}
