/*
 * 文件名：ResetRateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.resetrate.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.resetrate.dto.ResetRateReaderResult;
import com.joyintech.tams.invest.batch.resetrate.dto.ResetRateResult;
import com.joyintech.tams.invest.batch.resetrate.service.ResetRateProcessorService;

/**
 * Processor
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月21日
 * @see ResetRateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ResetRateProcessor extends BaseProcessor<NeedOperateEntity, ResetRateResult>{

    @Autowired
    private ResetRateProcessorService processorService;
    @Override
    public ResetRateResult process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        ResetRateReaderResult readerResult = (ResetRateReaderResult) item;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(BatchConstant.READER_RESULT, readerResult);

        // 调用service方法进行逻辑逻辑
        ResetRateResult result = processorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new ResetRateResult();
        }

        return result;
    }
    
}
