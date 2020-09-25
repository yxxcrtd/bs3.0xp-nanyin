/*
 * 文件名：FinProductExpire002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.expire.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.validator.CommonValidator;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpire002Dto;
import com.joyintech.tams.invest.product.productduration.expire.dto.FinProductExpireBranchInfoDto;
import com.joyintech.tams.invest.product.productduration.expire.service.FinProductExpire002Service;

/**
 * 产品到期确认新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月13日
 * @see FinProductExpire002Validator
 * @since 1.0
 */
@Component
public class FinProductExpire002Validator implements CommonValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductExpire002Service finProductExpire002Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        FinProductExpire002Dto finProductExpire002Dto = (FinProductExpire002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                finProductExpire002Dto.getBussTrdTransferOrder());
            validList.addAll(list);
        }
        
        if (!StringUtil.isEmptyList(finProductExpire002Dto.getBussTrdTransferOrder())){
            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : finProductExpire002Dto.getBussTrdTransferOrder()){
                if (StringUtil.isNotEmpty(bussTrdTransferOrderDto.getPurpose())
                    && bussTrdTransferOrderDto.getPurpose().length() > 300){
                    validList.add(new ValidMessage("bussTrdTransferOrder",
                        this.getMessage("FW_E_0002",
                            this.getMessage("BussTrdTransferOrder.purpose"))));
                }
            }
        }
        //add by wm 2019-08-19 增加收益型产品已做到期后增加控制 start
        if(StringUtil.equals(DictCodeList.交易方向_产品到期.getValue(), finProductExpire002Dto.getDealType())){
            if(finProductExpire002Service.isExpireTradeByFinprodId(finProductExpire002Dto.getFinprodId(),DictCodeList.交易方向_产品到期.getValue())>0){
            	validList.add(new ValidMessage("finprodId",
                         this.getMessage("该产品已做过到期交易")));
            }
        }
        else if(StringUtil.equals(DictCodeList.交易方向_提前终止.getValue(), finProductExpire002Dto.getDealType())) {
            if(finProductExpire002Service.isExpireTradeByFinprodId(finProductExpire002Dto.getFinprodId(),DictCodeList.交易方向_提前终止.getValue())>0){
            	validList.add(new ValidMessage("finprodId",
                        this.getMessage("该产品已做过提前终止交易")));
            }
        }
        //add by wm 2019-08-19 增加收益型产品已做到期后增加控制 end
        if (!StringUtil.isEmptyList(finProductExpire002Dto.getFinProductExpireBranchInfoDtoList())){
            // 判断分行信息是否重复
            Map<String, FinProductExpireBranchInfoDto> goalInfoMap = finProductExpire002Dto.getFinProductExpireBranchInfoDtoList().stream().collect(
                Collectors.toMap(FinProductExpireBranchInfoDto::getBranchCode, a -> a, (k1, k2) -> k1));
            if (goalInfoMap.size() != finProductExpire002Dto.getFinProductExpireBranchInfoDtoList().size()) {
                validList.add(new ValidMessage("finProductExpireBranchInfoDtoList",
                    this.getMessage("FIN_PROD_E_0081")));
            }
        }
        //add by wm 增加支付日不能小于赎回/到期日校验 2020-01-14 start
        if(StringUtil.isNotEmpty(finProductExpire002Dto.getPayDate())&&StringUtil.isNotEmpty(finProductExpire002Dto.getMdate())
        		&&finProductExpire002Dto.getPayDate().compareTo(finProductExpire002Dto.getMdate()) < 0){
            validList.add(new ValidMessage("payDate",
                    this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductExpire002.payDate"),
                        this.getMessage("FinProductExpire002.mdate"))));
        }
        //add by wm 增加支付日不能小于赎回/到期日校验 2020-01-14 end
        return validList;
    }

}
