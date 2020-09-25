/*
 * 文件名：EtrInvestBondInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrinvestbondinfo.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.ArithmeticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto.EtrInvestBondInfoDto;
import com.joyintech.tams.invest.entrust.etrinvestbondinfo.service.EtrInvestBondInfoService;

/**
 * 债券投资委托校验 〈功能详细描述〉
 *
 * @author xiexinting
 * @version 1.0 2018年9月18日
 * @see EtrInvestBondInfoValidator
 * @since 1.0
 */
@Component
public class EtrInvestBondInfoValidator implements EtrCommonValidator {

    /**
     * 获取service
     */
    @Autowired
    private EtrInvestBondInfoService etrInvestBondInfoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrInvestBondInfoDto dto = (EtrInvestBondInfoDto)value;
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            List<EtrInvestBondInfoDto> listDto = dto.getSelectedItem();
            for (EtrInvestBondInfoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_删除.getValue());
                errorList.addAll(etrInvestBondInfoService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "close")) {
            List<EtrInvestBondInfoDto> listDto = dto.getSelectedItem();
            for (EtrInvestBondInfoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_关闭.getValue());
                errorList.addAll(etrInvestBondInfoService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "recall")) {
            List<EtrInvestBondInfoDto> listDto = dto.getSelectedItem();
            for (EtrInvestBondInfoDto entity : listDto) {
                entity.setOperateType(DictCodeListExt1.委托操作类型_撤回.getValue());
                errorList.addAll(etrInvestBondInfoService.check(entity));
            }
        }
        else if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
                && !StringUtil.isNotEmpty(dto.getPortfolioId())) {
                errorList.add(new ValidMessage("finprodId", this.getMessage("ETR_BOND_001")));
            }
            errorList.addAll(etrInvestBondInfoService.check(dto));
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
            if (!((StringUtil.equals(dto.getTradeMarket(),
                DictCodeListExt1.交易场所_上海证券交易所.getValue())
                   || StringUtil.equals(dto.getTradeMarket(),
                       DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                  && StringUtil.equals(dto.getTradeMarketMode(),
                      DictCodeListExt1.交易市场_一级市场.getValue()))) {
                if (StringUtil.isEmpty(dto.getTradePlat())) {
                    errorList.add(new ValidMessage("tradePlat",
                        getMessage("FW_E_0006", getMessage("EtrInvestBondInfo002.tradePlat"))));
                }
            }
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
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+DateUtil.format(finmdate)));
                }
            }
            BigDecimal entrustNum = dto.getEntrustNum();
//            券面总额除以100等于数量
            entrustNum = ArithmeticUtil.divide(entrustNum,100,2);
            String ps = dto.getPs();
            if (!etrInvestBondInfoService.isRight(dto.getFinprodId())
            		&& (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), dto.getPs())
            				|| StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), dto.getPs()))) {
                ValidMessage validMessage = new ValidMessage("非行权债不可做行权交易！");
                    errorList.add(validMessage);
            }
            if (entrustNum != null && StringUtil.isNotEmpty(ps)) {
                BigDecimal proAvailableCash = dto.getProAvailableCash();
                BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                BigDecimal zero = new BigDecimal(0);
                if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())
                    && (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())
                        || StringUtil.equals(ps, DictCodeList.交易方向_行权_赎回.getValue())// huwenbin添加行权赎回与行权回售不能大于持仓校验
                        || StringUtil.equals(ps, DictCodeList.交易方向_行权_回售.getValue()))
                    && assetAvailablePosition != null) {
                    if (entrustNum.compareTo(assetAvailablePosition) > 0) {
                        errorList.add(
                            new ValidMessage("entrustNum", this.getMessage("ETR_BOND_002")));
                    }
                }
                else if (StringUtil.equals(
                    CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                    DictCodeListExt1.是或否_否.getValue())
                         && StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())
                         && proAvailableCash != null) {
                    if (dto.getEntrustNum().compareTo(proAvailableCash) == 1) {
                        errorList.add(
                            new ValidMessage("entrustNum", this.getMessage("ETR_BOND_003")));
                    }
                }
                if(entrustNum.compareTo(zero) != 1){
                    errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_BOND_004")));
                }
                // delete by renping 改为共通处理 2019-08-22 start
//                String etrlimit_switch = CacheUtil.getSysParam(BussinessConstant.ETRLIMIT_SWITCH);
//                if(StringUtil.equals(etrlimit_switch, "01")){
//                    BigDecimal limitAmt = etrInvestBondInfoService.selectLimitAmt(dto);
//                    if(limitAmt != null && entrustNum.compareTo(limitAmt)==1){
//                        errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_BOND_005")+limitAmt));
//                    }
//                }
                // delete by renping 改为共通处理 2019-08-22 end
            }
            if ((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
            		// 一体化对应etliu start
//                 || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权.getValue()))
            		|| (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())
            				|| StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())))
            		// 一体化对应etliu end
                && !StringUtil.isNotEmpty(dto.getFinprodMarketId())) {
                errorList.add(new ValidMessage("finprodId", this.getMessage("ETR_BOND_001")));
            }
            if (!StringUtil.isNotEmpty(dto.getFinprodId())) {
                // 当价格模式不为市价指令时，才check净价和收益率
            	if (StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue()) ||
                		StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue())) {
	                if (!StringUtil.isNotEmpty(dto.getPrice())
	                    && !StringUtil.isNotEmpty(dto.getExerYield())
	                    && !StringUtil.isNotEmpty(dto.getMatureYield())) {
	                    errorList.add(new ValidMessage(this.getMessage("ETR_BOND_006")));
	                }
            	}
            }
            errorList.addAll(etrInvestBondInfoService.check(dto));
        }
        return errorList;
    }
}
