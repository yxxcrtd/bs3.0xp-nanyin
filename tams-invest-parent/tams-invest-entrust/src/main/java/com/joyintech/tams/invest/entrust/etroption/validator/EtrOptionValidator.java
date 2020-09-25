/*
 * 文件名：EtrOptionValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：likai
 * 修改时间：2019年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etroption.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etroption.dto.EtrOptionDto;
import com.joyintech.tams.invest.entrust.etroption.service.EtrOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author likai
 * @version 1.0 2019年12月20日
 * @see EtrOptionValidator
 * @since 1.0
 */
@Component
public class EtrOptionValidator implements EtrCommonValidator {

    /**
     * 画面端service
     */
    @Autowired
    private EtrOptionService etrOptionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrOptionDto dto = (EtrOptionDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrOptionDto> listDto = dto.getSelectedItem();
            for (EtrOptionDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrOptionService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrOptionDto> listDto = dto.getSelectedItem();
            for (EtrOptionDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrOptionService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrOptionDto> listDto = dto.getSelectedItem();
            for (EtrOptionDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrOptionService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
//            if (dto.getEntrustAmt() != null) {
////                BigDecimal num = new BigDecimal(0);
////                if (dto.getEntrustAmt().compareTo(num) == 0
////                    || dto.getEntrustAmt().compareTo(num) == -1) {
////                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrLendSpecInfo001.entrustAmt"))));
////                }
////            }
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
            // errorList.addAll(etrLendSpecInfoService.check(dto));
        }
        else {
        	//----modify by 裴家俊  2019/03/20----------
           /* String riskmathswitch = CacheUtil.getSysParam(BussinessConstant.RISK_MATH_SWITCH);
            if(StringUtil.equals(riskmathswitch, "01")){
                if(dto.getRiskTrialRetDto() == null || dto.getRiskTrialRetDto().size() == 0){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_013")));
                }
            }*/
            // delete by renping 改为共通处理 2019-08-22 start
//            BigDecimal entrustAmt = dto.getEntrustAmt();
//            BigDecimal proAvailableCash = dto.getProAvailableCash();
//            if(entrustAmt != null){
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrLendSpecInfoService.selectLimitAmt(dto);
//                    if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_009",this.getMessage("EtrLendSpecInfo001.entrustAmt"))+limitAmt));
//                    }
//                }
//            }
            // delete by renping 改为共通处理 2019-08-22 end
//            if (entrustAmt != null && proAvailableCash != null
//                && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_拆出.getValue())) {
//                if (entrustAmt.compareTo(proAvailableCash) == 1) {
//                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_010",this.getMessage("EtrLendSpecInfo001.entrustAmt"))));
//                }
//            }
            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
//            if (dto.getEntrustAmt() != null) {
//                BigDecimal num = new BigDecimal(0);
//                if (dto.getEntrustAmt().compareTo(num) == 0
//                    || dto.getEntrustAmt().compareTo(num) == -1) {
//                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrLendSpecInfo001.entrustAmt"))));
//                }
//            }
            errorList.addAll(etrOptionService.check(dto));
        }
        return errorList;
    }

}
