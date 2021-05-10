/*
 * 文件名：TrdDepoDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdassetlossclear.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdassetlossclear.dto.TrdAssetLossClearDto;
import com.joyintech.service.period.trddepoduration.dto.TrdDepoDurationDto;
import com.joyintech.tams.common.base.entity.EtrSecPositionFlow;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年10月11日
 * @see TrdAssetLossClearMapper
 * @since 1.0
 */
@Repository
public interface TrdAssetLossClearMapper {

	/**
	 * 画面查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> findPage(Map<String, Object> param);
	
	/**
	 * 撤销查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	TrdAssetLossClearDto findUndoInformation(Map<String, Object> param);
	
	   /**
     * 撤销查询方法
     * <功能详细描述>
     * @param param 界面参数
     * @return 查询的数据结果
     * @see
     * @since 1.0
     */
    List<EtrSecPositionFlow> getEtrSecPositionFlow(Map<String, Object> param);
    /**
      * 撤销查询方法
      * <功能详细描述>
      * @param param 界面参数
      * @return 查询的数据结果
      * @see
      * @since 1.0
      */
    List<PtlSecPositionFlow> getPtlSecPositionFlow(Map<String, Object> param);
    /**
     * 撤销查询方法
     * <功能详细描述>
     * @param param 界面参数
     * @return 查询的数据结果
     * @see
     * @since 1.0
     */
   List<PtlRecePositionFlow> getPtlRecePositionFlow(Map<String, Object> param);
	/**
	 * 画面查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> findNewPage(Map<String, Object> param);
	
	/**
	 * 画面查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> selectDetail(Map<String, Object> param);
	
	/**
	 * 画面查询方法
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> selectByKey(Map<String, Object> param);
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
	List<SelectOptionDto>  finprodMarketId(Map<String, Object> param);
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
	 * 通道查询 
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	int selectChannelQuery(Map<String,Object> map);
	/**
	 * 证券账户查询
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	int selectSecuritiesAccountEnquiry(Map<String,Object> map);
	/**
	 * 应收款查询
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> selectAccountReceivableEnquiry(Map<String,Object> map);
	
	/**
	 * 根据交易编号查询金融产品交易汇总表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	TrdFinProductDealTotal selectTfpdtById(Map<String,Object> map);
	
	/**
	 * 根据交易编号查询金融产品交易汇总表2
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdFinProductDealTotal> selectTfpdtById2(Map<String,Object> map);
	/**
	 * 根据交易编号查询金融产品交易汇总附属表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	TrdDealTotalAdd selectTdtaById(Map<String,Object> map);	
	/**
	 * 根据交易编号查询金融产品交易汇总附属表2
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdDealTotalAdd> selectTdtaById2(Map<String,Object> map);
	/**
	 * 根据交易编号查询金融产品交易表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdProductDeal> selectTpdById(Map<String,Object> map);
	/**
	 * 根据交易编号查询金融产品交易附属表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<TrdProductDealAdd> selectTpdaById(Map<String,Object> map);
	
	/**
	 * 根据交易编号查询组合交易表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	List<PtlTrade> selectPtById(Map<String,Object> map);
	
	/**
     * 获取自增序列值
     * @param sequenceName 序列名
     * @return
     */
    String getNextId(@Param("sequenceName")String sequenceName);
    
	/**
	 * 更新金融产品交易汇总表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
    List<TrdAssetLossClearDto> selectRecType(Map<String,Object> map);
    
	/**
	 * 更新金融产品交易汇总表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	void updateTfpdtById(Map<String,Object> map);
	
	/**
	 * 更新金融产品交易汇总表2
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	void updateTfpdtById2(Map<String,Object> map);
	
	/**
	 * 更新金融产品交易表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	void updateTpdById(Map<String,Object> map);
	
	/**
	 * 根据交易编号查询组合交易表
	 * <功能详细描述>
	 * @param map 画面参数
	 * @return 查询结果
	 * @see
	 * @since 1.0
	 */
	int selectAredyLossAsset(Map<String,Object> map);
	/**
	 * 指令持仓流水查询
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> selectEspf(Map<String, Object> param);
	/**
	 * 组合持仓流水查询
	 * <功能详细描述>
	 * @param param 界面参数
	 * @return 查询的数据结果
	 * @see
	 * @since 1.0
	 */
	List<TrdAssetLossClearDto> selectPspf(Map<String, Object> param);
}
