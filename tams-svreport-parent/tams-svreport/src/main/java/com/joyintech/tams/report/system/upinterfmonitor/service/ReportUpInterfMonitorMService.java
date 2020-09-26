/*
 * 文件名：ReportUpInterfMonitor001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统
 * 修改人：hujing
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统
 */
package com.joyintech.tams.report.system.upinterfmonitor.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.report.system.upinterfmonitor.dao.ReportUpInterfMonitor001Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.system.upinterfmonitor.dto.ReportUpInterfMonitor001Dto;

/**
 * 上游接口文件读取处理监控 〈功能详细描述〉
 * 
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see ReportUpInterfMonitorMService
 * @since 1.0
 */
@Service
public class ReportUpInterfMonitorMService extends BaseService<ReportUpInterfMonitor001Dto> {

	@Autowired
	private ReportUpInterfMonitor001Mapper mapper;
	
	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	
	/**
     * 数据文件名下拉框
     * @return 数据文件名列表
     */
	public List<SelectOptionDto> queryHandleFileNameList() {
    	return restTemplate.getForList(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/ReportUpInterfMonitor/gethandleFileName",
          		SelectOptionDto.class);
    } 
    
    /**
     * 查询列表
     *
     * @return DtPage
     */
    public Page<ReportUpInterfMonitor001Dto> searchForPage(Map<String,Object> paramMap) {
        return restTemplate.postForPage(
          		SvrServiceApplication.COMMON_SERVICE_NAME + "/ReportUpInterfMonitor/findPage",paramMap,
          		ReportUpInterfMonitor001Dto.class);
    }

    public ResponseEntity<byte[]> export(String seqNos){
    	ResponseEntity<byte[]> responseEntity = null;
		List<String> listSeqNo = StringUtil.splitToList(GlobalConstant.COMMA,seqNos);
		if(!StringUtil.isEmptyList(listSeqNo)){
			UploadFile	uploadFile=null;
			List<File> sourceFiles = new ArrayList<>();
			int fileNum=listSeqNo.size();
			if(fileNum==1){
				uploadFile=fileAttachmentProcess.fetch(listSeqNo.get(0));
				File exportFile=uploadFile.getTargetFile();
				
				String fileName=uploadFile.getFileName();
				String fileType = fileName.substring(fileName.indexOf("."));
				
				fileName = fileName.substring(0,fileName.indexOf(".")) + GlobalConstant.UNDER_LINE + DateUtil.getDateTimestamp() + fileType;
				responseEntity = ExportUtil.export(fileName,exportFile,false);
			}else{
				for (int i = 0; i < fileNum; i++) {
					uploadFile=fileAttachmentProcess.fetch(listSeqNo.get(i));
					sourceFiles.add(uploadFile.getTargetFile());
				}
				String fileName=uploadFile.getFileName();
				String zipFileName = fileName.substring(0,fileName.indexOf(".")) + GlobalConstant.UNDER_LINE + DateUtil.getCurrentDateTime() + ".zip";
				zipFileName = zipFileName.replaceAll(GlobalConstant.COLON,GlobalConstant.SPLIT);
				
				String filePath=uploadFile.getTargetFile().getAbsolutePath();
				
				String exportFilePath = filePath.substring(0,filePath.lastIndexOf("/"));
				File outFile = new File(exportFilePath + File.separator + zipFileName);

				try {
					ZipFileUtil.compressZip(outFile,false,sourceFiles.toArray(new File[]{}));
				} catch(Exception e) {
					e.printStackTrace();
				}
				responseEntity = ExportUtil.export(outFile.getName(),outFile,true);
			}
		}
		return responseEntity;
    	// List<Map<String,String>> listMap = mapper.getFilePath(listSeqNo);
    	// if(listMap != null && listMap.size()>0){
    	// 	if(listMap.size()==1){
    	// 		String fileName = listMap.get(0).get("FILENAME");
    	// 		String fileType = fileName.substring(fileName.indexOf("."));
    	// 		// 文件名加时间戳
    	// 		fileName = fileName.substring(0,fileName.indexOf(".")) + GlobalConstant.UNDER_LINE + DateUtil.getDateTimestamp() + fileType;
    	// 		String filePath = listMap.get(0).get("PATH");
    	// 		File exportFile = new File(filePath);
    	// 		responseEntity = ExportUtil.export(fileName,exportFile,false);
		// 	}else {
    	// 		List<File> sourceFiles = new ArrayList<>();
		// 		for (Map<String, String> map : listMap) {
		// 			File file = new File(map.get("PATH"));
		// 			sourceFiles.add(file);
		// 		}
		// 		String filePath = listMap.get(0).get("PATH");
		// 		String exportFilePath = filePath.substring(0,filePath.lastIndexOf("/"));
		// 		String fileName = listMap.get(0).get("FILENAME");
		// 		String zipFileName = fileName.substring(0,fileName.indexOf(".")) + GlobalConstant.UNDER_LINE + DateUtil.getCurrentDateTime() + ".zip";
		// 		zipFileName = zipFileName.replaceAll(GlobalConstant.COLON,GlobalConstant.SPLIT);
		// 		File outFile = new File(exportFilePath + File.separator + zipFileName);
		// 		try {
		// 			ZipFileUtil.compressZip(outFile,false,sourceFiles.toArray(new File[]{}));
		// 		} catch(Exception e) {
		// 			e.printStackTrace();
		// 		}
		// 		responseEntity = ExportUtil.export(outFile.getName(),outFile,true);
		// 	}
		// }
		// return responseEntity;
	}


}
