package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.handler.JoyinBookConfig;
import com.joyintech.framework.export.handler.JoyinCellConfig;
import com.joyintech.framework.export.handler.JoyinSheetConfig;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest003Dto;

/**
 * 触发统计Service
 *
 * @author 王贵奇
 * @version 1.0 2019/10/23
 * @see
 * @since 1.0
 */
@Service
public class PtlAnalyzeAfterInvest003Service extends CommonExportService<PtlAnalyzeAfterInvest003Dto> {
	
    /**
     * 进度条接口
     */
    @Autowired
    private ProgressBarService progressBarService;

    /**
     * 导出
     * @return ResponseEntity<byte[]>
     * @throws Exception
     */
	public ResponseEntity<byte[]> export(Map<String, Object> selectParam) throws IOException {
		String pageId = (String) selectParam.get("pageId");
		String datas = (String) selectParam.get("data");
		List<PtlAnalyzeAfterInvest003Dto> dtos = new ArrayList<PtlAnalyzeAfterInvest003Dto>();
		if(datas != null) {
			String[] data =  datas.split(" ");
			for(int i = 0; i<data.length; i++) {
				String[] attributes = data[i].split("\\+");
				PtlAnalyzeAfterInvest003Dto dto = new PtlAnalyzeAfterInvest003Dto();
				dto.setTriggerDate(attributes[0]);
				dto.setTriggerPirce(new BigDecimal(attributes[1]));
				dto.setCloseLine(new BigDecimal(attributes[2]));
				dto.setWarningLine(new BigDecimal(attributes[3]));
				dto.setStatement(attributes[4]);
				dtos.add(dto);
			}
		} 
		
		String configFile = "/com/joyintech/tams/invest/portfolio/ptlanalyzeafterinvest/conf/export_conf003.xml";
		// 读取配置文件
        JoyinBookConfig reportConfig = getConfig(configFile);
        // 文件后缀
		String fileType = GlobalConstant.POINT + FileNameUtil.getFileExtName(reportConfig.getTempleteFile());
		File outFile = makeSingleFile(getWorkbook(reportConfig), reportConfig, pageId, fileType, dtos, null, 0, 0);

		String exportFileName = SpringContextUtil.getTextValue(pageId)
                + GlobalConstant.OPEN_PARENTHESIS + DateUtil.getCurrentDate()
                + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT
                + FileNameUtil.getFileExtName(outFile);
		return ExportUtil.export(exportFileName, outFile, true);
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
            		"/tpl/" + tempFilePath);
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
                                cell.setCellValue((Calendar)value);
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
                    progressBarService.updatePercent(pageId, "exportProcessBar", current.doubleValue() / total, false);
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
    
}
