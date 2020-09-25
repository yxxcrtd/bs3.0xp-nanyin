/*
 * 文件名：BussFinProductFloatFeeValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.component.business.prod.BussFinProductFloatFeeService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFloatFeeDto;
import com.joyintech.tams.constant.DictCodeList;


/**
 * 浮动管理费验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月18日
 * @see BussFinProductFloatFeeValidator
 * @since 1.0
 */
@Component
public class BussFinProductFloatFeeValidator implements BeanValidator {
	  @Autowired
	  private BussFinProductFloatFeeService bussFinProductFloatFeeService;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussFinProductFloatFeeDto bussFinProductFloatFeeDto = (BussFinProductFloatFeeDto) value;
        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        
        if (StringUtil.equals(bussFinProductFloatFeeDto.getPageId(), "FinProductBuild002")){
            if (StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFeeId())){
                if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getBasicBill())){
                    validList.add(new ValidMessage("basicBill", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductFloatFee.basicBill")), bussFinProductFloatFeeDto.getEntityName()));
                }
                if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getBasis())){
                    validList.add(new ValidMessage("basis", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductFloatFee.basis")), bussFinProductFloatFeeDto.getEntityName()));
                }
                if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getNetAssetValueDay())){
                    validList.add(new ValidMessage("netAssetValueDay", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductFloatFee.netAssetValueDay")), bussFinProductFloatFeeDto.getEntityName()));
                }
                if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getProvisionFirstDay())){
                    validList.add(new ValidMessage("provisionFirstDay", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductFloatFee.provisionFirstDay")), bussFinProductFloatFeeDto.getEntityName()));
                }
                if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFloatBaseRule())){
                    validList.add(new ValidMessage("floatBaseRule", getMessage("BUSS_COM_E_0038",
                        getMessage("BussFinProductFloatFee.floatBaseRule")), bussFinProductFloatFeeDto.getEntityName()));
                }
            }
            
            if (!StringUtil.isEmptyList(bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList())){
                for (FinFloatCalcRuleDetail finFloatCalcRuleDetail : bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList()){
                    if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getLev())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductFloatFee.table.floatBaseLevel"))));
                    }
                    if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getBaseRuleId())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductFloatFee.table.baseRate"))));
                    }
                    if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getCoefficient())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductFloatFee.table.coefficient"))));
                    }
                    if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getSpreadRate())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductFloatFee.table.spreadRate"))));
                    }
                    if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getChargeRatio())){
                        validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
                            getMessage("BussFinProductFloatFee.table.chargeRatio"))));
                    }
                }
            }
		} 
        else if (StringUtil.equals(bussFinProductFloatFeeDto.getPageId(), "FinNetValueProductBuild002")) {
			//判断是否为分档产品
			boolean isLay = false;

			if (StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFeeId())) {
			
    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getBasicBill())) {
    				validList.add(new ValidMessage("basicBill",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.basicBill")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getBasis())) {
    				validList.add(new ValidMessage("basis",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.basis")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
//    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getNetAssetValueDay())) {
//    				validList.add(new ValidMessage("netAssetValueDay",
//    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.netAssetValueDay")),
//    						bussFinProductFloatFeeDto.getEntityName()));
//    			}
    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getProvisionFirstDay())) {
    				validList.add(new ValidMessage("provisionFirstDay",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.provisionFirstDay")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
    			/*
    			 * if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFloatBaseRule())){
    			 * validList.add(new ValidMessage("floatBaseRule", getMessage("BUSS_COM_E_0038",
    			 * getMessage("BussFinProductFloatFee.floatBaseRule")),
    			 * bussFinProductFloatFeeDto.getEntityName())); }
    			 */
    			if (isLay && !StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getProdStyle())) {
    				validList.add(new ValidMessage("prdPattern",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.prdPattern")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
//    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getfAccrEndDate())) {
//    				validList.add(new ValidMessage("fAccrEndDate",
//    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.fAccrEndDate")),
//    						bussFinProductFloatFeeDto.getEntityName()));
//    			}
    
    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.geteAccrDate())) {
    				validList.add(new ValidMessage("eAccrDate",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.eAccrDate")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
//    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getAccrTdayRule())) {
//    				validList.add(new ValidMessage("accrTdayRule",
//    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.accrTdayRule")),
//    						bussFinProductFloatFeeDto.getEntityName()));
//    			}
//    
//    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getInterValRule())) {
//    				validList.add(new ValidMessage("interValRule",
//    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.interValRule")),
//    						bussFinProductFloatFeeDto.getEntityName()));
//    			}
//    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getAccrType())) {
//    				validList.add(new ValidMessage("accrType",
//    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.accrType")),
//    						bussFinProductFloatFeeDto.getEntityName()));
//    			}
    
    			if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getPayCycle())) {
    				validList.add(new ValidMessage("payCycle",
    						getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.payCycle")),
    						bussFinProductFloatFeeDto.getEntityName()));
    			}
    			if(!StringUtil.equals(DictCodeList.频率_不规则.getValue(), bussFinProductFloatFeeDto.getPayCycle())){
    			    if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getFirstPayDate())) {
                        validList.add(new ValidMessage("firstPayDate",
                                getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.firstPayDate")),
                                bussFinProductFloatFeeDto.getEntityName()));
                    }
        
                    if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getEndTdayRule())) {
                        validList.add(new ValidMessage("endTdayRule",
                                getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.endTdayRule")),
                                bussFinProductFloatFeeDto.getEntityName()));
                    }
                    if (!StringUtil.isNotEmpty(bussFinProductFloatFeeDto.getPayTdayRule())) {
                        validList.add(new ValidMessage("payTdayRule",
                                getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.payTdayRule")),
                                bussFinProductFloatFeeDto.getEntityName()));
                    }
    			}
    			if (isLay && !StringUtil.isEmptyList(bussFinProductFloatFeeDto.getFinFloatCalcRuleDetailDtoList())) {
    				for (FinFloatCalcRuleDetail finFloatCalcRuleDetail : bussFinProductFloatFeeDto
    						.getFinFloatCalcRuleDetailDtoList()) {
    					if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getLev())) {
    						validList.add(new ValidMessage(getMessage("BUSS_COM_E_0038",
    								getMessage("BussFinProductFloatFee.table.floatBaseLevel"))));
    					}
    					if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getBaseRuleId())) {
    						validList.add(new ValidMessage(
    								getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.table.baseRate"))));
    					}
    					if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getCoefficient())) {
    						validList.add(new ValidMessage(
    								getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.table.coefficient"))));
    					}
    					if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getSpreadRate())) {
    						validList.add(new ValidMessage(
    								getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.table.spreadRate"))));
    					}
    					if (!StringUtil.isNotEmpty(finFloatCalcRuleDetail.getChargeRatio())) {
    						validList.add(new ValidMessage(
    								getMessage("BUSS_COM_E_0038", getMessage("BussFinProductFloatFee.table.chargeRatio"))));
    					}
    				}
    			}
    			
			}
			// 检查列表中是否已存在 费用名称+产品款型的数据
			if (bussFinProductFloatFeeDto.getBussFinProductFloatFeeInfoDtoList() != null
					&& !bussFinProductFloatFeeDto.getBussFinProductFloatFeeInfoDtoList().isEmpty()) {
				List<SelectOptionDto> floatfeeData = bussFinProductFloatFeeService
						.queryFeeInfoList(new HashMap<String, Object>());
				for (BussFinProductFloatFeeDto single : bussFinProductFloatFeeDto
						.getBussFinProductFloatFeeInfoDtoList()) {
					if (single.getFeeId().equals(bussFinProductFloatFeeDto.getFeeId())
							&& StringUtil.isNotEmpty(single.getProdStyle())
							&&single.getProdStyle().equals(bussFinProductFloatFeeDto.getProdStyle())) {
						String msg1 = single.getFeeId();
						String msg2 = single.getProdStyle();
						for (SelectOptionDto feeItem : floatfeeData) {
							if (feeItem.getId().equals(single.getFeeId())) {
								msg1 = feeItem.getText();
							}
						}
						// 获取产品款型的text用于提示
						// msg2 = "";
						validList.add(new ValidMessage("", this.getMessage("MST_FLOATFEEINFO_E_0001", msg1, msg2)));
						// break;
					}
					else if (single.getFeeId().equals(bussFinProductFloatFeeDto.getFeeId())
							&& StringUtil.isNotEmpty(single.getInvestmentCycle())
							&&single.getInvestmentCycle().equals(bussFinProductFloatFeeDto.getInvestmentCycle())) {
						String msg1 = single.getFeeId();
						String msg2 = single.getInvestmentCycle();
						for (SelectOptionDto feeItem : floatfeeData) {
							if (feeItem.getId().equals(single.getFeeId())) {
								msg1 = feeItem.getText();
							}
						}
						// 获取产品款型的text用于提示
						// msg2 = "";
						validList.add(new ValidMessage("", this.getMessage("MST_FLOATFEEINFO_E_0002", msg1, msg2)));
						// break;
					}
				}
			}
        }
        
        return validList;
    }

}
