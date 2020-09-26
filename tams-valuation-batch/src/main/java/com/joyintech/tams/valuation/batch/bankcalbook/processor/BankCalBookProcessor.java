/*
 * 文件名：BankCalBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：Apr 10, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.bankcalbook.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.bankcalbook.dto.BankCalBookComResult;
import com.joyintech.tams.valuation.batch.bankcalbook.service.BankCalBookProcessorService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 银行账计提账务处理 Processor
 * 
 * @author zhangwei
 * @version 1.0 Apr 10, 2019
 * @see BankCalBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class BankCalBookProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private BankCalBookProcessorService bankCalBookProcessorService;

    @Override
    public BookGenerateResult process(NeedOperateEntity item)
        throws Exception {

        BankCalBookComResult acctEntity = (BankCalBookComResult)item;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
        BookGenerateResult result = bankCalBookProcessorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new BookGenerateResult();
        }

        result.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBooksetId()); // 标识

        return result;
    }

}
