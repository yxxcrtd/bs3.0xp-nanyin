/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.cashflowdiscount.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto.CashFlowDiscountReaderResult;
import com.joyintech.tams.model.valuation.batch.cashflowdiscount.service.CashFlowDiscountReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 现金流贴现批处理(读取)
 * @author zznn
 * @date 20210706
 */
@Service
@Scope("prototype")
public class CashFlowDiscountTaskReader extends BaseReader<CashFlowDiscountReaderResult> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private CashFlowDiscountReaderService cashFlowDiscountReaderService;
    

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<CashFlowDiscountReaderResult> createList() {
        return cashFlowDiscountReaderService.execute(getParams());
    }

}
