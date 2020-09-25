/*
 * 文件名：EtrPlanAssetCValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrplanassetc.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrplanassetc.dto.EtrPlanAssetCDto;
import com.joyintech.tams.invest.entrust.etrplanassetc.service.EtrPlanAssetCService;

/**
 * 计划类货币委托校验
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月3日
 * @see EtrPlanAssetCValidator
 * @since 1.0
 */
@Component
public class EtrPlanAssetCValidator implements EtrCommonValidator {

    @Autowired
    private EtrPlanAssetCService etrPlanAssetCService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrPlanAssetCDto dto = (EtrPlanAssetCDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrPlanAssetCDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetCDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrPlanAssetCService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrPlanAssetCDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetCDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrPlanAssetCService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrPlanAssetCDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetCDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrPlanAssetCService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
        }
        else {
            BigDecimal amt = dto.getEntrustAmt();
            BigDecimal num = dto.getEntrustNum();
            BigDecimal num2 = dto.getEntrustNum2();
            BigDecimal proAvailableCash = dto.getProAvailableCash();
            BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
            BigDecimal zero = new BigDecimal(0);
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue()) && amt == null){
                errorList.add(new ValidMessage("entrustAmt","请输入委托金额。"));
            }
            else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()) && num == null){
                errorList.add(new ValidMessage("entrustNum","请输入委托份额。"));
            }
            else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue()) && num == null){
                errorList.add(new ValidMessage("entrustNum","请输入委托份额。"));
            }
            else if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue()) && num2 == null){
                errorList.add(new ValidMessage("entrustNum2","请输入委托份额。"));
            }
            else if (amt == null && num == null && num2 == null) {
                errorList.add(new ValidMessage("请输入委托金额/委托份额。"));
            }
            if (amt != null) {
                if (!(amt.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustAmt", "委托金额不能小于等于0。"));
                }
                /*if(proAvailableCash != null && amt.compareTo(proAvailableCash)==1){
                    errorList.add(new ValidMessage("entrustAmt", "委托金额不能大于组合可用现金。"));
                }*/
//                if(assetAvailablePosition != null && amt.compareTo(assetAvailablePosition)==1){
//                    errorList.add(new ValidMessage("entrustAmt", "委托金额不能大于可用持仓。"));
//                }
//                BigDecimal limitAmt = etrPlanAssetCService.selectLimitAmt(dto);
//                if(limitAmt != null && amt.compareTo(limitAmt)==1){
//                    errorList.add(new ValidMessage("entrustAmt", "委托金额不能大于授权额度"+limitAmt));
//                }
            }
            if (num != null) {
                if (!(num.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustNum", "委托份额不能小于等于0。"));
                }
               /* if(assetAvailablePosition != null && num.compareTo(assetAvailablePosition)==1){
                    errorList.add(new ValidMessage("entrustNum", "委托份额不能大于可用持仓。"));
                }*/
                BigDecimal limitAmt = etrPlanAssetCService.selectLimitAmt(dto);
                if(limitAmt != null && num.compareTo(limitAmt)==1){
                    errorList.add(new ValidMessage("entrustNum", "委托份额不能大于授权额度"+limitAmt));
                }
            }
            
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", "委托开始日不能大于委托结束日"));
                }
                if(finvdate != null && vdate.before(finvdate)){
                    errorList.add(new ValidMessage("vdate","委托开始日不能小于资产起息日"+DateUtil.format(finvdate)));
                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate","委托终止日不能大于资产到期日"+DateUtil.format(finmdate)));
                }
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                if (!StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getCounterId())) {
                    errorList.add(new ValidMessage("etrInvestPersonInfo.counterId","请输入交易对手。"));
                }
            }

        BigDecimal entrustNum = dto.getEntrustNum();
        // 交易方向为卖出时，卖出份额为entrustNum2字段
        BigDecimal entrustNum2 = dto.getEntrustNum2();
        String ps = dto.getPs();
        if (entrustNum != null && StringUtil.isNotEmpty(ps)) {
            if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())){
                if(StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())){
                    if (entrustNum2.compareTo(assetAvailablePosition) > 0){
                        errorList.add(new ValidMessage("entrustNum2", "委托份额不能大于可用持仓。"));
                    }
                }else if(StringUtil.equals(ps, DictCodeList.交易方向_赎回.getValue())){
                    if (entrustNum.compareTo(assetAvailablePosition) > 0){
                        errorList.add(new ValidMessage("entrustNum", "委托份额不能大于可用持仓。"));
                    }
                }
            } else if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())
                    && (StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())
                    || StringUtil.equals(ps, DictCodeList.交易方向_申购.getValue()))
                    && proAvailableCash != null) {
                if (dto.getEntrustAmt().compareTo(proAvailableCash) == 1) {
                    errorList.add(
                            new ValidMessage("entrustAmt", "委托金额不能大于可用现金。"));
                }
            }
        }
            errorList.addAll(etrPlanAssetCService.check(dto));
        }
        return errorList;
    }

}
