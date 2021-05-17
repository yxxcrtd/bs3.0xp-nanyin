/*
 * 文件名：DocumentManagement002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.documentmanagement.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.file.SafelyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.valuation.documentmanagement.dao.DocumentManagement002Mapper;
import com.joyintech.tams.valuation.documentmanagement.dto.DocumentManagement001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.service.BaseCommonService;

@Service
public class DocumentManagement002Service extends CommonExportService<DocumentManagement001Dto> {

	@Autowired
	private ProgressBarService progressBarService;

	@Autowired
	private BaseCommonService baseCommonService;

	@Autowired
	private DocumentManagement002Mapper documentManagement002Mapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(DocumentManagement002Service.class);

	/**
	 * 读取文件并上传到文件服务器
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void readFile(HttpServletRequest request) throws Exception {
		// 文件本地路径
		String filePath = request.getParameter("fileName1");
		/*
		//上传文件名称
		String name = request.getParameter("fileName");*/
		// 文件名称
		String fileName = FileNameUtil.formatFileName(filePath);

		File file = new SafelyFile(filePath);
		boolean rec = file.renameTo(new SafelyFile(fileName));
		if (rec) {
			logger.info("处理成功");
		}

		String Sysdate = baseCommonService.getSysDateString();
		if (Sysdate == null || "".equals(Sysdate)) {
			Sysdate = DateUtil.format(new Date());
		}
	
		progressBarService.updatePercent("DocumentManagement002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
	}

	/**
	 * 上传并把数据插入数据库 <一句话功能简述> <功能详细描述>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int insertFileTransferInfo(HttpServletRequest request) throws Exception {
		DocumentManagement001Dto dto = new DocumentManagement001Dto();
		// 系统时间
		String Sysdate = baseCommonService.getSysDateString();
		if (Sysdate == null || "".equals(Sysdate)) {
			Sysdate = DateUtil.format(new Date());
		}
		// 生成流水号
		String fileId = IDGenerator.generate();
		dto.setFileId(fileId);
		// 设置文件名称
		String fileName = request.getParameter("fileNameOnly");
		dto.setFileName(fileName);
		// 设置文件类型
		String fileType = request.getParameter("fileType");		
		//String fileType = DictCodeList.文件类型_操作手册.getValue();
		dto.setFileType(fileType);
		// 文件存放目录
		dto.setFilePath(request.getParameter("fileName1"));

		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/DocumentManagement002/uploadFile",
                dto, Integer.class);
	}

}
