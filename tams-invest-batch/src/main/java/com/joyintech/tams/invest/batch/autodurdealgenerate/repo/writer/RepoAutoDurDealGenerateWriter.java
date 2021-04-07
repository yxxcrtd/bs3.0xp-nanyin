/*
 * 文件名：RepoAutoDurDealGenerateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * writer
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RepoAutoDurDealGenerateWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;
    
    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回的list值
        @SuppressWarnings("unchecked")
        List<RepoAutoDurDealGenerateResult> resultList = (List<RepoAutoDurDealGenerateResult>)items;

        if (!StringUtil.isEmptyList(resultList)) {
            for (RepoAutoDurDealGenerateResult autoDurDealGenerateResult : resultList) {
                BaseTrdDurDbInertDto baseTrdDurDbInertDto = autoDurDealGenerateResult.getBaseTrdDurDbInertDto();
                if (baseTrdDurDbInertDto == null) {
                    continue;
                }
                autoPeriodCommonService.insertDealData(baseTrdDurDbInertDto, autoDurDealGenerateResult.getFinProductTradeDto());
                pinganBatchCommonService.deal有效(autoDurDealGenerateResult.getFinProductTradeDto());
            }
        }
    }

}
