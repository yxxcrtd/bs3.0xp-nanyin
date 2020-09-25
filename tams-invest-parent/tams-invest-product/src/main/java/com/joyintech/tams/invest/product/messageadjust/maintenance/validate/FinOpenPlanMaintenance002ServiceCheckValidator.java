package com.joyintech.tams.invest.product.messageadjust.maintenance.validate; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenance002Dto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenanceDto;

/**
 * 产品报价管理Validator
 * 
 * @author wangkang
 * @version 1.0 2018年10月11日
 * @see FinOpenPlanMaintenance002Validator
 * @since 1.0
 */
@Component
public class FinOpenPlanMaintenance002ServiceCheckValidator implements BeanValidator {

	@Autowired
    private IPlatformDate iPlatformDate;
	
	@Override
	public List<ValidMessage> valid(Object o) {
		FinOpenPlanMaintenance002Dto dto = (FinOpenPlanMaintenance002Dto) o;
		ArrayList<ValidMessage> errorList = new ArrayList<>();

		if(!StringUtil.equals(DictCodeList.开放模式_高频开放.getValue(), dto.getBussFinProductOpenPlanDto().getOpenMode())) {
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getOpenMode())) { // 开放模式
				errorList.add(new ValidMessage("openMode",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.openMode"))));
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getOpenDaysType())) { // 开放天数类型
				errorList.add(new ValidMessage("openDaysType",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.openDaysType"))));
			}
			if(!"02".equals(dto.getBussFinProductOpenPlanDto().getOpenDaysType())) { // 开放天数类型为“每天”，不需要校验开放天数不为空
				if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getOpenDays())) { // 开放天数
					errorList.add(new ValidMessage("openDays",
							getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.openDays"))));
				}
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getOpenCycle())) { // 开放周期
				errorList.add(new ValidMessage("openCycle",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.openCycle"))));
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getCalcRule())) { // 推算规则
				errorList.add(new ValidMessage("calcRule",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.calcRule"))));
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getCpriceRule())) { // 净值日准则
				errorList.add(new ValidMessage("cpriceRule",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.cpriceRule"))));
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getTradedayRule())) { // 营业日准则
				errorList.add(new ValidMessage("cpriceRule",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.tradedayRule"))));
			}
			if (!StringUtil.isNotEmpty(dto.getBussFinProductOpenPlanDto().getFinOpenPlanList()) || dto.getBussFinProductOpenPlanDto().getFinOpenPlanList().size()<=0) { // 开放计划列表
				errorList.add(new ValidMessage("cpriceRule",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.finOpenPlanList")))); // 开放计划列表不可为空
			}
			
			if(dto.getNextOpenDay() != null && dto.getNextOpenDay().compareTo(iPlatformDate.getSysDate()) < 0){
				errorList.add(new ValidMessage(this.getMessage("下一开放日大于等于平台日",
	                getMessage("下一开放日") + DateUtil.format(dto.getNextOpenDay()),
	                getMessage("平台日") + DateUtil.format(iPlatformDate.getSysDate()))));
	        }
			if (!StringUtil.isNotEmpty(dto.getNextOpenDay())) { // 下一开放日
				errorList.add(new ValidMessage("nextOpenDay",
						getMessage("FIN_PROD_E_0225", getMessage("FinOpenPlanMaintenance002.nextOpenDay"))));
			}
		}
		
		List<FinOpenPlan> finOpenPlanList = dto.getBussFinProductOpenPlanDto().getFinOpenPlanList();
		for(int i = 0; i< finOpenPlanList.size(); i++ ){
			FinOpenPlan entity = finOpenPlanList.get(i);
            //开放周期check
            for(int j = 0; j < finOpenPlanList.size(); j++ ){
                if(i == j){
                    continue;
                }
                FinOpenPlan entity2 = finOpenPlanList.get(j);
                if(DateUtil.compareDate(entity.getOpenDateStr(), entity2.getOpenDateStr()) >=0 &&  DateUtil.compareDate(entity.getOpenDateStr(), entity2.getOpenDateEnd()) <= 0){
                	errorList.add(new ValidMessage(this.getMessage("计划列表中第["+ (i+1) + "]行开放开始日被包含在第["+ (j+1) +"]行开放计划周期内!" )));
                }
                if(DateUtil.compareDate(entity.getOpenDateEnd(), entity2.getOpenDateStr()) >=0 &&  DateUtil.compareDate(entity.getOpenDateEnd(), entity2.getOpenDateEnd()) <= 0){
                	errorList.add(new ValidMessage(this.getMessage("计划列表中第["+ (i+1) + "]行开放结束日被包含在第["+ (j+1) +"]行开放计划周期内!" )));
                }
            }
		}

		return errorList;
	}
}
