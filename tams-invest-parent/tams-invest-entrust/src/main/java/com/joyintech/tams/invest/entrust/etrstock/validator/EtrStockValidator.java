package com.joyintech.tams.invest.entrust.etrstock.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;

/**
 * @title: EtrStockValidator
 * @package com.joyintech.tams.invest.entrust.etrstock.validator
 * @description: 股票委托校验
 * @author: junYin
 * @date: 2019-06-04 14:56
*/
@Component
public class EtrStockValidator implements EtrCommonValidator {

    @Autowired
    private EtrStockService etrStockService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrStockDto dto = (EtrStockDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrStockDto> listDto = dto.getSelectedItem();
            for (EtrStockDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrStockService.check(entity));
            }
        }else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrStockDto> listDto = dto.getSelectedItem();
            for (EtrStockDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrStockService.check(entity));
            }
        }else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrStockDto> listDto = dto.getSelectedItem();
            for (EtrStockDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrStockService.check(entity));
            }
        }else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
        }else {
            BigDecimal amt = dto.getEntrustAmt();
            BigDecimal num = dto.getEntrustNum();
            BigDecimal price = dto.getPrice();
            BigDecimal proAvailableCash = dto.getProAvailableCash();
            BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
            BigDecimal zero = new BigDecimal(0);
            String secAcctId = dto.getSecAcctId();
            String chlId = dto.getChlId();
            if(secAcctId != null  && !"".equals(secAcctId) && chlId != null && !"".equals(chlId)){
                errorList.add(new ValidMessage(this.getMessage("ETR_X_012",this.getMessage("EtrStock002.secAcctIdOrchlId"+"invest.channelId"))));
            }
            //委托金额 ，委托数量与委托单价  二选一  必填
            /*if((!StringUtil.isNotEmpty(num) || !StringUtil.isNotEmpty(price))  ||!StringUtil.isNotEmpty(amt) ){
            	if(!StringUtil.isNotEmpty(amt)&&!StringUtil.isNotEmpty(num)&&!StringUtil.isNotEmpty(price)){
            		errorList.add(new ValidMessage("entrustAmt","委托金额必填或委托数量与委托单价必填!"));
            	}else{
            		if(StringUtil.isNotEmpty(amt)){
//                		errorList.add(new ValidMessage("entrustAmt","委托金额不能为空！"));
            			//委托金额不为空 不需要处理
            			
                	}else{
                		if(!StringUtil.isNotEmpty(num)){
                    		errorList.add(new ValidMessage("entrustNum","委托数量不能为空！"));
                    	}
                    	if(!StringUtil.isNotEmpty(price)){
                    		errorList.add(new ValidMessage("price","委托价格不能为空！"));
                    	}
                	}
                	
            	}
            	
            }*/
            //价格指令
            if(!StringUtil.isNotEmpty(amt) && !StringUtil.isNotEmpty(num)){
            	errorList.add(new ValidMessage("entrustAmt","委托金额或委托数量必填!"));
            }
            if((StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue())||StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue()))
            		&&!StringUtil.isNotEmpty(dto.getPrice())){
            	errorList.add(new ValidMessage("price","非市价指令，委托价格必填!"));
            }
            
            BigDecimal limitAmt = etrStockService.selectLimitAmt(dto);
            //委托金额不为空
            // 增加组合可用现金和可用持仓的校验开关 by 赵东东 2020121 start
            if(amt!=null){
            	 if (!(amt.compareTo(zero) == 1)) {
                     errorList.add(new ValidMessage("entrustAmt",getMessage("ETR_X_008", getMessage("EtrStock002.entrustAmt"))));
                 }
            	 if(proAvailableCash != null && amt.compareTo(proAvailableCash)==1&& StringUtil.equals(
                     CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                     DictCodeListExt1.是或否_否.getValue())){
                     errorList.add(new ValidMessage("entrustAmt",this.getMessage("ETR_X_010", this.getMessage("EtrStock002.entrustAmt"))));
                 }
                 if(limitAmt != null && amt.compareTo(limitAmt)==1){
                     errorList.add(new ValidMessage("entrustAmt",this.getMessage("ETR_X_009", this.getMessage("EtrStock002.entrustAmt")) + limitAmt));
                 }
            }
            if (num != null && price != null) {
                amt = num.multiply(price);
                if (!(amt.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage(getMessage("ETR_X_008", getMessage("EtrStock002.entrustAmt"))));
                }
                if(proAvailableCash != null && amt.compareTo(proAvailableCash)==1&&StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_010", this.getMessage("EtrStock002.entrustAmt"))));
                }
                if(limitAmt != null && amt.compareTo(limitAmt)==1){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_009", this.getMessage("EtrStock002.entrustAmt")) + limitAmt));
                }
            }
            if (num != null) {
                if (!(num.compareTo(zero) == 1)) {
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_008", this.getMessage("EtrStock002.entrustNum"))));
                }
                if(assetAvailablePosition != null && num.compareTo(assetAvailablePosition)==1&&StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_011", this.getMessage("EtrStock002.entrustNum"))));
                }
            }
            
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_005")));
                }
            }
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())||StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                if (!StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getCounterId())) {
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_012",this.getMessage("etrInvestPersonInfo.counterId"))));
                }
            }
            errorList.addAll(etrStockService.check(dto));
        }
        return errorList;
        
    }

}
