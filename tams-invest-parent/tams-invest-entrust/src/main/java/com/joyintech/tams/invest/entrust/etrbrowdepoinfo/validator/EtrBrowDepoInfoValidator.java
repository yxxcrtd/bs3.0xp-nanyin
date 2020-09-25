/*
 * 文件名：EtrBrowDepoInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrbrowdepoinfo.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.tams.invest.entrust.etrbrowdepoinfo.service.EtrBrowDepoInfoService;

/**
 * 存款委托check
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月25日
 * @see EtrBrowDepoInfoValidator
 * @since 1.0
 */
@Component
public class EtrBrowDepoInfoValidator implements EtrCommonValidator {

    @Autowired
    private EtrBrowDepoInfoService etrBrowDepoInfoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrBrowDepoInfoDto dto = (EtrBrowDepoInfoDto)value;
//        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                List<EtrBrowDepoInfoDto> listDto = dto.getSelectedItem();
                for (EtrBrowDepoInfoDto entity : listDto) {
                        entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                        errorList.addAll(etrBrowDepoInfoService.check(entity));
                }
            }
            else if (StringUtil.equals(dto.getOperateType(), "close")) {
                List<EtrBrowDepoInfoDto> listDto = dto.getSelectedItem();
                for (EtrBrowDepoInfoDto entity : listDto) {
                        entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                        errorList.addAll(etrBrowDepoInfoService.check(entity));
                }
            }
            else if (StringUtil.equals(dto.getOperateType(), "recall")) {
                List<EtrBrowDepoInfoDto> listDto = dto.getSelectedItem();
                for (EtrBrowDepoInfoDto entity : listDto) {
                        entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                        errorList.addAll(etrBrowDepoInfoService.check(entity));
                }
            }
            else if (StringUtil.equals(dto.getOperateType(), "edit")) {
                // delete by renping 改为共通处理 2019-08-22 start
//                BigDecimal entrustAmt = dto.getEntrustAmt();
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    if(entrustAmt!=null){
//                        BigDecimal limitAmt = etrBrowDepoInfoService.selectLimitAmt(dto);
//                        if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
//                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_009",getMessage("EtrBrowDepoInfo001.entrustAmt"))+limitAmt));
//                        }
//                    }
//                }
                // delete by renping 改为共通处理 2019-08-22 end
                dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
                if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                    && !StringUtil.isNotEmpty(dto.getPortfolioId())) {
                    errorList.add(new ValidMessage("finprodId", getMessage("ETR_X_012", getMessage("EtrBrowDepoInfo001.finprodId"))));
                }
                errorList.addAll(etrBrowDepoInfoService.check(dto));
            }
            else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
                BigDecimal entrustAmt = dto.getEntrustAmt();
                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
                if(StringUtil.equals(etrlimit_switch, "01")){
                    if(entrustAmt!=null){
                        BigDecimal limitAmt = etrBrowDepoInfoService.selectLimitAmt(dto);
                        if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_009",getMessage("EtrBrowDepoInfo001.entrustAmt"))+limitAmt));
                        }
                    }
                }
                if(StringUtil.isNotEmpty(dto.getMdate()) && StringUtil.isNotEmpty(dto.getDateEnd())){
                    if(dto.getMdate().compareTo(dto.getDateEnd()) != -1){
                        errorList.add(new ValidMessage("mdate", getMessage("ETR_X_007")));
                    }
                }
                if(dto.getPs().equals(DictCodeList.交易方向_买入.getValue())){
                    if(StringUtil.isNotEmpty(dto.getEntrustAmt())){
                        if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
                        }
                    }
                }
                else{
                    if(StringUtil.isNotEmpty(dto.getEntrustAmt()) && StringUtil.isNotEmpty(dto.getAssetAvailablePosition())){
                        if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
                        }
                        else{
                            int result = dto.getEntrustAmt().compareTo(dto.getAssetAvailablePosition());
                            if(result==1){
                                //MODIFY BY GAOPENG 20200114 错误提示信息修改 start
                                errorList.add(new ValidMessage("",getMessage("ETR_BROW_0013")));
                                //MODIFY BY GAOPENG 20200114 错误提示信息修改 end
                            }
                        }
                    }
                }
                if(StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())){
                    if(dto.getVdate().compareTo(dto.getMdate()) == 1){
                        errorList.add(new ValidMessage("vdate", getMessage("ETR_X_005")));
                    }
                }
                if (dto.getEntrustAmt()!=null && dto.getIusseAmt()!=null && dto.getEntrustAmt().compareTo(dto.getIusseAmt())==1){
                    errorList.add(new ValidMessage("entrustAmt",getMessage("ETR_X_015")+dto.getIusseAmt().toString()));
                }

            }
            else if(StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
                BigDecimal entrustAmt = dto.getEntrustAmt();
                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
                if(StringUtil.equals(etrlimit_switch, "01")){
                    if(entrustAmt!=null){
                        BigDecimal limitAmt = etrBrowDepoInfoService.selectLimitAmt(dto);
                        if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_009",getMessage("EtrBrowDepoInfo001.entrustAmt"))+limitAmt));
                        }
                    }
                }
                if(StringUtil.isNotEmpty(dto.getMdate()) && StringUtil.isNotEmpty(dto.getDateEnd())){
                    if(dto.getMdate().compareTo(dto.getDateEnd()) != -1){
                        errorList.add(new ValidMessage("mdate", getMessage("ETR_X_007")));
                    }
                }
                if(dto.getPs().equals(DictCodeList.交易方向_买入.getValue())){
                    if(StringUtil.isNotEmpty(dto.getEntrustAmt())){
                        if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
                        }
                    }
                }
                else{
                    if(StringUtil.isNotEmpty(dto.getEntrustAmt()) && StringUtil.isNotEmpty(dto.getAssetAvailablePosition())){
                        if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
                            errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
                        }
                        else{
                            int result = dto.getEntrustAmt().compareTo(dto.getAssetAvailablePosition());
                            if(result==1){
                                //MODIFY BY GAOPENG 20200114 错误提示信息修改 start
                                errorList.add(new ValidMessage("",getMessage("ETR_BROW_0013"))); 
                                //MODIFY BY GAOPENG 20200114 错误提示信息修改 end
                            }
                        }
                    }
                }
            }
            if(StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())){
                if(dto.getVdate().compareTo(dto.getMdate()) == 1){
                    errorList.add(new ValidMessage("vdate", getMessage("ETR_X_005")));
                }
            }
//        }
//        else{
//          //新建校验
//            if(dto.getPs().equals(DictCodeList.交易方向_买入.getValue())){
//                if(StringUtil.isNotEmpty(dto.getEntrustAmt())){
//                    if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
//                        errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
//                    }
//                }
//            }
//            else{
//                if(StringUtil.isNotEmpty(dto.getEntrustAmt()) && StringUtil.isNotEmpty(dto.getAssetAvailablePosition())){
//                    if(dto.getEntrustAmt().compareTo(new BigDecimal(0)) != 1){
//                        errorList.add(new ValidMessage("entrustAmt", getMessage("ETR_X_008",getMessage("EtrBrowDepoInfo001.entrustAmt"))));
//                    }
//                    else{
//                        int result = dto.getEntrustAmt().compareTo(dto.getAssetAvailablePosition());
//                        if(result==1){
//                            errorList.add(new ValidMessage("",getMessage("ETR_E_0013")));
//                        }
//                    }
//                }
//            }
//            if(StringUtil.isNotEmpty(dto.getVdate()) && StringUtil.isNotEmpty(dto.getMdate())){
//                if(dto.getVdate().compareTo(dto.getMdate()) == 1){
//                    errorList.add(new ValidMessage("vdate", getMessage("ETR_X_005")));
//                }
//            }
//            if(StringUtil.isNotEmpty(dto.getMdate()) && StringUtil.isNotEmpty(dto.getDateEnd())){
//                if(dto.getMdate().compareTo(dto.getDateEnd()) != -1){
//                    errorList.add(new ValidMessage("mdate", getMessage("ETR_X_007")));
//                }
//            }
//        }
        return errorList;
    }

}
