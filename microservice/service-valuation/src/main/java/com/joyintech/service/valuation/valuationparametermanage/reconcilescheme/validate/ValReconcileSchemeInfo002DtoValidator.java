/*
 * 文件名：ReconcileSchemeInfoDtoValidator.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dao.ValReconcileSchemeInfo002Mapper;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;


/**
 * 名称保存校验
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo002DtoValidator
 * @since 1.0
 */
@Component(value="serviceValReconcileSchemeInfo002DtoValidator")
public class ValReconcileSchemeInfo002DtoValidator implements BeanValidator {

    @Autowired
    private ValReconcileSchemeInfo002Mapper valReconcileSchemeInfo002Mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        // 查询按钮按下合法性Check
        ValReconcileSchemeInfo002Dto schemeDto = (ValReconcileSchemeInfo002Dto)value;
        // 映射名称不能重复
        if (valReconcileSchemeInfo002Mapper.findSchemeName(schemeDto) > 0) {
            String message = this.getMessage("VAL_COMMON_E_0028", "方案名称");
            errorList.add(new ValidMessage("schemeName", message));
        }
        // 第一个方案必须为默认方案
        if(DictCodeList.是否_否.getValue().equals(schemeDto.getIsDefault())) {
            int schemeCount = valReconcileSchemeInfo002Mapper.getAllSchemeCount(schemeDto);
            if (schemeCount == 0) {
                errorList.add(new ValidMessage("该方案类型和所属账套下的第一个方案必须为默认方案"));
            }
        }
        // 检查是否已存在过默认方案
        else if (DictCodeList.是否_是.getValue().equals(schemeDto.getIsDefault())) {
            ValReconcileSchemeInfo002Dto defaultScheme = valReconcileSchemeInfo002Mapper.selectDefaultSchemeByType(schemeDto);
            if (defaultScheme != null) {
                errorList.add(new ValidMessage("该账套下已存在默认方案，不可重复新建"));
            }
        }
        
//        if (StringUtil.isNotEmpty(infoDto.getSchId())
//            && DictCodeList.是否_否.getValue().equals(infoDto.getIsDefault())) {
//            BokSchemeInfo info = bokSchemeInfoMapper.selectByPrimaryKey(
//                infoDto.getSchId());
//            if (DictCodeList.是否_是.getValue().equals(info.getIsDefault())) {
//                errorList.add(new ValidMessage("isdefaule", "默认的对账方案不可变更为否"));
//            }
//        }
        return errorList;
    }

}
