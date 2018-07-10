package com.systemManage.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	/**
	 * 获得基础路径
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request, HttpServletResponse response) {
		String scheme = request.getScheme()+"://";
		String requestURL = request.getRequestURL().toString();
		requestURL = requestURL.substring(scheme.length());
		int last = requestURL.indexOf("/");
		if(last==-1){
			last=requestURL.length();
		}
		requestURL = requestURL.substring(0,last);
		String basePath = scheme+requestURL+request.getContextPath()+"/";
		return basePath;
	}
	

	/**
	 * 获取请求中的映射名
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getRequestMappingName(HttpServletRequest request, HttpServletResponse response){
		String basePath = getBasePath(request,response);
		String str = request.getRequestURL().toString().substring(basePath.length());
		return str;
	}
	
	
	/**
	 * 获取请求中的映射名和参数
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getRequestMappingNameAndParm(HttpServletRequest request, HttpServletResponse response){
		String basePath = getBasePath(request,response);
		String str = request.toString().substring(basePath.length());
		String parms = request.getQueryString();
		if(parms!=null){
			str = str+"?"+parms;
		}
		return str;
	}
	
	/**
	 * 获取客户端真实IP地址
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request, HttpServletResponse response) {
	    // 避免反向代理不能获取真实地址, 取X-Forwarded-For中第一个非unknown的有效IP字符串
	    String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
	    return ip;
	}
	
	/**
	 * 重定向到登录页
	 * @param response
	 * @throws IOException
	 */
	public static void toLogin(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();  
        out.println("<html>");      
        out.println("<script>");      
        out.println("window.open ('http://xrj.widonet.com/www/dongcai/','_top')");      
        out.println("</script>");      
        out.println("</html>");
	}
    
}
