/*
 * 文件名：ValBokBalanceBatch.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.valbokbalance.dto;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.valuation.batch.common.accounting.entity.BalanceDetailInfoEntity;
import com.joyintech.tams.valuation.batch.common.base.entity.BatchOperationInterface;
import com.joyintech.tams.valuation.batch.common.base.spring.annotation.BaseEntityClassName;

/**
 * 科目余额Batch
 * 【weipeng-note: 暂时没有科目余额实体，先用SubjectBalance代替】
 * @author weipeng
 * @version 2.0 2018年5月29日
 * @see ValBokBalanceBatch
 * @since 1.0
 */
@BaseEntityClassName(value = BokBalance.class)
public class ValBokBalanceBatch extends BokBalance implements BatchOperationInterface{
    
    private static final long serialVersionUID = 1L;

    public ValBokBalanceBatch(){
        
    }
    
    public ValBokBalanceBatch(BalanceDetailInfoEntity entity){
        //账套代码
        this.setBooksetId(entity.getBooksetId());
        //余额日期--入账日
        this.setBalanceDate(entity.getBookDate());
        //科目号
        this.setSubjectNo(entity.getSubjectNo());
        //上级科目号
        this.setFsubjectNo(entity.getFsubjectNo());
        //科目级别
        this.setSubjectLevel(entity.getSubjectLevel());
        //原币
        this.setoCcy(entity.getoCcy());
        //原币余额
        this.setoAmt(entity.getBalance());
        //数量余额
        this.setNumAmt(entity.getNum());
        //余额方向
        this.setBalFlag(entity.getBalFlag());
        //本位币余额
        this.setbAmt(entity.getBalance());
    }
}
