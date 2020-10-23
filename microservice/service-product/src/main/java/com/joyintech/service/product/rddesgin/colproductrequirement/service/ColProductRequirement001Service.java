package com.joyintech.service.product.rddesgin.colproductrequirement.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.colproductrequirement.dao.ColProductRequirementMapper;
import com.joyintech.service.product.rddesgin.colproductrequirement.dto.ColProductRequirement001Dto;


/**
 * 产品需求收集service
 * 
 * @author yaozhenguo
 * @version 1.0 2018年8月21日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Service("backColProductRequirement001Service")
public class ColProductRequirement001Service extends BaseService<ColProductRequirement001Dto> {

	@Autowired
	private ColProductRequirementMapper colProductRequirementMapper;
	
	/**
	 * 需求收集页面查询
	 * @param page
	 * @param length
	 * @param param
	 * @return
	 */
	public Page<ColProductRequirement001Dto> selectForPage(int page, int length, Map<String, Object> param){
		
		return queryForPage(page,length, ()->{
			colProductRequirementMapper.selectForPage(param);
		});
	}
	
	/**
	 * 根据需求ID查询
	 * @param requirementId
	 * @return
	 */
	public ColProductRequirement001Dto queryRequirementByRequireId(String requirementId) {
		return colProductRequirementMapper.queryRequirementByRequireId(requirementId);
	}
	
	/**
	 * 产品收集新建插入
	 * @param colProductRequirement001Dto
	 * @throws Exception
	 */
	public void insertColProductRequirement(ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirementMapper.insertColProductRequirement(colProductRequirement001Dto);
		return;
	}
	
	/**
	 * 根据需求ID删除
	 * @param requirementId
	 * @return
	 */
	public void deleteColProductRequirement(String requirementId) {
		colProductRequirementMapper.deleteColProductRequirement(requirementId);
		return;
	}
	
	/**
	 * 更新需求
	 * @param colProductRequirement001Dto
	 */
	public void updateColProductRequirement(ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirementMapper.updateColProductRequirement(colProductRequirement001Dto);
		return;
	}
	
	/**
	 * 逻辑删除
	 * @param requirementId
	 */
	public void deleteByRequirementId(ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirementMapper.deleteByRequirementId(colProductRequirement001Dto);
		return;
	}
	
	/**
	 * 发起初评
	 * @param requirementId
	 */
	public void reviewStart(ColProductRequirement001Dto colProductRequirement001Dto) {
		colProductRequirementMapper.reviewStart(colProductRequirement001Dto);
	}
}
