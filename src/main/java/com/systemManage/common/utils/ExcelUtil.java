package com.systemManage.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

public class ExcelUtil {
    
    /**
     * 方法名: style
     * 描述: 设置标题样式
     * 参数: @param workbook
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月19日 下午4:12:11
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: HSSFCellStyle
     */
    public static HSSFCellStyle style(HSSFWorkbook workbook){
        //设置标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight((short) 10);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 粗体
        font.setColor(HSSFColor.BLACK.index); // 黑字
        //把字体应用到当前的样式
        style.setFont(font);
        return style;
    }
    
    /**
     * 方法名: styleCell
     * 描述: 设置内容样式
     * 参数: @param workbook
     * 参数: @return     
     * 创建人: Zhang JinQiu 
     * 创建时间: 2017年6月19日 下午4:12:26
     * 版本号: v1.0   
     * 抛出异常:
     * 返回类型: HSSFCellStyle
     */
    public static HSSFCellStyle styleCell(HSSFWorkbook workbook){
        HSSFCellStyle styleCell = workbook.createCellStyle();
        HSSFFont fontCell = workbook.createFont();
        styleCell.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleCell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleCell.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        fontCell.setFontHeightInPoints((short) 10); // 字体大小
        fontCell.setBoldweight((short) 10);
        fontCell.setFontName("宋体");
        styleCell.setFont(fontCell);
        return styleCell;
    }

}
