/*
 * 文件名：TrdConBondRevokeDtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.conbond.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.trade.conbond.dto.TrdConBondRevokeDto;
import com.joyintech.tams.invest.trade.conbond.service.TrdConBond001Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBondRevokeDtoValidator
 * @since 1.0
 */
@Component
public class TrdConBondRevokeDtoValidator implements BeanValidator {

    /**
     * TrdLendInvestTrade001Service
     */
    @Autowired
    private TrdConBond001Service trdConBond001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	TrdConBondRevokeDto inputDto = (TrdConBondRevokeDto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        if (!trdConBond001Service.revokeCheck(inputDto)) {
            errorList.add(new ValidMessage(this.getMessage("TRD_X_0007")));
        }
        if (!trdConBond001Service.revokeCheckWfl(inputDto)) {
            errorList.add(new ValidMessage("存在撤销流程中数据，不允许再次撤销!"));
        }
        if (!DictCodeListExt1.交易流程状态_有效.getValue().equals(inputDto.getStatusValid())) {
            errorList.add(new ValidMessage(getMessage("FIN_ASSET_E_0006")));
        }

        if (StringUtil.isNotEmpty(inputDto.getPs())
                && StringUtil.equals(inputDto.getPs(), DictCodeListExt3.交易方向_退款到账.getValue())) {
                errorList.add(new ValidMessage("退款到账交易不能单独撤销请撤销对应的申购确认交易!"));
            }
        return errorList;
    }
}
