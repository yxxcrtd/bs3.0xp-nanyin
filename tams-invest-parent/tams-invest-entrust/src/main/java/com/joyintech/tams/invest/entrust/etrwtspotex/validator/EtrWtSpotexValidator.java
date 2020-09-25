/*
 * 文件名：EtrWtSpotexValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrwtspotex.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrwtspotex.dto.EtrWtSpotexDto;
import com.joyintech.tams.invest.entrust.etrwtspotex.service.EtrWtSpotexService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author wangguoxian
 * @version 1.0 2018年9月28日
 * @see EtrWtSpotexValidator
 * @since 1.0
 */
@Component
public class EtrWtSpotexValidator implements EtrCommonValidator {

    /**
     * 画面端service
     */
    @Autowired
    private EtrWtSpotexService EtrWtSpotexService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrWtSpotexDto dto = (EtrWtSpotexDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrWtSpotexDto> listDto = dto.getSelectedItem();
            for (EtrWtSpotexDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(EtrWtSpotexService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrWtSpotexDto> listDto = dto.getSelectedItem();
            for (EtrWtSpotexDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(EtrWtSpotexService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrWtSpotexDto> listDto = dto.getSelectedItem();
            for (EtrWtSpotexDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(EtrWtSpotexService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
            // 交易方向是卖出的时候，交易货币金额是否可以小于等于零？软校验还是硬校验？
            if (dto.getEntrustAmt() != null
                && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                BigDecimal num = new BigDecimal(0);
                if (dto.getEntrustAmt().compareTo(num) == 0
                    || dto.getEntrustAmt().compareTo(num) == -1) {
                    errorList.add(new ValidMessage("entrustAmt",
                        this.getMessage("ETR_X_008", this.getMessage("EtrWtSpotex.entrustAmt"))));
                }
            }
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
            // errorList.addAll(EtrWtSpotexService.check(dto));
        }
        else {
            // ----modify by 裴家俊 2019/03/20----------
            /*
             * String riskmathswitch = CacheUtil.getSysParam(BussinessConstant.RISK_MATH_SWITCH);
             * if(StringUtil.equals(riskmathswitch, "01")){ if(dto.getRiskTrialRetDto() == null ||
             * dto.getRiskTrialRetDto().size() == 0){ errorList.add(new
             * ValidMessage(this.getMessage("ETR_X_013"))); } }
             */
            // delete by renping 改为共通处理 2019-08-22 start
//            BigDecimal entrustAmt = dto.getEntrustAmt();
//            BigDecimal proAvailableCash = dto.getProAvailableCash();
//            if (entrustAmt != null) {
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if (StringUtil.equals(etrlimit_switch, "01")) {
//                    BigDecimal limitAmt = EtrWtSpotexService.selectLimitAmt(dto);
//                    if (limitAmt != null && entrustAmt.compareTo(limitAmt) == 1) {
//                        errorList.add(new ValidMessage("entrustAmt",
//                            this.getMessage("ETR_X_009", this.getMessage("EtrWtSpotex.entrustAmt"))
//                                                                     + limitAmt));
//                    }
//                }
//            }
            // delete by renping 改为共通处理 2019-08-22 end
            // if (entrustAmt != null && proAvailableCash != null
            // && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_拆出.getValue())) {
            // if (entrustAmt.compareTo(proAvailableCash) == 1) {
            // errorList.add(new ValidMessage("entrustAmt",
            // this.getMessage("ETR_X_010",this.getMessage("EtrWtSpotex.entrustAmt"))));
            // }
            // }
            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
            // 交易方向是卖出的时候，交易货币金额是否可以小于等于零？软校验还是硬校验？
            if (dto.getEntrustAmt() != null
                && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                BigDecimal num = new BigDecimal(0);
                if (dto.getEntrustAmt().compareTo(num) == 0
                    || dto.getEntrustAmt().compareTo(num) == -1) {
                    errorList.add(new ValidMessage("entrustAmt",
                        this.getMessage("ETR_X_008", this.getMessage("EtrWtSpotex.entrustAmt"))));
                }
            }
            errorList.addAll(EtrWtSpotexService.check(dto));
        }
        return errorList;
    }

}
