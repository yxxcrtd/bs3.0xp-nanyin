/*
 * 文件名：AccountCurrentRateService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.trademanage.accountcurrentrate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.service.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 活期存款页面利率共通流程service
 * 
 * @author HuangJian
 * @version 1.0 2018年11月22日
 * @see AccountCurrentRateService
 * @since 1.0
 */
@Service(value="serviceAccountCurrentRateService")
@Transactional(rollbackFor = Exception.class)
public class AccountCurrentRateService extends CommonExportService<AccountCurrentRateDto> implements BaseProcessService<AccountCurrentRateDto> {

    /**
     * accountCurrentRate001Service
     */
    @Autowired
    private AccountCurrentRate001Service accountCurrentRate001Service;

    /**
     * accountCurrentRate002Service
     */
    @Autowired
    private AccountCurrentRate002Service accountCurrentRate002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(AccountCurrentRateDto entity, OperationType type) {
        // 删除
        if (OperationType.DELETE.equals(type)) {
            accountCurrentRate001Service.delete(entity);

        }
        // 新建
        // 修改
        else {
            accountCurrentRate002Service.doCommit(entity);

        }

    }

}
