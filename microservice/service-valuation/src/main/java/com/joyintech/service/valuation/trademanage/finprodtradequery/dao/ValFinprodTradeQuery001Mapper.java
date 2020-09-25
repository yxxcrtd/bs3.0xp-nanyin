/*
 * 文件名：ValFinprodTradeQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.trademanage.finprodtradequery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.service.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;

/**
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Mapper
 * @since 1.0
 */
@Repository(value="serviceValFinprodTradeQuery001Mapper")
public interface ValFinprodTradeQuery001Mapper {

    /**
     * 分页查询
     * 
     * @param params 参数
     * @return 金融产品交易结果集
     * @see
     * @since 1.0
     */
    List<ValFinprodTradeQuery001Dto> queryPageList(Map<String, Object> params);

    /**
     * 取得随交易生成的 指令编号
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<String> getOrderIdList(Map<String, Object> params);

    /**
     * 取得随交易生成的指令的账务明细代码 列表
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<String> getBokDetailIdList(Map<String, Object> params);

    /**
     * 删除交易费用明细表
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    int deleteTgTradeFeeDetail(Map<String, Object> params);

    /**
     * 删除结算指令扩展表
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    int deleteStlOrdersExt(Map<String, Object> params);

    /**
     * 删除结算指令表
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    int deleteStlOrders(Map<String, Object> params);

    /**
     * 删除入账账务明细表
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokDealAccountingDetail(Map<String, Object> params);

    /**
     * 删除入账信息表
     * 
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokDealAccounting(Map<String, Object> params);
    
    /**
     * 删除金融产品实物交易表
     * 
     * @return
     * @see
     * @since 1.0
     */
    int deleteTrdFinProductBarter(Map<String, Object> params);
    
    /**
     * 判断是否在流程中的数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param processMark
     * @return
     */
    List<String> getBusinessDataInFlow(ProcessMark processMark);
}
