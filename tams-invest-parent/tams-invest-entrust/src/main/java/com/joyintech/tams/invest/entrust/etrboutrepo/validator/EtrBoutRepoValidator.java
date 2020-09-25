/*
 * 文件名：EtrBoutRepoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrboutrepo.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrboutrepo.dto.EtrBoutRepoDto;
import com.joyintech.tams.invest.entrust.etrboutrepo.service.EtrBoutRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月10日
 * @see EtrBoutRepoValidator
 * @since 1.0
 */
@Component
public class EtrBoutRepoValidator implements EtrCommonValidator {
    
    @Autowired
    private EtrBoutRepoService etrBoutRepoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrBoutRepoDto dto = (EtrBoutRepoDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrBoutRepoDto> listDto = dto.getSelectedItem();
            for (EtrBoutRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrBoutRepoService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrBoutRepoDto> listDto = dto.getSelectedItem();
            for (EtrBoutRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrBoutRepoService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrBoutRepoDto> listDto = dto.getSelectedItem();
            for (EtrBoutRepoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrBoutRepoService.check(entity));
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
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date mdateAdd = dto.getMdateAdd();
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
            if(mdateAdd != null && finmdate != null && mdateAdd.after(finmdate)){
                errorList.add(new ValidMessage("mdateAdd",this.getMessage("ETR_X_014",DateUtil.format(finmdate),DateUtil.format(mdateAdd))));
            }
            //证券托管户 与通道二选一
            if(("".equals(dto.getChlId())||dto.getChlId()==null)&&("".equals(dto.getSecuritiesCust())||dto.getSecuritiesCust()==null)){
                ValidMessage validMessage = new ValidMessage("chlId", getMessage("TRD_E_0014",getMessage("invest.channelId")));
                errorList.add(validMessage);
            }
            BigDecimal firstprice = dto.getFirstprice();
            BigDecimal firstprice2 = dto.getFirstprice2();
            if(firstprice != null && firstprice2 != null){
                if(firstprice.compareTo(firstprice2)==1){
                    errorList.add(new ValidMessage("firstprice2",this.getMessage("ETR_REPO_001")));
                }
            }
            BigDecimal entrustAmt = dto.getEntrustAmt();
            BigDecimal proAvailableCash = dto.getProAvailableCash();
            BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
            // delete by renping 改为共通处理 2019-08-22 start
//            if(entrustAmt != null){
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrBoutRepoService.selectLimitAmt(dto);
//                    if(limitAmt != null && entrustAmt.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_009",this.getMessage("EtrBoutRepo002.entrustAmt"))+limitAmt));
//                    }
//                }
//            }
            // delete by renping 改为共通处理 2019-08-22 end
            // 给指令可用持仓check增加开关 by 赵东东 start 20200121
            if(entrustAmt != null && assetAvailablePosition != null && entrustAmt.divide(new BigDecimal(100)).compareTo(assetAvailablePosition) ==1&&StringUtil.equals(
                CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                DictCodeListExt1.是或否_否.getValue())){
                errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_011",this.getMessage("EtrBoutRepo002.entrustAmt"))));
            }
            // 给指令可用现金check增加开关 by 赵东东 start 20200121
            if (entrustAmt != null && proAvailableCash != null
                && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())&&StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())) {
                if (entrustAmt.compareTo(proAvailableCash) == 1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_010",this.getMessage("EtrRepo002.entrustAmt"))));
                }
            }
            if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
            if (dto.getEntrustAmt() != null) {
                BigDecimal num = new BigDecimal(0);
                if (dto.getEntrustAmt().compareTo(num) == 0
                    || dto.getEntrustAmt().compareTo(num) == -1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrBoutRepo002.entrustAmt"))));
                }
            }

            // 当价格模式不为市价指令时，才check回购利率和首次净价
            if (!StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())) {
            	if(dto.getPrice() == null) {
                	errorList.add(new ValidMessage("price", this.getMessage("ETR_X_012",this.getMessage("EtrBoutRepo002.price"))));
            	}
            	if(dto.getFirstprice() == null) {
                	errorList.add(new ValidMessage("firstprice", this.getMessage("ETR_X_012",this.getMessage("EtrBoutRepo002.firstprice1"))));
            	}
            	if(dto.getFirstprice2() == null) {
                	errorList.add(new ValidMessage("firstprice2", this.getMessage("ETR_X_012",this.getMessage("EtrBoutRepo002.firstprice2"))));
            	}
            }
            errorList.addAll(etrBoutRepoService.check(dto));
        }
        return errorList;
    }

}
