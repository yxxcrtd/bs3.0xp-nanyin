package com.joyintech.service.product.rddesgin.reportproductrequirement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.rddesgin.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.service.product.rddesgin.reportproductrequirement.dto.ReportProductRequirementDto;

@Repository
public interface ReportProductRequirementMapper {
	
	/**
	 * 产品收集查询页
	 * @param param
	 * @return
	 */
	List<ReportProductRequirementDto> selectForPage(Map<String, Object> param);
	
	/**
	 * 根据Id查询
	 * @param requirementId
	 * @return
	 */
	ReportProductRequirementDto queryRequirementByRequireId(@Param("requirementId")String requirementId,@Param("dataStatus")String dataStatus);
	
	/**
	 * 新建产品附属表
	 * @param colProductRequirement001Dto
	 */
	void insertColProductRequirementAdd(ReportProductRequirementDto reportProductRequirementDto);
	
	/**
	 * 根据Id删除
	 * @param requirementId
	 * @return
	 */
	void deleteColProductRequirement(String requirementId);
	
	/**
	 * 需求上报状态更新
	 * @param colProductRequirement001Dto
	 */
	void updateReportStatus(ReportProductRequirementDto reportProductRequirementDto);
	
	/**
	 * 根据ID查询产品收集附属表是否存在
	 * @param requirementId
	 * @return
	 */
	int countByRequirementId(String requirementId);
}
