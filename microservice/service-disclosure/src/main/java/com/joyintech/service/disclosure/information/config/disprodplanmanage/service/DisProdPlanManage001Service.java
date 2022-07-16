/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.disclosure.information.config.disprodplanmanage.service;

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

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.*;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage001Mapper;
import com.joyintech.service.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage001Dto;
import com.joyintech.tams.constant.DictCode;
import org.apache.commons.collections4.CollectionUtils;
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
import com.joyintech.tams.common.base.dao.RepInfoMapper;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.util.JasparConnectUtilService;
import com.joyintech.tams.disclosure.common.common.util.OfficeMergeUtil;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * 记账凭证查询service
 * 
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage001Service
 * @since 2.0
 */
@Service(value="serviceDisProdPlanManage001Service")
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage001Service extends CommonExportService<DisProdPlanManage001Dto> {
    
    
    @Autowired
    private RepInfoMapper repInfoMapper;
    @Autowired
    private DisProdPlanManage001Mapper disProdPlanManage001Mapper;
    @Autowired
    private JasparConnectUtilService jasparConnectUtilService;
        /**
         * 日志
         */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage001Service.class);

    /**
     * <一句话功能简述> 导出估值表
     * 
     * @param
     * @throws IOException
     * @see
     * @since 1.0
     */
    public ResponseEntity<byte[]> exportExcel(List<DisProdPlanManage001Dto> list)
        throws IOException {

        // TODO  字段问题  报错注释
        return null;
            /*for (DisProdPlanManage001Dto disProdPlanManage001Dto : list) {
                createExcel(disProdPlanManage001Dto);
            }

            String exportFilePath = CacheUtil.getSysParam(
                GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String zipFileName = FileNameUtil.createFileName("zip");
            File zipFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
            FileOutputStream stream = null;
            ZipOutputStream  zipOut =null;
            try{
                 stream = new FileOutputStream(zipFile);
                 zipOut = new ZipOutputStream(stream);
                 InputStream input = null;
                 
                 for (DisProdPlanManage001Dto dto : list) {
                     try{
                         zipOut.putNextEntry(new ZipEntry(dto.getName()));
                         input = new FileInputStream(dto.getTemLoad());
                         int tmp = 0;
                         while ((tmp = input.read()) != -1) {
                             zipOut.write(tmp);
                         }
                      }catch (Exception e) {
 //                       logger.error(e.getMessage(),e);
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
            return ExportUtil.export(zipFileName, zipFile, true);*/
    }

    public void createExcel(DisProdPlanManage001Dto dto) throws IOException {

        try {

            // TODO  字段问题  报错注释
        	/*UploadFile uploadFile = jasparConnectUtilService.connectJasparAndExport(dto.getUrl(), dto.getCode(), dto.getFileType(),dto.getCode(),dto.getParam());
              dto.setName(uploadFile.getSrcName());
              dto.setTemLoad(uploadFile.getTargetFile().getPath());*/
        } catch (Exception e) {
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
         * @param
         * @return
         * @throws Exception 
         * @see
         * @since 1.0
         */
        public ResponseEntity<byte[]> mergeExport(List<DisProdPlanManage001Dto> list) throws IOException {
            // TODO  字段问题  报错注释
            return null;
           /* List<String> srcFileList = new ArrayList<String>();
            for (DisProdPlanManage001Dto disProdPlanManage001Dto : list) {
                createExcel(disProdPlanManage001Dto);
                srcFileList.add(disProdPlanManage001Dto.getTemLoad());
            }
            String fileType = list.get(0).getFileType();
            String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String mergeExportFileName = FileNameUtil.createFileName(fileType);
            File exportFile = new SafelyFile(exportFilePath + File.separator + mergeExportFileName);
            if("docx".equals(fileType)){
                OfficeMergeUtil.mergeWord(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }else if("xls".equals(fileType) || "xlsx".equals(fileType)){
                OfficeMergeUtil.mergeExcel(exportFilePath + File.separator + mergeExportFileName, srcFileList);
            }
            return ExportUtil.export(mergeExportFileName, exportFile, true);*/
        }
        
        /**
         * 导出合并文件
         * 
         * @param
         * @return
         * @throws Exception 
         * @see
         * @since 1.0
         */
        public ResponseEntity<byte[]> mergeExportSingle(DisProdPlanManage001Dto disProdPlanManage001Dto) throws IOException {
            // TODO  字段问题  报错注释
            return null;
        	/*//连接报表服务器并且将文件下载到本地
            createExcel(disProdPlanManage001Dto);
            //文件类型
            String fileType =disProdPlanManage001Dto.getFileType();
            //生成的文件名
            //String mergeExportFileName = disProdPlanManage001Dto.getCode()+GlobalConstant.POINT+fileType;
            String fileName =(String)disProdPlanManage001Dto.getParam().get("repName")+GlobalConstant.POINT+fileType;
            if(disProdPlanManage001Dto.getParam().get("endDate")!=null){
                fileName=disProdPlanManage001Dto.getParam().get("endDate")+"_"+fileName;
            }
            if(disProdPlanManage001Dto.getParam().get("portnum")!=null){
                fileName=disProdPlanManage001Dto.getParam().get("portnum")+"_"+fileName;
            }
            
            //获取已经下载到本地的文件
            File exportFile = new SafelyFile(disProdPlanManage001Dto.getTemLoad());
            
            return ExportUtil.export(fileName, exportFile, true);*/
        }
        
        public List<Map<String,String>> searchReports(Map<String,Object> params) throws IOException, ParserConfigurationException, SAXException {
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
        
        public List<DisProdPlanManage001Dto> searchReportsNew(Map<String,Object> params){
    		List<DisProdPlanManage001Dto> result = disProdPlanManage001Mapper.searchReports(params);
            // 遍历构造数据
            if(CollectionUtils.isNotEmpty(result)){
                result.stream().forEach(item -> {
                    item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                });
            }
    		return result;
        }
        /**
    	 * 指标库查询service <一句话功能简述> <功能详细描述>
    	 * 
    	 * @param params
    	 * @return
    	 */
    	public Page<DisProdPlanManage001Dto> findByPage(int page, int length, Map<String, Object> params)
        {
            String dataStatus = (String)params.get("dataStatus");
            String[] dataStatusArr = null;
            if(StringUtil.isNotEmpty(dataStatus)){
                dataStatusArr = dataStatus.split(",");
            }
            params.put("dataStatusArr", dataStatusArr);
            Page<DisProdPlanManage001Dto> pages = super.queryForPageForSelectReturn(page, length, () -> disProdPlanManage001Mapper.searchReports(params));
            List<DisProdPlanManage001Dto> list = pages.getRoot();
            // 遍历构造数据
            if(CollectionUtils.isNotEmpty(list)){
                list.stream().forEach(item -> {
                    item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                });
                pages.setRoot(list);
            }
    		return pages;
    	}
}
