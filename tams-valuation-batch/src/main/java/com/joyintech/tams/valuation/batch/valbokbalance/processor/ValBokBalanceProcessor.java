/*
 * 文件名：ValBokBalanceProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.valbokbalance.processor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.valuation.batch.common.accounting.entity.ValBokBalanceDto;
import com.joyintech.tams.valuation.batch.common.accounting.service.AccountingCommonService;
import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBatchTaskTodoDto;
import com.joyintech.tams.valuation.batch.valbokbalance.dto.ValBokBalanceGenerateResult;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月31日
 * @see ValBokBalanceProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValBokBalanceProcessor extends BaseProcessor<NeedOperateEntity, ValBokBalanceGenerateResult> {

    /**
     * 共通方法的 Service
     */
    @Autowired
    private AccountingCommonService accountingCommonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ValBokBalanceGenerateResult process(NeedOperateEntity items)
        throws Exception {
        // 获取reader方法的返回值
        ValBatchTaskTodoDto acctEntity = (ValBatchTaskTodoDto)items;
        // 获取入账日
        Date date = acctEntity.getBookDate();
        // 取入账日前一年的12月31日
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //update by xr  期初认为是上一日
        cal.add(Calendar.DATE, -1);
//        cal.set(cal.get(Calendar.YEAR), 11, 31);
        // 期初日期（入账日前一年的12月31日）
        Date earlyDate = cal.getTime();
        // 调用共通service方法进行余额表生成处理
        List<ValBokBalanceDto> list = accountingCommonService.generateSchedulInfo(
            acctEntity.getBooksetId(), acctEntity.getBookDate(), earlyDate,
            acctEntity.getBookDate(), acctEntity.getStandardCcy());
        ValBokBalanceGenerateResult result = new ValBokBalanceGenerateResult();
        // 取得科目余额
        result.setValBokBalanceBatch(list);
        result.setProdId(acctEntity.getBooksetId());
        result.setBookDate(date);
        result.setBooksetName(acctEntity.getBooksetName());
        result.setFinprodName(acctEntity.getFinprodName());
        return result;
    }

}
