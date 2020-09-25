/*
 * 文件名：EtrWtFxswapValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrwtfxswap.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.tams.invest.entrust.etrwtfxswap.service.EtrWtFxswapService;

/**
 * 外汇掉期委托校验
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapValidator
 * @since 1.0
 */
@Component
public class EtrWtFxswapValidator implements EtrCommonValidator{

    @Autowired
    private EtrWtFxswapService etrWtFxswapService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrWtFxswapDto dto = (EtrWtFxswapDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrWtFxswapDto> listDto = dto.getSelectedItem();
            for (EtrWtFxswapDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrWtFxswapService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrWtFxswapDto> listDto = dto.getSelectedItem();
            for (EtrWtFxswapDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrWtFxswapService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrWtFxswapDto> listDto = dto.getSelectedItem();
            for (EtrWtFxswapDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrWtFxswapService.check(entity));
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
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            String termType=dto.getTermType();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
               
            }
           if(termType.equals(DictCodeListExt2.外汇期限类型_即期对远期掉期.getValue()) &&
               StringUtil.isEmpty(dto.getRemoteDeliveryDate())){
               errorList.add(new ValidMessage("remoteDeliveryDate", this.getMessage("ETR_X_012",this.getMessage("EtrWtFxswap002.remoteDeliveryDate"))));
           }else if(termType.equals(DictCodeListExt2.外汇期限类型_远期对远期掉期.getValue())){
               if(StringUtil.isNotEmpty(dto.getRemoteDeliveryDate()) && StringUtil.isNotEmpty(dto.getProximalDeliveryDate())){
                   List<String> remoteDeliveryDateList = StringUtil.splitToList(" ", dto.getRemoteDeliveryDate());
                   Date remoteDate=DateUtil.parse(remoteDeliveryDateList.get(0));
                   List<String> proximalDeliveryDateList = StringUtil.splitToList(" ", dto.getProximalDeliveryDate());
                   Date proximalDate=DateUtil.parse(proximalDeliveryDateList.get(2));
                   if (proximalDate.after(remoteDate)) {
                       errorList.add(new ValidMessage("proximalDeliveryDate", this.getMessage("ETR_FXSWAP_001")));
                   }
               }
               if(StringUtil.isEmpty(dto.getRemoteDeliveryDate())){
                   errorList.add(new ValidMessage("remoteDeliveryDate", this.getMessage("ETR_X_012",this.getMessage("EtrWtFxswap002.remoteDeliveryDate"))));
               }
               if(StringUtil.isEmpty(dto.getProximalDeliveryDate())){
                   errorList.add(new ValidMessage("proximalDeliveryDate", this.getMessage("ETR_X_012",this.getMessage("EtrWtFxswap002.proximalDeliveryDate"))));
              }
           }
           //交易对手非空校验
           if(!StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getCounterId())){
               errorList.add(new ValidMessage("etrInvestPersonInfo.counterId", this.getMessage("ETR_FXSWAP_002")));
           }
            
        }
        return errorList;
    }

}
