package com.joyintech.tams.disclosure.common.common.util;

import java.io.File;
import java.io.FileInputStream;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.dto.ExcelValidDto;

/**
 * excel操作
 *
 * @author daiyue
 * @version 1.0 2016年12月1日
 * @see ExcelUtil
 * @since 1.0
 */
public class ExcelUtil {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
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
        
      //update by leipng 2.1.9升级报错  目前暂无使用.注释掉
//        switch (cell.getCellTypeEnum()) {
//            case NUMERIC: // 数字
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    value = DateUtil.format(cell.getDateCellValue(),
//                        DateUtil.DEFAULT_ANOTHER_FORMAT);
//                    return value;
//                }
//                value = point.format(cell.getNumericCellValue());
//                break;
//            case STRING: // 字符串
//                value = cell.getStringCellValue();
//                break;
//            case FORMULA: // 公式
//                cell.setCellType(CellType.STRING);
//                value = cell.getStringCellValue();
//                break;
//            case BLANK: // 空值
//                break;
//            case BOOLEAN: // Boolean
//                break;
//            case ERROR: // 故障
//                break;
//            default:
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    value = DateUtil.format(cell.getDateCellValue());
//                }
//                break;
//        }

        return value;
    }

    /**
     * 读取Excel文件，支持2000与2007格式
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream
     * @return wb
     * @throws Exception yc
     */
    public static Workbook loadWorkbook(String fileName, File file, InputStream inputStream)
        throws Exception {
        Workbook wb = null;
        try {
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

            if (fileName.toLowerCase().endsWith(OFFICE_EXCEL_2003_POSTFIX)) {
                POIFSFileSystem fs = null;
                try {
                    fs = new POIFSFileSystem(inputStream);
                    wb = new HSSFWorkbook(fs);

                }
                catch (Exception e) {
                    logger.error("error:",e);
                }
                finally {
                    inputStream.close();
                    if(fs!=null)
                    {
                        fs.close();
                    }

                }

            }
            else if (fileName.toLowerCase().endsWith(OFFICE_EXCEL_2007_POSTFIX)) {
                try {
                    wb = new XSSFWorkbook(inputStream);

                }
                catch (Exception e) {
                    logger.error("error:",e);
                }
                finally {
                    inputStream.close();
                }
            }
            else {
                throw new Exception("不是一个有效的Excel文件");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null)
            {
                inputStream.close();
            }
        }


        return wb;
    }

    /**
     * 获取文件的sheet名称
     *
     * @param fileName 文件名
     * @param file 文件
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
     * 检查Excel模板是否正确
     *
     * @param flieName 上传文件名
     * @return headList 模板头list
     * @see
     * @since 1.0
     */
    public static List<ExcelValidDto> checkExcelTemp(String flieName, List<String> headList) {
        List<ExcelValidDto> tempCheckResult = new ArrayList<ExcelValidDto>();
        String errorMsg = "导入模板不一致，请重新选择文件！";
        ExcelValidDto excelValidDto = new ExcelValidDto();
        excelValidDto.setErrorInfo(errorMsg);
        tempCheckResult.add(excelValidDto);

        // 检查模板是否正确
        List<List<String>> tempDataList;
        try {
            tempDataList = ExcelUtil.readToList(
                FileNameUtil.getUploadFilePath(flieName), null, null, 0, 0, 0);
        }
        catch (Exception e) {
            // Excel解析异常
            return tempCheckResult;
        }

        if (StringUtil.isEmptyList(tempDataList)) {
            // Excel没有有效数据
            return tempCheckResult;
        }
        List<String> excelHeadList = tempDataList.get(0);
        if (excelHeadList.size() < headList.size()) {
            // Excel中列数少于要求列数
            return tempCheckResult;
        }

        for (int i = 0; i < headList.size(); i++ ) {
            if (!excelHeadList.get(i).contains(headList.get(i))) {
                // Excel中列不一致
                return tempCheckResult;
            }
        }

        return null;
    }
}
