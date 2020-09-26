/*
 * 文件名：SubjectBalance.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.subjectbalancecollect.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.valuation.batch.common.base.entity.BatchOperationInterface;
import com.joyintech.tams.valuation.batch.common.base.spring.annotation.BaseEntityClassName;

/**
 * 科目余额Batch 【weipeng-note: 暂时没有科目余额实体，先用SubjectBalance代替】
 * 
 * @author weipeng
 * @version 2.0 2018年5月29日
 * @see SubjectBalanceBatch
 * @since 1.0
 */
@BaseEntityClassName(value = BokBalance.class)
public class SubjectBalanceBatch extends BokBalance implements BatchOperationInterface {

    private static final long serialVersionUID = 1L;

    public SubjectBalanceBatch() {

    }

    public SubjectBalanceBatch(SubjectBalance entity) {
        // 账套代码
        this.setBooksetId(entity.getBooksetId());
        // this.setDetailSubjectNo(entity.getDetailSubjectNo());
        // 余额日期--入账日
        this.setBalanceDate(entity.getBookDate());
        // 科目号
        this.setSubjectNo(entity.getSubjectNo());
        // 上级科目号
        this.setFsubjectNo(entity.getFsubjectId());        
        // 科目级别
        this.setSubjectLevel(entity.getSubjectLevel());
        // 余额方向
        this.setBalFlag(entity.getBalFlagHappenDate());
        //结转余额方向
        this.setBalCoFlag(entity.getBalCoFlag());
        // 原币
        this.setoCcy(entity.getOCcy());
        // 原币余额
        this.setoAmt(entity.getBalance() != null ? entity.getBalance() : BigDecimal.ZERO);
        // 贷方发生额
        this.setoCAmt(entity.getcAmt() != null ? entity.getcAmt() : BigDecimal.ZERO);
        // 借方发生额
        this.setoDAmt(entity.getdAmt() != null ? entity.getdAmt() : BigDecimal.ZERO);
        //原币结转余额
        this.setoCoAmt(entity.getoCcyKnotsAmt() != null ? entity.getoCcyKnotsAmt() : BigDecimal.ZERO);
        //原币结转余额贷方
        this.setoCoCAmt(entity.getoCcyKnotsAmtC() != null ? entity.getoCcyKnotsAmtC() : BigDecimal.ZERO);
        //原币结转余额借方
        this.setoCoDAmt(entity.getoCcyKnotsAmtD() != null ? entity.getoCcyKnotsAmtD() : BigDecimal.ZERO);        
        //原币当日发生额
        this.setTdyOAmt(entity.getTdyOAmt()!= null ? entity.getTdyOAmt() : BigDecimal.ZERO);
        //原币当日发生额贷方
        this.setTdyOCAmt(entity.getTdyOCAmt()!= null ? entity.getTdyOCAmt() : BigDecimal.ZERO);
        //原币当日发生额借方
        this.setTdyODAmt(entity.getTdyODAmt()!= null ? entity.getTdyODAmt() : BigDecimal.ZERO);
        //原币结转当日发生额
        this.setTdyOCoAmt(entity.getTdyOCoAmt()!= null ? entity.getTdyOCoAmt() : BigDecimal.ZERO);
        //原币结转当日发生额借方
        this.setTdyOCoCAmt(entity.getTdyOCoCAmt()!= null ? entity.getTdyOCoCAmt() : BigDecimal.ZERO);
        //原币结转当日发生额借方
        this.setTdyOCoDAmt(entity.getTdyOCoDAmt()!= null ? entity.getTdyOCoDAmt() : BigDecimal.ZERO);
        // 数量余额
        this.setNumAmt(entity.getNum());
        // 是否叶子节点
        this.setIsLeaf(entity.getIsleaf());       
        //当日发生额方向
        this.setTdyAmtFlag(entity.getTdyAmtFlag());
        //结转当日发生额方向
        this.setTdyCoFlag(entity.getTdyCoFlag());
        //原币申购未实现平准金借
        this.setTdyPurODUlamt(entity.getTdyPurODUlamt()!= null ? entity.getTdyPurODUlamt() : BigDecimal.ZERO);
        //原币赎回未实现平准金借
        this.setTdyRedODUlamt(entity.getTdyRedODUlamt()!= null ? entity.getTdyRedODUlamt() : BigDecimal.ZERO);
        //原币申购未实现平准金贷
        this.setTdyPurOCUlamt(entity.getTdyPurOCUlamt()!= null ? entity.getTdyPurOCUlamt() : BigDecimal.ZERO);
        //原币赎回未实现平准金贷
        this.setTdyRedOCUlamt(entity.getTdyRedOCUlamt()!= null ? entity.getTdyRedOCUlamt() : BigDecimal.ZERO);
             
        // 暂时不对本位币做操作...
        //本位币
        this.setbCcy(entity.getbCcy());
        //本位币余额
        this.setbAmt(entity.getbAmt()!= null ? entity.getbAmt() : BigDecimal.ZERO);
        //本位币余额贷方
        this.setbCAmt(entity.getbCAmt()!= null ? entity.getbCAmt() : BigDecimal.ZERO);
        //本位币余额借方
        this.setbDAmt(entity.getbDAmt()!= null ? entity.getbDAmt() : BigDecimal.ZERO);
        //本位币结转余额
        this.setbCoAmt(entity.getbCoAmt()!= null ? entity.getbCoAmt() : BigDecimal.ZERO);
        //本位币结转余额贷方
        this.setbCoCAmt(entity.getbCoCAmt()!= null ? entity.getbCoCAmt() : BigDecimal.ZERO);
        //本位币结转余额借方
        this.setbCoDAmt(entity.getbCoDAmt()!= null ? entity.getbCoDAmt() : BigDecimal.ZERO);
        //本位币当日发生额
        this.setTdyBAmt(entity.getTdyBAmt()!= null ? entity.getTdyBAmt() : BigDecimal.ZERO);
        //本位币当日发生额贷方
        this.setTdyBCAmt(entity.getTdyBCAmt()!= null ? entity.getTdyBCAmt() : BigDecimal.ZERO);
        //本位币当日发生额借方
        this.setTdyBDAmt(entity.getTdyBDAmt()!= null ? entity.getTdyBDAmt() : BigDecimal.ZERO);
        //本位币结转当日发生额
        this.setTdyBCoAmt(entity.getTdyBCoAmt()!= null ? entity.getTdyBCoAmt() : BigDecimal.ZERO);
        //本位币结转当日发生额贷方
        this.setTdyBCoCAmt(entity.getTdyBCoCAmt()!= null ? entity.getTdyBCoCAmt() : BigDecimal.ZERO);
        //本位币结转当日发生额借方
        this.setTdyBCoDAmt(entity.getTdyBCoDAmt()!= null ? entity.getTdyBCoDAmt() : BigDecimal.ZERO);
        // 本位币申购未实现平准金借
        this.setTdyPurBDUlamt(entity.getTdyPurBDUlamt()!= null ? entity.getTdyPurBDUlamt() : BigDecimal.ZERO);
        // 本位币赎回未实现平准金借
        this.setTdyRedBDUlamt(entity.getTdyRedBDUlamt()!= null ? entity.getTdyRedBDUlamt() : BigDecimal.ZERO);
        // 本位币申购未实现平准金贷
        this.setTdyPurBCUlamt(entity.getTdyPurBCUlamt()!= null ? entity.getTdyPurBCUlamt() : BigDecimal.ZERO);
        // 本位币赎回未实现平准金贷
        this.setTdyRedBCUlamt(entity.getTdyRedBCUlamt()!= null ? entity.getTdyRedBCUlamt() : BigDecimal.ZERO);
    }
}
