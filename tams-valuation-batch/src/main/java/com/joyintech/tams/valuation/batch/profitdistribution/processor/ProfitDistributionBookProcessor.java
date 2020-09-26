/*
 * 文件名：ProfitDistributionBookProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年6月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitdistribution.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionReaderDto;
import com.joyintech.tams.valuation.batch.profitdistribution.dto.ProfitDistributionResult;
import com.joyintech.tams.valuation.batch.profitdistribution.service.ProfitDistributionBookProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 利润分配批处理 -- 处理Processor
 * 
 * @author chengzhong
 * @version 1.0 2018年6月5日
 * @see ProfitDistributionBookProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookProcessor extends BaseProcessor<NeedOperateEntity, ProfitDistributionResult> {

    /**
     * taFloadFeeAccountsService
     */
    @Autowired
    private ProfitDistributionBookProcessorService profitDistributionBookProcessorService;

    /**
     * 返回记账凭证生成结果
     */

    @Override
    public ProfitDistributionResult process(NeedOperateEntity items)
        throws Exception {
        
        // 获取reader方法的返回值
        ProfitDistributionReaderDto acctEntity = (ProfitDistributionReaderDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        ProfitDistributionResult result = profitDistributionBookProcessorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new ProfitDistributionResult();
        }
        
        result.setProdId(acctEntity.getBooksetId());

        return result;
    }

}
