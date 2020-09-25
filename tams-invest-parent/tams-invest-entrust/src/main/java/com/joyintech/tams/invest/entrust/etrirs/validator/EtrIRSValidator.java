/*
 * 文件名：EtrIRSValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2018年9月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrirs.validator;

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
import com.joyintech.tams.invest.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.tams.invest.entrust.etrirs.service.EtrIRSService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtrIRSValidator
 * @since 1.0
 */
@Component
public class EtrIRSValidator implements EtrCommonValidator {

    /**
     * 画面端service
     */
    @Autowired
    private EtrIRSService etrIRSService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrIRSDto dto = (EtrIRSDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrIRSDto> listDto = dto.getSelectedItem();
            for (EtrIRSDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrIRSService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrIRSDto> listDto = dto.getSelectedItem();
            for (EtrIRSDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrIRSService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrIRSDto> listDto = dto.getSelectedItem();
            for (EtrIRSDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrIRSService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            
            
            errorList.addAll(myValid(dto));
            
            if (!(DictCodeList.委托状态_已撤回.getValue().equals(dto.getEntrustStatus())
                  || DictCodeList.委托状态_已退回.getValue().equals(dto.getEntrustStatus()))) {
                errorList.add(new ValidMessage(
                    // “新建”、“已撤回”、“已退回”
                    this.getMessage("ETR_E_0009", this.getMessage("ETR_LEND_001"))));
            }
        }
        else {
            //新增
        	errorList.addAll(myValid(dto));
        	 //交易对手必填
//            if(StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getCounterId())){
//            	errorList.add(new ValidMessage("counterId", this.getMessage("ETR_IRS_002")));
//            }
            errorList.addAll(etrIRSService.check(dto));
        
        }
        return errorList;
    }
    
    public List<ValidMessage> myValid(EtrIRSDto dto ) {
    	List<ValidMessage> errorList =  new ArrayList<>();
        // delete by renping 改为共通处理 2019-08-22 start
//    	//授权组件
//        BigDecimal entrustAmt = dto.getEntrustAmt();
//        if (entrustAmt != null) {
//            String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//            if (StringUtil.equals(etrlimit_switch, "01")) {
//                BigDecimal limitAmt = etrIRSService.selectLimitAmt(dto);
//                if (limitAmt != null && entrustAmt.compareTo(limitAmt) == 1) {
//                    errorList.add(new ValidMessage("entrustAmt",
//                        this.getMessage("ETR_X_009", this.getMessage("EtrWtSpotex.entrustAmt"))
//                                                                 + limitAmt));
//                }
//            }
//        }
        // delete by renping 改为共通处理 2019-08-22 end
        //委托日期
        if (dto.getMdate() != null && dto.getVdate() != null) {
            if (dto.getMdate().before(dto.getVdate())) {
                errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
            }
        }
        //期限品种和到期日只能填一个，且必填一个
        if(StringUtil.isNotEmpty(dto.getTermType())&&(StringUtil.isNotEmpty(dto.getDueDate()) && !StringUtil.equals(" - ", dto.getDueDate()))){
        	errorList.add(new ValidMessage("termType", this.getMessage("ETR_IRS_001")));
        }
        if(StringUtil.isEmpty(dto.getTermType())&&(StringUtil.isEmpty(dto.getDueDate()) || StringUtil.equals(" - ", dto.getDueDate()))){
        	errorList.add(new ValidMessage("termType", this.getMessage("ETR_IRS_001")));
        }
       
    	return errorList;
    }

}
