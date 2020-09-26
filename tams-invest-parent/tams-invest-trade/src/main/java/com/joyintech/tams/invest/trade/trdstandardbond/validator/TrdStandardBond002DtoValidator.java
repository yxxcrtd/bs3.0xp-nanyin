/*
 * 文件名：TrdRepoTrade002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdstandardbond.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.trade.common.validator.TradeCommonValidator;
import com.joyintech.tams.invest.trade.trdrepotrade.dto.TrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.trdrepotrade.service.TrdRepoTrade002Service;

/**
 * 回购资产交易新建画面：TrdRepoTrade002DtoValidator
 *
 * @author 裴家俊
 * @version 1.0 2018年11月1日
 * @see TrdStandardBond002DtoValidator
 * @since 1.0
 */
@Component
public class TrdStandardBond002DtoValidator implements TradeCommonValidator {


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        TrdStandardBond002Dto inputDto = (TrdStandardBond002Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        List<TrdStandardBond002Dto> trdStandardBond002Dtos = inputDto.getPledgeBondList();
        if (StringUtil.isEmptyList(trdStandardBond002Dtos)) {
            ValidMessage validMessage = new ValidMessage("pledgeBondList",
                    getMessage("TRD_BOND_STANDAR_005"));
            errorList.add(validMessage);
        }
        else {
            for (TrdStandardBond002Dto dto : trdStandardBond002Dtos) {
                if (dto.getPledgePrinAmt() == null || BigDecimal.ZERO.compareTo(dto.getPledgePrinAmt()) >= 0) {
                    ValidMessage validMessage = new ValidMessage("pledgeBondList",
                            getMessage("TRD_BOND_STANDAR_006"));
                    errorList.add(validMessage);
                }
            }
        }
        return errorList;
    }
}
