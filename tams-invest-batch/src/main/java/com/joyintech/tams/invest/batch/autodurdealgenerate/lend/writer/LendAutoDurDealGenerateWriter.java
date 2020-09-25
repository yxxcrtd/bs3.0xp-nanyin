/*
 * 文件名：LendAutoDurDealGenerateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.lend.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.lend.dto.LendAutoDurDealGenerateResult;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;

/**
 * writer
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see LendAutoDurDealGenerateWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class LendAutoDurDealGenerateWriter extends BaseWriter<NeedToWriteEntity> {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回的list值
        @SuppressWarnings("unchecked")
        List<LendAutoDurDealGenerateResult> resultList = (List<LendAutoDurDealGenerateResult>)items;

        if (!StringUtil.isEmptyList(resultList)) {
            for (LendAutoDurDealGenerateResult autoDurDealGenerateResult : resultList) {
                BaseTrdDurDbInertDto baseTrdDurDbInertDto = autoDurDealGenerateResult.getBaseTrdDurDbInertDto();
                if (baseTrdDurDbInertDto == null) {
                    continue;
                }

                autoPeriodCommonService.insertWflData(baseTrdDurDbInertDto);
            }
        }
    }

}
