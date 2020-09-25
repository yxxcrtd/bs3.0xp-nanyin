/*
 * 文件名：ProjectAutoDurDealGenerateWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.project.writer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.project.dto.ProjectAutoDurDealGenerateResult;
import com.joyintech.tams.invest.common.autoperiod.project.AutoPeriodProjectCommonService;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

/**
 * writer
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see ProjectAutoDurDealGenerateWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ProjectAutoDurDealGenerateWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private AutoPeriodProjectCommonService autoPeriodProjectCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(List<? extends NeedToWriteEntity> items)
        throws Exception {
        // 获取process返回的list值
        @SuppressWarnings("unchecked")
        List<ProjectAutoDurDealGenerateResult> resultList = (List<ProjectAutoDurDealGenerateResult>)items;

        if (!StringUtil.isEmptyList(resultList)) {
            for (ProjectAutoDurDealGenerateResult autoDurDealGenerateResult : resultList) {
                BaseTotalTradeInfoDto baseTotalTradeInfoDto = autoDurDealGenerateResult.getBaseTotalTradeInfoDto();
                if (baseTotalTradeInfoDto == null) {
                    continue;
                }

                autoPeriodProjectCommonService.insertWflData(baseTotalTradeInfoDto);
            }
        }
    }

}
