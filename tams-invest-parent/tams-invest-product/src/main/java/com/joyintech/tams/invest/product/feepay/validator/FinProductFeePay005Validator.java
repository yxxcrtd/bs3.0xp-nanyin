/*
 * 文件名：FinProductFeePay005Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zcw
 * 修改时间：2019年02月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay002Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay005Dto;

/**
 * 产品费用支付确认校验
 * 
 * @author zcw
 * @version 1.0 2019年02月13日
 * @see FinProductFeePay005Validator
 * @since 1.0
 */
@Component
public class FinProductFeePay005Validator implements BeanValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
     
    @Autowired
    private IPlatformDate iPlatformDate;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductFeePay005Dto finProductFeePay005Dto = (FinProductFeePay005Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        // 验证划款指令
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                finProductFeePay005Dto.getBussTrdTransferOrder());
            validList.addAll(list);
            if (DictCodeList.费用品种类型_销售手续费.getValue().equals(finProductFeePay005Dto.getFeeType())
                    || DictCodeList.费用品种类型_托管费.getValue().equals(finProductFeePay005Dto.getFeeType())
                    || DictCodeList.费用品种类型_固定管理费.getValue().equals(finProductFeePay005Dto.getFeeType())
            ) {
                for (BussTrdTransferOrderDto dataDto : finProductFeePay005Dto.getBussTrdTransferOrder()) {
                    // 收款账号
                    if (StringUtil.isBlank(dataDto.getRecAcctNo())) {
                        validList.add(new ValidMessage("bussTrdTransferOrder", getMessage("FW_E_0006",
                                getMessage("BussTrdTransferOrder.recAcctNo"))));
                    }
                }
            }
        }
        // 验证产品列表
        if (StringUtil.isEmptyList(finProductFeePay005Dto.getFinProductFeePay())) {
            validList.add(
                new ValidMessage("finProductFeePay", this.getMessage("FIN_PROD_E_0096"))
                );
        }else {
        	for(FinProductFeePay002Dto dto :finProductFeePay005Dto.getFinProductFeePay()) {
				if (DateUtil.compareDate(finProductFeePay005Dto.getPayDate(), dto.getMdate()) < 0) {
					validList.add(new ValidMessage("支付日必须大于等于计提结束日！"));
				}
				if (DateUtil.compareDate(finProductFeePay005Dto.getPayDate(), DateUtil.platDateFromCache()) < 0) {
					validList.add(new ValidMessage("支付日必须大于等于平台日！"));
				}
        		if(StringUtil.isEmpty(dto.getFinprodId())) {
                    validList.add(
                            new ValidMessage("finProductFeePay", this.getMessage("FIN_PROD_E_0099",
                                    this.getMessage("FinProductFeePay004.prodTable.finprodId")))
                            );
        		}
        		if(StringUtil.isEmpty(dto.getFinprodAbbr())) {
                    validList.add(
                            new ValidMessage("finProductFeePay", this.getMessage("FIN_PROD_E_0099",
                                    this.getMessage("FinProductFeePay004.prodTable.finprodAbbr")))
                            );
        		}
        		if(StringUtil.isEmpty(dto.getBasis())) {
                    validList.add(
                            new ValidMessage("finProductFeePay", this.getMessage("FIN_PROD_E_0099",
                                    this.getMessage("FinProductFeePay004.prodTable.basis")))
                            );
        		}
        		if(StringUtil.isEmpty(dto.getIsClean())) {
                    validList.add(
                            new ValidMessage("finProductFeePay", this.getMessage("FIN_PROD_E_0099",
                                    this.getMessage("FinProductFeePay004.prodTable.isClean")))
                            );
        		}
        	}
        }
        
     
        return validList;
    }

}
