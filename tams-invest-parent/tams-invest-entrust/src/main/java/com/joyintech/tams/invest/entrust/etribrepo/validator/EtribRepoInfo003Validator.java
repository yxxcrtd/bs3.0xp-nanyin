package com.joyintech.tams.invest.entrust.etribrepo.validator;

import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtribRepoInfo003Dto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtribRepoInfo003Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author 王波
 * @date 2021年01月23日
 * @see EtribRepoInfo003Validator
 * @since 1.0
 */
@Component
public class EtribRepoInfo003Validator implements EtrCommonValidator  {

    @Autowired
    private EtribRepoInfo003Service etribRepoInfo003Service;

    @Override
    public String getMessage(String messageCode, String... args) {
        return SpringContextUtil.getTextValue(messageCode, args);
    }

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtribRepoInfo003Dto dto = (EtribRepoInfo003Dto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtribRepoInfo003Dto> listDto = dto.getSelectedItem();
            for (EtribRepoInfo003Dto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etribRepoInfo003Service.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtribRepoInfo003Dto> listDto = dto.getSelectedItem();
            for (EtribRepoInfo003Dto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etribRepoInfo003Service.check(entity));
            }
            //调用交易系统的是否允许撤销指令判断接口 判断指令能否撤销 begin chaowei
//            if(StringUtil.isEmptyList(errorList)) {
//                ValidMessage validMessage = etrCommonService.validEtrClose(listDto);
//                if(validMessage!=null) {
//                    errorList.add(validMessage);
//                }
//            }
            //调用交易系统的是否允许撤销指令判断接口 判断指令能否撤销 end chaowei
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtribRepoInfo003Dto> listDto = dto.getSelectedItem();
            for (EtribRepoInfo003Dto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etribRepoInfo003Service.check(entity));
            }
            //调用交易系统的是否允许撤销指令判断接口 判断指令能否撤销 begin chaowei
//            if(StringUtil.isEmptyList(errorList)) {
//                ValidMessage validMessage = etrCommonService.validEtrClose(listDto);
//                if(validMessage!=null) {
//                    errorList.add(validMessage);
//                }
//            }
            //调用交易系统的是否允许撤销指令判断接口 判断指令能否撤销 end chaowei

        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            errorList.addAll(etribRepoInfo003Service.check(dto));
        }
        else {
            //----modify by 裴家俊  2019/03/20----------
           /* String riskmathswitch = CacheUtil.getSysParam(BussinessConstant.RISK_MATH_SWITCH);
            if(StringUtil.equals(riskmathswitch, "01")){
                if(dto.getRiskTrialRetDto() == null || dto.getRiskTrialRetDto().size() == 0){
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_013")));
                }
            }*/
            // 交易所债一级市场时，交易平台非必填 add by renping 20191105 start
//            if (!((StringUtil.equals(dto.getTradeMarket(),
//                    DictCodeListExt1.交易场所_上海证券交易所.getValue())
//                    || StringUtil.equals(dto.getTradeMarket(),
//                    DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
//                    && StringUtil.equals(dto.getTradeMarketMode(),
//                    DictCodeListExt1.交易市场_一级市场.getValue()))) {
//                if (StringUtil.isEmpty(dto.getTradePlat())) {
//                    errorList.add(new ValidMessage("tradePlat",
//                            getMessage("FW_E_0006", getMessage("EtrInvestRepoInfo002.tradePlat"))));
//                }
//            }
            // 交易所债一级市场时，交易平台非必填 add by renping 20191105 end
//            //PB上下行关联改造： 当交易平台为“固定收益平台”或“大宗交易平台”时，交易对手增加必填校验 add by caoxue 2019-12-27 start
//            if (StringUtil.isNotEmpty(dto.getTradePlat())) {
//            	if((StringUtil.equals(dto.getTradePlat(), DictCodeList.交易平台_固定收益平台.getValue())
//                        || StringUtil.equals(dto.getTradePlat(), DictCodeList.交易平台_大宗交易平台.getValue()))
//            			&& StringUtil.isEmpty(dto.getEtrInvestPersonInfo().getCounterId())){
//                    errorList.add(new ValidMessage("etrInvestPersonInfo.counterId",
//                            getMessage("FW_E_0006", getMessage("etrInvestPersonInfo.counterId"))));
//            	}
//            }
//            //PB上下行关联改造： 当交易平台为“固定收益平台”或“大宗交易平台”时，交易对手增加必填校验 add by caoxue 2019-12-27 end
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
//                if(finvdate != null && vdate.before(finvdate)){
//                    errorList.add(new ValidMessage("vdate",this.getMessage("ETR_X_006")+DateUtil.format(finvdate)));
//                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+ DateUtil.format(finmdate)));
                }
            }
         // 当价格模式不为市价指令时，才check回购利率
            if (!StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue()) &&
                    dto.getPrice() == null) {
            	errorList.add(new ValidMessage("price", this.getMessage("ETR_X_012",this.getMessage("EtrRepo002.price"))));
            }
         // 可用现金校验
            if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                DictCodeListExt1.是或否_否.getValue())
                && StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_逆回购.getValue())
                && dto.getProAvailableCash() != null && dto.getEntrustAmt() != null
                && dto.getEntrustAmt().compareTo(dto.getProAvailableCash()) == 1) {
                errorList.add(new ValidMessage("entrustAmt",
                    this.getMessage("BUSS_COM_E_0003", this.getMessage("EtrRepo002.entrustAmt"),
                        this.getMessage("EtrRepo002.proAvailableCash"))));
            }
            errorList.addAll(etribRepoInfo003Service.check(dto));
        }
        return errorList;
    }
}
