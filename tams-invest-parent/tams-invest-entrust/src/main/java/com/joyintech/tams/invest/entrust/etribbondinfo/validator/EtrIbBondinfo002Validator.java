/*
 * 文件名：EtrIbBondinfo002Validator.java
 */

package com.joyintech.tams.invest.entrust.etribbondinfo.validator;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.EtrIbBondinfo002Dto;
import com.joyintech.tams.invest.entrust.etribbondinfo.service.EtrIbBondinfo002Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * 债券投资委托校验 〈功能详细描述〉
 *
 * @since 1.0
 */
@Component
public class EtrIbBondinfo002Validator implements EtrCommonValidator {

    /**
     * 获取service
     */
    @Autowired
    private EtrIbBondinfo002Service etrIbBondinfo002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrIbBondinfo002Dto dto = (EtrIbBondinfo002Dto)value;
        
        if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            //checkCounter证券账户是否一致(errorList, dto);
            errorList.addAll(etrIbBondinfo002Service.check(dto));
        }
        else {

            //checkCounter证券账户是否一致(errorList, dto);
            //PB上下行关联改造： 当交易平台为“固定收益平台”或“大宗交易平台”时，交易对手增加必填校验
            Date vdate = dto.getVdate();
            Date mdate = dto.getMdate();
            Date finmdate = dto.getFinmdate();
            Date finvdate = dto.getFinvdate();
            if (vdate != null && mdate != null) {
                if (vdate.after(mdate)) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
                if(finmdate != null && mdate.after(finmdate)){
                    errorList.add(new ValidMessage("mdate",this.getMessage("ETR_X_007")+DateUtil.format(finmdate)));
                }
            }
            String ps = dto.getPs();
            if (!etrIbBondinfo002Service.isRight(dto.getFinprodId())
            		&& (StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), dto.getPs())
            				|| StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), dto.getPs()))) {
                ValidMessage validMessage = new ValidMessage("非行权债不可做行权交易！");
                    errorList.add(validMessage);
            }
            if(dto.getEntrustNum() != null) {
                BigDecimal entrustNum = dto.getEntrustNum();
                //券面总额除以100等于数量,前台单位是万元，2021-01-29
                entrustNum = entrustNum.multiply(new BigDecimal(100));
                if (entrustNum != null && StringUtil.isNotEmpty(ps)) {
                    BigDecimal proAvailableCash = dto.getProAvailableCash();
                    BigDecimal assetAvailablePosition = dto.getAssetAvailablePosition();
                    BigDecimal zero = new BigDecimal(0);
                    if (StringUtil.equals(
                            CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_POS_SWITCH),
                            DictCodeListExt1.是或否_否.getValue())
                            && (StringUtil.equals(ps, DictCodeList.交易方向_卖出.getValue())
                            || StringUtil.equals(ps, DictCodeList.交易方向_行权_赎回.getValue())
                            || StringUtil.equals(ps, DictCodeList.交易方向_行权_回售.getValue()))
                            && assetAvailablePosition != null) {
                        if (entrustNum.compareTo(assetAvailablePosition) > 0) {
                            errorList.add(
                                    new ValidMessage("entrustNum", this.getMessage("ETR_BOND_002")));
                        }
                    }
                    // 前台单位万元
                    else if (StringUtil.equals(
                            CacheUtil.getSysParam(BussinessConstant.ETR_CHECK_CASH_SWITCH),
                            DictCodeListExt1.是或否_否.getValue())
                            && StringUtil.equals(ps, DictCodeList.交易方向_买入.getValue())
                            && proAvailableCash != null) {
                        if (dto.getEntrustNum().multiply(new BigDecimal(10000)).compareTo(proAvailableCash) == 1) {
                            errorList.add(
                                    new ValidMessage("entrustNum", this.getMessage("ETR_BOND_003")));
                        }
                    }
                    if (entrustNum.compareTo(zero) != 1) {
                        errorList.add(new ValidMessage("entrustNum", this.getMessage("ETR_BOND_004")));
                    }
                }
            }
            if ((StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
            		|| (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())
            				|| StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())))
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
            //指定价格模式下净价不能为空
            if (StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue())) {
                if (!StringUtil.isNotEmpty(dto.getPrice())) {
                    errorList.add(new ValidMessage(this.getMessage("ETR_X_020",
                            getMessage("EtrExchangeTrade001.priceModel"),
                            DictCodeListExt2.价格模式_指定价格.getCodeName(),
                            getMessage("EtrExchangeTrade001.price"))));
                }
            }
            //指定价格模式下净价不能为空
            errorList.addAll(etrIbBondinfo002Service.check(dto));
        }
        return errorList;
    }

/*    private void checkCounter证券账户是否一致(List<ValidMessage> errorList, EtrIbBondinfo002Dto dto) {
        if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
            if (!StringUtil.isNotEmpty(dto.getPortfolioId())) {
                errorList.add(new ValidMessage("finprodId", this.getMessage("ETR_BOND_001")));
            }

            String counterId = dto.getCounterId();
            if (StringUtil.isNotEmpty(dto.getSecAcctId()) && StringUtil.isNotEmpty(counterId)) {
                PtlPortfolio portfolio = etrIbBondinfo002Service.findPortfolio(counterId);
                if (portfolio != null) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("portfolioId", counterId);
                    param.put("search", dto.getSecAcctId());
                    if (StringUtil.isEmptyList(etrIbBondinfo002Service.getSecManageAcctId(param))) {
                        ValidMessage validMessage = new ValidMessage("etrInvestPersonInfo.counterId", "卖出交易的内部交易对手证券托管户不存在:" + dto.getSecAcctId());
                        errorList.add(validMessage);
                    }
                }
            }
        }
    }*/
}
