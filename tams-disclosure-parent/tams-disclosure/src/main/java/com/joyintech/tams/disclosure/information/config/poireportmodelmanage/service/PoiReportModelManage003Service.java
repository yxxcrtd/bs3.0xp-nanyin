/*
 * 文件名：ReportModelManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.poireportmodelmanage.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageInfo;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.DisPoiTabMapper;
import com.joyintech.tams.common.base.entity.DisPoiTab;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dao.PoiReportModelManage003Mapper;
import com.joyintech.tams.disclosure.information.config.poireportmodelmanage.dto.PoiReportModelManage003Dto;

/**
 * 报表模板管理导出报表service
 * 
 * @author zhangyf
 * @version 2.0 2020年2月21日
 * @see PoiReportModelManage003Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoiReportModelManage003Service extends CommonExportService<PoiReportModelManage003Dto> {

	@Autowired
	private PoiReportModelManage003Mapper poiReportModelManage003Mapper;
	@Autowired
	private DisPoiTabMapper disPoiTabMapper;

	@Autowired
	private PoiReportService poiReportService;

	@Autowired
	private FileAttachmentProcess fileAttachmentProcess;
	
	/**
	 * 获取报表生成条件
	 * 
	 * @param dto
	 * @return
	 */
	public List<PoiReportModelManage003Dto> getReportSearchConditions(Map<String, Object> map) {
		return poiReportModelManage003Mapper.getReportSearchConditions(map);
	}

	/**
	 * 分页获取下拉框内容
	 * 
	 * @param paramMap
	 * @return
	 */
	public Page<SelectOptionDto> getSelectOptionForPage(Map<String, Object> paramMap) {
		List<SelectOptionDto> list = this.querySelectOptions(paramMap);
		return new Page<SelectOptionDto>(new PageInfo<SelectOptionDto>(list));
	}
	
	/**
	 * 查询下拉框内容列表
	 * 
	 * @param map
	 * @return
	 */
	private List<SelectOptionDto> querySelectOptions(Map<String, Object> map) {
		PoiReportModelManage003Dto disPrams = poiReportModelManage003Mapper.selectDisPramsByKey(map);
		if (null == disPrams) {
			return null;
		}
		int pageNum = StringUtil.integerValueOf(RequestUtil.getParam(GlobalConstant.PARAM_PAGE), 1);
		int pageSize = 40;
		// 组装查询下拉列表数据的sql
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT " + disPrams.getDisKey() + " AS ID, " + disPrams.getDisVal() + " AS VALUE,ROWNUM AS RN";
		sql += " FROM " + disPrams.getDisTable();
		sql += " WHERE 1=1 " + disPrams.getDisParams();
		if(StringUtil.isNotEmpty(map.get("search"))) {
			sql += " AND (" + disPrams.getDisKey() + " LIKE '%" + map.get("search") +  "%'";
			sql += " OR " + disPrams.getDisVal() + " LIKE '%" + map.get("search") +  "%')";
		}
		sql += " ) T WHERE 1=1 AND RN > " + (pageNum-1)*pageSize;
		sql += " AND RN <= "  + pageNum*pageSize;
		
		List<List<String>> pramsList = poiReportService.getQuery(sql);
		if (CollectionUtils.isEmpty(pramsList)) {
			return null;
		}
		List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
		SelectOptionDto option;
		for (List<String> row : pramsList) {
			option = new SelectOptionDto();
			option.setId(row.get(0));
			option.setText(row.get(1));
			options.add(option);
		}
		return options;
	}

	/***
	 * 导出文件（仅导出查阅，不做其他操作）
	 * 
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<byte[]> reportExport(Map<String, Object> param) throws IOException {
		// 生成的文件（本地）
		File generateFile = poiReportService.createReport(param.get("tabNum").toString(), param);
		if (!generateFile.exists()) {
			throw new BusinessException("文件[" + generateFile.getPath() + "]不存在！");
		}
		
		// 组装文件名：产品代码_结束日期_报表名.文件拓展名
		String exportFileName = createFileName(param, generateFile);
		return ExportUtil.export(exportFileName, generateFile, true);
	}

	/**
	 * 生成文件
	 * 
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public Boolean reportGenerate(Map<String, Object> param) throws IOException {
		// 生成的文件（本地）
		File localFile = poiReportService.createReport(param.get("tabNum").toString(), param);
		if (null != localFile && localFile.exists()) {
			String fileNo = IDGenerator.generate();
			UploadFile uploadFile = new UploadFile(localFile.getName(), fileNo, localFile);
			uploadFile.setFileName(localFile.getPath());
			// 存入SYS_UPLOAD_FILE_INFO表
			if (null != fileAttachmentProcess) {
				fileAttachmentProcess.upload(uploadFile);
			}
			// 组装文件名：产品代码_结束日期_报表名.文件拓展名
			String fileName = createFileName(param, localFile);
			param.put("fileName", fileName);
			param.put("localFile_name", localFile.getName());
			param.put("uploadFile_fileName", uploadFile.getFileName());
			param.put("uploadFile_fileNo", uploadFile.getFileNo());
			// 组装报表确认信息，存表
			restTemplate.postForObject(
					ServiceApplication.VALUATION_SERVICE_NAME + "/PoiReportModelManage003/createRepReveal", param,
					Integer.class);
			return true;
		}
		return false;
	}

	/**
	 * 存在相同且已确认的报表
	 * 
	 * @param params
	 * @return
	 */
	public Boolean hasConfirmStatus(Map<String, Object> params) {
		int i = poiReportModelManage003Mapper.selectConfirmStatus(params);
		return i > 0;
	}
	/**
	 * 是否存在相同的报表
	 * 
	 * @param params
	 * @return
	 */
	public Boolean querySameCount(Map<String, Object> params) {
		int i = poiReportModelManage003Mapper.querySameCount(params);
		return i > 0;
	}
	
	/**
	 * 组装文件名（产品代码_结束日期_报表名.文件拓展名）
	 * 
	 * @param param 报表生成条件
	 * @param generateFile 生成的文件
	 * @return
	 */
	private String createFileName(Map<String, Object> param, File generateFile) {
		String fileName = "";
		DisPoiTab disPoiTab = disPoiTabMapper.selectByPrimaryKey(param.get("tabNum").toString());
		if (StringUtil.isNotEmpty(param.get("PROD_ID").toString())) {
			fileName += param.get("PROD_ID").toString();
		}
		if (StringUtil.isNotEmpty(param.get("END_DATE").toString())) {
			fileName += GlobalConstant.UNDER_LINE + param.get("END_DATE").toString();
		}
		fileName += GlobalConstant.UNDER_LINE + disPoiTab.getTabName() + GlobalConstant.POINT
				+ FileNameUtil.getFileExtName(generateFile);
		return fileName;
	}
}
