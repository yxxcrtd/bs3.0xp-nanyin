package com.joyintech.tams.svreport.chb.monthreport.service;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述 POI 读取 Excel 转 HTML 支持 03xls 和 07xlsx 版本  包含样式
 * @author Devil
 * @创建时间 2015/4/19 21:34
 */
public class XlsxToHtml {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(XlsxToHtml.class);

    /**
     * 程序入口方法
     * @param filePath 文件的路径
     * @param isWithStyle 是否需要表格样式 包含 字体 颜色 边框 对齐方式
     * @return <table>...</table> 字符串
     */
    public static String readExcelToHtml(File file , boolean isWithStyle){
        
        InputStream is = null;
        String htmlExcel = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(file);
            wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook) {
                XSSFWorkbook xWb = (XSSFWorkbook) wb;
                htmlExcel = XlsxToHtml.getExcelInfo(xWb,isWithStyle);
            }else if(wb instanceof HSSFWorkbook){
                HSSFWorkbook hWb = (HSSFWorkbook) wb;
                htmlExcel = XlsxToHtml.getExcelInfo(hWb,isWithStyle);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
            try {
                if(is!=null)
                {
                    is.close();
                }

            } catch (IOException e) {
//                e.printStackTrace();
                logger.error(e.getMessage(),e);
             }
            try {
                if(wb!=null)
                {
                    wb.close();
                }
            } catch (IOException e) {
//                e.printStackTrace();
                logger.error(e.getMessage(),e);
             }
         }
         return htmlExcel;
     }
     
     
     
     public static String getExcelInfo(Workbook wb,boolean isWithStyle){
         
         StringBuffer sb = new StringBuffer();
         for(int i=0;i<2;i++){
        	 Sheet sheet = wb.getSheetAt(i);//获取第一个Sheet的内容
             int lastRowNum = sheet.getLastRowNum();
             Map<String, String> map[] = getRowSpanColSpanMap(sheet);
             sb.append("<table style='border-collapse:collapse;' width='100%'>");
             Row row = null;        //兼容
             Cell cell = null;    //兼容
             
             for (int rowNum = sheet.getFirstRowNum(); rowNum <= lastRowNum; rowNum++) {
                 row = sheet.getRow(rowNum);
                 if (row == null) {
                     sb.append("<tr><td > &nbsp;</td></tr>");
                     continue;
                 }
                 sb.append("<tr>");
                 int lastColNum = row.getLastCellNum();
                 for (int colNum = 0; colNum < lastColNum; colNum++) {
                     cell = row.getCell(colNum);
                     if (cell == null) {    //特殊情况 空白的单元格会返回null
                         sb.append("<td>&nbsp;</td>");
                         continue;
                     }
     
                     String stringValue = getCellValue(cell);
                     if (map[0].containsKey(rowNum + "," + colNum)) {
                         String pointString = map[0].get(rowNum + "," + colNum);
                         map[0].remove(rowNum + "," + colNum);
                         int bottomeRow = Integer.valueOf(pointString.split(",")[0]);
                         int bottomeCol = Integer.valueOf(pointString.split(",")[1]);
                         int rowSpan = bottomeRow - rowNum + 1;
                         int colSpan = bottomeCol - colNum + 1;
                         sb.append("<td rowspan= '" + rowSpan + "' colspan= '"+ colSpan + "' ");
                     } else if (map[1].containsKey(rowNum + "," + colNum)) {
                         map[1].remove(rowNum + "," + colNum);
                         continue;
                     } else {
                         sb.append("<td ");
                     }
                     
                     //判断是否需要样式
                     if(isWithStyle){
                         dealExcelStyle(wb, sheet, cell, sb);//处理单元格样式
                     }
                     
                     sb.append(">");
                     if (stringValue == null || "".equals(stringValue.trim())) {
                         sb.append(" &nbsp; ");
                     } else {
                         // 将ascii码为160的空格转换为html下的空格（&nbsp;）
                         sb.append(stringValue.replace(String.valueOf((char) 160),"&nbsp;"));
                     }
                     sb.append("</td>");
                 }
                 sb.append("</tr>");
             }
     
             sb.append("</table>"); 
        	 
         }
        
         return sb.toString();
     }
     
     private static Map<String, String>[] getRowSpanColSpanMap(Sheet sheet) {
 
         Map<String, String> map0 = new HashMap<String, String>();
         Map<String, String> map1 = new HashMap<String, String>();
         int mergedNum = sheet.getNumMergedRegions();
         CellRangeAddress range = null;
         for (int i = 0; i < mergedNum; i++) {
             range = sheet.getMergedRegion(i);
             int topRow = range.getFirstRow();
             int topCol = range.getFirstColumn();
             int bottomRow = range.getLastRow();
             int bottomCol = range.getLastColumn();
             map0.put(topRow + "," + topCol, bottomRow + "," + bottomCol);
             // System.out.println(topRow + "," + topCol + "," + bottomRow + "," + bottomCol);
             int tempRow = topRow;
             while (tempRow <= bottomRow) {
                 int tempCol = topCol;
                 while (tempCol <= bottomCol) {
                     map1.put(tempRow + "," + tempCol, "");
                     tempCol++;
                 }
                 tempRow++;
             }
             map1.remove(topRow + "," + topCol);
         }
         Map[] map = { map0, map1 };
         return map;
     }
     
     
     /**
      * 获取表格单元格Cell内容
      * @param cell
      * @return
      */
     private static String getCellValue(Cell cell) {
 
         String result = new String();
         if (CellType.NUMERIC.equals(cell.getCellType())) {
             if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                 SimpleDateFormat sdf = null;
                 if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                     sdf = new SimpleDateFormat("HH:mm");
                 } else {// 日期
                     sdf = new SimpleDateFormat("yyyy-MM-dd");
                 }
                 Date date = cell.getDateCellValue();
                 result = sdf.format(date);
             } else if (cell.getCellStyle().getDataFormat() == 58) {
                 // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 double value = cell.getNumericCellValue();
                 Date date = org.apache.poi.ss.usermodel.DateUtil
                         .getJavaDate(value);
                 result = sdf.format(date);
             } else {
                 double value = cell.getNumericCellValue();
                 CellStyle style = cell.getCellStyle();
                 String temp = style.getDataFormatString();
                 DecimalFormat format = new DecimalFormat(temp.replaceAll("_",""));
                 // 单元格设置成常规
                 if (temp.equals("General")||temp.equals("@")) {
                     format.applyPattern("#");
                     if(!(value+"").contains("E")){
                         return value+"";
                     }
                 }
                 result = format.format(value);
             }
         } else if (CellType.STRING.equals(cell.getCellType())) {
             result = cell.getRichStringCellValue().toString();
         } else {
             result = "";
         }
         return result;  
     }
     
     /**
      * 处理表格样式
      * @param wb
      * @param sheet
      * @param cell
      * @param sb
      */
     private static void dealExcelStyle(Workbook wb,Sheet sheet,Cell cell,StringBuffer sb){
         
         CellStyle cellStyle = cell.getCellStyle();
         if (cellStyle != null) {
             HorizontalAlignment alignment = cellStyle.getAlignment();
             sb.append("align='" + convertAlignToHtml(alignment) + "' ");//单元格内容的水平对齐方式
             VerticalAlignment verticalAlignment = cellStyle.getVerticalAlignment();
             sb.append("valign='"+ convertVerticalAlignToHtml(verticalAlignment)+ "' ");//单元格中内容的垂直排列方式
             
             if (wb instanceof XSSFWorkbook) {
                             
                 XSSFFont xf = ((XSSFCellStyle) cellStyle).getFont(); 
                 short boldWeight = xf.getBold()?(short)700:(short)400;
                 sb.append("style='");
                 sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                 sb.append("font-size: " + xf.getFontHeight() / 2 + "%;"); // 字体大小
                 int columnWidth = sheet.getColumnWidth(cell.getColumnIndex()) ;
                 sb.append("width:" + columnWidth + "px;");
                 
                 XSSFColor xc = xf.getXSSFColor();
                 if (xc != null && !"".equals(xc)) {
                     sb.append("color:#" + xc.getARGBHex().substring(2) + ";"); // 字体颜色
                 }
                 
                 XSSFColor bgColor = (XSSFColor) cellStyle.getFillForegroundColorColor();
                 //System.out.println("************************************");
                 //System.out.println("BackgroundColorColor: "+cellStyle.getFillBackgroundColorColor());
                 //System.out.println("ForegroundColor: "+cellStyle.getFillForegroundColor());//0
                 //System.out.println("BackgroundColorColor: "+cellStyle.getFillBackgroundColorColor());
                 //System.out.println("ForegroundColorColor: "+cellStyle.getFillForegroundColorColor());
                 //String bgColorStr = bgColor.getARGBHex();
                 //System.out.println("bgColorStr: "+bgColorStr);
                 if (bgColor != null && !"".equals(bgColor)) {
                     sb.append("background-color:#" + bgColor.getARGBHex().substring(2) + ";"); // 背景颜色
                 }
                 sb.append(getBorderStyle(0,cellStyle.getBorderTop(), ((XSSFCellStyle) cellStyle).getTopBorderXSSFColor()));
                 sb.append(getBorderStyle(1,cellStyle.getBorderRight(), ((XSSFCellStyle) cellStyle).getRightBorderXSSFColor()));
                 sb.append(getBorderStyle(2,cellStyle.getBorderBottom(), ((XSSFCellStyle) cellStyle).getBottomBorderXSSFColor()));
                 sb.append(getBorderStyle(3,cellStyle.getBorderLeft(), ((XSSFCellStyle) cellStyle).getLeftBorderXSSFColor()));
                     
             }else if(wb instanceof HSSFWorkbook){
                 
                 HSSFFont hf = ((HSSFCellStyle) cellStyle).getFont(wb);
                 short boldWeight = hf.getBold()?(short)700:(short)400;
                 short fontColor = hf.getColor();
                 sb.append("style='");
                 HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette(); // 类HSSFPalette用于求的颜色的国际标准形式
                 HSSFColor hc = palette.getColor(fontColor);
                 sb.append("font-weight:" + boldWeight + ";"); // 字体加粗
                 sb.append("font-size: " + hf.getFontHeight() / 2 + "%;"); // 字体大小
                 String fontColorStr = convertToStardColor(hc);
                 if (fontColorStr != null && !"".equals(fontColorStr.trim())) {
                     sb.append("color:" + fontColorStr + ";"); // 字体颜色
                 }
                 int columnWidth = sheet.getColumnWidth(cell.getColumnIndex()) ;
                 sb.append("width:" + columnWidth + "px;");
                 short bgColor = cellStyle.getFillForegroundColor();
                 hc = palette.getColor(bgColor);
                 String bgColorStr = convertToStardColor(hc);
                 if (bgColorStr != null && !"".equals(bgColorStr.trim())) {
                     sb.append("background-color:" + bgColorStr + ";"); // 背景颜色
                 }
                 sb.append( getBorderStyle(palette,0,cellStyle.getBorderTop(),cellStyle.getTopBorderColor()));
                 sb.append( getBorderStyle(palette,1,cellStyle.getBorderRight(),cellStyle.getRightBorderColor()));
                 sb.append( getBorderStyle(palette,3,cellStyle.getBorderLeft(),cellStyle.getLeftBorderColor()));
                 sb.append( getBorderStyle(palette,2,cellStyle.getBorderBottom(),cellStyle.getBottomBorderColor()));
             }
 
             sb.append("' ");
         }
     }
     
     /**
      * 单元格内容的水平对齐方式
      * @param alignment
      * @return
      */
     private static String convertAlignToHtml(HorizontalAlignment alignment) {
 
         String align = "left";
         if (HorizontalAlignment.LEFT.equals(align)) {
             align = "left";
         } else if (HorizontalAlignment.CENTER.equals(align)) {
             align = "center";
         } else if (HorizontalAlignment.RIGHT.equals(align)) {
             align = "right";
         }
         return align;
     }
 
     /**
      * 单元格中内容的垂直排列方式
      * @param verticalAlignment
      * @return
      */
     private static String convertVerticalAlignToHtml(VerticalAlignment verticalAlignment) {

         String valign = "middle";
         if (VerticalAlignment.BOTTOM.equals(valign)) {
             valign = "bottom";
         } else if (VerticalAlignment.CENTER.equals(valign)) {
             valign = "center";
         } else if (VerticalAlignment.TOP.equals(valign)) {
             valign = "top";
         }
         return valign;
     }
    
    private static String convertToStardColor(HSSFColor hc) {

        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            if (HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex() == hc.getIndex()) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                sb.append(fillWithZero(Integer.toHexString(hc.getTriplet()[i])));
            }
        }

        return sb.toString();
    }
    
    private static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }
    
    static String[] bordesr={"border-top:","border-right:","border-bottom:","border-left:"};
    static String[] borderStyles={"solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid ","solid","solid","solid","solid","solid"};

    private static  String getBorderStyle(  HSSFPalette palette ,int b,BorderStyle s, short t){
         
        if(s.getCode()==0)return  bordesr[b]+borderStyles[s.getCode()]+"#d0d7e5 1px;";;
        String borderColorStr = convertToStardColor( palette.getColor(t));
        borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr;
        return bordesr[b]+borderStyles[s.getCode()]+borderColorStr+" 1px;";
        
    }
    
    private static  String getBorderStyle(int b,BorderStyle s, XSSFColor xc){
         
         if(s.getCode()==0)return  bordesr[b]+borderStyles[s.getCode()]+"#d0d7e5 1px;";;
         if (xc != null && !"".equals(xc)) {
             String borderColorStr = xc.getARGBHex();//t.getARGBHex();
             borderColorStr=borderColorStr==null|| borderColorStr.length()<1?"#000000":borderColorStr.substring(2);
             return bordesr[b]+borderStyles[s.getCode()]+borderColorStr+" 1px;";
         }
         
         return "";
    }

}