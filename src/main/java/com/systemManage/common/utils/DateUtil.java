package com.systemManage.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.systemManage.common.exception.SystemException;

/**
 * 类名称：DateUtil 内容摘要：处理日期的工具类
 * @version 1.0 2017-6-27
 */
public class DateUtil {

    /** 格式化日期对象 */
    private static SimpleDateFormat format = new SimpleDateFormat();

    /** 日期格式【yyyyMMddHHmmssSSS】 */
    public static String PATTERN_YYYY_MM_DD_HH_mm_ss_SSS = "yyyyMMddHHmmssSSS";

    /** 日期格式【yyMMdd】 */
    public static String PATTERN_YY_MM_DD = "yyMMdd";

    /**
     * 日期格式： yyyy
     */
    public static final String DATE_FMT_YYYY = "yyyy";

    /**
     * 日期格式： MM
     */
    public static final String DATE_FMT_MM = "MM";

    /**
     * 日期格式： yyyyMM
     */
    public static final String DATE_FMT_YYYYMM_NS = "yyyyMM";

    /**
     * 日期格式： yyyy/MM/dd
     */
    public static final String DATE_FMT_YYYYMMDD = "yyyy/MM/dd";

    /**
     * 日期格式： yyyyMMdd
     */
    public static final String DATE_FMT_YYYYMMDD_NS = "yyyyMMdd";

    /**
     * 日期格式： yyyy/MM/dd HH:mm:ss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_FMT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式：yyyy年MM月dd日
     */
    public static final String DATE_FMT_YYYY_MM_DD2 = "yyyy年MM月dd日";
    
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FMT_YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    /**
      * 方法名: getCurrentDate
      * 描述: 获取指定格式的当前日期
      * 参数：  pattern 日期格式
      * 创建人: Xia ZhengWei
      * 创建时间: 2016年8月25日 上午9:50:25
      * 返回类型: String
     */
    public static String getCurrentDate(String pattern) {
        format.applyPattern(pattern);
        return format.format(new Date());
    }

    /**
     * 取得日期字符串
     * @param date 日期
     * @param pattern 日期格式
     * @return String 日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        format.applyPattern(pattern);
        return format.format(date);
    }

    /**
     * 向前或是向后滚动年
     * @param Date date
     * @param int rollCount
     * @return Date 日期
     */
    public static Date rollYear(Date date, int rollCount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.roll(Calendar.YEAR, rollCount);
        return ca.getTime();
    }

    /**
     * 向前或者向后滚动月份 正数像前滚 负数向后滚
     * @param date 准备滚动的日期
     * @param rollCount 滚动参数
     * @return Date 滚动之后的日期
     */
    public static Date rollMonth(Date date, int rollCount) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, rollCount);
        return ca.getTime();
    }

    /**
     * 时间滚动 正数像前滚 负数向后滚
     * @param date 准备滚动的日期
     * @param calendar 滚动系数
     *            年 Calendar.YEAR;
     *            月 Calendar.MONTH;
     *            日 Calendar.DATE;
     *            时 Calendar.HOUR;
     *            钟Calendar.MINUTE;
     * @param rollCount 滚动参数
     * @return Date 滚动之后的日期
     */
    public static Date rollDate(Date date, int calendar, int rollCount) {

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(calendar, rollCount);
        return ca.getTime();
    }

    /**
     * 取得去年的今天
     * @return String 去年的今天
     */
    public static Date getLastYearOfToday() {
        return rollYear(new Date(), -1);
    }

    /**
     * 取得日期字符串
     * @param strDate 日期
     * @param pattern 日期格式
     * @return Date 转换后日期类型的值
     * @throws SystemException
     */
    public static Date parseDate(String strDate, String pattern) throws SystemException {
        if (StringUtil.isEmpty(strDate)) {
            return null;
        }
        format.applyPattern(pattern);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            throw new SystemException(e.getMessage());
        }
    }

    /**
     * 取得当前年
     * @return String 年的字符型值
     */
    public static String getYear() {
        return formatDate(new Date(), DATE_FMT_YYYY);
    }

    /**
     * 取得去年
     * @return String 去年的字符型值
     */
    public static String getLastYear() {
        return formatDate(getLastYearOfToday(), DATE_FMT_YYYY);
    }

    /**
     * 取得当前年月日
     * @return String 年月日的字符型值
     */
    public static String getYearMonthDay() {
        return formatDate(new Date(), DATE_FMT_YYYY_MM_DD);
    }
    
    /**
     * 取得当前年月日(前一天)
     * @return String 年月日的字符型值
     */
    public static String getPrevYearMonthDay(){
    	 Calendar c = Calendar.getInstance();  
         Date date = new Date();  
         c.setTime(date);  
         int day = c.get(Calendar.DATE);  
         c.set(Calendar.DATE, day - 1);  
         String dayBefore = new SimpleDateFormat(DATE_FMT_YYYY_MM_DD).format(c  
                 .getTime());  
         return dayBefore;  
    }

    /**
     * 取得当前年月日时分秒
     * @return String 年月日时分秒的日期型值
     */
    public static String getSystemTime() {
    	Date date = new Date();
    	SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmmss"); 
    	return from.format(date);

    }
    /**
     * 取得当前年月日时分秒
     * @return String 年月日时分秒的日期型值
     */
    public static String getSystemTimeMs() {
    	Date date = new Date();
    	SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
    	return from.format(date);

    }
    /**
     * 取得去年月日(去年的今天)
     * @return String 去年月日的字符型值
     */
    public static String getLastYearMonthDay() {
        return formatDate(getLastYearOfToday(), DATE_FMT_YYYY_MM_DD);
    }

    /**
     * 方法名: getMonthDays
     * 描述: 根据月份获取天数
     * 参数: @param month
     * 参数: @return
     * 返回类型: String
     */
    @SuppressWarnings("deprecation")
    public static Integer getMonthDays(Integer month){
        HashMap<Integer, Integer> monthDaysMap = new HashMap<Integer, Integer>();
        Date date = new Date();
        int year = date.getYear();
        monthDaysMap.put(1, 31);
        if((year%4==0&&year%100!=0)||year%400==0){
            //今年是闰年
            monthDaysMap.put(2, 29);
        }else{
            monthDaysMap.put(2, 28);
        }
        monthDaysMap.put(3, 31);
        monthDaysMap.put(4, 30);
        monthDaysMap.put(5, 31);
        monthDaysMap.put(6, 30);
        monthDaysMap.put(7, 31);
        monthDaysMap.put(8, 31);
        monthDaysMap.put(9, 30);
        monthDaysMap.put(10, 31);
        monthDaysMap.put(11, 30);
        monthDaysMap.put(12, 31);
        return monthDaysMap.get(month);
    }
    
    /**
      * 方法名: getFirstDayOfBeforeMonth
      * 描述: 获取前一个月的第一天
      * 参数：  @return
      * 返回类型: String
     */
    public static String getFirstDayOfBeforeMonth() {
        Calendar calendar = Calendar.getInstance();    
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        format.applyPattern("yyyy-MM-dd");
        String firstDay = format.format(calendar.getTime());
        return firstDay;
    }
    
    /**
      * 方法名: getLastDayOfBeforeMonth
      * 描述: 获取前一个月的最后一天
      * 参数：  @return
      * 返回类型: String
     */
    public static String getLastDayOfBeforeMonth() {
        Calendar calendar = Calendar.getInstance();    
        calendar.set(Calendar.DAY_OF_MONTH, 0);  
        format.applyPattern("yyyy-MM-dd");
        String lastDay = format.format(calendar.getTime());
        return lastDay;
    }
    
    /**
     * 方法名: getFirstDayOfMonth 
     * 描述: 获取本月第一天
     * 参数: @return     
     * 返回类型: String
     */
    public static String getFirstDayOfMonth() {
    	Calendar calendar = Calendar.getInstance();    
    	calendar.add(Calendar.MONTH, 0);
    	calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        format.applyPattern("yyyy-MM-dd");
        String firstDay = format.format(calendar.getTime());
    	return firstDay;
    }
    
    /**
     * 方法名: getLastDayOfMonth 
     * 描述: 获取本月最后一天
     * 参数: @return     
     * 返回类型: String
     */
    public static String getLastDayOfMonth() {
    	Calendar calendar = Calendar.getInstance();    
    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        format.applyPattern("yyyy-MM-dd");
        String lastDay = format.format(calendar.getTime());
    	return lastDay;
    }
    
    /**
     * 方法名: nextMonthFirstDate 
     * 描述: 获取当前日期的下个月第一天
     * 参数: @return     
     * 返回类型: String
     */
    public static String nextMonthFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        format.applyPattern("yyyy-MM-dd");
        String nextFirstDay = format.format(calendar.getTime());
    	return nextFirstDay;
    }

	/**
	  * 方法名: getMonthWorkDays
	  * 描述: 获取给定月份(年份) 工作日
	  * 参数:  month
	  * 参数:  year
	  * 参数:  @return
	  * 返回类型: String
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
    public static String getMonthWorkDays(String year,String month){
	    //创建计数器
	    int count = 0;
	    //创建该月起始日期
	    Date startDate = new Date(year+"/"+month+"/01");
	    //获得该月有多少天
	    Integer monthDays = getMonthDays(Integer.valueOf(month));
	    //创建日期操作对象
        Calendar calendar = new GregorianCalendar(); 
	    //迭代该月所有天
	    for(int i = 0;i<monthDays;i++){
	        if(i>0){
	            //日期+1
	            calendar.setTime(startDate); 
	            //把日期往后增加一天.整数往后推,负数往前移动 
	            calendar.add(calendar.DATE,1); 
	            //这个时间就是日期往后推一天的结果 
	            startDate = calendar.getTime();
	            //判断是否是周六周天
                if(startDate.getDay() == 6 || startDate.getDay() == 0){
                    count = count+1;
                }
	        }else{
	            //判断是否是周六周天
                if(startDate.getDay() == 6 || startDate.getDay() == 0){
                    count = count+1;
                }
	        }
	    }
	    return Integer.toString((monthDays - count));
	}
	
	/**
	  * 方法名: getWeekOfDate
	  * 描述: 根据时间获取星期
	  * 参数：  @param dt 时间
	  * 创建人: yangx
	  * 创建时间: 2017年1月12日 上午10:07:39
	  * 版本号: v1.0   
	  * 抛出异常: 
	  * 返回类型: String
	 */
	public static String getWeekOfDate(Date dt){
		String[] weekDays = {"周日","周一","周二","周三","周四","周五","周六"};
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		int index = calendar.get(Calendar.DAY_OF_WEEK)-1;
		if (index < 0) {
			index = 0;
		}
		return weekDays[index];
		
	}
}

