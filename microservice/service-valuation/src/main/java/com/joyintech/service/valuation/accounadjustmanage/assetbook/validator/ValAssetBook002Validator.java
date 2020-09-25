/*
 * 文件名：ValAssetBook002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.assetbook.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dao.ValAssetBook002Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.dto.ValAssetbook002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assetbook.service.ValAssetBook002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 账套新建校验
 * 
 * @author weipeng
 * @version 1.0 2018年7月14日
 * @see ValAssetBook002Validator
 * @since 1.0
 */
@Component(value="serviceValAssetBook002Validator")
public class ValAssetBook002Validator implements BeanValidator {

    @Autowired
    private ValAssetBook002Mapper valAssetBook002Mapper;

    @Autowired
    private ValAssetBook002Service valAssetBook002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ValAssetbook002Dto dto = (ValAssetbook002Dto)value;
        String booksetId = dto.getBooksetId();
        Date st = dto.getStartDate();
        Date et = dto.getEndDate();
        // 判断当是新建操作时，校验是否已存在当前账套代码的记录
        if (!DictCodeList.是否_是.getValue().equals(dto.getIsEdit())
            && StringUtil.isNotEmpty(booksetId)) {
            if (valAssetBook002Mapper.checkBooksetId(dto.getBooksetId())) {
                String msg = this.getMessage("VALASSETBOOK_E_001",
                    this.getMessage("ValAssetbook.booksetId"));
                validMessageList.add(new ValidMessage("booksetId", msg));
            }
        }
        //当时修改操作时,判断名称是否与其他账套名称重复
        if (DictCodeList.是否_是.getValue().equals(dto.getIsEdit())
            && StringUtil.isNotEmpty(booksetId)) {
            if(valAssetBook002Service.checkBooksetName(dto)){
                String msg = this.getMessage("VALASSETBOOK_E_002",
                    this.getMessage("ValAssetbook.booksetName"));
                validMessageList.add(new ValidMessage("booksetName", msg));
            }
        }
        
        // 开始时间不能大于结束时间
        if (StringUtil.isNotEmpty(st) && StringUtil.isNotEmpty(et)) {
            if (DateUtil.compareDate(st, et) > 0) {
                String msg = this.getMessage("VAL_COMMON_E_0001",
                    this.getMessage("ValAssetbook.startDate"),
                    this.getMessage("ValAssetbook.endDate"));
                validMessageList.add(new ValidMessage("startDate", msg));
            }
        }

        // 是否有流程中数据
        List<String> list = checkIsProcessing(dto);
        if (list.size() > 0) {
            validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021",
                getMessage("ValAssetbook.booksetId"), StringUtil.join(list, ","))));
        }
        return validMessageList;
    }

    private List<String> checkIsProcessing(Object value) {
        ValAssetbook002Dto dto = (ValAssetbook002Dto)value;
        // 是否有流程中数据

        List<String> booksetIds = new ArrayList<String>();
        ValAssetbook002Dto dto1 = new ValAssetbook002Dto();
        dto1.setBooksetId(dto.getBooksetId());
        if (valAssetBook002Service.checkBusinessDataInFlow(dto1)) {
            booksetIds.add(dto1.getBooksetId());

        }
        return booksetIds;
    }
}
