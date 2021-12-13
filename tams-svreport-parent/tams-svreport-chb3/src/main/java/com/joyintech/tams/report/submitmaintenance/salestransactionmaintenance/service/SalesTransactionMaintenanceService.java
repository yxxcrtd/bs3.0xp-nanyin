package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dao.SalesTransactionMaintenanceMapper;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.dto.SvrAjaxResult;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 销售结算资金交易信息维护
 */
@Service
public class SalesTransactionMaintenanceService extends CommonExportService<SalesTransactionMaintenanceDto> {


	@Autowired
	private SalesTransactionMaintenance001Service salestransactionmaintenance001Service;

	@Autowired
	private SalesTransactionMaintenanceMapper salestransactionmaintenanceMapper;
	/**
	 * 分页查询列表
	 *
	 * @return Page
	 */
	public Page<SalesTransactionMaintenanceDto> searchForPage(Map<String, Object> param) {
		return restTemplate.postForPage(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/findPage",param,
				SalesTransactionMaintenanceDto.class);
	}

	/**
	 * 导出
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<SalesTransactionMaintenanceDto> queryAllList(Map<String, Object> params) throws Exception{
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/queryAllList",
				params, SalesTransactionMaintenanceDto.class);
	}

	/**
	 * 导入数据文件之前检查数据
	 *
	 * @param fileName 导入的文件名
	 * @return 处理结果
	 */
	public AjaxResult checkConfirmMsgForImportData(String fileName)  {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataList", ExcelUtil.getDataList(fileName));
		param.put("orgCode", CommonUtil.getOrgCode());
		param.put("deptCode", CommonUtil.getDeptCode());
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/checkConfirmMsgForImportData",
				param,
				CommonAjaxResult.class);
	}

	/**
	 * 导入数据文件
	 *
	 * @param fileName 导入的文件名
	 * @return 导入结果，包含验证内容，如果验证失败，返回message
	 */
	public List<ExcelValidDto> importData(String fileName) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataList", ExcelUtil.getDataList(fileName));
		param.put("tempList", ExcelUtil.getTempList(fileName));
		param.put("userName", CommonUtil.getUserName());
		param.put("orgCode", CommonUtil.getOrgCode());
		param.put("deptCode", CommonUtil.getDeptCode());
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/importData",
				param,
				ExcelValidDto.class);
	}

	/**
	 * 双击查看或编辑info
	 * selectDetail
	 */
	public SalesTransactionMaintenanceDto selectDetail(String registSeqno) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("registSeqno",registSeqno);
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/selectDetail", param,
				SalesTransactionMaintenanceDto.class);
	}

	/**
	 * 双击查看或编辑erro
	 * selectErroDetail
	 */
	public SalesTransactionMaintenanceDto selectErroDetail(String registSeqno) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("registSeqno",registSeqno);
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/selectErroDetail", param,
				SalesTransactionMaintenanceDto.class);
	}

	/**
	 *
	 * 保存
	 * <功能详细描述>
	 * @param dto 待校验数据
	 * @return jiaoyanjieguo
	 * @see
	 * @since 1.0
	 */
	public AjaxResult save(SalesTransactionMaintenanceDto dto){
		if(StringUtil.isEmpty(dto.getOrgCode())){
			dto.setOrgCode(CommonUtil.getOrgCode());
		}
			return restTemplate.postForObject(
					SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/Save", dto,
					SvrAjaxResult.class);
	}
	/**
	 *
	 * 修改保存
	 * <功能详细描述>
	 * @param dto 待校验数据
	 * @return jiaoyanjieguo
	 * @see
	 * @since 1.0
	 */
	public AjaxResult update(SalesTransactionMaintenanceDto dto){
		if(StringUtil.isEmpty(dto.getOrgCode())){
			dto.setOrgCode(CommonUtil.getOrgCode());
		}
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/update", dto,
				SvrAjaxResult.class);
	}

	/**
	 * 删除err表并保存在info表
	 * @param dto
	 * @return
	 */
	public AjaxResult deleteAndSave(SalesTransactionMaintenanceDto dto){
		if(StringUtil.isEmpty(dto.getOrgCode())){
			dto.setOrgCode(CommonUtil.getOrgCode());
		}
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/deleteAndSave", dto,
				SvrAjaxResult.class);
	}

	/**
	 * 删除erro
	 * <功能详细描述>
	 * @param dto 待校验数据
	 * @return jiaoyanjieguo
	 * @see
	 * @since 1.0
	 */
	public boolean deleteByPrimaryKey(SalesTransactionMaintenanceDto dto){
		if(StringUtil.isEmpty(dto.getOrgCode())){
			dto.setOrgCode(CommonUtil.getOrgCode());
		}
		return restTemplate.postForObject(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/deleteByPrimaryKey", dto,
				Boolean.class);
	}

	/**
	 * 提供销售结算资金服务机构代码下拉框
	 */
	public List<SuggestDto> getInstitutionCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/getInstitutionCode",
				map, SuggestDto.class);
	}

	/**
	 * 产品登记编码下拉框
	 */
	public List<SuggestDto> getPrdRegistCode(Map<String, Object> map) {
		return restTemplate.postForList(
				SvrServiceApplication.CHB3_SERVICE_NAME + "/SalesTransactionMaintenance/getPrdRegistCode",
				map, SuggestDto.class);
	}

	/**
	 * 销售结算资金交易流水号下拉框
	 */
	public List<SelectOptionDto> getTradeNo(Map<String, Object> map) {
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		dtoList = salestransactionmaintenanceMapper.getTradeNo(map);
		return dtoList;
	}
}
