/*
 * 文件名：InterfCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokAcctLayeringInfo;
import com.joyintech.tams.common.base.entity.BokBooksetPlan;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.valuation.common.interf.base.dto.BokBooksetSonDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ValChannelProdDealDto;

@Repository
public interface ChannelCommonMapper {
    /**
     * 查询子账套信息
     */
	List<BokBooksetSonDto> queryBokBooksetSonInfo(Map<String, Object> map);
    /**
     *  删除标的类金融产品表信息
     * */
	int delValFinProductBatch(List<ValChannelProdDealDto> list);
    /**
     *  删除标的类金融产品扩展表信息、
     * */
	int delValFinProductExtBatch(List<ValChannelProdDealDto> list);
	/**
     *删除金融产品估值参数表
     *add by wujie on 20190325
     * */
	int delFinProductValuationPara(List<ValChannelProdDealDto> list);
	
	/**
     *删除标的类金融产品类型表信息
     * 
     * */

	int delValFinProductTypeBatch(List<ValChannelProdDealDto> list);
	
	/**
     *删除母子账套关系表信息
     * 
     * */

	int delBokBooksetReBatch(List<ValChannelProdDealDto> list);
	/**
     *删除账套日期表信息
     * 
     * */
	int delBokBooksetDateBatch(List<ValChannelProdDealDto> list);
	
	/**
	 * 删除账套配置List
	 * @param list
	 * @return
	 */
	int delBokBooksetExtBatch(List<ValChannelProdDealDto> list);
	
	/**
     *删除核算主体信息表信息
     * 
     * */
	int delBokAcctSubjectInfoBatch(List<ValChannelProdDealDto> list);

	/**
     *	删除核算分层信息表信息
     * 
     * */
	int delBokAcctLayeringInfoBatch(List<ValChannelProdDealDto> list);
	/**
     *删除估值方案、税务方案
     * 
     * */

	int delBokBooksetPlanBatch(List<ValChannelProdDealDto> list);
	/**
     * 	删除现金流信息表信息(费用、增值税)
     * */
	int delFinCashInfoBatch(List<ValChannelProdDealDto> list);
	/**
     * 删除现金流信息计算规则表信息（费用）
     * */

	int delfinCashCalcRuleBatch(List<ValChannelProdDealDto> list);
	/**
     * 	删除金融产品账户表
     * */
	int delStlFinProductAcctBatch(List<ValChannelProdDealDto> list);
	/**
     * 	删除账套清算款账户表
     * */
	int delBokBooksetCapacctInfoBatch(List<ValChannelProdDealDto> list);
    /**
     * 查询子账套信息
     */
	List<BokBooksetSonDto> queryBokBooksetReInfo(Map<String, Object> map);
    /**
     * 更新子账套的账套日期信息
     */
	int updateBokBooksetReInfo(Map<String, Object> map);
	/**
     * 	更新子账套核算分层信息
     */
	int updateBokAcctSubjectInfo(Map<String, Object> map);
	/**
     * 获取默认方案
     */
	BokBooksetPlan queryBokBooksetPlan();
	/**
     * 	税务方案信息-前台推送
     */
	BokBooksetPlan queryFinProductValuationPara(@Param("finprodId")String finprodId);
	/**
     * 	查询通道交易
     */
	ValChannelProdDealDto queryChannelProdDeal(Map<String, Object> map);
	/**
     * 查询接口平台日期更新的通道现金流计算规则信息
     */
	List<FinCashInfo> queryFinCashCalcRuleList(Map<String, Object> map);
	/**
	 * 通道账套对应的母账套查询
	 */
	List<BokAcctLayeringInfo> queryBokBooksetReList(Map<String, Object> map);

	/**
	 * 查询母子账套关系
	 * @param map
	 * @return
	 */
	/**add by yanya on 20190104**/
	List<BokBooksetSonDto> queryBokBooksetRelationship(Map<String, Object> map);
   
}