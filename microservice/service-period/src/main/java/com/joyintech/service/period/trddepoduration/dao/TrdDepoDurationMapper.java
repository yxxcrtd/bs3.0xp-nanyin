/*
 * 文件名：TrdDepoDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trddepoduration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trddepoduration.dto.TrdDepoDurationDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年10月11日
 * @see TrdDepoDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdDepoDurationMapper {

	/**
	 * 画面查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdDepoDurationDto> findPage(Map<String, Object> param);
	/**
	 * 
	 * 获取代办数据
	 * <功能详细描述>
	 * @param param 系统参数
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
	TrdDepoDurationDto findEffectiveData(Map<String, Object> param);
	/**
	 * 
	 * 获取代办数据
	 * <功能详细描述>
	 * @param param 系统参数
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
	TrdDepoDurationDto findEndData(Map<String, Object> param);
	/**
	 * 判断其在现金流对账模块是否完成匹配
	 * <功能详细描述>
	 * @param busiCode 交易编号
	 * @return 统计结果
	 * @see
	 * @since 1.0
	 */
	int countCashMatch(String busiCode);
	/**
	 * 
	 * 市场机构代码查询
	 * <功能详细描述>
	 * @param param 参数
	 * @return 市场机构代码
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);
	
	//add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息start
	Map<String, Object> getTradeId(Map<String, Object> map);
	//add by yujiaping 2020-12-5 BS-1562【存款类存续期】同一资产同一计息结束日只能做一次活期存放结息end
    /**
     * 
     * 市场代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
	Map<String, Object> finprodMarketIdLink(Map<String, Object> map);
	/**
     * 
     * 组合代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
	Map<String, Object> portfolioIdLink(Map<String, Object> map);
	/**
	 *
	 * 组合代码联动
	 * add by jiangpeng on 20201215 ZGBS-69
	 * @param map 参数
	 * @return 联动的详情数据
	 * @see
	 * @since 1.0
	 */
	Map<String, Object> getPortfolioName(Map<String, Object> map);
	/**
	 * 剩余本金计算联动
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	Map<String, Object> calResiPrincipal(Map<String, Object> map);
	/**
	 * 组合代码下拉框
	 * <功能详细描述>
	 * @param param 画面参数
	 * @return 下拉菜单
	 * @see
	 * @since 1.0
	 */
	List<SelectOptionDto>  getPortfolioId(Map<String, Object> param);
	/**
	 * 查询利息明细信息
	 * <功能详细描述>
	 * @param params 参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

	/**
	 * 剩余本金计算联动（活期存放）
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	Map<String, Object> calResiPrincipalDemand(Map<String, Object> map);

	/**
	 * 理论应计利息计算联动（活期存放）
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	Map<String,Object> calTheoryIntDemand(Map<String, Object> map);

	int countTradeForDemand(Map<String,Object> map);
	
	/**
     * 撤销时根据汇总表的主键查询授信交易编号
     *findFinprodIdByBusiCode
     */
    List<String> findCeTradeIdByTotleTradeId(String totleTradeId);
	//add by liupengfei for 多级法人改造 S
	List<SelectOptionDto> getBusiCode(Map<String, Object> param);
	//add by liupengfei for 多级法人改造 E
}
