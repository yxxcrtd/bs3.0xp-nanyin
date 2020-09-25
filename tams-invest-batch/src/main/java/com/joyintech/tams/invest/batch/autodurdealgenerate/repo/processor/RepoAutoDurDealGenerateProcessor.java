/*
 * 文件名：RepoAutoDurDealGenerateProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.service.RepoAutoDurDealGenerateProcessorService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;

/**
 * Processor
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepoAutoDurDealGenerateProcessor extends BaseProcessor<NeedOperateEntity, RepoAutoDurDealGenerateResult> {

    /**
     * RepoAutoDurDealGenerateProcessorService
     */
    @Autowired
    private RepoAutoDurDealGenerateProcessorService processorService;

    /**
     * {@inheritDoc}
     */
    @Override
    public RepoAutoDurDealGenerateResult process(NeedOperateEntity item)
        throws Exception {
        // 获取reader方法的返回值
        RepoAutoDurDealGenerateReaderResult readerResult = (RepoAutoDurDealGenerateReaderResult) item;

        Map<String, Object> thisParams = new HashMap<>();
        thisParams.putAll(this.getParams());
        // 将返回值塞入参数中
        thisParams.put(BatchConstant.READER_RESULT, readerResult);

        // 调用service方法进行逻辑逻辑
        RepoAutoDurDealGenerateResult result = processorService.execute(thisParams);

        // 当为null时候，给予初始化
        if (result == null) {
            result = new RepoAutoDurDealGenerateResult();
        }

        return result;
    }

}
