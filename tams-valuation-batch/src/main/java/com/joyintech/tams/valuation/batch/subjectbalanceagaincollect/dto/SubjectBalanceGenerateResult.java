/*
 * 文件名：BooksetTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lby
 * 修改时间：2020年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 科目余额生成结果
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectBalanceGenerateResult
 * @since 1.0
 */
public class SubjectBalanceGenerateResult extends NeedToWriteEntity {

    /**
     * 科目余额明细
     */
    private List<SubjectBalanceBatch> subjectBalanceBatchs;
    /**
     * 科目余额重汇信息记录DTO
     */
    private BokBalanceAgainCollectLogDto bokBalanceAgainCollectLogDto;
    /**
     * 取得 科目余额明细
     * 
     * @return 科目余额明细
     */
    public List<SubjectBalanceBatch> getSubjectBalanceBatchs() {
        return subjectBalanceBatchs;
    }

    /**
     * 设置 科目余额明细
     * 
     * @param subjectBalanceBatchs
     *            科目余额明细
     */
    public void setSubjectBalanceBatchs(List<SubjectBalanceBatch> subjectBalanceBatchs) {
        this.subjectBalanceBatchs = subjectBalanceBatchs;
    }

    public BokBalanceAgainCollectLogDto getBokBalanceAgainCollectLogDto() {
        return bokBalanceAgainCollectLogDto;
    }

    public void setBokBalanceAgainCollectLogDto(BokBalanceAgainCollectLogDto bokBalanceAgainCollectLogDto) {
        this.bokBalanceAgainCollectLogDto = bokBalanceAgainCollectLogDto;
    }
}
