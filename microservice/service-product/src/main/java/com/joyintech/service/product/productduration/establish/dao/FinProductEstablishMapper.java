/*
 * 文件名：FinProductEstablishMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productduration.establish.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishBranchDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishLayerDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishShowDto;


/**
 * 产品成立
 * 
 * @author zhangliang
 * @version 1.0 2018年12月17日
 * @see FinProductEstablishMapper
 * @since 1.0
 */
@Repository
public interface FinProductEstablishMapper {
    
    /**
     * 产品成立信息查询
     * @param map 参数
     * @return 成立信息
     */
    List<FinProductEstablishDto> productEstablishInfoQuery(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询费用列表
     * @param map 参数
     * @return 费用列表
     */
    List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询分层金额明细
     * @param map 参数
     * @return 分层金额明细
     */
    List<FinProductEstablishLayerDto> queryLayAmtDetailByFinprodId(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询分层金额明细
     * @param bussId 交易编号
     * @return 分层金额明细
     */
    List<FinProductEstablishLayerDto> queryLayAmtDetailByBussId(String bussId);
    
    /**
     * 根据金融产品代码查询募集金额明细
     * @param map 参数
     * @return 募集金额明细
     */
    PaTrdFinProductDealTotal queryRaiseAmtInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询分行募集明细
     * @param map 参数
     * @return 分行募集明细
     */
    List<FinProductEstablishBranchDto> queryBranchRaiseInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 根据金融产品代码查询分行募集明细
     * @param bussId 交易编号
     * @return 分行募集明细
     */
    List<FinProductEstablishBranchDto> queryBranchRaiseInfoByBussId(String bussId);
    
    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    List<SelectOptionDto> queryBranchInfoIdList(Map<String, Object> map);
    
    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    List<SelectOptionDto> queryBranchInfoNameList(Map<String, Object> map);
    
    /** 删除交易类金融产品
     * @param map 参数
     * @return DB
     */
    int deleteFinTradeProductByFinprodId(Map<String, Object> map);
    
    /** 删除现金流信息
     * @param map 参数
     * @return DB
     */
    int deleteFinCashInfoByFinprodId(Map<String, Object> map);
    
    /** 删除现金流计算规则信息
     * @param map 参数
     * @return DB
     */
    int deleteFinCashCalcRuleByFinprodId(Map<String, Object> map);
    
    /**
     * 产品代码联想下拉框(发行成功)
     * 
     * @param map 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> map);
    
    /**
	 * 根据金融产品代码查询产品信息
	 * @param map 参数
	 * @return 产品信息
	 */
	FinProductCommonInfoDto queryProdInfoByFinprodId(Map<String, Object> map);
	
	FinProductEstablishShowDto selectProdInfoByFinprodId(Map<String, Object> map);
	
	/**
	 * 查询交易类金融表Branch是否在
	 * @param map
	 * @return
	 */
	Integer selectIsBranch(Map<String, Object> map);
	
	Integer selectIsTrade(Map<String, Object> map);
	/**
	 * 查询产品是否做过其他交易
	 * @param finprodId
	 * @return
	 */
	Integer selectDealAdd(Map<String, Object> map);
	/**
	 * 查看除当前认购交易外是否还有其他认购交易
	 * @param map
	 * @return
	 */
	Integer selectOtherSurDeal(Map<String, Object> map);
}
