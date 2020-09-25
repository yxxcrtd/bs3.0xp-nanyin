/*
 * 文件名：RepoAutoDurDealGenerateDelProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateResult;

/**
 * DelProcessor
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateDelProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepoAutoDurDealGenerateDelProcessor extends BaseProcessor<NeedOperateEntity, RepoAutoDurDealGenerateResult> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RepoAutoDurDealGenerateResult process(NeedOperateEntity item)
        throws Exception {
        RepoAutoDurDealGenerateResult result = new RepoAutoDurDealGenerateResult();
        result.setProdId("回购自动存续期删除处理");
        return result;
    }

}
