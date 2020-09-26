/*
 * 文件名：SubjectBalanceGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalancecollect.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 科目余额生成结果
 * 
 * @author weipeng
 * @version 1.0 2018年5月29日
 * @see SubjectBalanceGenerateResult
 * @since 1.0
 */
public class SubjectBalanceGenerateResult extends NeedToWriteEntity {

    /**
     * 科目余额明细
     */
    private List<SubjectBalanceBatch> subjectBalanceBatchs;

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

}
