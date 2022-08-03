package com.joyintech.tams.disclosure.busicommon.infodisclosure.getprdinfoselect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

@Repository
public interface GetPrdInfoSelectMapper {

	List<SelectOptionDto> getPrdInfoSelect(Map<String, Object> paraMap);
	
	List<SelectOptionDto> getCusInfoSelect(Map<String, Object> paraMap);

	List<SelectOptionDto>	getInfoSelect();

	/**
	 * 获取所有的正常状态的产品信息，包含母产品和子产品，支持根据产品代码和产品名称 进行模糊查询
	 * 支持筛选只查询 母产品 sonFlag 标识不为空
	 * @param paraMap
	 * @return
	 */
	List<SelectOptionDto> getAllPrdInfoSelect(Map<String, Object> paraMap);


	/**
	 * 获取所有的正常状态的产品信息，包含母产品和子产品，支持根据产品代码和产品名称 进行模糊查询
	 * 支持筛选只查询 母产品 sonFlag 标识不为空
	 * @param paraMap
	 * @return
	 */
	List<SelectOptionDto> getAllPrdInfoSelect2(Map<String, Object> paraMap);

	List<SelectOptionDto> getPProdIdList(Map<String, Object> paraMap);

	List<SelectOptionDto> getProdIdList(Map<String, Object> paraMap);

	List<SelectOptionDto> getFeeNameList(Map<String, Object> paraMap);

	List<SelectOptionDto> getElementNameList(Map<String, Object> paraMap);

	/**
	 * 获取所有的正常状态的产品信息，包含母产品和子产品，支持根据产品代码和产品名称 进行模糊查询
	 * 支持筛选只查询 母产品 sonFlag 标识不为空
	 * @param paraMap
	 * @return
	 */
	List<SelectOptionDto> getMontherAndCommonProdId(Map<String, Object> paraMap);

}
