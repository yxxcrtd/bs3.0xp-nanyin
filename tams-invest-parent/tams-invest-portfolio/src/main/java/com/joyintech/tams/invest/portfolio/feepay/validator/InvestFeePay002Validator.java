/*
 * 文件名：InvestFeePay002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.portfolio.feepay.service.InvestFeePay002Service;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay001Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay003Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 投资端费用支付新建
 * 
 * @author malinlin
 * @version 1.0 2019年12月9日
 * @see InvestFeePay002Validator
 * @since 1.0
 */
@Component
public class InvestFeePay002Validator implements BeanValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired(required = false)
    private InvestFeePay002Service investFeePay002Service;
    
    
    @Autowired
    private IPlatformDate iPlatformDate;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        InvestFeePay002Dto investFeePay002Dto = (InvestFeePay002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId("InvestFeePay002");
        List<InvestFeePay002Dto> businessData = investFeePay002Service.getBusinessData(parameter);
        if (businessData != null && businessData.size() > 0 && investFeePay002Dto != null){
            for (InvestFeePay002Dto businessDatum : businessData) {
                if (businessDatum.getAllData() != null
                        && StringUtil.isNotEmpty(businessDatum.getAllData())
                        && !StringUtil.equals(businessDatum.getAllData(), "")){
                    try {
                        Map<String, Object> dtos = JsonUtils.stringToMap(businessDatum.getAllData());
                        if (null != dtos && !dtos.isEmpty()){
                            //当登记托管机构，费用类型，支付日，证券账户都与流程中数据一致时，返回提示信息
                            if (StringUtil.equals(dtos.get("feeType"),investFeePay002Dto.getFeeType())
                            && StringUtil.equals(dtos.get("regTrustOrg"),investFeePay002Dto.getRegTrustOrg())
                           // && StringUtil.equals(String.valueOf(dtos.get("payDate")),String.valueOf(investFeePay002Dto.getPayDate().getTime()))
                            && StringUtil.equals(String.valueOf(dtos.get("startDate")),String.valueOf(investFeePay002Dto.getStartDate().getTime()))
                                    && StringUtil.equals(dtos.get("secAcctId"),investFeePay002Dto.getSecAcctId())){
                                validList.add(new ValidMessage(getMessage("FinProductFeePay002.workflowMsg")));
                                return validList;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        //add by  yujiaping 20210304 ZGBSS-702 当登记托管机构，费用类型，计费开始日，证券账户都与有效数据一致时不允许提交 start
          Map<String, Object> map = new HashMap<>();        
           map.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());               
           map.put("noFlag", DictCodeList.是否_否.getValue());       
           map.put("TG_BUSI_TYPE", "142");
          List<InvestFeePay001Dto> effectData = investFeePay002Service.getEffectData(map);
        if (effectData != null && effectData.size() > 0 && investFeePay002Dto != null){
            for (InvestFeePay001Dto effectDatum : effectData) {  
            	if(effectDatum.getRegTrustOrg() == null) {
            		effectDatum.setRegTrustOrg("");
            	}
            	if(effectDatum.getSecAcctId() == null) {
            		effectDatum.setSecAcctId("");
            	}
                            //当登记托管机构，费用类型，计费开始日，证券账户都与有效数据一致时，返回提示信息
                            if (StringUtil.equals(effectDatum.getFeeType(),investFeePay002Dto.getFeeType())
                            		&& StringUtil.equals(effectDatum.getRegTrustOrg(),investFeePay002Dto.getRegTrustOrg())
                           // && StringUtil.equals(String.valueOf(dtos.get("payDate")),String.valueOf(investFeePay002Dto.getPayDate().getTime()))
                            && StringUtil.equals(String.valueOf(effectDatum.getPositionStartDate()),String.valueOf(investFeePay002Dto.getStartDate()))
                                    && StringUtil.equals(effectDatum.getSecAcctId(),investFeePay002Dto.getSecAcctId())){
                                validList.add(new ValidMessage(getMessage("已存在有效数据，请勿重复提交")));
                                return validList;
                            }
                        }                                 
            }
        //add by  yujiaping 20210304 ZGBSS-702 当登记托管机构，费用类型，计费开始日，证券账户都与有效数据一致时不允许提交 end
        // 划款指令check
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                investFeePay002Dto.getBussTrdTransferOrder());
            validList.addAll(list);
            if (DictCodeList.费用品种类型_销售手续费.getValue().equals(investFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_托管费.getValue().equals(investFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_固定管理费.getValue().equals(investFeePay002Dto.getFeeType())
            ) {
                for (BussTrdTransferOrderDto dataDto : investFeePay002Dto.getBussTrdTransferOrder()) {
                    // 收款账号
                    if (StringUtil.isBlank(dataDto.getRecAcctNo())) {
                        validList.add(new ValidMessage("bussTrdTransferOrder", getMessage("FW_E_0006",
                                getMessage("BussTrdTransferOrder.recAcctNo"))));
                    }
                }
            }
        }


        
        if(StringUtil.equals(investFeePay002Dto.getChargeType(),
                DictCodeList.收费类型_不规则费用.getValue())) {
            if (StringUtil.isNotEmpty(investFeePay002Dto.getPayDate())) {
                if (DateUtil.compareDate(investFeePay002Dto.getPayDate(),
                		DateUtil.platDateFromCache()) < 0) {
                    validList.add(new ValidMessage("endDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductFeePay002.payDate"),
                            "平台日期")));
                }
            }
        	if (StringUtil.equals(investFeePay002Dto.getFeeType(),
                    DictCodeList.费用品种类型_结算服务费.getValue())
                    || StringUtil.equals(investFeePay002Dto.getFeeType(),
                        DictCodeListExt1.一次性费用类型_账户维护费.getValue())) {
            	// 一体化对应etliu end
                if (StringUtil.isEmpty(investFeePay002Dto.getRegTrustOrg())) {
                    validList.add(
                        new ValidMessage("regTrustOrg", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.regTrustOrg"))));
                }
                
            	if (!StringUtil.equals(investFeePay002Dto.getFeeType(),
                        DictCodeList.费用品种类型_结算服务费.getValue())) {
                	// 一体化对应etliu end
                    if (!StringUtil.isNotEmpty(investFeePay002Dto.getPayAmt())) {
                        validList.add(new ValidMessage("payAmt", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.payAmt"))));
                    }
                    if (!StringUtil.isNotEmpty(investFeePay002Dto.getAdjustAmt())) {
                        validList.add(new ValidMessage("adjustAmt", this.getMessage(
                            "BUSS_COM_E_0038", this.getMessage("FinProductFeePay002.adjustAmt"))));
                    }
                }
            }
            if (!StringUtil.isNotEmpty(investFeePay002Dto.getActPayAmt())) {
                validList.add(new ValidMessage("actPayAmt", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("FinProductFeePay002.actPayAmt"))));
            }
            return validList;
            
        } else if (StringUtil.equals(investFeePay002Dto.getFeeType(),
                DictCodeList.费用品种类型_浮动管理费.getValue())) {
                //TODO 获取产品到期日字段
        		
        }else {
            // 付费明细列表不能为空
            if (StringUtil.isEmptyList(investFeePay002Dto.getFeeInfoList())) {
                validList.add(
                    new ValidMessage("feeInfoList", this.getMessage("FIN_PROD_E_0068")));
            }else{
            	for (InvestFeePay003Dto Dto : investFeePay002Dto.getFeeInfoList()) {
            		if(StringUtil.isNotEmpty(investFeePay002Dto.getPayDate())){
                    	if(DateUtil.compareDate(investFeePay002Dto.getPayDate(), Dto.getCalcEndDate()) < 0){
                    		validList.add(new ValidMessage("endDate",
                                    this.getMessage("BUSS_COM_E_0004",
                                        this.getMessage("FinProductFeePay002.payDate"),
                                        this.getMessage("FinProductFeePay002.feeTable.calcEndDate"))));
                    	}
                    }
            		if(StringUtil.isEmpty(Dto.getIsClean())){
           			 	validList.add(new ValidMessage("请填写费用信息列表中是否结清字段！"));
            		}
				}
            	
            	
            }
        }
        
        if (StringUtil.isNotEmpty(investFeePay002Dto.getPayDate())) {
                if (DateUtil.compareDate(investFeePay002Dto.getPayDate(),
                		iPlatformDate.getSysDate()) < 0) {
                    validList.add(new ValidMessage("endDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductFeePay002.payDate"),
                            "平台日期")));
                }
            }
        
        
        if (StringUtil.isNotEmpty(investFeePay002Dto.getStartDate())
            && StringUtil.isNotEmpty(investFeePay002Dto.getEndDate())) {
            if (DateUtil.compareDate(investFeePay002Dto.getStartDate(),
                investFeePay002Dto.getEndDate()) >= 0) {
                validList.add(new ValidMessage("endDate",
                    this.getMessage("BUSS_COM_E_0036",
                        this.getMessage("FinProductFeePay002.positionEndDate"),
                        this.getMessage("FinProductFeePay002.positionStartDate"))));
            }
        }

        if (StringUtil.isNotEmpty(investFeePay002Dto.getEndDate())
            && StringUtil.isNotEmpty(investFeePay002Dto.getPayDate())) {
            if (DateUtil.compareDate(investFeePay002Dto.getPayDate(),
                investFeePay002Dto.getEndDate()) < 0) {
                validList.add(new ValidMessage("endDate",
                    this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductFeePay002.payDate"),
                        this.getMessage("FinProductFeePay002.positionEndDate"))));
            }
        }
        
        if (!StringUtil.isEmptyList(investFeePay002Dto.getBussTrdTransferOrder())){
            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : investFeePay002Dto.getBussTrdTransferOrder()){
                if (StringUtil.isNotEmpty(bussTrdTransferOrderDto.getPurpose())
                    && bussTrdTransferOrderDto.getPurpose().length() > 300){
                    validList.add(new ValidMessage("bussTrdTransferOrder",
                        this.getMessage("FW_E_0002",
                            this.getMessage("BussTrdTransferOrder.purpose"))));
                }
            }
        }
        
        if (StringUtil.isNotEmpty(investFeePay002Dto.getRemark())
            && investFeePay002Dto.getRemark().length() > 300){
            validList.add(new ValidMessage("remark",
                this.getMessage("FW_E_0002",
                    this.getMessage("FinProductFeePay002.tradeRemark"))));
        }

        return validList;
    }

}
