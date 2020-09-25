package com.joyintech.service.valuation.accounadjustmanage.checkcommon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * excel操作
 *
 * @author daiyue
 * @version 1.0 2016年12月1日
 * @see ExcelUtilForValuareconcy
 * @since 1.0
 */
public class ExcelUtilForValuareconcy {

    /**
     * 去。0的格式化
     */
    public static final DecimalFormat point = new DecimalFormat("###################.###########");

    /**
     * excel2003格式
     */
    public static final String OFFICE_EXCEL_2003_POSTFIX = ".xls";

    /**
     * excel2007以上格式
     */
    public static final String OFFICE_EXCEL_2007_POSTFIX = ".xlsx";

    /**
     * Excel文件到List
     *
     * @param file 文件
     * @param sheetIndex 工作表索引(下标从0开始)
     * @return 读取结果列表
     * @throws Exception 异常
     */
    public static List<List<String>> readToList(File file, int sheetIndex)
        throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        Workbook wb = loadWorkbook(null, file, null);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(0).getPhysicalNumberOfCells();
            for (int i = 0; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    break;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空结束
                if (dataFlag) {
                    break;
                }
                ls.add(r);
            }
        }
        return ls;
    }

    /**
     * Excel文件到List,对账文件读取用
     *
     * @param file 文件
     * @param sheetIndex 工作表索引(下标从0开始)
     * @param skipRows 跳过的表头(行数)
     * @return 读取结果列表
     * @throws Exception 异常
     */
    public static List<List<String>> readToList(File file, int sheetIndex, int skipRows)
        throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        Workbook wb = loadWorkbook(null, file, null);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(0).getPhysicalNumberOfCells();
            for (int i = skipRows; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    continue;
                }

                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空继续，并记录连续空行数
                int blankRow = 0;
                if (dataFlag) {
                    blankRow++ ;
                    continue;
                }
                else {
                    // 清空连续空行数
                    blankRow = 0;
                }
                // 连续空行超过15时，结束循环
                if (blankRow > 15) {
                    break;
                }
                ls.add(r);
            }
        }
        return ls;
    }

    /**
     * Excel文件到List
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream 文件流
     * @param sheetIndex 工作表索引(下标从0开始)
     * @param skipRows 跳过的表头(行数)
     * @param titleRow 表头序号(下标从0开始)
     * @return 读取结果列表
     * @throws Exception 异常
     */
    public static List<List<String>> readToList(String fileName, File file,
                                                InputStream inputStream, int sheetIndex,
                                                int skipRows, int titleRow)
        throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        Workbook wb = loadWorkbook(fileName, file, inputStream);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(titleRow).getPhysicalNumberOfCells();
            Row tRow = sh.getRow(titleRow);
            // 列标题为空的话结束
            for (int c = 0; c < cells; c++ ) {
                if (tRow.getCell(c) == null
                    || StringUtil.isEmpty(getStringCellValue(tRow.getCell(c)))) {
                    cells = c;
                    break;
                }
            }

            for (int i = skipRows; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    break;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空结束
                if (dataFlag) {
                    break;
                }
                ls.add(r);
            }
        }
        return ls;
    }
    
    /**
     * Excel文件到List，POC专用，遇到空行继续
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream 文件流
     * @param sheetIndex 工作表索引(下标从0开始)
     * @param skipRows 跳过的表头(行数)
     * @param titleRow 表头序号(下标从0开始)
     * @return 读取结果列表
     * @throws Exception 异常
     */
    public static List<List<String>> readToListForPOC(String fileName, File file,
                                                      InputStream inputStream, int sheetIndex,
                                                      int skipRows, int titleRow)
        throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        Workbook wb = loadWorkbook(fileName, file, inputStream);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(titleRow).getPhysicalNumberOfCells();
            Row tRow = sh.getRow(titleRow);
            // 列标题为空的话结束
            for (int c = 0; c < cells; c++ ) {
                if (tRow.getCell(c) == null
                    || StringUtil.isEmpty(getStringCellValue(tRow.getCell(c)))) {
                    cells = c;
                    break;
                }
            }

            for (int i = skipRows; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    continue;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK
                        || StringUtil.EMPTY.equals(getStringCellValue(row.getCell(c)))) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空继续，并记录连续空行数
                int blankRow = 0;
                if (dataFlag) {
                    blankRow++ ;
                    continue;
                }
                else {
                    // 清空连续空行数
                    blankRow = 0;
                }
                // 连续空行超过15时，结束循环
                if (blankRow > 15) {
                    break;
                }
                ls.add(r);
            }
        }
        return ls;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param cell 单元格
     * @return 值
     * @see
     * @since 1.0
     */
    @SuppressWarnings("deprecation")
    private static String getStringCellValue(Cell cell) {
        String value = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue(),
                        DateUtil.DEFAULT_ANOTHER_FORMAT);
                    return value;
                }
                value = point.format(cell.getNumericCellValue());
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case FORMULA: // 公式
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
                break;
            case BLANK: // 空值
                break;
            case BOOLEAN: // Boolean
                break;
            case ERROR: // 故障
                break;
            default:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue());
                }
                break;
        }

        return value;
    }

    /**
     * 读取Excel文件，支持2000与2007格式
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream inputStream
     * @return wb
     * @throws Exception yc
     */
    public static Workbook loadWorkbook(String fileName, File file, InputStream inputStream)
        throws Exception {
        if (null == file && StringUtil.isEmpty(fileName)) {
            return null;
        }
        if (inputStream == null) {
            if (null == file) {
                file = new File(fileName);
            }
            if (StringUtil.isEmpty(fileName)) {
                fileName = file.getName();
            }
            inputStream = new FileInputStream(file);
        }

        Workbook wb = null;
        if (fileName.toLowerCase().endsWith(OFFICE_EXCEL_2003_POSTFIX)) {
            POIFSFileSystem fs = null;
            try {
                fs = new POIFSFileSystem(inputStream);
                wb = new HSSFWorkbook(fs);
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fs.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else if (fileName.toLowerCase().endsWith(OFFICE_EXCEL_2007_POSTFIX)) {
            try {
                wb = new XSSFWorkbook(inputStream);
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            throw new Exception("不是一个有效的Excel文件");
        }
        return wb;
    }

    /**
     * 获取文件的sheet名称
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream inputStream
     * @param sheetIndex 工作表索引(下标从0开始)
     * @return sheet名称
     * @throws Exception 文件异常
     */
    public static String getSheetName(String fileName, File file, InputStream inputStream,
                                      int sheetIndex)
        throws Exception {
        Workbook wb = loadWorkbook(fileName, file, inputStream);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            if (sh != null) {
                return sh.getSheetName();
            }
        }
        return "";
    }

    /**
     * 获取文件中所有sheet的序号
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream inputStream
     * @return 文件中所有sheet的序号
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static int getNumberOfSheets(String fileName, File file, InputStream inputStream)
        throws Exception {
        Workbook wb = loadWorkbook(fileName, file, inputStream);
        if (null != wb) {
            return wb.getNumberOfSheets();
        }

        return 0;
    }

    /**
     * 将Excel表格中列号字母转成列索引，从1对应A开始
     *
     * @param column 列号字母
     * @return 列索引
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static int columnToIndex(String column)
        throws Exception {
        if (!column.matches("[A-Z]+")) {
            throw new Exception("Invalid parameter");
        }
        int index = 0;
        char[] chars = column.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++ ) {
            index += (chars[i] - 'A' + 1) * (int)Math.pow(26, chars.length - i - 1);
        }
        return index;
    }

    /**
     * 将excel表格中列索引转成列号字母，从A对应1开始
     *
     * @param index 列索引
     * @return 列号字母
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public static String indexToColumn(int index)
        throws Exception {
        if (index <= 0) {
            throw new Exception("Invalid parameter");
        }
        index-- ;
        String column = "";
        do {
            if (column.length() > 0) {
                index-- ;
            }
            column = ((char)(index % 26 + 'A')) + column;
            index = (index - index % 26) / 26;
        }
        while (index > 0);
        return column;
    }

}
