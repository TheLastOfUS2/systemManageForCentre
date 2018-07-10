package com.systemManage.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 类名称：翻页工具类
 * 内容摘要：计算总页数 计算数据库查询起始页
 * @version 1.0 2011-7-23
 */
public class PageUtil {
	
	/**
     * 取得分页开始条数
     * @param currentPage 当前页数
     * @param perPage 每页显示条数
     * @return int 每页开始条数
     */
    public static int getStartRecord(int currentPage, int perPage) {
        int startRecord = 0;
        currentPage--;
        // 如果当前为0则开始条数是0 否则每页开始条数为当前页减1乘每页显示条数
        if (currentPage <= 0) {
            startRecord = 0;
        }
        else {
            // 设置当前页的初始条数
            startRecord = currentPage * perPage;
        }
        return startRecord;
    }
    
    
    
    
    
	
    /**
     * 获取到总页数
     * @param totalRecord 总条数
     * @param perPage 每页显示条数
     * @return int 总页数
     */
    public static int getTotalPageConut(int totalRecord, int perPage) {

        int totalPage = 0;
        // 判断总条数 每页显示条数取于 如果等于0则总条数除每页显示条数 否则加1
        if (totalRecord % perPage == 0) {
            totalPage = totalRecord / perPage;
        }
        else {
            totalPage = (totalRecord / perPage) + 1;
        }
        return totalPage;
    }

    
    
    /**
     * 取得分页查询的截止记录数
     * @param startRecord 分页查询的起始记录数
     * @param perPage 每页显示条数
     * @return int 分页查询的截止记录数
     */
    public static int getEndRecord(int startRecord, int perPage){
        if(perPage <= 0){
        	perPage = 10;
        }
        return (startRecord + perPage);
    }
    
    /**
     * 方法名: getPageOrRows
     * 描述: 分页判断处理
     * 参数: @param pageOrRows
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月6日 上午10:23:30
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: int
     */
    public static int getPageOrRows(String pageOrRows){
        if(StringUtils.isNotBlank(pageOrRows)){
            return Integer.parseInt(pageOrRows);
        }else{
            return 0;
        }
    }
}
