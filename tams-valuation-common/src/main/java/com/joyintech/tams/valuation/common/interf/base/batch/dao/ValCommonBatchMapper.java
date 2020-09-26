/*
 * 文件名：ValFinProductBatchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：魏鹏
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;


@Repository
public interface ValCommonBatchMapper {
	//估值核算交易扩展表
    int deleteProductDealExt(Map map);
    
    //交易费用明细表
    int deleteTradeFeeDetail(Map map);
    
    //入账账务明细表
    int deleteDealAccountingDetail(Map map);
    
    /** modify by wujie on 20190123 remark:重复导入删除逻辑调整
    int deleteDealAccountingDetailSingle(List<BokDealAccountingDetail> list);
    **/
    
    //入账信息表
    int deleteDealAccounting(Map map);
    
    /** modify by wujie on 20190123 remark:重复导入删除逻辑调整
    int deleteDealAccountingSingle(List<BokDealAccounting> list);
    **/
    
    //结算指令表 
    int deleteOrders(Map map);
    
    /** modify by wujie on 20190123 remark:重复导入删除逻辑调整
    int deleteOrdersSingle(Map map);
    **/
    
    //结算指令扩展表
    int deleteOrdersExt(Map map);
    
    /** modify by wujie on 20190123 remark:重复导入删除逻辑调整
    int deleteOrdersExtSingle(List<StlOrdersExt> list);
    **/
    
    //金融产品交易
    int deleteProductDeal(Map map);
    
    //金融产品分类表
    int deleteProductType(Map map);
    
    //交易类金融产品表
    int deleteTradeFinProduct(Map map);
    
    //质押品信息表
    int deleteFinDealPledge(Map map);
    
    //删除现金流信息 --根据金融产品代码、金融产品类型
    int deleteFinCashInfo(List<FinTradeProduct> list);
    
    //删除计算规则表 --根据金融产品代码、金融产品类型
    int deleteFinCashCalcRule(List<FinTradeProduct> list);
    
    //删除支付规则表 --根据金融产品代码、金融产品类型
    int deleteFinCashPayRule(List<FinTradeProduct> list);
    
    //删除现金流计划表 --根据金融产品代码、金融产品类型
    int deleteFinCashPlan(List<FinTradeProduct> list);
    
    //删除质押品信息表 --根据金融产品代码、金融产品类型
    int deleteBatchPledge(List<FinDealPledge> list);
    
    //删除质押品信息表 --根据金融产品代码
    int deleteBatchPledgeById(List<FinDealPledge> list);
    
    //删除现金流计划表 --根据现金流代码
    int deleteFinCashPlanByCashId(List<FinCashPlan> list);
    
    /**
     * 查询待删除交易
     * add by wujie on 20190121
     */
    List<TrdProductDeal> queryUnDelDeal(Map map);
    
    
    //估值核算交易扩展表--根据交易编号
    int delProductDealExtByTradeId(List<TrdProductDeal> list);
    
    //交易费用明细表--根据交易编号
    int delTradeFeeDetailByTradeId(List<TrdProductDeal> list);
    
    //入账账务明细表--根据交易编号
    int delDealAccountingDetailByTradeId(List<TrdProductDeal> list);
    
    //入账信息表--根据交易编号
    int delDealAccountingByTradeId(List<TrdProductDeal> list);
    
    //结算指令扩展表--根据交易编号
    int delOrdersExtByTradeId(List<TrdProductDeal> list);
    
    //结算指令表 --根据交易编号
    int delOrdersByTradeId(List<TrdProductDeal> list);
    
    //金融产品交易
    int delProductDealByTradeId(List<TrdProductDeal> list);

    //金融产品实物交易
    int delTrdFinProductBarterByTradeId(List<TrdProductDeal> list);

    /**
     * 查询待删除结算指令交易
     * add by wujie on 20190123
     */
    List<StlOrders> queryUnDelStlOrders(Map map);
    
    //入账账务明细表--根据指令编号
    int delDealAccountingDetailByOrderId(List<StlOrders> list);
    
    //入账信息表--根据指令编号
    int delDealAccountingByOrderId(List<StlOrders> list);
   
    //结算指令扩展表--根据指令编号
    int delOrdersExtByOrderId(List<StlOrders> list);
    
    //结算指令表 --根据指令编号
    int delOrdersByOrderId(List<StlOrders> list);

    //删除现金流计划表 --根据现金流代码
//    int deleteFinCashPlanByCashId(List<FinCashPlan> list);

	void deleteSingleProductDealExt(List<String> needDelTradeId);

	void deleteSingleTradeFeeDetail(List<String> needDelTradeId);

	void deleteSingleDealAccountingDetail(List<String> needDelTradeId);

	void deleteSingleDealAccounting(List<String> needDelTradeId);

	void deleteSingleOrdersExt(List<String> needDelTradeId);

	void deleteSingleOrders(List<String> needDelTradeId);
	//删除产品交易表
	void deleteSingleProductDeal(List<String> needDelTradeId);
    /**
	 * 查询需要删除的交易数据
	 * @param paramMap
	 * @return
	 */
	List<String> queryNeedDelTradeId(Map<String, Object> paramMap);
}
