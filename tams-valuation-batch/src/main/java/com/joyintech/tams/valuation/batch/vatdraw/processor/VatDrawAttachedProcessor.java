/*
 * 文件名：ValAddedTaxDealProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.vatdraw.dto.PrdCashDto;
import com.joyintech.tams.valuation.batch.vatdraw.dto.VatBatchResult;
import com.joyintech.tams.valuation.batch.vatdraw.service.VatDrawAttachedProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 增值税计提批处理Processor
 * 
 * @author weipeng
 * @version 1.0 2018年7月31日
 * @see VatDrawAttachedProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class VatDrawAttachedProcessor extends BaseProcessor<NeedOperateEntity, VatBatchResult> {

    /**
     * processor方法的 Service
     */
    @Autowired
    private VatDrawAttachedProcessorService vatDrawAttachedProcessorService;

    @Override
    public VatBatchResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        PrdCashDto acctEntity = (PrdCashDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        // 调用service方法进行逻辑逻辑
        VatBatchResult result = vatDrawAttachedProcessorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new VatBatchResult();
        }

        result.setProdId(
            acctEntity.getBooksetId() + GlobalConstant.SPLIT + acctEntity.getBokDetailId());

        return result;
    }

}
