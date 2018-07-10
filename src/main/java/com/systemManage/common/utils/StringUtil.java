package com.systemManage.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * 类名称：StringUtil
 * 内容摘要：对String的操作
 * @author 闫冬
 * @version 1.0 2011/07/01
 */
public class StringUtil extends StringUtils {

    /** 目标字符串类型 Xml */
    public static final String XML = "Xml";
    /** 目标字符串类型 Xml */
    public static final String SQL = "Sql";
    /** 目标字符串类型 Xml */
    public static final String JAVA_SCRIPT = "JavaScript";
    /** 目标字符串类型 Xml */
    public static final String JAVA = "Java";
    /** 目标字符串类型 Xml */
    public static final String HTML = "Html";
    /** null的字符串 */
    public static final String STR_NULL = "null";
    /** 没有日期的字符串 */
    public static final String NONE_DATE = "0000-00-00";
    /** 固定电话 */
    public static final String telephone = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
    /** 手机 */
    public static final String moblie = "^[1][3578][0-9]{9}$";
    /** 电子邮箱  */
    public static final String email = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    /** 身份证号  */
    public static final String idcardnumber = "^(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)$";
    
    /**
     *方法名:getUUID
     *返回值:String
     *作     用:获得uuid生成的id
     *参     数:@return
     *作     者:夏胜春
     *日     期:2014年12月2日 上午9:07:23
     */
    public static String getUUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 将字符串转换成目标类型的普通字符串
     * @param filed 需要转换的字符串
     * @param type 目标类型
     * @return String 转换后的字符串
     */
    public static String escapeUtils(String filed, String type) {

        // 需要转换的字符串不为空
        if (StringUtil.isNotEmpty(filed)) {
            // 目标类型不为空
            if (StringUtil.isNotEmpty(type)) {
                // 目标类型为Html
                if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(HTML))) {
                    filed = StringEscapeUtils.escapeHtml(filed);
                }
                // 目标类型为Java
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(JAVA))) {
                    filed = StringEscapeUtils.escapeJava(filed);
                }
                // 目标类型为javascript
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(JAVA_SCRIPT))) {
                    filed = StringEscapeUtils.escapeJavaScript(filed);
                }
                // 目标类型为sql
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(SQL))) {
                    filed = StringEscapeUtils.escapeSql(filed);
                }
                // 目标类型为xml
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(XML))) {
                    filed = StringEscapeUtils.escapeXml(filed);
                }
            }
        }
        return filed;
    }

    /**
     * 将字符串还原为指定类型的普通字符串
     * @param filed 目标字符串
     * @param type 目的类型
     * @return String 反转后的字符串
     */
    public static String unEscapeUtils(String filed, String type) {

        // 需要反转的字符串不为空
        if (StringUtil.isNotEmpty(filed)) {
            // 目的类型不为空
            if (StringUtil.isNotEmpty(type)) {
                // 反转html字符串
                if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(HTML))) {
                    filed = StringEscapeUtils.unescapeHtml(filed);
                }
                // 反转java字符串
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(JAVA))) {
                    filed = StringEscapeUtils.unescapeJava(filed);
                }
                // 反转javascript字符串
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(JAVA_SCRIPT))) {
                    filed = StringEscapeUtils.unescapeJavaScript(filed);
                }
                // 反转xml字符串
                else if (StringUtil.equals(StringUtil.upperCase(type), StringUtil.upperCase(XML))) {
                    filed = StringEscapeUtils.unescapeXml(filed);
                }
            }
        }
        return filed;
    }

    /**
     * 将list转换成string 根据指定分隔符分割
     * @param list 需要转换的集合
     * @param separator 分隔符
     * @return String 转换之后的字符串
     */
    public static String listToString(List<String> list, String separator) {
        // 如果分隔符为空则默认以“ ”分割
        if (isEmpty(separator)) {
            separator = " ";
        }

        StringBuilder result = new StringBuilder();

        // 循环添加list数据到result，并在后面添加分割符
        for (int i = 0, n = list.size(); i < n; i++) {
            result.append(list.get(i)).append(separator);
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }

    /**
     * 将对象转换成字符串,如果该对象为null 则直接反回null
     * @param object 待转换对象
     * @return String 转换后String对象
     */
    public static String parseString(Object object) {
        // 如果对象为空
        if (null == object) {
            // 直接返回null
            return null;
        }
        // 转换类型 并返回
        return String.valueOf(object);
    }

    /**
     * 将对象转换成字符串,如果该对象为null 则直接反回""
     * @param object 待转换对象
     * @return String 转换后String对象
     */
    public static String parseNullString(Object object) {
        // 如果对象为空
        if (null == object) {
            // 直接返回""
            return "";
        }
        // 转换类型 并返回
        return String.valueOf(object);
    }

    /**
     * 将指定字符串转换成int值
     * @param number 字符串值
     * @return int 对应的int值
     */
    public static int getIntValue(String number) {
        // 判断参数是不是空的
        if (StringUtils.isNotBlank(number)) {
            try {
                // 如果参数不为空 强制转换类型
                return Integer.parseInt(number);
            } catch (Exception e) {
                return -1;
            }
        }
        // 如果参数为空 则返回0
        return 0;
    }

    /**
     * 将指定字符串转换成double值
     * @param number 字符串值
     * @return double 对应的double值
     */
    public static double getDoubleValue(String number) {
        // 判断参数是不是空的
        if (StringUtils.isNotBlank(number)) {
            try {
                // 如果参数不为空 强制转换类型
                return Double.parseDouble(number);
            } catch (Exception e) {
                return -1D;
            }
        }
        // 如果参数为空 则返回0.0D
        return 0.0D;
    }

    /**
     * 将指定字符串转换成float值
     * @param number 字符串值
     * @return float 对应的float值
     */
    public static float getFloatValue(String number) {
        // 判断参数是不是空的
        if (StringUtils.isNotBlank(number)) {
            try {
                // 如果参数不为空 强制转换类型
                return Float.parseFloat(number);
            } catch (Exception e) {
                return -1F;
            }
        }
        // 如果参数为空 则反回0.0F
        return 0.0F;
    }

    /**
     * 将指定字符串转换成boolean值
     * @param bool 字符串值
     * @return boolean 对应的boolean值
     */
    public static boolean getBooleanValue(String bool) {
        // 判断参数是不是空的
        if (StringUtils.isNotBlank(bool)) {
            try {
                // 如果参数不为空 强制转换类型
                return Boolean.parseBoolean(bool);
            } catch (Exception e) {
                try {
                    // 如果转换出现异常 则取得该值的int值
                    int b = getIntValue(bool);

                    // 将int值转换为boolean 大于0为true 小于等于0为false
                    return b > 0;
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        // 如果参数为空 则返回false
        return false;
    }

    /**
     * 将指定int值转换成boolean值
     * @param bool int值
     * @return boolean 对应的boolean值
     */
    public static boolean getBooleanValue(int bool) {
        // 大于等于1为true 小于1为false
        return bool >= 1;
    }

    /**
     * 以分割符分割，将一个String数组转换成一个String
     * @param str String数组
     * @param op 分割符
     * @return String 转换后的结果String
     */
    public static String toString(String[] str, String op) {
        // 声明一个 字符串操作对象
        StringBuilder sb = null;
        // 判断数组是否为空
        if (!ArrayUtils.isEmpty(str)) {
            // 定义变量接收数组
            String[] arrayOfString = str;
            int j = str.length;
            // 循环操作,将遍历出的String都追回到StringBuilder
            for (int i = 0; i < j; i++) {
                String s = arrayOfString[i];
                if (sb == null)
                    sb = new StringBuilder(s);
                else {
                    sb.append(op).append(s);
                }
            }
            // 将操作完成的StringBuilder 转换成String
            return sb.toString();
        }
        // 如果数组为空直接返回空字符串
        return "";
    }

    /**
     * 将字符串转换成指定编码的字符串
     * @param str 参数字符串
     * @param encode 编码名称
     * @return String 转换编码后的字符串
     */
    public static String toChinese(String str, String encode) {
        try {
            return new String(str.getBytes("ISO-8859-1"), encode);
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return str;
    }

    /**
     * 将字符串转换成UTF8编码的字符串
     * @param str 待转换的字符串
     * @return String 转换编码后的字符串
     */
    public static String toChineseOfUTF8(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return str;
    }

    /**
     * 将字符串转换成GB2312编码的字符串
     * @param str 待转换的字符串
     * @return String 转换编码后的字符串
     */
    public static String toChineseOfGB2312(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return str;
    }

    /**
     * 将字符串转换成GBK编码的字符串
     * @param str 待转换的字符串
     * @return String 转换编码后的字符串
     */
    public static String toChineseOfGBK(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "GBK");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
        }
        return str;
    }

    /**
     * 将字符串的首字母转换成大写
     * @param str 字符串参数
     * @return String 转换后的字符串
     */
    public static String toFirstUpperClass(String str) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtil.isNotEmpty(str)) {
            buffer.append(StringUtils.capitalize(str));
        }
        return buffer.toString();
    }

    /**
     * 将字符串的首字母转换成小写
     * @param str 字符串参数
     * @return String 转换后的字符串
     */
    public static String toFirstLowerClass(String str) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtil.isNotEmpty(str)) {
            buffer.append(Character.toString(str.charAt(0)).toLowerCase());
            buffer.append(str.substring(1));
        }
        return buffer.toString();
    }

    /**
     * 将表名改成部分类名的方法
     * @param tableName
     * @return String
     */
    public static String convertTableNameToClass(String tableName) {

        StringBuffer sb = null;
        try {
            sb = new StringBuffer();
            String[] tableNameArr = tableName.split("_");
            String firstChar = null;
            String otherChar = null;
            int strLength = 0;
            for (int i = 0; i < tableNameArr.length; i++) {
                // 若索引为0 全部改写为大写
                if (i == 0) {
                    firstChar = tableNameArr[i].toUpperCase();
                    sb.append(firstChar);
                    // 若索引不为0 只将首字符改写为大写
                }
                else {
                    strLength = tableNameArr[i].length();
                    firstChar = tableNameArr[i].substring(0, 1).toUpperCase();
                    otherChar = tableNameArr[i].substring(1, strLength);
                    sb.append(firstChar);
                    sb.append(otherChar);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 金额格式化
     * @param s 金额
     * @param len 小数位数
     * @return 格式后的金额
     */
    public static String moneyForamt(String s, int len) {
        String formatString = "";
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        double num = Double.parseDouble(s);
        if (len == 0) {
            formater = new DecimalFormat("###,###");

        }
        else {
            StringBuffer buff = new StringBuffer();
            buff.append("###,###.");
            for (int i = 0; i < len; i++) {
                buff.append("0");
            }
            formater = new DecimalFormat(buff.toString());
        }
        formatString = formater.format(num);
        return formatString;
    }

    /**
     * 金额去掉“,”
     * @param s 金额
     * @return 去掉“,”后的金额
     */
    public static String moneyDelFormat(String s) {
        String formatString = "";
        if (s != null && s.length() >= 1) {
            formatString = s.replaceAll(",", "");
        }
        return formatString;
    }

    /**
     * 获得有字符组成的验证码
     * @return 验证码
     */
    public static String getVerificationCode() {
        String[] chars = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        Random random = new Random();
        Set<String> set = new HashSet<String>();
        while (set.size() < 6) {
            set.add(chars[random.nextInt(26)]);
        }
        StringBuffer sb = new StringBuffer();
        for (String code : set) {
            sb.append(code);
        }
        return sb.toString();
    }
    
    /**
	 * 过滤掉"<",">","""
	 * @param str
	 * @return
	 */
	public static String filterToText(String str) {
		if(null==str){
			return str;
		}
		str= str.replaceAll("<", "");
		str = str.replaceAll(">", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("'", "");
		return str;
	}
    
    /**
	 * 清除掉字符串中所有特殊字符 包括空格
	 * @param str 待处理的字符串
	 * @return 返回处理后的结果字符串
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 清除掉所有特殊字符 包括空格
		String regEx = "[`~_!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);// 找到所有匹配的字符
		String result = m.replaceAll("").trim();// 将所有匹配的字符都替换为空字符 并消除两端的空格
		return result.replace("-", "");
	}
	
	/**
	 * 正则Check
	 * 方法名: stringRegexCheck
	 * 描述: 正则验证字符串
	 * 参数: @param regex 正则
	 * 参数: @param text 文本
	 * 参数: @return
	 * 创建人: yi
	 * 创建时间: 2016年4月15日 上午10:58:44
	 * 版本号: v1.0
	 * 抛出异常:
	 * 返回类型: boolean
	 */
	public static boolean stringRegexCheck(String regex,String text){
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(text);  
        return matcher.matches();
	}
	
	 /**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
