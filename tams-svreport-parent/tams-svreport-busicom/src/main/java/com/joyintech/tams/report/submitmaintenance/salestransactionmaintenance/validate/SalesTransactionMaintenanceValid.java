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
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service.SalesTransactionMaintenance002Service;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.ChbTransformRuleObjDto;
import com.joyintech.tams.report.submitmaintenance.transformrule.dto.TransformRule001Dto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 登记页面数据检查
     *
     * @param registList 选择的数据
     * @param flag 1 数据检查 2 报送前检查
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public static List<ExcelValidDto> checkData(List<SalesTransactionMaintenanceRegist001Dto> registList,
                                                String flag,
                                                Map<String, TransformRule001Dto> spzMap,
                                                Map<String, List<ChbTransformRuleObjDto>> objFiledsMap) {
        List<ExcelValidDto> checkResult = new ArrayList<>();
        SalesTransactionMaintenanceRegist001Dto salesTransactionMaintenanceRegist001Dto = null;
        for (int i = 0; i < registList.size(); i++ ) {
            salesTransactionMaintenanceRegist001Dto = registList.get(i);
            // 验证消息
            List<String> message = new ArrayList<>();
            if (!message.isEmpty()) {
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i),
                        StringUtil.joinString(message, GlobalConstant.COMMA)));
                continue;
            }
            if (CollectionUtils.isNotEmpty(message)) {
                ExcelValidDto excelValidDto = new ExcelValidDto(salesTransactionMaintenanceRegist001Dto.getTradeNo(),
                        String.valueOf(i), StringUtil.joinString(message, "<br>"));
                excelValidDto.setDataTime(salesTransactionMaintenanceRegist001Dto.getUpdateTime());
                excelValidDto.setOrgCode(salesTransactionMaintenanceRegist001Dto.getOrgCode());
                excelValidDto.setCreateDept(salesTransactionMaintenanceRegist001Dto.getCreateDept());
                checkResult.add(excelValidDto);
            }
            else if (ReportGlobalConstant.IS_NUMBER_FLAG.equals(flag)) {
                ExcelValidDto excelValidDto = new ExcelValidDto(salesTransactionMaintenanceRegist001Dto.getTradeNo(),String.valueOf(i), null);
                excelValidDto.setDataTime(salesTransactionMaintenanceRegist001Dto.getUpdateTime());
                excelValidDto.setOrgCode(salesTransactionMaintenanceRegist001Dto.getOrgCode());
                excelValidDto.setCreateDept(salesTransactionMaintenanceRegist001Dto.getCreateDept());
                checkResult.add(excelValidDto);
            }
        }
        return checkResult;
    }
}
