
package com.joyintech.tams.cebbank.bondptlfeepay.validator;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.cebbank.bondptlfeepay.dto.BondPtlFeePay001Dto;
import com.joyintech.tams.cebbank.bondptlfeepay.service.BondPtlFeePay001Service;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 证券组合费支付
 * 
 * @author hechuan
 * @version 1.0 2021年4月21日
 * @see BondPtlFeePay001Validator
 * @since 1.0
 */
@Component
public class BondPtlFeePay001Validator implements BeanValidator {

    /**
     * {@linkplain}
     */
    @Autowired
    private BondPtlFeePay001Service bondPtlFeePay001Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	BondPtlFeePay001Dto bondPtlFeePay001Dto = (BondPtlFeePay001Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        if (StringUtil.equals(bondPtlFeePay001Dto.getOperateType(), "confirm")) {
            // 数据状态必须为待处理
            if (!StringUtil.equals(bondPtlFeePay001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_待处理.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_待处理.getCodeName())));
            }
            else{
                // 查询是否有小于当前日期的待处理的计划
                if (bondPtlFeePay001Service.checkUndoPlanExists(bondPtlFeePay001Dto.getCashId(),
                		bondPtlFeePay001Dto.getCashNum()) > 0) {
                    validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0063")));
                }
            }
        }
        else if (StringUtil.equals(bondPtlFeePay001Dto.getOperateType(), "revoke")) {
        	if (DateUtil.compareDate(bondPtlFeePay001Dto.getPayDate(), DateUtil.platDateFromCache()) < 0) {
        		validList.add(new ValidMessage("不能撤销平台日大于支付日的费用交易！"));
        	}
            // 数据状态必须为有效
            if (!StringUtil.equals(bondPtlFeePay001Dto.getEffectStatusStr(),
                DictCodeListExt1.有效状态_有效.getCodeName())) {
                validList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032",
                    DictCodeListExt1.有效状态_有效.getCodeName())));
            }

            // 存在流程中数据
            if (WorkFlowUtil.isNotFromToDoList()) {
                if (bondPtlFeePay001Service.isInFlowDataExisted(
                		bondPtlFeePay001Dto.getTradeId())) {
                    validList.add(new ValidMessage(
                        getMessage("COMMON_E_0012", bondPtlFeePay001Dto.getTradeId())));
                }
            }

            // 删除状态不允许撤回
            if (bondPtlFeePay001Service.isRevokeDataExisted(bondPtlFeePay001Dto.getTradeId())) {
                validList.add(new ValidMessage(getMessage("TRD_W_0001",
                    getMessage("pingan.common.revoke"), getMessage("pingan.common.revoke"))));
            }
        }

        return validList;
    }

}
