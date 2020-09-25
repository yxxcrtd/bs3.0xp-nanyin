/*
 * 文件名：DepoAutoDurDealGenerateProcesser.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.depo.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.service.DepoAutoDurDealGenerateProcessorService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * Processor
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see DepoAutoDurDealGenerateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepoAutoDurDealGenerateProcessor extends BaseProcessor<NeedOperateEntity, DepoAutoDurDealGenerateResult> {

    /**
     * DepoAutoDurDealGenerateProcessorService
     */
    @Autowired
    private DepoAutoDurDealGenerateProcessorService processorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public DepoAutoDurDealGenerateResult process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        DepoAutoDurDealGenerateReaderResult readerResult = (DepoAutoDurDealGenerateReaderResult) item;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(BatchConstant.READER_RESULT, readerResult);

        // 调用service方法进行逻辑逻辑
        DepoAutoDurDealGenerateResult result = processorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new DepoAutoDurDealGenerateResult();
        }

        return result;
    }

}
