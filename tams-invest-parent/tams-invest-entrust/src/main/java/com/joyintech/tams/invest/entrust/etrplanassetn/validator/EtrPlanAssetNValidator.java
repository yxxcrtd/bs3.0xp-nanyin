/*
 * 文件名：EtrPlanAssetNValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrplanassetn.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.tams.invest.entrust.etrplanassetn.service.EtrPlanAssetNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 计划类净值委托校验类 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年10月8日
 * @see EtrPlanAssetNValidator
 * @since 1.0
 */
@Component
public class EtrPlanAssetNValidator implements EtrCommonValidator {

    /**
     * 画面层service
     */
    @Autowired
    private EtrPlanAssetNService etrPlanAssetNService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrPlanAssetNDto dto = (EtrPlanAssetNDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrPlanAssetNDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetNDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrPlanAssetNService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrPlanAssetNDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetNDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrPlanAssetNService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrPlanAssetNDto> listDto = dto.getSelectedItem();
            for (EtrPlanAssetNDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrPlanAssetNService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            // BigDecimal amt = dto.getEntrustAmt();
            // BigDecimal num = dto.getEntrustNum();
            // BigDecimal zero = new BigDecimal(0);
            // if(amt == null && num == null){
            // errorList.add(new ValidMessage("请输入交易金额/交易份额"));
            // }
            // if(!(amt != null && amt.compareTo(zero)==1)){
            // errorList.add(new ValidMessage("entrustAmt","交易金额不能小于等于0"));
            // }
            // if(!(num != null && num.compareTo(zero)==1)){
            // errorList.add(new ValidMessage("entrustNum","交易份额不能小于等于0"));
            // }
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            // if(dto.getMdate().before(dto.getVdate())){
            // errorList.add(new ValidMessage("vdate","委托结束日不能小于委托开始"));
            // errorList.add(new ValidMessage("mdate","委托开始日不能大于委托结束日"));
            // }
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
            // errorList.addAll(etrPlanAssetNService.check(dto));
        }
        else {
        	//----modify by 裴家俊  2019/03/20----------
            /*String riskmathswitch = CacheUtil.getSysParam(BussinessConstant.RISK_MATH_SWITCH);
            if(StringUtil.equals(riskmathswitch, "01")){
                if(dto.getRiskTrialRetDto() == null || dto.getRiskTrialRetDto().size() == 0){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_013")));
                }
            }*/
            BigDecimal amt = dto.getEntrustAmt();
            BigDecimal num = dto.getEntrustNum();
            BigDecimal proAvailableCash = dto.getProAvailableCash();
            BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
            BigDecimal zero = new BigDecimal(0);
            if (amt == null && num == null) {
                errorList.add(new ValidMessage(this.getMessage("ETR_PLANN_001")));
            }
            if (amt != null) {
                if (!(amt.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrPlanAssetN001.entrustAmt"))));
                }
//                if(proAvailableCash != null && amt.compareTo(proAvailableCash)==1){
//                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_010",this.getMessage("EtrPlanAssetN001.entrustAmt"))));
//                }
//                if(assetAvailablePosition != null && amt.compareTo(assetAvailablePosition)==1){
//                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_011",this.getMessage("EtrPlanAssetN001.entrustAmt"))));
//                } // 不做金额跟持仓的check
                // delete by renping 改为共通处理 2019-08-22 start
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrPlanAssetNService.selectLimitAmt(dto);
//                    if(limitAmt != null && amt.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_009",this.getMessage("EtrPlanAssetN001.entrustAmt"))+limitAmt));
//                    }
//                }
                // delete by renping 改为共通处理 2019-08-22 end
            }
            if (num != null) {
                if (!(num.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_008",this.getMessage("EtrPlanAssetN002.entrustNum"))));
                }
                /*可用现金与持仓Check  add by zhuyongkang 2019/1/20 start*/
                // 可用现金校验
                if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                        DictCodeListExt1.是或否_否.getValue())
                        && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
                        ||StringUtil.equals(dto.getPs(),DictCodeList.交易方向_申购.getValue()))
                        && dto.getProAvailableCash() != null
                        && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_018",this.getMessage("EtrPlanAssetN002.entrustAmt"))));
                }
                // 可用持仓校验
                else if (StringUtil.equals(
                        CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                        DictCodeListExt1.是或否_否.getValue())
                        && (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                        || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue()))
                        && dto.getAssetAvailablePosition() != null
                        && dto.getEntrustNum().compareTo(dto.getAssetAvailablePosition()) > 0) {
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_011",this.getMessage("EtrPlanAssetN002.entrustNum"))));
                }
              /*  if(assetAvailablePosition != null && num.compareTo(assetAvailablePosition)==1){
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_011",this.getMessage("EtrPlanAssetN002.entrustNum"))));
                }*/
                /*可用现金与持仓Check  add by zhuyongkang 2019/1/20 end*/
                // delete by renping 改为共通处理 2019-08-22 start
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrPlanAssetNService.selectLimitAmt(dto);
//                    if(limitAmt != null && num.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_X_009",this.getMessage("EtrPlanAssetN002.entrustNum"))+limitAmt));
//                    }
//                }
                // delete by renping 改为共通处理 2019-08-22 end
            }
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
                if(finvdate != null && vdate.before(finvdate)){
                    errorList.add(new ValidMessage("vdate",this.getMessage("ETR_X_006")+DateUtil.format(finvdate)));
                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+DateUtil.format(finmdate)));
                }
            }
            if (!StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getCounterId())) {
                errorList.add(new ValidMessage("etrInvestPersonInfo.counterId","请输入交易对手。"));
            }
            errorList.addAll(etrPlanAssetNService.check(dto));
        }
        return errorList;
    }

}
