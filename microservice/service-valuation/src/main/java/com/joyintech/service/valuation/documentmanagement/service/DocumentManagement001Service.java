/*
 * 文件名：ValDocumentManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.documentmanagement.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSchException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.service.valuation.documentmanagement.dao.DocumentManagement001Mapper;
import com.joyintech.service.valuation.documentmanagement.dto.DocumentManagement001Dto;
/**
 * 
 * 文档管理service 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author lishicheng
 * @version 1.0 2018年10月24日
 * @see DocumentManagement001Service
 * @since 1.0
 */
@Service(value="serviceDocumentManagement001Service")
public class DocumentManagement001Service extends CommonExportService<DocumentManagement001Dto> {

	@Autowired
	private DocumentManagement001Mapper documentManagement001Mapper;

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(DocumentManagement001Service.class);

	/**
	 * 查询service <一句话功能简述> <功能详细描述>
	 * @param length 
	 * @param page 
	 * 
	 * @param pageParam
	 * @return
	 * @throws Exception
	 */
	public Page<DocumentManagement001Dto> findPage(int page, int length, Map<String, Object> pageParam) throws Exception {
		return super.queryForPage(() -> documentManagement001Mapper.queryPageList(pageParam));
	}

	/**
	 * 导出 <一句话功能简述> <功能详细描述>
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<DocumentManagement001Dto> findPageout(Map<String, Object> params) throws Exception {

		return documentManagement001Mapper.queryPageList(params);
	}

	/**
	 * 读取文件并上传到文件服务器 <一句话功能简述> <功能详细描述>
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws JSchException
	 */
	public ResponseEntity<byte[]> downloadFile(Map<String, Object> params) throws IOException, JSchException {
		// 文件本地路径
		String fileId = (String)params.get("fileId");
		DocumentManagement001Dto dto = documentManagement001Mapper.qryBokDocInfoByFileId(fileId);
		// 附件存储路径
		String filePath = dto.getFilePath();

		// 文件名称
		String fileName = dto.getFileName();
		File file = new File(filePath);
		
		return ExportUtil.export(fileName, file, false);
	}

}
