/*
 * 文件名：SubjectBalance.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.psbc.bankpositioncollect.dto;

import java.math.BigDecimal;

import com.joyintech.tams.batch.psbc.bankcalbook.dto.BokBankPositionDto;
import com.joyintech.tams.valuation.batch.common.base.entity.BatchOperationInterface;

/**
 * 银行账科目余额Batch
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankSubjectBalanceBatch
 * @since 1.0
 */ 
public class BankSubjectBalanceBatch extends BokBankPositionDto implements BatchOperationInterface {

    private static final long serialVersionUID = 1L;

    public BankSubjectBalanceBatch() {

    }

    /**
     * @param entity
     */
    public BankSubjectBalanceBatch(BokBankPositionDto entity) {
        // 账套代码
        this.setFinprodId(entity.getFinprodId());
        // 余额日期--入账日
        this.setBalanceDate(entity.getBalanceDate());
        // 科目号
        this.setSubjectNo(entity.getSubjectNo());
     // 科目号
        this.setBooksetId(entity.getBooksetId());
        // 上级科目号
        this.setFsubjectNo(entity.getFsubjectNo());        
        // 科目级别
        this.setSubjectLevel(entity.getSubjectLevel());
        // 余额方向
        this.setBalFlag(entity.getBalFlag()); 
        // 原币
        this.setoCcy(entity.getoCcy());
        // 原币余额
        this.setoAmt(entity.getoAmt() != null ? entity.getoAmt() : BigDecimal.ZERO);
        // 本币
        this.setbCcy(entity.getbCcy());
        // 本币余额
        this.setbAmt(entity.getbAmt()!= null ? entity.getbAmt() : BigDecimal.ZERO);
        // 数量余额
        this.setNumAmt(entity.getNumAmt());
        // 是否叶子节点
        this.setIsLeaf(entity.getIsLeaf());    
    }
}
