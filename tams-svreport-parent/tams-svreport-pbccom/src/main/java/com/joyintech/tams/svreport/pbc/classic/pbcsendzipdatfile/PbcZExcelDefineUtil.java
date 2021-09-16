package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * excel操作 -- report的行，列配置初始化数据
 *
 * @author fxmei
 * @version 1.0 2021年08月10日
 * @see PbcZExcelDefineUtil
 * @since 1.0
 */
public class PbcZExcelDefineUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcZExcelDefineUtil.class);

    /**
     * 去。0的格式化
     */
    public static final DecimalFormat POINT = new DecimalFormat("###################.###########");

    /**
     * 数字表达式:整数/小数/负数
     */
    private static Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
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
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("deprecation")
    public static List<ChbTypeMappingEx> readToList(String reportType,File file, int sheetIndex) throws FileNotFoundException{
        List<ChbTypeMappingEx> ls = new ArrayList<ChbTypeMappingEx>();
        Workbook wb = loadWorkbook(null, file, null);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            String sheetName=sh.getSheetName();
            sheetName=sheetName.substring(1,4);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(0).getPhysicalNumberOfCells();
            Map<Integer,String> columnMap=new HashMap<>();
            for (int i = 0; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    break;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                String rowNum="";
                for (int c = 0; c < cells; c++ ) {
                    Cell cell1 = row.getCell(c);

                    if (row.getCell(c) == null
                        || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        //r.add("");
                    }
                    else {
                        dataFlag = false;
                        Cell cell=row.getCell(c);
                        String strValue=getStringCellValue(row.getCell(c));
                        if(c==0){
                            if(isNumeric(strValue)|| CommonUtil.isEnglish(strValue)){
                                rowNum=strValue;
                            }else {
                                break;
                            }
                        }else {
                            if(strValue.length()==1&&Character.isUpperCase(strValue.charAt(0))){ //获取列号
                                columnMap.put(cell.getColumnIndex(),strValue);
                            }else if (strValue.contains("#{") && strValue.contains("}")&&!strValue.contains("#{report")) {
                                String keyVal = strValue.substring(strValue.indexOf("#{") + 2, strValue.indexOf("}"));
                                String properyCode=keyVal.substring(0, keyVal.indexOf("."));
                                properyCode=properyCode.replace(" ", "");//去掉空格
                                String properyFiled=keyVal.substring(keyVal.indexOf(".")+1,keyVal.length());
                                properyFiled=properyFiled.replace(" ", "");//去掉空格
                                ChbTypeMappingEx ex=new ChbTypeMappingEx();
                                ex.setMappingid(UUID.randomUUID().toString());
                                ex.setMappingcode(reportType);
                                ex.setMappingname("MI");
                                ex.setObjfield("");//防止空异常
                                ex.setSrcfield1(properyFiled);
                                ex.setSrcfield(properyCode);
                                ex.setObjfield1(rowNum);
                                ex.setObjfield2(columnMap.get(cell.getColumnIndex()));
                                ls.add(ex);
                            }
                        }
                    }
                }
                // 遇到一行全为空结束
                if (dataFlag) {
                    break;
                }

            }
        }
        return ls;
    }
    /**
     * Excel文件到List从业人员特殊处理
     *
     * @param fileName 文件名
     * @param file 文件
     * @param inputStream 文件流
     * @param sheetIndex 工作表索引(下标从0开始)
     * @return 读取结果列表
     * @throws FileNotFoundException
     */
    @SuppressWarnings("deprecation")
    public static List<List<String>> readToListEMPLOYEE(String fileName, File file,
                                                        InputStream inputStream, int sheetIndex) throws FileNotFoundException{
        List<List<String>> ls = new ArrayList<List<String>>();
        Workbook wb = loadWorkbook(fileName, file, inputStream);
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(1).getPhysicalNumberOfCells();
            Row tRow = sh.getRow(1);
            // 列标题为空的话结束
            for (int c = 0; c < cells; c++ ) {
                if (tRow.getCell(c) == null
                        || StringUtil.isEmpty(getStringCellValue(tRow.getCell(c)))) {
                    cells = c;
                    break;
                }
            }
            //获取从业人员表头数据
            Row rowtitle = sh.getRow(0);
            if (rowtitle != null) {
                String issuePartyId = getStringCellValue(rowtitle.getCell(3));
                String employeeType = getStringCellValue(rowtitle.getCell(5));
                String issuePartyIdTitle = getStringCellValue(rowtitle.getCell(2));
                String employeeTypeTitle = getStringCellValue(rowtitle.getCell(4));

                //获取从业人员详情
                for (int i = 1; i < rows; i++ ) {
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

                    //将数据重新按照统一数据维护的顺序组装
                    if(i==1){
                        r.add(0,employeeTypeTitle);
                        r.add(3,issuePartyIdTitle);
                    }else{
                        r.add(0,employeeType);
                        r.add(3,issuePartyId);
                    }

                    ls.add(r);
                }
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
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("deprecation")
    public static List<List<String>> readToList(String fileName, File file,
                                                InputStream inputStream, int sheetIndex,
                                                int skipRows, int titleRow) throws FileNotFoundException{
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
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue(),
                        DateUtil.DEFAULT_ANOTHER_FORMAT);
                    return value;
                }
                value = POINT.format(cell.getNumericCellValue());
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
     * @param inputStream
     * @return wb
     * @throws FileNotFoundException,BusinessException 
     */
    public static Workbook loadWorkbook(String fileName, File file, InputStream inputStream) 
            throws FileNotFoundException{
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
			    try (POIFSFileSystem fs = new POIFSFileSystem(inputStream)){                
			        wb = new HSSFWorkbook(fs);
			        inputStream.close();
			    }
			    catch (Exception e) {
//                e.printStackTrace();
			        logger.error(e.getMessage(),e);
			    }

			}
			else if (fileName.toLowerCase().endsWith(OFFICE_EXCEL_2007_POSTFIX)) {
			    try {
			        wb = new XSSFWorkbook(inputStream);
			        inputStream.close();
			    }
			    catch (Exception e) {
//                e.printStackTrace();
			        logger.error(e.getMessage(),e);
			    }
			}
			else {
			    throw new BusinessException("不是一个有效的Excel文件");
			}
		} finally {
			try {
            	if(inputStream !=null)
            		inputStream.close();
            	if(wb !=null)
            		wb.close();
            } catch (IOException e) {
            	logger.error(e.getMessage(),e);
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
     * @throws FileNotFoundException 
     */
    public static String getSheetName(String fileName, File file, InputStream inputStream,
                                      int sheetIndex) throws FileNotFoundException {
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
            tempDataList = PbcZExcelDefineUtil.readToList(
                FileNameUtil.getUploadFilePath(flieName), null, null, 0, 0, 0);
        }
        catch (FileNotFoundException e) {
            logger.error(e.getMessage(),e);
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
    
    /**
     * 检查Excel模板是否正确
     *
     * @param flieName 上传文件名
     * @return headList 模板头list
     * @see
     * @since 1.0
     */
    public static List<ExcelValidDto> checkExcelTemp(List<List<String>> tempDataList, List<String> headList) {
        List<ExcelValidDto> tempCheckResult = new ArrayList<ExcelValidDto>();
        String errorMsg = "导入模板不一致，请重新选择文件！";
        ExcelValidDto excelValidDto = new ExcelValidDto();
        excelValidDto.setErrorInfo(errorMsg);
        tempCheckResult.add(excelValidDto);


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
    
    public static List<List<String>> getDataList(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = PbcZExcelDefineUtil.readToList(
                FileNameUtil.getUploadFilePath(fileName), null, null, 0, 1, 0);
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }
    
    public static List<List<String>> getDataList1(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = PbcZExcelDefineUtil.readToList(
                fileName, null, null, 0, 1, 0);
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }
    
    public static List<List<String>> getTempList(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = PbcZExcelDefineUtil.readToList(
                    FileNameUtil.getUploadFilePath(fileName), null, null, 0, 0, 0);
            
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }
    
    public static List<List<String>> getTempList1(String fileName){
        List<List<String>> dataList = null;
        try {
            dataList = PbcZExcelDefineUtil.readToList(
                    fileName, null, null, 0, 0, 0);
            
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(),e1);
        }
        return dataList;
    }

    /**
     * 判断是否是数字
     *
     * @param str 需要判断的字符串
     * @return boolean
     * @see
     * @since 1.0
     */
    public static boolean isNumeric(String str) {
        if (str != null && str.length() > 0) {
            Matcher m = NUMERIC_PATTERN.matcher(str);
            if (m.find()) {
                return true;
            }
        }
        return false;
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
