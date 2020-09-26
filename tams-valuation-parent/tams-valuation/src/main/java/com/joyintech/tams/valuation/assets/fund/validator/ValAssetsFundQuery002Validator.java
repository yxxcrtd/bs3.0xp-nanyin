/*
 * 文件名：ValAssetsFundQuery003Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.tams.valuation.assets.fund.service.ValAssetsFundQuery002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 基金类资产新建、修改-校验
 * 
 * @author niuqianyu
 * @version 1.0 2018年10月16日
 * @see ValAssetsFundQuery002Validator
 * @since 1.0
 */
@Component
public class ValAssetsFundQuery002Validator implements BeanValidator {

//    @Autowired
//    private ValAssetsFundQuery002Mapper valAssetsFundQuery002Mapper;

    @Autowired
    private ValAssetsFundQuery002Service valAssetsFundQuery002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        ValAssetsFundQuery002Dto dto = (ValAssetsFundQuery002Dto)value;
        String findprodId = dto.getFinprodId();
        // 计息开始日
        Date vdate = dto.getVdate();
        // 计息结束日
        Date mdate = dto.getMdate();
        // 分红日
        Date sdate = dto.getSdate();
        // 判断当是新建操作时，校验输入的资产代码是否唯一
        if (dto.getOperateType().equals("new") && StringUtil.isNotEmpty(findprodId)) {
            if (valAssetsFundQuery002Service.checkFindprodId(dto.getFinprodId())) {
                String msg = this.getMessage("VALASSETSFUND_E_001",
                    this.getMessage("ValAssetsFund.finprodId"));
                validMessageList.add(new ValidMessage("finprodId", msg));
            }
        }

        // 当基金类型为货币类型时，分红日不能为空
        if (!StringUtil.isNotEmpty(sdate)
            && dto.getFundType().equals(DictCodeList.基金类型_货币基金.getValue())) {
            String msg = this.getMessage("请输入分红日");
            validMessageList.add(new ValidMessage("sdate", msg));
        }
        // 计息开始日不能大于计息结束日
        if (StringUtil.isNotEmpty(vdate) && StringUtil.isNotEmpty(mdate)) {
            if (DateUtil.compareDate(vdate, mdate) > 0) {
                String msg = this.getMessage("VAL_COMMON_E_0001",
                    this.getMessage("ValAssetsFund.vdate"),
                    this.getMessage("ValAssetsFund.mdate"));
                validMessageList.add(new ValidMessage("vdate", msg));
            }
        }

        // 是否有流程中数据
        List<String> list = checkIsProcessing(dto);
        if (list.size() > 0) {
            validMessageList.add(new ValidMessage(getMessage("COMMON_E_0021",
                getMessage("ValAssetsFund.finprodId"), StringUtil.join(list, ","))));
        }
        return validMessageList;
    }

    private List<String> checkIsProcessing(Object value) {
        ValAssetsFundQuery002Dto dto = (ValAssetsFundQuery002Dto)value;
        // 是否有流程中数据

        List<String> booksetIds = new ArrayList<String>();
        ValAssetsFundQuery002Dto dto1 = new ValAssetsFundQuery002Dto();
        dto1.setFinprodId(dto.getFinprodId());
        if (valAssetsFundQuery002Service.checkBusinessDataInFlow(dto1)) {
            booksetIds.add(dto1.getFinprodId());

        }
        return booksetIds;
    }
}
