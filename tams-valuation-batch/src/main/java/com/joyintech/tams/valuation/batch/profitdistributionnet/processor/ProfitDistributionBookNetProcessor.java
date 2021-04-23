/*
 * 文件名：ProfitDistributionBookNetProcessor.java
 * 版权：Copyright 2020-2021 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.1
 * 修改人：WUJIE
 * 修改时间：2020年4月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.1
 */
package com.joyintech.tams.valuation.batch.profitdistributionnet.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.profitdistributionnet.dto.ProfitDistributionNetReaderDto;
import com.joyintech.tams.valuation.batch.profitdistributionnet.dto.ProfitDistributionNetResult;
import com.joyintech.tams.valuation.batch.profitdistributionnet.service.ProfitDistributionBookNetProcessorService;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 净值产品利润分配批处理 -- 处理Processor
 * 
 * @author WUJIE
 * @version 1.0 2020年4月1日
 * @see ProfitDistributionBookNetProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProfitDistributionBookNetProcessor extends BaseProcessor<NeedOperateEntity, ProfitDistributionNetResult> {

    /**
     * taFloadFeeAccountsService
     */
    @Autowired
    private ProfitDistributionBookNetProcessorService processorService;

    /**
     * 返回记账凭证生成结果
     */

    @Override
    public ProfitDistributionNetResult process(NeedOperateEntity items)
        throws Exception {
        
        // 获取reader方法的返回值
        ProfitDistributionNetReaderDto acctEntity = (ProfitDistributionNetReaderDto)items;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(GlobalConstant.READER_RESULT, acctEntity);

        // 调用service方法进行逻辑逻辑
        ProfitDistributionNetResult result = processorService.execute(thisParams);
        
        //当为null时候，给予初始化
        if(result == null){
            result = new ProfitDistributionNetResult();
        }
        
        result.setProdId(acctEntity.getBooksetId());

        return result;
    }

}
