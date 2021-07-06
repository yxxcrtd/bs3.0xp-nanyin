package com.joyintech.tams.invest.entrust.etribrepo.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.entrust.common.validator.EtrCommonValidator;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoinfo002Dto;
import com.joyintech.tams.invest.entrust.etribrepo.service.EtrIbRepoinfo002Service;

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
public class EtrIbRepoinfo002Validator implements EtrCommonValidator {

    /**
     * 获取service
     */
    @Autowired
    private EtrIbRepoinfo002Service etrIbRepoinfo002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        EtrIbRepoinfo002Dto dto = (EtrIbRepoinfo002Dto)value;
        
        if (StringUtil.equals(dto.getOperateType(), "edit")) {
            dto.setOperateType(DictCodeListExt1.委托操作类型_修改.getValue());
            //checkCounter证券账户是否一致(errorList, dto);
            errorList.addAll(etrIbRepoinfo002Service.check(dto));
        }
        else {
        	if (dto.getMdate() != null && dto.getVdate() != null) {
                if (dto.getMdate().before(dto.getVdate())) {
                    errorList.add(new ValidMessage("vdate", this.getMessage("ETR_X_005")));
                }
            }
        	//模式03 指定质押券库
        	if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.REPO_PLEDGE_MODEL),"03")&&StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())) {
        		if (!StringUtil.isNotEmpty(dto.getZyqk())) {
                    errorList.add(new ValidMessage("zyqk", this.getMessage("请输入质押券库！")));
                }
        	}
        	//模式02  质押券必填
        	if (StringUtil.equals(CacheUtil.getSysParam(BussinessConstant.REPO_PLEDGE_MODEL),"02")&&StringUtil.equals(dto.getPs(), DictCodeList.回购拆借方向_正回购.getValue())) {
        		if (!StringUtil.isNotEmpty(dto.getBussRepoSec())&&!StringUtil.isNotEmpty(dto.getBussRepoSec().getPledgeBondList())) {
                    errorList.add(new ValidMessage("zyqk", this.getMessage("请输入质押券信息！")));
                }
        	}
            if (dto.getEntrustAmt() != null) {
                if (dto.getEntrustAmt().compareTo(BigDecimal.ZERO) == 0
                    || dto.getEntrustAmt().compareTo(BigDecimal.ZERO) == -1) {
                    errorList.add(new ValidMessage("entrustAmt", this.getMessage("ETR_X_008",this.getMessage("EtrRepo002.entrustAmt"))));
                }
            }
            errorList.addAll(etrIbRepoinfo002Service.check(dto));
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
        }
        return errorList;
    }
}
