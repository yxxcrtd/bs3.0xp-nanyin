/*
 * 文件名：MstNaturalPersonValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.validate;

import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service.SalesTransactionMaintenance002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 自然人管理校验 〈功能详细描述〉
 */
@Component
public class SalesTransactionMaintenanceValid implements BeanValidator {
    /**
     * 获取service对象
     */
    @Autowired
    private SalesTransactionMaintenance002Service salestransactionmaintenance002Service;

    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        SalesTransactionMaintenanceDto dto = (SalesTransactionMaintenanceDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        //非空校验
        if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW) || StringUtil.equals(dto.getPageModel(),
                GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
        }
        errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, dto, null, null, ValidMessage.class);
        return errorList;
    }
}
