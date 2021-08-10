package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.*;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFileMService
 * @since 1.0
 */
@Service
public class PbcSendZipDatFileMService extends BaseService<PbcSendZipDatFile>{

	private static final String PRODUCT_BASE_INFO_NAME = "产品基本信息";
	private static final String PRODUCT_END_INFO_NAME = "产品终止信息";
	private static final String TIPS_SHEET_NAME_ERROR = "文件名称不规范，请确认文件名称后，重新导入";


	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcSendZipDatFileMService.class);

	private static final String TIPS_FILE_NAME_ERROR = "文件名称不规范，请确认文件名称后，重新导入。";

	@Autowired
	private PbcSendZipDatFileMapper pbcSendZipDatFileMapper;

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<PbcSendZipDatFile> searchPage(Map<String, Object> param) {
		return restTemplate.postForPage(
        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/findPage",param,
				PbcSendZipDatFile.class);
	}



	/**
	 *
	 * 业务导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AjaxResult checkBaseInfoSubDat(String fileName) {
		Map<String,Object> param = new HashMap<>();
		param.put("dataList", ExcelUtil.getDataList1(fileName));
		String name = FileNameUtil.getName(fileName);
		String[] strName = name.split("_");
		if(strName.length > 0){
			if(strName[0].equals(PRODUCT_BASE_INFO_NAME)){
				return restTemplate.postForObject(
						SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseInfoSubDat/checkBaseInfoSubDat?fileName=" + fileName, param,
						CommonAjaxResult.class);
			}else if(strName[0].equals(PRODUCT_END_INFO_NAME)){
				return restTemplate.postForObject(
						SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseInfoEndSubDat/checkBaseInfoSubDat?fileName="+fileName,param,
						CommonAjaxResult.class);
			}
		}
		return new AjaxResult(TIPS_SHEET_NAME_ERROR,MessageType.错误消息);
	}


	/**
	 * 导入数据文件
	 *
	 * @param fileName 导入的文件名
	 * @return 导入结果，包含验证内容，如果验证失败，返回message
	 */
	public List<ExcelValidDto> importBaseInfoSubDat(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataList", ExcelUtil.getDataList1(fileName));
		String name = FileNameUtil.getName(fileName);
		String[] strName = name.split("_");
		if(strName.length > 0){
			if(strName[0].equals(PRODUCT_BASE_INFO_NAME)){
				return restTemplate.postForList(
						SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseInfoSubDat/importBaseInfoSubDat?fileName="+fileName,param,
						ExcelValidDto.class);
			}else if(strName[0].equals(PRODUCT_END_INFO_NAME)){
				return restTemplate.postForList(
						SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseInfoEndSubDat/importBaseInfoSubDat?fileName="+fileName,param,
						ExcelValidDto.class);
			}
		}
		List<ExcelValidDto> errorList = new ArrayList<>();
		ExcelValidDto validDto = new ExcelValidDto();
		validDto.setErrorInfo(TIPS_SHEET_NAME_ERROR);
		errorList.add(validDto);
		return errorList;

	}

	/**
	 *
	 * 业务导入检查事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AjaxResult checkMonthReport(String fileName) {
		AjaxResult commonAjaxResult = restTemplate.postForObject(
				SvrServiceApplication.PBC_SERVICE_NAME + "/NewPbcMonthReport/checkMonthReport?fileName=" + fileName, new HashMap<>(),
				CommonAjaxResult.class);
		if(StrUtil.isNotEmpty(commonAjaxResult.getMessage())){
			AjaxResult ajaxResult = new AjaxResult();
			JoyinMessage message = new JoyinMessage(commonAjaxResult.getMessage(),MessageType.确认消息);
			ajaxResult.setJoyinMessages(Arrays.asList(message));
			ajaxResult.setMessageType(MessageType.确认消息);
			ajaxResult.setStatuCode(HttpStatus.OK.value());
			return ajaxResult;
		}
		return commonAjaxResult;
		/*if(commonAjaxResult != null && CollectionUtil.isNotEmpty(commonAjaxResult.getErrorList())){
			return commonAjaxResult;
		}
		AjaxResult ajaxResult = new AjaxResult();
		String name = FileNameUtil.getName(fileName);
		String[] strName = name.split("_");
		if(strName.length == 0){
			ValidMessage message = new ValidMessage();
			message.setMessage(TIPS_FILE_NAME_ERROR);
			ajaxResult.setErrorList(Arrays.asList(message));
			return ajaxResult;
		}
		String fName = strName[0];
		String statusVdate = fName.substring(0,4).concat("-").concat(fName.substring(4,6)).concat("-").concat(fName.substring(6,8));
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("statusVdate",statusVdate);
		paramMap.put("datZipType","MI");
		List<PbcSendZipDatFile> pbcSendZipDatFiles = pbcSendZipDatFileMapper.selectByZipTypeAndStatusVdate(paramMap);
		List<JoyinMessage> validMessageList = new ArrayList<>();
		Set<String> sets = new HashSet<>();
		for (PbcSendZipDatFile pbcSendZipDatFile : pbcSendZipDatFiles) {
			if(pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_接口报送.getValue())
					|| pbcSendZipDatFile.getZipSendStatus().equals(DictCodeListExt2.报送状态_手工处理.getValue())){
				sets.add(pbcSendZipDatFile.getDatZipDatType());
			}
		}
		StringBuilder waningReport = new StringBuilder();
		if(sets.size() > 0){
			for (String set : sets) {
				waningReport.append("[").append(set).append("]");
			}
			String warningInfo = String.format("统计模板%s数据已报送，不支持对数据进行维护",waningReport.toString());
			JoyinMessage message = new JoyinMessage(warningInfo,MessageType.确认消息);
			validMessageList.add(message);
		}
		if(CollectionUtil.isNotEmpty(validMessageList)){
			ajaxResult.setJoyinMessages(validMessageList);
			ajaxResult.setMessageType(MessageType.确认消息);
			ajaxResult.setStatuCode(HttpStatus.OK.value());
			return ajaxResult;
		}
		ajaxResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
		return ajaxResult;*/
	}


	/**
	 * 导入数据文件
	 *
	 * @param fileName 导入的文件名
	 * @return 导入结果，包含验证内容，如果验证失败，返回message
	 */
	public List<ExcelValidDto> importMonthReport(String fileName) {
		return restTemplate.postForList(
				SvrServiceApplication.PBC_SERVICE_NAME + "/NewPbcMonthReport/importMonthReport?fileName="+fileName,new HashMap<>(),
				ExcelValidDto.class);
	}
	/**
	 * 生成压缩文件
	 * @return 压缩文件存放地址
	 */
	public List<String> createZipFile(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.PBC_SERVICE_NAME + "/PbcSendZipDatFile/createZipFile",param,
				List.class);
	}




}
