package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.handler.JoyinBookConfig;
import com.joyintech.framework.export.handler.JoyinCellConfig;
import com.joyintech.framework.export.handler.JoyinSheetConfig;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.logging.LoggingActionService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.framework.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetExport {

    /**
     * 日志记录开始时间
     */
    private ThreadLocal<Date> logDate = new ThreadLocal<>();

    /**
     * 导出总条数
     */
    private ThreadLocal<Long> exportSize = new ThreadLocal<>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TMP_DIR = "/tpl/";

    /**
     * 进度条ID
     */
    private static final String PROCESS_KEY = "exportProcessBar";

    /**
     * 进度条接口
     */
    @Autowired
    private ProgressBarService progressBarService;

    public File makeFile(String configFile, List<Map<String,Object>> contents,String outFileName){
        if (null == contents) {
            contents = Collections.emptyList();
        }
        beforeExprot();
        // 读取配置文件
        JoyinBookConfig reportConfig = getConfig(configFile);
        // 获取工作簿
        Workbook wb = null;
        try{
            wb = getWorkbook(reportConfig);
        }
        catch (IOException e){
            throw new BusinessException(e);
        }

        // 模板文件后缀名
        String fileType = GlobalConstant.POINT
                + FileNameUtil.getFileExtName(reportConfig.getTempleteFile());
        // 如果导出数据大于系统参数设置的excel行数
        int count = StringUtil.integerValueOf(
                CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_ROW_COUNT), 50000);
        File targetFile = null;
        List<File> fileList = new ArrayList<>(contents.size() / count + 1);
        beforeCreateExcel(contents);
        String date = DateUtil.format(new Date());
        try {
            int i=1;
            boolean flag = false;

            while (contents.size() > count) {
                List<Map<String,Object>> subList = contents.subList(0, count);
                contents = contents.subList(count, contents.size());
                String fileName = "";
                fileName = outFileName + GlobalConstant.OPEN_PARENTHESIS
                        + date +  GlobalConstant.SPLIT + i + GlobalConstant.CLOSE_PARENTHESIS;
                i++;
                fileList.add(makeSingleFile(wb, reportConfig, null, fileType, subList, new LongAdder(), 0L, count,fileName));
                flag = true;
            }
            String fileName = "";
            if (flag){
                fileName = outFileName + GlobalConstant.OPEN_PARENTHESIS
                        + date +  GlobalConstant.SPLIT + i + GlobalConstant.CLOSE_PARENTHESIS;
            }else {
                fileName = outFileName + GlobalConstant.OPEN_PARENTHESIS
                        + date + GlobalConstant.CLOSE_PARENTHESIS;
            }
            fileList.add(makeSingleFile(wb, reportConfig, null, fileType, contents, new LongAdder(), 0L, count,fileName));
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        finally {
            try {
                if (null != wb) {
                    wb.close();
                }
            }
            catch (Exception e) {
            }
        }
        // 如果只有一个excel文件，不需要生成压缩文件
        if (fileList.size() == 1) {
            targetFile = fileList.get(0);
        }
        else {
            String exportFilePath = CacheUtil.getSysParam(
                    GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = outFileName + GlobalConstant.OPEN_PARENTHESIS
                    + date + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT + "zip" ;
            targetFile = new File(exportFilePath, zipFileName);
            try {
                ZipFileUtil.compressZip(targetFile, true, fileList.toArray(new File[] {}));
            }
            catch (Exception e) {
                // 压缩文件失败后，删除所有临时文件
                for(File file : fileList) {
                    if(file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        afterExprot(null, contents.size());
        return targetFile;
    }

    public void afterExprot(String pageId, long total) {
        // 导出完成，从内存中移除当前画面的导出进度
        progressBarService.removePercent(pageId, PROCESS_KEY);

        // log
        LoggingActionService loggingActionService = SpringContextUtil.getBean(
                LoggingActionService.class);
        if (null != loggingActionService) {
            loggingActionService.LoggingImpExpInfo(total, "导出", logDate.get(), new Date());
        }
    }

    private File makeSingleFile(Workbook wb, JoyinBookConfig reportConfig, String pageId, String fileType,
                                List<Map<String,Object>> contents, LongAdder current, long total, int sheetMaxRowCount,String outFileName) throws IOException {
        ReportCfgHandler handler = new ReportCfgHandler();
        wb.close();
        wb = getWorkbook(reportConfig);
        // 配置文件中的工作表列表
        List<JoyinSheetConfig> sheetList = reportConfig.getSheetConfList();
        // 循环处理每个Sheet
        for (int i = 0; i < sheetList.size(); i++ ) {
            // 获取配置文件中的工作表配置
            JoyinSheetConfig sheetCfg = sheetList.get(i);
            // javaBean
            String beanName = sheetCfg.getJavaBean();
            // 获取配置文件中每行对应的单元格配置
            List<JoyinCellConfig> cellList = sheetCfg.getCellConfList();
            // 获取模板文件中的sheet页
            Sheet sheet = wb.getSheetAt(i);

            Boolean isNotNull = false;
            Iterator it = contents.iterator();
            while (it.hasNext()) {
                if (StringUtil.isNotEmpty(BeanUtil.object2Map(it.next()).get("belongInstitutionCode"))) {
                    isNotNull = true;
                    break;
                }
            }
            if (MultilevelDataAuthorityUtil.isEnabled() && isNotNull) {
                JoyinCellConfig ejfrConfig = new JoyinCellConfig();
                ejfrConfig.setOrder(cellList.size() + 1);
                ejfrConfig.setBeanPropName("belongInstitutionName");
                cellList.add(ejfrConfig);

                Cell oldcell = sheet.getRow(0).getCell(0);

                int rowStart = sheetCfg.getRowStart();
                int row = 0;
                if(rowStart == 0 || rowStart == 1){
                    row = 0 ;
                }else if(rowStart>1){
                    row = rowStart -1;
                }else{
                    // 提供默认值
                    row = 0;
                }

                Cell cell = sheet.getRow(row).createCell(sheet.getRow(row).getPhysicalNumberOfCells());
                cell.setCellStyle(oldcell.getCellStyle());
                cell.setCellValue("机构名称");

            }

            // 模板文件填充的开始行，默认从第二行开始。第一行预留为标题行
            int index = sheetCfg.getRowStart();
            // 模板文件最后一行
            Row lastRow = sheet.getRow(sheet.getLastRowNum());
            for (Map<String, Object> map : contents) {
                Row row = sheet.getRow(index++ );
                if (row == null) {
                    handler.copyRow(wb, lastRow, sheet.createRow(index - 1), false);
                    row = sheet.getRow(index - 1);
                }

                for (int ci = 0; ci < cellList.size(); ci++ ) {
                    JoyinCellConfig cfg = cellList.get(ci);
                    // 获取单元格
                    Cell cell = row.getCell(ci, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String propName = cfg.getBeanPropName();
                    if (StringUtil.isNotEmpty(propName)) {
                        Cell hCell = lastRow.getCell(ci);
                        if (null == cell && null != hCell) {
                            cell = row.createCell(ci);
                            handler.copyCell(wb, hCell, cell, false);
                            setCellStyle(wb, cell.getCellStyle());
                        }
                        Object value = map.get(propName);
                        if (StringUtil.isNotEmpty(value)) {
                            if (value instanceof Date) {
                                cell.setCellValue(DateUtil.formatDateTime((Date)value));
                            }
                            else if (value instanceof String) {
                                if (StringUtil.isNotEmpty(value)) {
                                    value = StringUtil.getLimitLengthString(value.toString(),
                                            16383, StringUtil.EMPTY);
                                }

                                if(StringUtil.equals("belongInstitutionName",propName)){
                                    cell.setCellStyle(row.getCell(0).getCellStyle());
                                }
                                cell.setCellValue((String)value);

                            }
                            else if (value instanceof Boolean) {
                                cell.setCellValue((Boolean)value);
                            }
                            else if (value instanceof Calendar) {
                                cell.setCellValue((Calendar)value);
                            }
                            else if (value instanceof RichTextString) {
                                cell.setCellValue((RichTextString)value);
                            }
                            else if (value instanceof Double) {
                                cell.setCellValue((Double)value);
                            }
                            else if (value instanceof BigDecimal) {
                                BigDecimal cellValue = (BigDecimal)value;
                                cell.setCellValue(cellValue.doubleValue());
                            }
                            else if (value instanceof Long) {
                                Long cellValue = (Long)value;
                                cell.setCellValue(cellValue.doubleValue());
                            }
                            else if (value instanceof Integer) {
                                Integer cellValue = (Integer)value;
                                cell.setCellValue(cellValue.doubleValue());
                            }
                            // 自定义的特殊处理
                            setCellValue(beanName, cell, propName, value);
                        }
                    }
                }

            }
        }

        //市场日历导出数量与实际不相符
        if(null != pageId && total > 0) {
            current.increment();
            progressBarService.updatePercent(pageId, PROCESS_KEY, current.doubleValue() / total, false);
        }

        // 从系统配置参数中获取文件存放路径
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        if(StringUtil.isNotEmpty(fileType)) {
            if(!fileType.startsWith(GlobalConstant.POINT)) {
                fileType = GlobalConstant.POINT + fileType;
            }
            outFileName = outFileName + fileType;
        }
        File file = new File(exportFilePath, outFileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        wb.write(new FileOutputStream(file));
        return file;
    }

    private Workbook getWorkbook(JoyinBookConfig reportConfig)
            throws IOException{
        // 工作表对象
        Workbook wb = null;
        String EXPORT_EXCEL_TYPE = CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_TYPE);
        // 模板文件后缀名
        String fileType = GlobalConstant.FILE_POSTFIX_XLSX;
        String fileName = StringUtil.splitToList(GlobalConstant.POINT, reportConfig.getTempleteFile()).get(0);
        if(StringUtil.equals(EXPORT_EXCEL_TYPE, DictCodeList.是否_否.getValue())){

            // TODO 由于数据库配置了导出文件的的后缀是 .xls，但是 \tams-svreport-parent\tams-svreport-chb\src\main\java\com\joyintech\tams\svreport\chb\assetinforeg\conf\export_conf.xml 定义的列数超过了 256，并且配置不起作用，所以这里暂时强制以 .xlsx 作为文件的后缀
            if (!reportConfig.getTempleteFile().startsWith("AssetInfoRegist.xlsx")) {
                fileType = GlobalConstant.FILE_POSTFIX_XLS;
            }
        }
        // 从配置文件中获取模板文件  以及文件类型
        String tempFilePath = fileName + fileType;
        File templeteFile = new File(buildTempleteFilePath(tempFilePath));
        reportConfig.setTempleteFile(fileName + fileType);
        // 模板输入流
        InputStream stream = null;
        if (templeteFile.exists()) {
            stream = new FileInputStream(templeteFile);
        }
        else {
            stream = CommonExportService.class.getResourceAsStream(
                    TMP_DIR + tempFilePath);
        }
        // 工作表对象初始化
        if (StringUtil.equals(fileType, GlobalConstant.FILE_POSTFIX_XLS)) {
            wb = new HSSFWorkbook(stream);
        }
        else if (StringUtil.equals(fileType, GlobalConstant.FILE_POSTFIX_XLSX)) {
            wb = new XSSFWorkbook(stream);
        }
        else {
            stream.close();
            throw new BusinessException("不是一个有效的Excel文件");
        }
        return wb;
    }

    public String buildTempleteFilePath(String templateFile) {
        return FileNameUtil.getTplFile(templateFile);
    }

    private JoyinBookConfig getConfig(String configFile){
        ReportCfgHandler handler = new ReportCfgHandler();
        // 读取配置文件
        JoyinBookConfig reportConfig = null;
        try {
            reportConfig = handler.readConfig(configFile);
        }
        catch (Exception e){
            throw new BusinessException(e);
        }
        return reportConfig;
    }

    public void beforeExprot() {
        logDate.set(new Date());
        exportSize.set(0L);
    }

    public void setCellValue(String beanName, Cell cell, String propName, Object value) {

    }

    public void setCellStyle(Workbook wb, CellStyle cellStyle) {

    }

    protected void beforeCreateExcel(List<Map<String,Object>> contents) {
    }

}
