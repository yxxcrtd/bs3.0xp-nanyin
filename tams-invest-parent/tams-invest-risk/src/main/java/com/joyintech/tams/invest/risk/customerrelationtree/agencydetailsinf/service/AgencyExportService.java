/*
 * 文件名：AgencyExportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.export.ExportService;
import com.joyintech.framework.export.handler.JoyinBookConfig;
import com.joyintech.framework.export.handler.JoyinCellConfig;
import com.joyintech.framework.export.handler.JoyinSheetConfig;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.logging.LoggingActionService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;

/**
 * 机构详细信息导出service
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年12月31日
 * @see AgencyExportService
 * @since 1.0
 */
public class AgencyExportService<T extends ITableRow> extends BaseService<T> implements ExportService {
	/**
     * 日志记录开始时间
     */
    private ThreadLocal<Date> logDate = new ThreadLocal<>();

    /**
     * 导出总条数
     */
    private ThreadLocal<Long> exportSize = new ThreadLocal<>();

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
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

    /**
     * 模板文件路径处理
     *
     * @param templateFile 模板文件名
     * @return 模板文件路径
     */
    public String buildTempleteFilePath(String templateFile) {
        return FileNameUtil.getTplFile(templateFile);
    }

    /**
     * 文件导出
     *
     * @param pageId 画面ID
     * @return 导出对象
     */
    @Override
    public ResponseEntity<byte[]> export(String pageId, Map<String, Object> selectParam){
        beforeExprot();
        // 判断当前画面的导出进度是否正在处理
        if(progressBarService.isProcessing(pageId, PROCESS_KEY)) {
            throw new BusinessException("当前画面正在执行导出操作，请耐心等待！");
        }
        // 设置当前画面进度为0
        progressBarService.updatePercent(pageId, PROCESS_KEY, 0D, false);
        //updatePercent(pageId, 0D);
        ExportParam param = new ExportParam(selectParam);
        buildExportParam(param);
        if (StringUtil.isEmpty(param.getConfigFile())/* || param.getSelect() == null*/) {
            throw new BusinessException("导出参数不能为空，请重写buildExportParam方法，以指定[数据获取接口]和[配置文件路径]！");
        }
        // 切换数据源
        DynamicDataSourceHolder.putDataSource(param.getDataSource());
        // 生成导出文件
        File outFile = null;
        try {
            outFile = export(param.getSelect(), param.getConfigFile(), pageId, selectParam);
        }
        catch (Exception e) {
            progressBarService.removePercent(pageId, PROCESS_KEY);
            throw new BusinessException(e);
        }
        // 还原数据源
        DynamicDataSourceHolder.clearDataSource();
        // 导出文件名
        String exportFileName = pageId;
        com.joyintech.framework.base.intf.Page page = CacheUtil.getPage(pageId);
        if(null != page) {
            exportFileName = (StringUtil.isNotEmpty(selectParam.get("fileName")) ? selectParam.get("fileName").toString():"") 
            				 + "详细信息"
                             + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(DateUtil.platDateFromCache())
                             + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                             + FileNameUtil.getFileExtName(outFile);
        }
        afterExprot(pageId, exportSize.get());
        // 执行导出
        return ExportUtil.export(exportFileName.trim(), outFile, true);
    }

    /**
     * 根据配置文件生成报表文件
     *
     * @param configFile 配置文件
     * @param contents 需要被生成的报表内容
     * @return 报表文件
     */
    public File makeFile(String configFile, List<T> contents){
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
        try {
            while (contents.size() > count) {
                List<?> subList = contents.subList(0, count);
                contents = contents.subList(count, contents.size());
                fileList.add(makeSingleFile(wb, reportConfig, null, fileType, subList, new LongAdder(), 0L, count));
            }
            fileList.add(makeSingleFile(wb, reportConfig, null, fileType, contents, new LongAdder(), 0L, count));
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
            String zipFileName = FileNameUtil.createFileName("zip");
            targetFile = new File(exportFilePath + File.separator + zipFileName);
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

    /**
     * excel文件生成之前调用
     */
    public void beforeExprot() {
        logDate.set(new Date());
        exportSize.set(0L);
    }

    /**
     * excel文件生成之前调用
     * @param pageId 画面ID
     * @param total 数据列表
     */
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

    /**
     * 设置单元格值
     * <p>
     * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
     * </p>
     *
     * @param beanName sheet对应的JavaBean
     * @param cell 单元格
     * @param propName 属性名
     * @param value 单元格的值
     */
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {

    }

    /**
     * 设置单元格样式
     *
     * @param wb Workbook
     * @param cellStyle 单元格样式
     */
    public void setCellStyle(Workbook wb, CellStyle cellStyle) {

    }

    /**
     * 设置导出参数
     *
     * @param exportParam 导出参数
     */
    protected void buildExportParam(ExportParam exportParam) {
    }

    /**
     * excel文件生成之前调用
     *
     * @param contents 数据列表
     */
    protected void beforeCreateExcel(List<T> contents) {
    }

    /**
     * 该方法会调用查询接口自动查询数据并生成导出对象<br>
     * 方法中自动判断查询接口的数据量大小，如果超过系统参数指定的 按照系统参数指定的文件存储量分批获取数据，生成excel文件
     *
     * @param select 数据查询接口
     * @param configFile 导出配置文件路径
     * @param pageId 画面ID
     * @return 生成后的文件
     * @throws IOException 异常信息
     */
    private File export(ISelect select, String configFile, String pageId, Map<String, Object> selectParam)
       throws IOException  {
        // 已导出条数
        LongAdder current = new LongAdder();
        // 总数据量
        long total = 0;
        // 开始页
        int pageNum = 1;
        // 每页查询数量（系统参数设置的excel行数）
        int pageSize = StringUtil.integerValueOf(CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_ROW_COUNT), 50000);
        // 读取配置文件
        JoyinBookConfig reportConfig = getConfig(configFile);
        // 获取工作簿
        Workbook wb = getWorkbook(reportConfig);
        // 模板文件后缀名
        String fileType = GlobalConstant.POINT
                          + FileNameUtil.getFileExtName(reportConfig.getTempleteFile());
        // 目标文件
        File targetFile = null;
        Page<T> page = null;
        List<File> sourceFiles = new ArrayList<>();
        try {
            do {
                page = selectPageData(pageNum++ , pageSize, select, total == 0, selectParam);
                if (total == 0) {
                    total = page.getPages();
                    exportSize.set(page.getTotal());
                }
                beforeCreateExcel(page.getResult());
                File tmpFile = makeSingleFile(wb, reportConfig, pageId, fileType, page, current, total, pageSize);
                sourceFiles.add(tmpFile);
            }
            while (current.longValue() < Math.min(total, 100));
        }
        catch (Exception e) {
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

        if (sourceFiles.isEmpty()) {
            throw new BusinessException("无数据");
        }
        // 如果生成excel文件个数大于1，生成压缩文件
        if (sourceFiles.size() > 1) {
            String exportFilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = FileNameUtil.createFileName("zip");
            targetFile = new File(exportFilePath + File.separator + zipFileName);
            try {
                ZipFileUtil.compressZip(targetFile, true, sourceFiles.toArray(new File[] {}));
            }
            catch (Exception e) {
                // 压缩文件失败后，删除所有临时文件
                for (File file : sourceFiles) {
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }
        else {
            targetFile = sourceFiles.get(0);
        }
        return targetFile;
    }



    /**
     * 根据配置文件生成报表文件
     *
     * @param wb 工作簿对象
     * @param reportConfig 配置文件
     * @param pageId 画面ID
     * @param fileType 文件类型
     * @param contents 需要被生成的报表内容
     * @param current 已导出条数
     * @param total 需要被导出的总条数
     * @param sheetMaxRowCount 一个Sheet中最大行
     * @return 报表文件
     * @throws IOException 异常
     */
    private File makeSingleFile(Workbook wb, JoyinBookConfig reportConfig, String pageId, String fileType,
                                List<?> contents, LongAdder current, long total, int sheetMaxRowCount)
        throws IOException {
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
            // 模板文件填充的开始行，默认从第二行开始。第一行预留为标题行
            int index = sheetCfg.getRowStart();
            // // 模板文件当前sheet中的最大行
            // int maxRowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
            // // 模板文件当前sheet中的最大列
            // int maxColNum = sheet.getRow(sheet.getFirstRowNum()).getPhysicalNumberOfCells();
            // 模板文件最后一行
            Row lastRow = sheet.getRow(sheet.getLastRowNum());
            for (Object dto : contents) {
                Map<String, Object> map = BeanUtil.object2Map(dto);
                Row row = sheet.getRow(index++ );
                if (row == null) {
                    handler.copyRow(wb, lastRow, sheet.createRow(index - 1), false);
                    row = sheet.getRow(index - 1);
                }

                for (int ci = 0; ci < cellList.size(); ci++ ) {
                    JoyinCellConfig cfg = cellList.get(ci);
                    // 获取单元格
                    Cell cell = row.getCell(ci, MissingCellPolicy.CREATE_NULL_AS_BLANK);
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
                            	if (null != cell)cell.setCellValue(DateUtil.formatDateTime((Date)value));
                            }
                            else if (value instanceof String) {
                                if (StringUtil.isNotEmpty(value)) {
                                    value = StringUtil.getLimitLengthString(value.toString(),
                                        16383, StringUtil.EMPTY);
                                }
                                if (null != cell)cell.setCellValue((String)value);
                            }
                            else if (value instanceof Boolean) {
                            	if (null != cell)cell.setCellValue((Boolean)value);
                            }
                            else if (value instanceof Calendar) {
                            	if (null != cell)cell.setCellValue((Calendar)value);
                            }
                            else if (value instanceof RichTextString) {
                            	if (null != cell)cell.setCellValue((RichTextString)value);
                            }
                            else if (value instanceof Double) {
                            	if (null != cell)cell.setCellValue((Double)value);
                            }
                            else if (value instanceof BigDecimal) {
                                BigDecimal cellValue = (BigDecimal)value;
                                if (null != cell)cell.setCellValue(cellValue.doubleValue());
                            }
                            else if (value instanceof Long) {
                                Long cellValue = (Long)value;
                                if (null != cell)cell.setCellValue(cellValue.doubleValue());
                            }
                            else if (value instanceof Integer) {
                                Integer cellValue = (Integer)value;
                                if (null != cell)cell.setCellValue(cellValue.doubleValue());
                            }
                            // 自定义的特殊处理
                            setCellValue(beanName, cell, propName, value);
                        }
                    }
                }
                if(null != pageId && total > 0) {
                    current.increment();
                    progressBarService.updatePercent(pageId, PROCESS_KEY, current.doubleValue() / total, false);
                }
            }
        }

        // 生成后的文件名
        String outFileName = FileNameUtil.createFileName(fileType);
        // 从系统配置参数中获取文件存放路径
        String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        File file = new File(exportFilePath + File.separator + outFileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        wb.write(new FileOutputStream(file));
        return file;
    }

    /**
     * 解析配置文件
     *
     * @param configFile 配置文件
     * @return JoyinBookConfig
     */
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

    /**
     * 获取配置文件指向的工作簿
     *
     * @param reportConfig 配置文件
     * @return Workbook对象
     * @throws IOException 异常
     */
    private Workbook getWorkbook(JoyinBookConfig reportConfig)
        throws IOException{
        // 工作表对象
        Workbook wb = null;
        String EXPORT_EXCEL_TYPE = CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_TYPE);
        // 模板文件后缀名
        String fileType = GlobalConstant.FILE_POSTFIX_XLSX;
        String fileName = StringUtil.splitToList(GlobalConstant.POINT, reportConfig.getTempleteFile()).get(0);
        if(StringUtil.equals(EXPORT_EXCEL_TYPE, DictCodeList.是否_否.getValue())){
        	fileType = GlobalConstant.FILE_POSTFIX_XLS;
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

    /**
     * 分页查询数据
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @param select 数据查询接口
     * @param count 是否需要count查询
     * @param selectParam 查询参数
     * @return Page
     */
    private Page<T> selectPageData(int pageNum, int pageSize, ISelect select, boolean count, Map<String, Object> selectParam) {
        selectParam.put("page", pageNum);
        selectParam.put("length", pageSize);
        com.joyintech.framework.grid.Page<T> p = selectPageData(selectParam);
        Page<T> page = null;
        if(p == null) {
            page = selectPageData(pageNum, pageSize, select, count);
        }
        else {
            page = new Page<T>(pageNum, pageSize);
            page.setTotal(p.getTotal());
            page.addAll(p.getRoot());
        }
        return page;
    }

    /**
     * 分页查询数据
     *
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @param select 数据查询接口
     * @param count 是否需要count查询
     * @return Page
     */
    private Page<T> selectPageData(int pageNum, int pageSize, ISelect select, boolean count) {
        return PageHelper.startPage(pageNum, pageSize).count(count).doSelectPage(select);
    }

    /**
     * 分页查询数据
     * @param selectParam
     * @return Page
     */
    public com.joyintech.framework.grid.Page<T> selectPageData(Map<String, Object> selectParam) {
        return null;
    }
}
