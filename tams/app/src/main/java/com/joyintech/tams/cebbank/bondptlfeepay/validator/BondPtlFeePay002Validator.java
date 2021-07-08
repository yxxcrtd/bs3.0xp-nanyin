
package com.joyintech.tams.cebbank.bondptlfeepay.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.cebbank.bondptlfeepay.dto.BondPtlFeePay002Dto;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 证券组合费支付新建
 * 
 * @author hechuan
 * @version 1.0 2021年4月21日
 * @see BondPtlFeePay002Validator
 * @since 1.0
 */
@Component
public class BondPtlFeePay002Validator implements BeanValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
     
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
    	BondPtlFeePay002Dto bondPtlFeePay002Dto = (BondPtlFeePay002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        // 划款指令check
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
            		bondPtlFeePay002Dto.getBussTrdTransferOrder());
            validList.addAll(list);
            if (DictCodeList.费用品种类型_销售手续费.getValue().equals(bondPtlFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_托管费.getValue().equals(bondPtlFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_固定管理费.getValue().equals(bondPtlFeePay002Dto.getFeeType())
            ) {
                for (BussTrdTransferOrderDto dataDto : bondPtlFeePay002Dto.getBussTrdTransferOrder()) {
                    // 收款账号
                    if (StringUtil.isBlank(dataDto.getRecAcctNo())) {
                        validList.add(new ValidMessage("bussTrdTransferOrder", getMessage("FW_E_0006",
                                getMessage("BussTrdTransferOrder.recAcctNo"))));
                    }
                }
            }
        }
        
        if(StringUtil.equals(bondPtlFeePay002Dto.getChargeType(),
                DictCodeList.收费类型_不规则费用.getValue())) {
            if (StringUtil.isNotEmpty(bondPtlFeePay002Dto.getPayDate())) {
                if (DateUtil.compareDate(bondPtlFeePay002Dto.getPayDate(),
                		DateUtil.platDateFromCache()) < 0) {
                    validList.add(new ValidMessage("endDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductFeePay002.payDate"),
                            "平台日期")));
                }
            }
        	if (StringUtil.equals(bondPtlFeePay002Dto.getFeeType(),
                    DictCodeList.费用品种类型_结算服务费.getValue())
                    || StringUtil.equals(bondPtlFeePay002Dto.getFeeType(),
                        DictCodeListExt1.一次性费用类型_账户维护费.getValue())) {
            	// 一体化对应etliu end
                if (StringUtil.isEmpty(bondPtlFeePay002Dto.getRegTrustOrg())) {
                    validList.add(
                        new ValidMessage("regTrustOrg", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.regTrustOrg"))));
                }
                
            	if (!StringUtil.equals(bondPtlFeePay002Dto.getFeeType(),
                        DictCodeList.费用品种类型_结算服务费.getValue())) {
                	// 一体化对应etliu end
                    if (!StringUtil.isNotEmpty(bondPtlFeePay002Dto.getPayAmt())) {
                        validList.add(new ValidMessage("payAmt", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.payAmt"))));
                    }
                    if (!StringUtil.isNotEmpty(bondPtlFeePay002Dto.getAdjustAmt())) {
                        validList.add(new ValidMessage("adjustAmt", this.getMessage(
                            "BUSS_COM_E_0038", this.getMessage("FinProductFeePay002.adjustAmt"))));
                    }
                }
            }
            if (!StringUtil.isNotEmpty(bondPtlFeePay002Dto.getActPayAmt())) {
                validList.add(new ValidMessage("actPayAmt", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("FinProductFeePay002.actPayAmt"))));
            }
            return validList;
            
        } else if (StringUtil.equals(bondPtlFeePay002Dto.getFeeType(),
                DictCodeList.费用品种类型_浮动管理费.getValue())) {
        		
        }else {
            // 付费明细列表不能为空
            if (StringUtil.isEmptyList(bondPtlFeePay002Dto.getFeeInfoList())) {
                validList.add(
                    new ValidMessage("feeInfoList", this.getMessage("FIN_PROD_E_0068")));
            }
        }
        
        if (!StringUtil.isEmptyList(bondPtlFeePay002Dto.getBussTrdTransferOrder())){
            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : bondPtlFeePay002Dto.getBussTrdTransferOrder()){
                if (StringUtil.isNotEmpty(bussTrdTransferOrderDto.getPurpose())
                    && bussTrdTransferOrderDto.getPurpose().length() > 300){
                    validList.add(new ValidMessage("bussTrdTransferOrder",
                        this.getMessage("FW_E_0002",
                            this.getMessage("BussTrdTransferOrder.purpose"))));
                }
            }
        }
        
        if (StringUtil.isNotEmpty(bondPtlFeePay002Dto.getRemark())
            && bondPtlFeePay002Dto.getRemark().length() > 300){
            validList.add(new ValidMessage("remark",
                this.getMessage("FW_E_0002",
                    this.getMessage("FinProductFeePay002.tradeRemark"))));
        }

        return validList;
    }

}
