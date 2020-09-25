/*
 * 文件名：ExchangeAutoDurDealGenerateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service.ExchangeAutoDurDealGenerateProcessorService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * 外汇远期掉期到期交易自动生成Processor
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExchangeAutoDurDealGenerateProcessor extends BaseProcessor<NeedOperateEntity, ExchangeAutoDurDealGenerateResult> {

    /**
     * ExchangeAutoDurDealGenerateProcessorService
     */
    @Autowired
    private ExchangeAutoDurDealGenerateProcessorService processorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ExchangeAutoDurDealGenerateResult process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        ExchangeAutoDurDealGenerateReaderResult readerResult = (ExchangeAutoDurDealGenerateReaderResult)item;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(BatchConstant.READER_RESULT, readerResult);

        // 调用service方法进行逻辑逻辑
        ExchangeAutoDurDealGenerateResult result = processorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new ExchangeAutoDurDealGenerateResult();
        }

        return result;
    }

}
