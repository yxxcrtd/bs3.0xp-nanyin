package com.joyintech.tams.valuation.bokdetailidupdate.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.service.SystemToolService;
import com.joyintech.tams.valuation.common.auxiliary.dto.ChangeSubjectNoResult;
import com.joyintech.tams.valuation.common.auxiliary.service.OperationAuxiliaryService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.BokDetailIdUpdate003Dto;
import com.joyintech.tams.valuation.bokdetailidupdate.dto.ResultData;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

@Service
public class BokDetailIdUpdate002Service extends CommonExportService<BokDetailIdUpdate003Dto>{
	@Autowired
	private InterfCommonService interfCommonService;
	@Autowired
	private SystemToolService systemToolService;
	@Autowired
	private OperationAuxiliaryService operationAuxiliaryService;
	/**
	 * 账务明细代码加密生成
	 * @param dto
	 * @return
	 */
	public String getBokdetailId(BokDetailIdUpdate003Dto dto){
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate002/getBokdetailId",
				dto, String.class);
	}
	/**
	 * 账务明细代码调整
	 */
	public ResultData doSubmit(BokDetailIdUpdate003Dto dto){
		return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/BokDetailIdUpdate002/doSubmit",
				dto, ResultData.class);
	}
	/**
	 * 账务明细代码调整
	 */
	public ChangeSubjectNoResult scriptGeneration(BokDetailIdUpdate003Dto dto){

		//定义 变量.分支序号 =0
		Long 分支序号 = 0L;
		//定义 变量.账务明细代码DTO
		BokDetailId 账务明细代码DTO = new BokDetailId();
		//定义 变量.账套参数Map
		Map<String,Object> 账套参数Map = new HashMap<String,Object>();
		//定义 变量.标的信息

		//IF 数据字典：金融产品类型.理财子产品 eq  画面.业务明细类型
		if (DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(dto.getBusiDetailType())) {
			//变量.分支序号 = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(画面.理财子产品起息日))
			分支序号 =  Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getVdate()));
		}
		//END IF
		//1. 账务明细代码加密
		//调用共通设计->【详细设计-接口共通方法设计.xlsx】->组装账务明细(资金帐户-现金类)SHEET
		//变量.账务明细代码DTO = 返回值
		账务明细代码DTO = interfCommonService.getBokDetailId(dto.getBusiId(), null, dto.getBusiDetailType(), dto.getInvAim(), 分支序号, dto.getCcy());
		//2.脚本生成
		//定义 变量.原资金账户号 = null
		String 原资金账户号 = null;
		//定义 变量.资金账户号 = null
		String 资金账户号 = null;
		//IF 画面.业务明细类型 IN (数据字典：资金账户类型) AND 画面.调整前资金账户 != 画面.资产代码
		DataDictItem date = systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), dto.getBusiDetailType());
		if (systemToolService.getDataDictItem(DictCode.资金账户类型.getValue(), dto.getBusiDetailType()) != null
			&& !StringUtil.equals(dto.getAcctNoOld(),dto.getBusiId())
		){
			//变量.原资金账户号 = 画面.调整前资金账户号
			原资金账户号 = dto.getAcctNoOld();
			//变量.资金账户号 = 画面.资产代码
			资金账户号 = dto.getBusiId();
		}
		//END IF
		//调用共通设计->【详细设计-运维辅助工具-共通方法.xlsx】->账务明细代码调整脚本生成SHEET
		/**
		 * 参数名：					参数值
		 * 账套代码					画面.账套代码List
		 * 原账务明细代码			画面.调整前账务明细代码
		 * 原资金账户号				变量.原资金账户号
		 * 账务明细代码DTO			变量.账务明细代码DTO
		 * 返回值					处理结果
		 * 					处理信息描述
		 * 					执行SQL
		 */
		List<String> booksetIds = Arrays.asList(dto.getBooksetId().split(","));
		ChangeSubjectNoResult result = operationAuxiliaryService.scriptGenerationAccountingDetailCode(booksetIds,dto.getBokdetailIdOld(),原资金账户号,账务明细代码DTO);
		//执行sql
		String execSql = result.getExecSql();
		return result;
	}
	/**
	 *
	 * @param sql
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<byte[]> downloadSqlFile(String sql,String sqlName) throws IOException {
		// 新建文件
		String fileName = UUID.randomUUID().toString() + ".sql";
		String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		String outPath = exportFilePath + "/" + fileName;
		File tempFile = new File(outPath);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		File file = new File(outPath);
		file.createNewFile();

		// 往文件中写入数据
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			byte[] bytes = sql.getBytes();
			outputStream.write(bytes);
		}catch (Exception e){

		}finally {
			outputStream.close();
		}

		// 导出文件
		return ExportUtil.export(sqlName+".sql",file,true);
	}
	public List<SuggestDto> getBusiId(String finprodType, String booksetId, String param) {
		Map<String, Object> params  = new HashMap<String,Object>();
        params.put("finprodType", finprodType);
        params.put("booksetId", booksetId);
        params.put("param", param);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/BokDetailIdUpdate002/getBusiId", params, SuggestDto.class);
	}
}
