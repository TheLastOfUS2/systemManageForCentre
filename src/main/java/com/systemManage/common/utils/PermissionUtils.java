package com.systemManage.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 账号权限工具类
 * @author Administrator
 */
public class PermissionUtils {
	
	/**
	 * 系统默认不拦截的url
	 */
	private static List<String> systemUrl = new ArrayList<String>();
	
    static {
	    // 存入不拦截的url信息
		systemUrl.add("toLogin");
		systemUrl.add("login");
		systemUrl.add("indexManage");
		systemUrl.add("common/toQuery");
		//systemUrl.add("common/toQuery");
		/*systemUrl.add("system/layout");
		systemUrl.add("system/retrievePass");
		systemUrl.add("system/forgetPass");
		systemUrl.add("system/setNewPass");
		systemUrl.add("system/updatePass");
		systemUrl.add("obtainAttendanceTxt/importTxtData");
		systemUrl.add("system/resetPassToPage");
		systemUrl.add("system/resetPass");*/
	}
	
	public static List<String> getSystemUrl() {
		return systemUrl;
	}
	
}
