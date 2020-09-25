/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.reportmodelmanage.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.busicommon.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.OfficeMergeUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.reportmodelmanage.ReportModelManage001Dto;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ReportModelManage001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportModelManage001Service extends CommonExportService<ReportModelManage001Dto> {
    
    
//    @Autowired
//    private RepInfoMapper repInfoMapper;
//    @Autowired
//    private ReportModelManage001Mapper reportModelManage001Mapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(ReportModelManage001Service.class);

    /**
     * <一句话功能简述> 导出估值表
     * 
     * @param map
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<ReportModelManage001Dto> list)
        throws IOException {
        
            for (ReportModelManage001Dto reportModelManage001Dto : list) {
                createExcel(reportModelManage001Dto);
            }

            String exportFilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = FileNameUtil.createFileName("zip");
            File zipFile = new File(exportFilePath + File.separator + zipFileName);
            FileOutputStream stream = null;
            ZipOutputStream  zipOut =null;
            try{
                 stream = new FileOutputStream(zipFile);
                 zipOut = new ZipOutputStream(stream);
                 InputStream input = null;
                 
                 for (ReportModelManage001Dto dto : list) {
                     try{
                         zipOut.putNextEntry(new ZipEntry(dto.getName()));
                         input = new FileInputStream(dto.getTemLoad());
                         int tmp = 0;
                         while ((tmp = input.read()) != -1) {
                             zipOut.write(tmp);
                         }
                      }catch (Exception e) {
 //                         e.printStackTrace();
                          logger.error("error:",e);
                      }finally{
                          if(input!=null){
                              input.close();
                          }
                      } 
                }
                 
                zipOut.flush();
            }catch (Exception e) {
                logger.error("",e);
            }finally{
                if(zipOut!=null){
                    zipOut.close();
                }
                if(stream!=null){
                    stream.close();
                }
            }
            return ExportUtil.export(zipFileName, zipFile, true);
    }

    public void createExcel(ReportModelManage001Dto dto) throws IOException {
        
        
       // URL url = new URL(dto.getUrl());

        
       // String fileName = dto.getName() + "." + dto.getFileType();
    	
    	// 把前台传来的请求参数属性解析成Map集合
    	Map<String, Object> map = new HashMap<String, Object>();
//    	if(dto!= null) {
//    		if(dto.getParam()!=null) {
//    			if(dto.getParam().get("data")!=null) {
//    				if(StringUtils.isNotBlank(dto.getParam().get("data").toString())) {		
//    					String newParamStirng = dto.getParam().get("data").toString();
//    					if(StringUtils.isNotBlank(newParamStirng)){
//    					      Gson gson = new Gson();
//    					       map = gson.fromJson(newParamStirng, new TypeToken<Map<String, String>>() {
//    					        }.getType());
//    					}
//    				}
//    			}
//    		}
//    	}
//    	// 保险丝
//    	if(map!=null && dto!=null) {
//    		dto.setParam(map);
//    	}
        UploadFile uploadFile = null;
        try {
            if(dto!=null)
            {
                 uploadFile = jasparConnectUtilService.connectJasparAndExport(dto.getUrl(), dto.getCode(), dto.getFileType(),dto.getCode(),dto.getParam());
            }
//            URLConnection conn = url.openConnection();
//            // 防止屏蔽程序抓取而返回403错误
//            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//            InputStream inputStream = conn.getInputStream();
//            
//            // 获取自己数组
//            byte[] getData = readInputStream(inputStream);
//            
//
//            
//            String path = FileNameUtil.createUploadFilePath("");
//            
//            fileName = dto.getName()+GlobalConstant.UNDER_LINE + FileNameUtil.createFileName(
//                    FileNameUtil.getExtension(fileName));
//          
//              File localFile = new File(path+ File.separator+fileName);
//              File dir = localFile.getParentFile();
//              if (!dir.exists()) {
//                  dir.mkdirs();
//              }
//                  
//              FileOutputStream fos = new FileOutputStream(localFile);
//              fos.write(getData);
//              if (fos != null) {
//                  fos.close();
//              }
//              if (inputStream != null) {
//                  inputStream.close();
//              }
            if(uploadFile!=null){
                dto.setName(uploadFile.getSrcName());
                dto.setTemLoad(uploadFile.getTargetFile().getPath());
            }

        } catch (Exception e) {
 //           e.printStackTrace();
        	logger.error("error:",e);
        }
        
    }
        
        
        /**
         * 从输入流中获取字节数组
         * 
         * @param inputStream
         * @return
         * @throws IOException
         */
        public static byte[] readInputStream(InputStream inputStream) throws IOException {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
            return bos.toByteArray();
        }

        /**
         * 导出合并文件
         * 
         * @param val001dto
         * @return
         * @throws Exception 
         * @see
         * @since 1.0
         */
        public ResponseEntity<byte[]> mergeExport(List<ReportModelManage001Dto> list) throws Exception {
            List<String> srcFileList = new ArrayList<String>();
            for (ReportModelManage001Dto reportModelManage001Dto : list) {
                createExcel(reportModelManage001Dto);
                srcFileList.add(reportModelManage001Dto.getTemLoad());
            }
            String fileType = list.get(0).getFileType();
            String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String mergeExportFileName = FileNameUtil.createFileName(fileType);
            File exportFile = new File(exportFilePath + File.separator + mergeExportFileName);
            if("docx".equals(fileType)){
                OfficeMergeUtil.mergeWord(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }else if("xls".equals(fileType) || "xlsx".equals(fileType)){
            	//update by leipng 2.1.9升级报错  目前暂无使用.注释掉
//                OfficeMergeUtil.mergeExcel(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }
            return ExportUtil.export(mergeExportFileName, exportFile, true);
        }
        
        /**
         * 导出合并文件
         * 
         * @param val001dto
         * @return
         * @throws Exception 
         * @see
         * @since 1.0
         */
        public ResponseEntity<byte[]> mergeExportSingle(ReportModelManage001Dto reportModelManage001Dto) throws Exception {
        	//提前获取模板中文名称，用以获取下载文件名称，如果中文名称没有，就用英文名称
        	
        	Map<String,String> resultMap = new HashMap<>() ;
        	
        	Map<String,Object> map = new HashMap<>();

        	
        	String fileName ="";
        	File exportFile ;
            if(null!=reportModelManage001Dto.getParam()&&StringUtil.isNotEmpty(reportModelManage001Dto.getParam().get("data").toString())){
            	Gson gson = new Gson();
			       map = gson.fromJson(reportModelManage001Dto.getParam().get("data").toString(), new TypeToken<Map<String, Object>>() {
			        }.getType());
			    if(null!=map.get("portnum")){
			    	if(map.get("portnum") instanceof ArrayList){
			    		List<String> list = (List<String>)map.get("portnum");
				    	for (int i = 0; i < list.size(); i++) {
				    		resultMap.put(list.get(i), null);

						}
			    	}else{
			    		resultMap.put(map.get("portnum").toString(), null);
			    	}
			    }
			       
            }

            ZipOutputStream  zipOut =null;

        	for (Map.Entry<String, String> val : resultMap.entrySet()) {
        		map.put("portnum", val.getKey());
        		reportModelManage001Dto.getParam().putAll(map);
        		createExcel(reportModelManage001Dto);
        		val.setValue(reportModelManage001Dto.getTemLoad());
			}
        	
        	if(resultMap.entrySet().size()>1){
        		String uploadFilePath  = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH); 
        		fileName = FileNameUtil.createFileName("zip");
        		exportFile = new File(uploadFilePath+fileName);
        		zipOut = new ZipOutputStream(new FileOutputStream(exportFile));
//    			zipOut.setEncoding(ReportGlobalConstant.SYS_PARA_SALES_FILE_ENCODE);
    			for (Map.Entry<String, String> val : resultMap.entrySet()) {
            		reportModelManage001Dto.getParam().put("portnum", val.getKey());
    				compressFile2(zipOut, new File(val.getValue()),buildFileName(reportModelManage001Dto));
				}
    			zipOut.close();
            	
            }else{
            	fileName = buildFileName(reportModelManage001Dto);
            	exportFile=new File(reportModelManage001Dto.getTemLoad());///NB/GLJY
            }
      
            
//    		return ExportUtil.export(zipFileName, zipFile, true);
            return ExportUtil.export(fileName, exportFile, true);
        }
        
        
        public String buildFileName(ReportModelManage001Dto reportModelManage001Dto){
        	
        	String repName = null;
        	if(StringUtils.isNotBlank(reportModelManage001Dto.getName())) {
        		repName = reportModelManage001Dto.getName();
        	}else {
        		repName = reportModelManage001Dto.getCode();
        	}
        	
        	//文件类型
            String fileType =reportModelManage001Dto.getFileType();
            //生成的文件名
            //String mergeExportFileName = reportModelManage001Dto.getCode()+GlobalConstant.POINT+fileType;
            //如果获取不到就直接替代上去，以避免出现文件名为Null的情况
            if(StringUtils.isBlank((String)reportModelManage001Dto.getParam().get("repName"))) {
            	if(StringUtils.isNotBlank(repName)) {
            	reportModelManage001Dto.getParam().put("repName", repName);
            	}
            }
            String fileName =(String)reportModelManage001Dto.getParam().get("repName")+GlobalConstant.POINT+fileType;
            if(reportModelManage001Dto.getParam().get("endDate")!=null){
                fileName=reportModelManage001Dto.getParam().get("endDate")+"_"+fileName;
            }
            if(reportModelManage001Dto.getParam().get("portnum")!=null){
                fileName=reportModelManage001Dto.getParam().get("portnum")+"_"+fileName;
            }
        	
			return fileName;
        }
        
        
        public List<Map<String,String>> searchReports(Map<String,Object> params) throws Exception{
        	//后台批处理报表地址单独啦出来
    		String serverUrl = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_SERVER_URL);
    		//用户名
    		String serverUser = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_USERNAME);
    		//密码 
    		String serverPwd = CacheUtil.getSysParam(ReportGlobalConstant.REPORT_PASSWORD);
    		//路径 
    		String path = params.get("folderUri").toString();
    		
    		String url = jasparConnectUtilService.getReportsUrl(serverUrl, serverUser, serverPwd, path);
    		//
    		List<Map<String,String>> result = jasparConnectUtilService.connectJasparAndGenXML(url,false);
    		return result;
        }
        
        public List<ReportModelManage001Dto> searchReportsNew(Map<String,Object> params) throws Exception{
//    		List<ReportModelManage001Dto> result = reportModelManage001Mapper.searchReports(params);
    		
        	List<ReportModelManage001Dto> result =restTemplate.postForList(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage001/findAll",
                    params,ReportModelManage001Dto.class);
    		
    		
    		return result;
        }
        /**
    	 * 指标库查询service <一句话功能简述> <功能详细描述>
    	 * 
    	 * @param pageParam
    	 * @return
    	 */
    	public Page<ReportModelManage001Dto> findByPage(Map<String, Object> pageParam) throws Exception {
//    		return super.queryForPage(() -> reportModelManage001Mapper.searchReports(pageParam));
    		
    		return restTemplate.postForPage(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ReportModelManage001/findPage",
                    pageParam, ReportModelManage001Dto.class);
    		
    		
    	}
    	
    	
    	/**
         * 压缩报表信息
         * @param
         * @param
         * @param
		 * @throws IOException 
         */
		private void compressFile2(ZipOutputStream out, File sourcefile, String basedir) throws IOException{
	        if (sourcefile.isDirectory()) {
	        	File[] files = sourcefile.listFiles();
	        	
	        	if(files==null||files.length==0) {
					out.putNextEntry(new ZipEntry(basedir + File.separator));
				} 
	        	else {
					for(int i=0; i<files.length; i++) {
						compressFile2(out, files[i], files[i].getName());
					}
				}
	        }
	        else {
    			out.putNextEntry(new ZipEntry(basedir));

				FileInputStream fos = null;
				BufferedInputStream bis = null;
    	        try {
					 fos = new FileInputStream(sourcefile);
					 bis = new BufferedInputStream(fos);
					int len;
					byte[] buf = new byte[1024];
					while((len=bis.read(buf, 0, 1024)) != -1) {
						out.write(buf, 0, len);
					}
				}catch (Exception e)
				{

				}
				finally {
					bis.close();
					if(fos!=null)
					{
						fos.close();
					}

				}


        	}
		}
    	
}
