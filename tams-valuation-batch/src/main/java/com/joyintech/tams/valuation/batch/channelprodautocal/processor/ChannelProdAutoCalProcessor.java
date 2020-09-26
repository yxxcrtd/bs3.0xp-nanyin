/*
 * 文件名：ProdPassAssetBonusProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年3月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.channelprodautocal.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.channelprodautocal.service.ChannelProdAutoCalProcessService;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.BookGenerateResult;
import com.joyintech.tams.valuation.batch.common.accountingmethod.entity.ValBooksetTodoDto;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;


/**
 * 通道产品自动结转Processor
 * @author weipeng
 * @version 1.0 2019年3月15日
 * @see ChannelProdAutoCalProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ChannelProdAutoCalProcessor extends BaseProcessor<NeedOperateEntity, BookGenerateResult> {

    @Autowired
    private ChannelProdAutoCalProcessService dealService;

    /**
     * {@inheritDoc}
     */
    @Override
    public BookGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        ValBooksetTodoDto acctEntity = (ValBooksetTodoDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        thisParams.put(BookConstant.TASK_PRI, this.getBatchTask().getTaskPri());

        // 调用service方法进行逻辑逻辑
        BookGenerateResult serviceDealResult = dealService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(serviceDealResult == null){
            serviceDealResult = new BookGenerateResult();
        }

        serviceDealResult.setProdId(acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());
        return serviceDealResult;
    }
}

