package com.joyintech.tams.invest.trade.investtrade.investdealquery.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;
import com.joyintech.tams.invest.trade.investtrade.investdealquery.dto.TrdDealQueryDto;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 转移定价审批表service
 * 〈功能详细描述〉
 * @author  wj
 * @version 1.0 2020年4月20日
 * @see AssetTransferExport001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AssetTransferExport001Service  extends CommonExportService<AssetTransferExport001Dto>{

    private Logger logger = LoggerFactory.getLogger(AssetTransferExport001Service.class);
    
    public static   String[] titles = {"outprodName","inProdName","assetName","assetId","tradeDate","unitCprice","unitFprice","tradeAmt","fpriceAmt","yield"};

    
    /**
	 * 交易查询service
	 */
	@Autowired
	private TrdInvestDealQuery001Service trdInvestDealQuery001Service;
	
	/**
     * 进度条接口
     */
    @Autowired
    private ProgressBarService progressBarService;
    
    /**
     * 进度条ID
     */
    private static final String PROCESS_KEY = "exportProcessBar";
    
    /**
     * 导出 
     */
 	@Override
 	protected void buildExportParam(ExportParam exportParam) {
 		// TODO Auto-generated method stub
 		exportParam.config(
 				"/com/joyintech/tams/invest/trade/investtrade/investdealquery/conf/export_conf_transexp.xml").dataSource(DataSourceType.MASERT);
 	}

    /**
	 * 查询导出数据
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<AssetTransferExport001Dto> selectForPage(Map<String, Object> pageParam){
		
		//勾选导出
		if (StringUtil.isNotEmpty(pageParam.get("tradeIds"))){
			List<String> selectedItem = StringUtil.splitToList(",",(String)pageParam.get("tradeIds"));
			if(selectedItem.size() > 0){
				pageParam.put("tradeIds",selectedItem);
			}
		}

		
        Page<AssetTransferExport001Dto> returnList = new Page<AssetTransferExport001Dto>(); 
		
		returnList = restTemplate.postForPage(
	                ServiceApplication.TRADE_SERVICE_NAME + "/AssetTransferExport001/selectTransExportPage", pageParam,
	                AssetTransferExport001Dto.class);
		
		return returnList;
	}
	
    
    /**
	 * 
	 */
	@Override
	public Page<AssetTransferExport001Dto> selectPageData(Map<String, Object> selectParam) {
		// TODO Auto-generated method stub
		return selectForPage(selectParam);
	}

	/**
	 * 生成单条 zip文件
	 * @param pageId
	 * @param dto
	 * @return
	 */
	public ResponseEntity<byte[]> newSingleExportExcel(String pageId,TrdDealQueryDto dto) {
		 progressBarService.removePercent(pageId, PROCESS_KEY);

		 beforeExprot();

        // 判断当前画面的导出进度是否正在处理
        if(progressBarService.isProcessing(pageId, PROCESS_KEY)) {
            throw new BusinessException("当前画面正在执行导出操作，请耐心等待！");
        }
        // 设置当前画面进度为0
        progressBarService.updatePercent(pageId, PROCESS_KEY, 0D, false);
	        
//		// TODO Auto-generated method stub
//		Page<AssetTransferExport001Dto> page = selectForPage(pageParam);
//		
//		List<AssetTransferExport001Dto> list = page.getRoot();
//		
//		List<Map<String,Object>> dataList = trdInvestDealQuery001Service.getColumnMap(list);
		
        List<Map<String,Object>> dataList = new ArrayList<>();
		try{
			Map<Object, Object> data = trdInvestDealQuery001Service.createData(dto);
        
		    dataList = (List<Map<String, Object>>)data.get("lists");
		
		}catch(Exception e){
			progressBarService.removePercent(pageId, PROCESS_KEY);
            throw new BusinessException(e);
		}
		
        String template = "AssetTransferExport001";
        
		return exportNewZip(pageId,dataList,template);
	}

	
	/**
	 * 生成文件并 压缩下载
	 * @param pageId
	 * @param dataList
	 * @param template
	 * @return
	 */
	private ResponseEntity<byte[]> exportNewZip(String pageId,List<Map<String, Object>> dataList, String template) {
		
		List<File> fileList = new ArrayList<>();
		
        try{
	        for(Map<String, Object> mapDto: dataList){ 
	        	
	        	String dateStr = DateUtil.format(DateUtil.now(),DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1);
		        String filename =  "转移定价审批表("+dateStr+").xls";;
	        	File singleFile = createFile(mapDto,filename,template);
	        	fileList.add(singleFile);
	        	
	        }
        }catch(Exception e) {
            progressBarService.removePercent(pageId, PROCESS_KEY);
            throw new BusinessException(e);
        }   
        afterExprot(pageId, fileList.size());
        
    	String date = DateUtil.format(DateUtil.now(),DateUtil.DEFAULT_DATE_FORMAT_CN);
        
		return trdInvestDealQuery001Service.exportZip(fileList, "转移定价审批表("+date+").zip");
	}

	/**
	 * 创建文件
	 * @param mapDto
	 * @param flieName
	 * @param template
	 * @return
	 */
	private File createFile(Map<String, Object> mapDto, String flieName, String template) {
		
//		String flieName =  termname +DateUtil.format(DateUtil.now(),DateUtil.DEFAULT_DATETIME_FORMAT_SSS) + ".xls";
		
        File depositfile = null;
        Workbook workbook = null;
		OutputStream os = null;
        try {

            // 传放地址
            String depositfilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            depositfile = new File(depositfilePath + File.separator + flieName);
            if (!depositfile.getParentFile().exists()) {
                depositfile.getParentFile().mkdirs();
            }
            String templateFile = template + ".xls";
            // 模板输入流
            InputStream stream  = null;
            
            String tplFile = FileNameUtil.getTplFile(templateFile);
            File templeteFile = new File(tplFile);
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            else {
                stream = CommonExportService.class.getResourceAsStream(
                        "/tpl/" + templateFile);
            }
            
           // 工作表对象初始化
            workbook = new HSSFWorkbook(stream);
            Sheet sheet = workbook.getSheetAt(0);
            
            for(int i = 0;i< titles.length ;i++){
            	 
            	 Row row = sheet.getRow(i+1);
            	 if(row == null){
            		 row = sheet.createRow(i+1);
            	 }
            	if(mapDto.containsKey(titles[i])){
            		
            		Cell cell = row.createCell(1);
            		cell.setCellValue(String.valueOf(mapDto.getOrDefault(titles[i], "")));
            		
            	}
            }

            os = new BufferedOutputStream(new FileOutputStream(depositfilePath + File.separator + flieName));
            workbook.write(os); 

        }
        catch (Exception e) {
            logger.error(template + "生成失败！");
            throw new RuntimeException(e);
        }finally{
        	try {
                if (null != workbook) {
                	workbook.close();
                }
            }
            catch (Exception e) {
            	logger.error("workbook关闭失败！");
            }
			try {
				if (null != os) {
					os.flush();
					os.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
        }
        return depositfile;
	}

	/**
	 * 
	 * @param pageId
	 * @param dto
	 * @return
	 */
	public ResponseEntity<byte[]> newBatchExportExcel(String pageId,TrdDealQueryDto dto) {
		progressBarService.removePercent(pageId, PROCESS_KEY);

		beforeExprot();
        // 判断当前画面的导出进度是否正在处理
        if(progressBarService.isProcessing(pageId, PROCESS_KEY)) {
            throw new BusinessException("当前画面正在执行导出操作，请耐心等待！");
        }
        // 设置当前画面进度为0
        progressBarService.updatePercent(pageId, PROCESS_KEY, 0D, false);
        

		Map<Object, Object> data = trdInvestDealQuery001Service.createData(dto);
		
        String termname = "转移定价审批表" +DateUtil.format(DateUtil.now(),DateUtil.DEFAULT_DATETIME_FORMAT_SSS) + ".xls";
        
        String template = "AssetTransferExport001";
        File outFile = null;
        try{
        	List<Map<String, Object>> batchLists = (List<Map<String, Object>>)data.get("lists");
		        
        	outFile = createBatchFile(batchLists, termname,  template);
        }catch (Exception e) {
            progressBarService.removePercent(pageId, PROCESS_KEY);
            throw new BusinessException(e);
        }
        
        afterExprot(pageId, 1L);

		return ExportUtil.export(termname, outFile, true);
	}

	
	/**
	 *  创建批量文件。单个
	 * @param batchLists
	 * @param termname
	 * @param template
	 * @return
	 */
	private File createBatchFile(List<Map<String, Object>> batchLists, String flieName, String template) {
		
		if(StringUtil.isEmptyList(batchLists)){
			return null;
		}
		
//		String flieName =  termname +DateUtil.format(DateUtil.now(),DateUtil.DEFAULT_DATETIME_FORMAT_SSS) + ".xls";
        File depositfile = null;
        Workbook workbook = null;
		OutputStream os =null;
        try {
            // 传放地址
            String depositfilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            depositfile = new File(depositfilePath + File.separator + flieName);
            if (!depositfile.getParentFile().exists()) {
                depositfile.getParentFile().mkdirs();
            }
            String templateFile = template + ".xls";
            // 模板输入流
            InputStream stream  = null;
            
            String tplFile = FileNameUtil.getTplFile(templateFile);
            File templeteFile = new File(tplFile);
            if (templeteFile.exists()) {
                stream = new FileInputStream(templeteFile);
            }
            else {
                stream = CommonExportService.class.getResourceAsStream(
                        "/tpl/" + templateFile);
            }
            
           // 工作表对象初始化
            workbook = new HSSFWorkbook(stream);
            Sheet sheet = workbook.getSheetAt(0);
            
            // 从第二行第二列开始循环
            //竖列生成execl 先循环数据 确定是在第几列 
            for(int i = 0;i<batchLists.size();i++){
            	Map<String, Object> mapDto = batchLists.get(i);
            	
            	int colum = i+1;
            	for(int j = 0;j< titles.length ;j++){
            		//从第二行开始写
            		Row row = sheet.getRow(j+1);
	               	if(row == null){
	               		row = sheet.createRow(j+1);
	               	}
	               	if(mapDto.containsKey(titles[j])){
	            		
	            		Cell cell = row.createCell(colum);
	            		cell.setCellValue(String.valueOf(mapDto.getOrDefault(titles[j], "")));
	            		
	            	}
            	}
            }

			os = new BufferedOutputStream(new FileOutputStream(depositfilePath + File.separator + flieName));
            workbook.write(os); 

        }
        catch (Exception e) {
            logger.error(template + "生成失败！");
            throw new RuntimeException(e);
        }finally{
        	try {
                if (null != workbook) {
                	workbook.close();
                }
            }
            catch (Exception e) {
            	logger.error("workbook关闭失败！");
            }
			try {
				if (null != os) {
					os.flush();
					os.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
        }
        return depositfile;
	}

	
}
