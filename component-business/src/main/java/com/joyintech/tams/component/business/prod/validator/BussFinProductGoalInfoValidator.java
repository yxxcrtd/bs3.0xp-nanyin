/*
 * 文件名：BussFinProductGoalInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussFinProductGoalInfoDto;

/**
 * 产品投向信息业务验证
 * 
 * @author 张亮
 * @version 1.0 2018年10月8日
 * @see BussFinProductGoalInfoValidator
 * @since 1.0
 */
@Component
public class BussFinProductGoalInfoValidator implements BeanValidator {

    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductGoalInfoDto bussFinProductGoalInfoDto = (BussFinProductGoalInfoDto)value;

        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (!StringUtil.isEmptyList(bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList())) {
            for (int i = 0; i < bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().size(); i++ ) {
                BussFinProductGoalInfoDto entity = bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().get(
                    i);
                if (!StringUtil.isNotEmpty(entity.getAssetClassify())) {
                    validList.add(
                        new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].assetClassify",
                            getMessage("BUSS_COM_E_0038",
                                getMessage("BussFinProductGoalInfo.assetClassify"))));
                }
                if (StringUtil.isNotEmpty(entity.getInvestMin())) {
                    if (entity.getInvestMin().compareTo(new BigDecimal(100)) > 0) {
                        // 持有下限%不能大于100。
                        validList.add(
                            new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].investMin",
                                this.getMessage("FIN_PROD_E_0017",
                                    this.getMessage("BussFinProductGoalInfo.investMin"), "100")));
                    }
                }
                if (StringUtil.isNotEmpty(entity.getInvestMax())) {
                    if (entity.getInvestMax().compareTo(new BigDecimal(100)) > 0) {
                        // 持有上限%不能大于100。
                        validList.add(
                            new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].investMax",
                                this.getMessage("FIN_PROD_E_0017",
                                    this.getMessage("BussFinProductGoalInfo.investMax"), "100")));
                    }
                }
                // 持有下限不能大于持有上限
                if (StringUtil.isNotEmpty(entity.getInvestMin())
                    && StringUtil.isNotEmpty(entity.getInvestMax())) {
                    if (entity.getInvestMin().compareTo(entity.getInvestMax()) > 0) {
                        validList.add(
                            new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].investMin",
                                this.getMessage("FIN_PROD_E_0017",
                                    this.getMessage("BussFinProductGoalInfo.investMin"),
                                    this.getMessage("BussFinProductGoalInfo.investMax"))));
                    }
                }
            }
            // 判断资产分类是否重复
            Map<String, BussFinProductGoalInfoDto> goalInfoMap = bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().stream().collect(
                Collectors.toMap(BussFinProductGoalInfoDto::getAssetClassify, a -> a,
                    (k1, k2) -> k1));
            if (goalInfoMap.size() != bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().size()) {
                validList.add(new ValidMessage("finProductPtlGoalDtoList",
                    this.getMessage("FIN_PROD_E_0021")));
            }
        }

        if (StringUtil.equals(bussFinProductGoalInfoDto.getPageId(), "FinProductBuild002")) {
            if (!StringUtil.isEmptyList(bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList())) {
                for (int i = 0; i < bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().size(); i++ ) {
                    BussFinProductGoalInfoDto entity = bussFinProductGoalInfoDto.getFinProductPtlGoalDtoList().get(
                        i);
                    if (!StringUtil.isNotEmpty(entity.getInvestMin())) {
                        validList.add(
                            new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].investMin",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductGoalInfo.investMin"))));
                    }
                    if (!StringUtil.isNotEmpty(entity.getInvestMax())) {
                        validList.add(
                            new ValidMessage("finProductPtlGoalDtoList[" + (i) + "].investMax",
                                getMessage("BUSS_COM_E_0038",
                                    getMessage("BussFinProductGoalInfo.investMax"))));
                    }
                    // if (!StringUtil.isNotEmpty(entity.getAssetClassify())) {
                    // validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                    // getMessage("BussFinProductGoalInfo.assetClassify"))));
                    // }
                }
            }
        }

        return validList;
    }

}
