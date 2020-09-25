package com.joyintech.service.product.productplanning.tacticconfig.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.service.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Repository
public interface TacticConfigMapper {
	
	/**
	 * 资产大类下拉框
	 * @param param
	 * @return
	 */
	List<SelectOptionDto> selectAllAssetType(Map<String, Object> param);
	
	/**
	 * 插入战术配置优化
	 * @param tacticConfig002Dto
	 */
	void insertTacticConfigOpt(TacticConfig002Dto tacticConfig002Dto);
	
	/**
	 * 根据配置Id删除战术配置明细表
	 * @param tacticConfigCode
	 */
	void deleteTacticConfigByCode(String tacticConfigCode);
	
	/**
	 * 根据配置Id和资产大类删除战术配置明细
	 * @param tacticConfig002Dto
	 */
	void deleteTacticConfig(TacticConfig001Dto tacticConfig001Dto);
	
	/**
	 * 根据配置Id查询战术配置明细列表
	 * @param tacticConfigCode
	 * @return
	 */
	List<TacticConfigOptDetail> queryByCode(String tacticConfigCode);
	
	/**
	 * 查询展示列表
	 * @param param
	 * @return
	 */
	List<TacticConfig001Dto> selectForPage(Map<String, Object> param);
	
	/**
	 * 查询最新一条战术配置
	 * @return
	 */
	TacticConfig002Dto selectFirstTacticConfigOpt();
	
	/**
	 * 根据战术配置ID查询战术配置
	 * @param tacticConfigCode
	 * @return
	 */
	TacticConfig002Dto selectByTacticConfigCode(String tacticConfigCode);
}
