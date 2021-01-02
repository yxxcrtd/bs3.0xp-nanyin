/**  
 * @Project     tams-invest-report  
 * @File        PalOpenMarketDealersMonthlyService.java  
 * @Package     com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.service  
 * @Version     V1.0  
 * @Date        2019年8月2日 下午2:35:54  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */  
    
package com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.report.common.util.ExcelUtil;
import com.joyintech.tams.invest.report.regulatoryreporting.palopenmarketdealersmonthly.dto.ChbBankMonthReportModelDto;
import com.joyintech.tams.valuation.accounadjustmanage.valuationtablegeneration.dto.ExportFileDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tools.util.ExcelCellValueFunction;
import com.joyintech.tools.util.ExcelTools;

/**  
 * @Description 公开市场业务一级交易商月报表业务逻辑层    
 * @ClassName   PalOpenMarketDealersMonthlyService  
 * @Date        2019年8月2日 下午2:35:54  
 * @Author      高健  
 * Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.  
 */
@Service
public class PalOpenMarketDealersMonthly001Service extends CommonExportService<ChbBankMonthReportModelDto>{
	
	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(PalOpenMarketDealersMonthly001Service.class);
    
    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";
	
	/**  
	 * @Description 导出EXCEL文件  
	 * @Author      高健  
	 * @Date        2019年8月3日 下午3:13:04  
	 * @param @return
	 * @param @throws Exception 参数  
	 * @return ResponseEntity<byte[]> 返回类型   
	 * @throws  
	 */  
	public ResponseEntity<byte[]> export(Map<String, Object> pageParam) throws Exception {
		ExportFileDto exportFileDto = createExcel(pageParam);
        File file = new File(exportFileDto.getFilePath());
        return ExportUtil.export(exportFileDto.getFileName(), file, true);
		
		
	}
	
	/**  
	 * @Description 生产EXCEL文件  
	 * @Author      高健  
	 * @Date        2019年8月6日 下午6:08:15  
	 * @param @param pageParam
	 * @param @return
	 * @param @throws ParseException 参数  
	 * @return ExportFileDto 返回类型   
	 * @throws  
	 */  
	private ExportFileDto createExcel(Map<String, Object> pageParam) throws ParseException {
		ChbBankMonthReportModelDto chbBankMonthReportModelDto = restTemplate.postForObject(ServiceApplication.REPORT_SERVICE_NAME + "/PalOpenMarketDealersMonthly/queryOpenMarketDealersMonthlyData",
				pageParam, ChbBankMonthReportModelDto.class);
		String statisticsMonth = (String) pageParam.get("statisticsMonth");
		statisticsMonth.replaceAll("-", "年");
		String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
        String fileName = "银行类公开市场业务一级交易商月报表(" + statisticsMonth + "月" + ").xls";
        String tempName = "PalOpenMarketDealersMonthly.xls";
        String tplFlodPath = "/regulatoryreporting/palopenmarketdealersmonthly/PalOpenMarketDealersMonthly.xls";
//        String tplPath = ExcelUtil.getTplFile(tplFlodPath);
        String outPath = exportFilePath + File.separator + fileName;
        ExportFileDto exportFileDto = new ExportFileDto();
        InputStream stream = ExcelUtil.getTplFile(tplFlodPath);
        Map<String, Object> map = new HashMap<>();
        map.put("orgname",chbBankMonthReportModelDto.getOrgname());
        map.put("date", chbBankMonthReportModelDto.getDate());
        map.put("modela", chbBankMonthReportModelDto.getAssets());
        exportFileDto.setFileName(fileName);
        exportFileDto.setFilePath(outPath);
        try {
            
            //针对weblogic环境部署，FileNameUtil.getTplFile无法获取tpl文件夹下模版。
//            File templeteFile = new File(tplPath);
//            //文件存在情况,直接该路径获取文件流
//            if (templeteFile.exists()) {
//                stream = new FileInputStream(templeteFile);
//            }
//            //文件不存在情况，即weblogic部署情况，需要换方式获取文件流。
//            else {
//                stream = this.getClass().getResourceAsStream(TMP_DIR + tplFlodPath);
//            }
            
            ExcelUtil.writeExcel(stream, tempName, map, outPath, new ExcelCellValueFunction() {
                
                @Override
                public void execute(String tplKey, Object value, Cell cell, Row row, Sheet sheet, Workbook workbook) {
                    
                    CellStyle newStyle = workbook.createCellStyle();
                    newStyle.cloneStyleFrom(cell.getCellStyle());
                }
            });
        } catch (Exception e) {
        	logger.error("",e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                	logger.error("",e);
                }
            }
        }
        return exportFileDto;
	}
	
	
}
