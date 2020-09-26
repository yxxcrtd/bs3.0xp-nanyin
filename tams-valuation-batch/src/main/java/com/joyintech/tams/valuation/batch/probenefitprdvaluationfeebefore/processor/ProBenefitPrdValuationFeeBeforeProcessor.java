/*
 * 文件名：ProBenefitPrdValuationFeeBeforeProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.dto.PrdValuationFeeBeforeReaderDto;
import com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.dto.PrdValuationFeeBeforeWriterDto;
import com.joyintech.tams.valuation.batch.probenefitprdvaluationfeebefore.service.ProBenefitPrdValuationFeeBeforeProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * (预期收益型产品估值计算-费前) - process
 * 
 * @author niuqianyu
 * @version 1.0 2018年9月28日
 * @see ProBenefitPrdValuationFeeBeforeProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProBenefitPrdValuationFeeBeforeProcessor extends BaseProcessor<NeedOperateEntity, PrdValuationFeeBeforeWriterDto> {

    @Autowired
    private ProBenefitPrdValuationFeeBeforeProcessorService proBenefitPrdValuationFeeBeforeProcessorService;

    @Override
    public PrdValuationFeeBeforeWriterDto process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        PrdValuationFeeBeforeReaderDto acctEntity = (PrdValuationFeeBeforeReaderDto)items;
        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);
        PrdValuationFeeBeforeWriterDto result = proBenefitPrdValuationFeeBeforeProcessorService.execute(
            thisParams);
        result.setProdId(acctEntity.getBooksetId());
        return result;
    }
}
