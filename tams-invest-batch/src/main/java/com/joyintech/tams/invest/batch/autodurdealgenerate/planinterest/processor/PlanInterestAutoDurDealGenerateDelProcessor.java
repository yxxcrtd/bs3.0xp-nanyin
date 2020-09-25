/*
 * 文件名：PlanInterestAutoDurDealGenerateDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.dto.PlanInterestAutoDurDealGenerateResult;

/**
 * DelProcessor
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see PlanInterestAutoDurDealGenerateDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlanInterestAutoDurDealGenerateDelProcessor extends BaseProcessor<NeedOperateEntity, PlanInterestAutoDurDealGenerateResult> {

    /**
     * {@inheritDoc}
     */
    @Override
    public PlanInterestAutoDurDealGenerateResult process(NeedOperateEntity item)
        throws Exception {
        PlanInterestAutoDurDealGenerateResult result = new PlanInterestAutoDurDealGenerateResult();
        result.setProdId("计划类资产利率型自动存续期删除处理");
        return result;
    }
}
