package com.joyintech.service.product.rddesgin.productSimulationTrial.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.productSimulationTrial.dto.ProductSimulationTrialDto;
import com.joyintech.tams.common.base.entity.MockResultInfo;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;

@Repository
public interface ProductSimulationTrialMapper {

	/**
	 * 产品设计系列编号下拉框
	 * @param paramMap 参数
	 * @return 下拉框列表
	 * @throws Exception
	 */
	List<SelectOptionDto> queryDesignIdInfo(Map<String, Object> paramMap);

	/**
	 * 分页查询画面模拟试算数据
	 * @param pageParam 参数
	 * @return 模拟试算数据
	 * @see
	 * @since 1.0
	 */
	List<ProductSimulationTrialDto> findPage(Map<String, Object> pageParam);
	
	/**
	 * 从模拟试算结果获取产品规模信息
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<MockResultInfo> queryPrdScaleFromMockResultInfo(Map<String, Object> param);
	
	/**
	 * 
	 * 从产品要素设计信息获取产品规模信息
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	MockResultInfo queryPrdScaleFromDesignInfo(Map<String, Object> param);
	
	/**
	 * 
	 * 获取利率信息
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<MstIntRateInfo> queryMstIntRateInfo(Map<String,Object> param);
	
	/**
	 * 删除模拟试算交易
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @see
	 * @since 1.0
	 */
	void deleteMockTradeDeal(Map<String,Object> param);
	
	/**
	 * 删除模拟试算交易利率
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @see
	 * @since 1.0
	 */
	void deleteMockTradeRate(Map<String,Object> param);
	
	/**
	 * 通过流水号获取产品模拟试算结果
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	ProductSimulationTrialDto getProductSimulationTrialBySeqNo(Map<String, Object> paramMap);
	
	/**
	 * 获取模拟试算交易
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<ProductSimulationTrialDto> findTablePage(Map<String, Object> paramMap);
	
	/**
	 * 获取新的批次号
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */
	String getNewBacthId();
	
	/**
	 * 浮动利率时获取浮动利率下拉框
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param paramMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> getIndexIdSource(Map<String, Object> paramMap);
}
