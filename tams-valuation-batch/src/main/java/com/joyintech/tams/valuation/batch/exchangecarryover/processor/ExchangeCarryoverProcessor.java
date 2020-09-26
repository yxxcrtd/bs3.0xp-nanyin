package com.joyintech.tams.valuation.batch.exchangecarryover.processor;

import com.google.common.collect.Maps;
import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.exchangecarryover.dto.ExchangeCarryoverEntity;
import com.joyintech.tams.valuation.batch.exchangecarryover.service.ExchangeCarryoverProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 汇兑损益结转
 * 
 * @author zhangwei
 * @createTime 2019-07-08 11:06
 */
@Service
@Scope("prototype")
public class ExchangeCarryoverProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    ExchangeCarryoverProcessorService processorService;

    @Override
    public BookGenerateResult process(NeedOperateEntity entity)
        throws Exception {

        // 获取 reader 方法的返回值
        ExchangeCarryoverEntity acctEntity = (ExchangeCarryoverEntity)entity;

        Map<String, Object> params = Maps.newHashMap();
        params.putAll(this.getParams());
        params.put(GlobalConstant.READER_RESULT, acctEntity); // 将reader返回值塞入参数中

        // 业务逻辑处理
        BookGenerateResult result = processorService.execute(params);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new BookGenerateResult();
        }

        // 标识
        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBooksetId());

        return result;
    }

}
