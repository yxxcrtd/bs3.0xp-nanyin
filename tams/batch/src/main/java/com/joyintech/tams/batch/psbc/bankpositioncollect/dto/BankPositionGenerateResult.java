/*
 * 文件名：SubjectBalanceGenerateResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.batch.psbc.bankpositioncollect.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 银行账科目余额生成结果
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionGenerateResult
 * @since 1.0
 */
public class BankPositionGenerateResult extends NeedToWriteEntity {

    /**
     * 科目余额明细
     */
    private List<BankSubjectBalanceBatch> bankSubjectBalanceBatchs;

    /**
     * 取得 科目余额明细
     * 
     * @return 科目余额明细
     */
    public List<BankSubjectBalanceBatch> getSubjectBalanceBatchs() {
        return bankSubjectBalanceBatchs;
    }

    /**
     * 设置 科目余额明细
     * 
     * @param subjectBalanceBatchs
     *            科目余额明细
     */
    public void setSubjectBalanceBatchs(List<BankSubjectBalanceBatch> bankSubjectBalanceBatchs) {
        this.bankSubjectBalanceBatchs = bankSubjectBalanceBatchs;
    }

}
