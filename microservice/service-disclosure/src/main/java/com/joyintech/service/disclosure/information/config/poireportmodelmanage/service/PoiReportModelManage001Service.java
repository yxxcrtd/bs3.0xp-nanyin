/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.poireportmodelmanage.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.service.disclosure.information.config.poireportmodelmanage.dao.PoiReportModelManage001Mapper;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.ReportModelManage001Dto;
import com.joyintech.service.disclosure.information.config.reportmodelmanage.dao.ReportModelManage001Mapper;
import com.joyintech.tams.common.base.dao.RepInfoMapper;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.OfficeMergeUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see PoiReportModelManage001Service
 * @since 2.0
 */
@Service(value="servicePoiReportModelManage001Service")
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage001Service extends CommonExportService<ReportModelManage001Dto> {
    
    
    @Autowired
    private RepInfoMapper repInfoMapper;
    @Autowired
    private ReportModelManage001Mapper reportModelManage001Mapper;
    @Autowired
    private PoiReportModelManage001Mapper poireportModelManage001Mapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(PoiReportModelManage001Service.class);

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
        
        try {
        	UploadFile uploadFile = jasparConnectUtilService.connectJasparAndExport(dto.getUrl(), dto.getCode(), dto.getFileType(),dto.getCode(),dto.getParam());
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
              dto.setName(uploadFile.getSrcName());
              dto.setTemLoad(uploadFile.getTargetFile().getPath());
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
                OfficeMergeUtil.mergeExcel(exportFilePath + File.separator + mergeExportFileName, srcFileList);
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
        	//连接报表服务器并且将文件下载到本地
            createExcel(reportModelManage001Dto);
            //文件类型
            String fileType =reportModelManage001Dto.getFileType();
            //生成的文件名
            //String mergeExportFileName = reportModelManage001Dto.getCode()+GlobalConstant.POINT+fileType;
            String fileName =(String)reportModelManage001Dto.getParam().get("repName")+GlobalConstant.POINT+fileType;
            if(reportModelManage001Dto.getParam().get("endDate")!=null){
                fileName=reportModelManage001Dto.getParam().get("endDate")+"_"+fileName;
            }
            if(reportModelManage001Dto.getParam().get("portnum")!=null){
                fileName=reportModelManage001Dto.getParam().get("portnum")+"_"+fileName;
            }
            
            //获取已经下载到本地的文件
            File exportFile = new File(reportModelManage001Dto.getTemLoad());
            
            return ExportUtil.export(fileName, exportFile, true);
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
        
        public List<ReportModelManage001Dto> searchReportsNew(Map<String,Object> params){
    		List<ReportModelManage001Dto> result = reportModelManage001Mapper.searchReports(params);
    		return result;
        }
        /**
    	 * 指标库查询service <一句话功能简述> <功能详细描述>
    	 * 
    	 * @param pageParam
    	 * @return
    	 */
    	public Page<DisPoiTab> findByPage(int page, int length, Map<String, Object> params) throws Exception {
    		return super.queryForPageForSelectReturn(page, length, () -> poireportModelManage001Mapper.searchReports(params));
    	}

    	/**
    	 * 根据id进行删除
    	 * @param id
    	 */
        public  int delete(String id){
        return 	poireportModelManage001Mapper.deleteByPrimaryKey(id);
        }
}
