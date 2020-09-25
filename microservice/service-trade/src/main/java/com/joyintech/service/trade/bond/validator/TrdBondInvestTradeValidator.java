/*
 * 文件名：TrdBondInvestTradeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.bond.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.service.trade.bond.service.TrdBondInvestTradeService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

@Repository
public class TrdBondInvestTradeValidator implements BeanValidator {
    /**
     *
     */
    @Autowired(required = false)
    private TrdBondInvestTradeService trdBondInvestTradeService;
    @Override
    public List<ValidMessage> valid(Object o) {
        TrdBondInvestTradeDto dto = (TrdBondInvestTradeDto)o;
        List<ValidMessage> ret = new ArrayList<>();
        if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_银行间.getValue())){
            ret.add(new ValidMessage("fTradeId",
                getMessage("FW_E_0006", getMessage("TrdBondInvestTrade.fTradeId"))));
        }
        if(DateUtil.compareDate(dto.getSettleDate(), dto.getMdate())>=0){
            ret.add(new ValidMessage("settleDate",
                getMessage("BUSS_COM_E_0004", getMessage("TrdBondInvestTrade.mdate"),
                    getMessage("TrdBondInvestTrade.settleDate"))));
        }
        if(trdBondInvestTradeService != null){
            if(StringUtil.isNotEmpty(dto.getEntrustId())){
                BaseEtrInvestInfoDto entrustInfo = trdBondInvestTradeService.getEntrustInfo(dto.getEntrustId());
                EtrEntrust etrEntrust = entrustInfo.getEtrEntrust();
                EtrEntrustAdd etrEntrustAdd = entrustInfo.getEtrEntrustAdd();
                if(etrEntrust != null){
                    if(etrEntrust.getEntrustAmt() != null && dto.getTradeAmt() != null && dto.getTradeAmt().compareTo(etrEntrust.getEntrustAmt()) > 0) {
                        ret.add(new ValidMessage("TrdBondInvestTrade.tradeAmt",
                                getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.tradeAmt"),
                                        getMessage("TrdBondInvestTrade.greater"))));
                    }
                    if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())){
                        if(etrEntrust.getPrice() != null && dto.getUnitCprice() != null && dto.getUnitCprice().compareTo(etrEntrust.getPrice()) > 0) {
                            ret.add(new ValidMessage("unitCprice",
                                    getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.unitCprice"),
                                            getMessage("TrdBondInvestTrade.greater"))));
                        }
                        if(etrEntrust.getPrice() != null && dto.getUnitCprice() != null && dto.getUnitCprice().compareTo(etrEntrust.getPrice()) > 0) {
                            ret.add(new ValidMessage("unitCprice",
                                    getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.unitCprice"),
                                            getMessage("TrdBondInvestTrade.greater"))));
                        }
                    }
                    if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                        if(etrEntrust.getPrice() != null && dto.getUnitCprice() != null && dto.getUnitCprice().compareTo(etrEntrust.getPrice()) < 0) {
                            ret.add(new ValidMessage("unitCprice",
                                    getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.unitCprice"),
                                            getMessage("TrdBondInvestTrade.less"))));
                        }
                    }
                }
                if(etrEntrustAdd != null){
                    if(etrEntrustAdd.getMatureYield() != null && dto.getYield() != null && dto.getYield().compareTo(etrEntrustAdd.getMatureYield()) > 0) {
                        ret.add(new ValidMessage("yield",
                                getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.yield"),
                                        getMessage("TrdBondInvestTrade.greater"))));
                    }
                    if(etrEntrustAdd.getExerYield() != null && dto.getExerYield() != null && dto.getExerYield().compareTo(etrEntrustAdd.getExerYield()) > 0) {
                        ret.add(new ValidMessage("exerYield",
                                getMessage("TRD_W_0007", getMessage("TrdBondInvestTrade.exerYield"),
                                        getMessage("TrdBondInvestTrade.greater"))));
                    }
                }
            }
        }
        return ret;
    }
}
