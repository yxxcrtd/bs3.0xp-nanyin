/*
 * 文件名：MoneyTaAutoCalWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.moneytaautocal.writer;

import java.util.List;

import com.joyintech.tams.valuation.batch.common.accountingmethod.accounting.BookCommonLogic;
import com.joyintech.tams.valuation.batch.moneytaautocal.dto.TradeInofoGenerateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 货币型产品自动结转处理
 * 
 * @author zhangwei
 * @version 1.0 May 25, 2019
 * @see MoneyTaAutoCalWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class MoneyTaAutoCalWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private BookCommonLogic bookCommonLogic;
    
    
    @Override
    @SuppressWarnings("unchecked")
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {

        // 获取process返回组合的list值
        List<TradeInofoGenerateResult> resultList = (List<TradeInofoGenerateResult>)items;

        for (TradeInofoGenerateResult result : resultList) {
            
            // 金融产品交易表 数据插入
            bookCommonLogic.insertValFinProductDeal(result.getListTrdProductDeal());

            // 估值核算交易扩展表 数据插入
            bookCommonLogic.insertValFinProductDealExt(result.getListTrdProductDealExt());
            
            // 入账信息表 数据插入
            bookCommonLogic.insertValDealAccounting(result.getListBokDealAccounting());

            // 入账账务明细表 数据插入
            bookCommonLogic.insertValDealAccountingDetail(result.getListBokDealAccountingDetail());

        }

    }

}
