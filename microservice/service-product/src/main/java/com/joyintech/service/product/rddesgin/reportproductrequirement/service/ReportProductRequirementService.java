package com.joyintech.service.product.rddesgin.reportproductrequirement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.colproductrequirement.dao.ColProductRequirementMapper;
import com.joyintech.service.product.rddesgin.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.service.product.rddesgin.reportproductrequirement.dao.ReportProductRequirementMapper;
import com.joyintech.service.product.rddesgin.reportproductrequirement.dto.ReportProductRequirementDto;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 产品需求收集service
 * 
 * @author yaozhenguo
 * @version 1.0 2018年9月27日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Service
public class ReportProductRequirementService extends BaseService<ReportProductRequirementDto> {

	@Autowired
	private ReportProductRequirementMapper reportProductRequirementMapper;
	
	/**
	 * 需求收集页面查询
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */
	public Page<ReportProductRequirementDto> selectForPage(int page, int length, Map<String, Object> param){
		param.put("dataStatus_revoke", DictCodeListExt1.有效状态_撤销.getValue());
		param.put("dataStatus_wait", DictCodeListExt1.有效状态_待处理.getValue());
		param.put("dataStatus_effective", DictCodeListExt1.有效状态_有效.getValue());
		param.put("reviewYesno_yes", DictCodeListExt1.是否_是.getValue());
		return queryForPage(page,length, ()->{
			reportProductRequirementMapper.selectForPage(param);
		});
	}
	
	/**
	 * 根据需求ID查询
	 * @param requirementId
	 * @return
	 */
	public ReportProductRequirementDto queryRequirementByRequireId(String requirementId) {
		return reportProductRequirementMapper.queryRequirementByRequireId(requirementId,DictCodeListExt1.有效状态_待处理.getValue());
	}
	
	/**
	 * 新建产品附属表
	 * @param colProductRequirement001Dto
	 * @throws Exception
	 */
	public void insertColProductRequirementAdd(ReportProductRequirementDto reportProductRequirementDto) {
		reportProductRequirementMapper.insertColProductRequirementAdd(reportProductRequirementDto);
		return;
	}
	
	/**
	 * 根据需求ID删除
	 * @param requirementId
	 * @return
	 */
	public void deleteColProductRequirement(String requirementId) {
		reportProductRequirementMapper.deleteColProductRequirement(requirementId);
		return;
	}
	
	/**
	 * 更新上报状态
	 * @param colProductRequirement001Dto
	 */
	public void updateReportStatus(ReportProductRequirementDto reportProductRequirementDto) {
		reportProductRequirementMapper.updateReportStatus(reportProductRequirementDto);
		return;
	}
	
	/**
	 * 根据ID查询产品收集附属表是否存在
	 * @param requirementId
	 * @return
	 */
	public int countByRequirementId(String requirementId) {
		return reportProductRequirementMapper.countByRequirementId(requirementId);
	}
}
