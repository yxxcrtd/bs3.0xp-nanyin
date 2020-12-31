/*
 * 文件名：FinProductFeePay002Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay001Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay002Dto;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay003Dto;
import com.joyintech.tams.invest.product.feepay.service.FinProductFeePay001Service;

/**
 * 产品费用支付新建
 * 
 * @author zhangliang
 * @version 1.0 2018年11月13日
 * @see FinProductFeePay002Validator
 * @since 1.0
 */
@Component
public class FinProductFeePay002Validator implements BeanValidator {

    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    @Autowired(required = false)
    private FinProductFeePay001Service finProductFeePay001Service;
    
    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    @Autowired
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        FinProductFeePay002Dto finProductFeePay002Dto = (FinProductFeePay002Dto)value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();

        //BS-783【产品费用支付】：净值周期型产品，期次子产品可支付固定费用同一费用多次
        // 增加校验 同一产品  同一费用。如果有流程中数据不允许提交
        WflValidInputParameter inputParameter = new WflValidInputParameter();
        inputParameter.setBusinessDataPk1(finProductFeePay002Dto.getPtlId());
        inputParameter.setBusinessDataPk2(finProductFeePay002Dto.getFeeId());
        inputParameter.setPageId("FinProductFeePay002");
        if (null != mstWorkflowCommonService &&
                mstWorkflowCommonService.isInWflProcess(inputParameter)) {
            validList.add(new ValidMessage(this.getMessage("COMMON_E_0012", finProductFeePay002Dto.getFinprodId()+":"+finProductFeePay002Dto.getFeeName())));
        }

        if(bussCommonMapperDelegateService != null && StringUtil.isNotEmpty(finProductFeePay002Dto.getFinprodId())) {
            FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finProductFeePay002Dto.getFinprodId(), FinProduct.class);
            if(finProduct != null && finProduct.getVdate() != null && (DateUtil.compareDate(finProductFeePay002Dto.getPayDate(), finProduct.getVdate())<0)){
                validList.add(new ValidMessage("payDate",getMessage("BUSS_COM_E_0004",
                        getMessage("FinProductFeePay002.payDate"),"产品起息日")));
            }
        }

        // 划款指令check
        if (bussTrdTransferOrderService != null) {
            List<ValidMessage> list = bussTrdTransferOrderService.check(
                finProductFeePay002Dto.getBussTrdTransferOrder());
            validList.addAll(list);
            if (DictCodeList.费用品种类型_销售手续费.getValue().equals(finProductFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_托管费.getValue().equals(finProductFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_固定管理费.getValue().equals(finProductFeePay002Dto.getFeeType())
            ) {
                for (BussTrdTransferOrderDto dataDto : finProductFeePay002Dto.getBussTrdTransferOrder()) {
                    // 收款账号
                    if (StringUtil.equals(dataDto.getIsNeedSend(), DictCodeList.是否_是.getValue())
                        && StringUtil.isBlank(dataDto.getRecAcctNo())) {
                        validList.add(new ValidMessage("bussTrdTransferOrder", getMessage("FW_E_0006",
                                getMessage("BussTrdTransferOrder.recAcctNo"))));
                    }
                }
            }
        }
        
        if(StringUtil.equals(finProductFeePay002Dto.getChargeType(),
                DictCodeList.收费类型_不规则费用.getValue())) {
            if (StringUtil.isNotEmpty(finProductFeePay002Dto.getPayDate())) {
                if (DateUtil.compareDate(finProductFeePay002Dto.getPayDate(),
                		DateUtil.platDateFromCache()) < 0) {
                    validList.add(new ValidMessage("endDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductFeePay002.payDate"),
                            "平台日期")));
                }
            }
            
            if(StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                DictCodeList.费用品种类型_结算服务费.getValue()) 
                || StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                    DictCodeList.费用品种类型_交易手续费.getValue())) {
                if(!StringUtil.isNotEmpty(finProductFeePay002Dto.getStartDate())) {
                    validList.add(
                        new ValidMessage("startDate", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.positionStartDate")))); 
                } 
                
                if(!StringUtil.isNotEmpty(finProductFeePay002Dto.getEndDate())) {
                    validList.add(
                        new ValidMessage("endDate", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.positionEndDate"))));
                } 
            }

            
        	if (StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                    DictCodeList.费用品种类型_结算服务费.getValue())
                    || StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                    		DictCodeList.费用品种类型_账户维护费.getValue())) {
            	// 一体化对应etliu end
                if (StringUtil.isEmpty(finProductFeePay002Dto.getRegTrustOrg())) {
                    validList.add(
                        new ValidMessage("regTrustOrg", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.regTrustOrg"))));
                }
                
            	/*if (!StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                        DictCodeList.费用品种类型_结算服务费.getValue())) {
                	// 一体化对应etliu end
                    if (!StringUtil.isNotEmpty(finProductFeePay002Dto.getPayAmt())) {
                        validList.add(new ValidMessage("payAmt", this.getMessage("BUSS_COM_E_0038",
                            this.getMessage("FinProductFeePay002.payAmt"))));
                    }
                    if (!StringUtil.isNotEmpty(finProductFeePay002Dto.getAdjustAmt())) {
                        validList.add(new ValidMessage("adjustAmt", this.getMessage(
                            "BUSS_COM_E_0038", this.getMessage("FinProductFeePay002.adjustAmt"))));
                    }
                }*/
            }
            if (!StringUtil.isNotEmpty(finProductFeePay002Dto.getActPayAmt())) {
                validList.add(new ValidMessage("actPayAmt", this.getMessage("BUSS_COM_E_0038",
                    this.getMessage("FinProductFeePay002.actPayAmt"))));
            }
            return validList;
            
        } else if (StringUtil.equals(finProductFeePay002Dto.getFeeType(),
            DictCodeList.费用品种类型_增值附加税费.getValue())) {
            // 增值税明细列表不能为空
            if (StringUtil.isEmptyList(finProductFeePay002Dto.getTaxPositionList())) {
                validList.add(
                    new ValidMessage("taxPositionList", this.getMessage("FIN_PROD_E_0064")));
            }
        } else if (StringUtil.equals(finProductFeePay002Dto.getFeeType(),
                DictCodeList.费用品种类型_浮动管理费.getValue())) {

            // 付费明细列表不能为空
            if (StringUtil.isEmptyList(finProductFeePay002Dto.getFeeInfoList())) {
                validList.add(
                        new ValidMessage("feeInfoList", this.getMessage("FIN_PROD_E_0068")));
            }else{
                for (FinProductFeePay003Dto Dto : finProductFeePay002Dto.getFeeInfoList()) {
                    if(StringUtil.isNotEmpty(finProductFeePay002Dto.getPayDate())){
                        if(DateUtil.compareDate(finProductFeePay002Dto.getPayDate(), Dto.getCalcEndDate()) < 0){
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
        		
        }else {
            // 付费明细列表不能为空
            if (StringUtil.isEmptyList(finProductFeePay002Dto.getFeeInfoList())) {
                validList.add(
                    new ValidMessage("feeInfoList", this.getMessage("FIN_PROD_E_0068")));
            }else{
            	for (FinProductFeePay003Dto Dto : finProductFeePay002Dto.getFeeInfoList()) {
            		if(StringUtil.isNotEmpty(finProductFeePay002Dto.getPayDate())){
                    	if(DateUtil.compareDate(finProductFeePay002Dto.getPayDate(), Dto.getCalcEndDate()) < 0){
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
        
        if (StringUtil.isNotEmpty(finProductFeePay002Dto.getPayDate())) {
                if (DateUtil.compareDate(finProductFeePay002Dto.getPayDate(),
                		iPlatformDate.getSysDate()) < 0) {
                    validList.add(new ValidMessage("endDate",
                        this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("FinProductFeePay002.payDate"),
                            "平台日期")));
                }
            }
        
        
        if (StringUtil.isNotEmpty(finProductFeePay002Dto.getStartDate())
            && StringUtil.isNotEmpty(finProductFeePay002Dto.getEndDate())) {
            if (DateUtil.compareDate(finProductFeePay002Dto.getStartDate(),
                finProductFeePay002Dto.getEndDate()) >= 0) {
                validList.add(new ValidMessage("endDate",
                    this.getMessage("BUSS_COM_E_0036",
                        this.getMessage("FinProductFeePay002.positionEndDate"),
                        this.getMessage("FinProductFeePay002.positionStartDate"))));
            }
        }

        if (StringUtil.isNotEmpty(finProductFeePay002Dto.getEndDate())
            && StringUtil.isNotEmpty(finProductFeePay002Dto.getPayDate())) {
            if (DateUtil.compareDate(finProductFeePay002Dto.getPayDate(),
                finProductFeePay002Dto.getEndDate()) < 0) {
                validList.add(new ValidMessage("endDate",
                    this.getMessage("BUSS_COM_E_0004",
                        this.getMessage("FinProductFeePay002.payDate"),
                        this.getMessage("FinProductFeePay002.positionEndDate"))));
            }
        }
        
        if (!StringUtil.isEmptyList(finProductFeePay002Dto.getBussTrdTransferOrder())){
            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : finProductFeePay002Dto.getBussTrdTransferOrder()){
                if (StringUtil.isNotEmpty(bussTrdTransferOrderDto.getPurpose())
                    && bussTrdTransferOrderDto.getPurpose().length() > 300){
                    validList.add(new ValidMessage("bussTrdTransferOrder",
                        this.getMessage("FW_E_0002",
                            this.getMessage("BussTrdTransferOrder.purpose"))));
                }
            }
        }
        
        if (StringUtil.isNotEmpty(finProductFeePay002Dto.getRemark())
            && finProductFeePay002Dto.getRemark().length() > 300){
            validList.add(new ValidMessage("remark",
                this.getMessage("FW_E_0002",
                    this.getMessage("FinProductFeePay002.tradeRemark"))));
        }
        
        if (DictCodeList.费用品种类型_销售手续费.getValue().equals(finProductFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_托管费.getValue().equals(finProductFeePay002Dto.getFeeType())
            || DictCodeList.费用品种类型_固定管理费.getValue().equals(finProductFeePay002Dto.getFeeType())
        ) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ptlId", finProductFeePay002Dto.getPtlId());
            param.put("feeId", finProductFeePay002Dto.getFeeId());
            param.put("payDateStart", DateUtil.format(finProductFeePay002Dto.getPayDate(), DateUtil.DEFAULT_DATE_FORMAT_EN));    
            param.put("payDateEnd", DateUtil.format(finProductFeePay002Dto.getPayDate(), DateUtil.DEFAULT_DATE_FORMAT_EN)); 
            Page<FinProductFeePay001Dto> page = finProductFeePay001Service.selectForPage(param);
            if (StringUtil.isNotEmpty(page) &&  page.getRoot().size()>0) {
                validList.add(new ValidMessage("同一产品、同一支付日、不可支付同一费用多次！"));
            }
        }
        return validList;
    }

}
