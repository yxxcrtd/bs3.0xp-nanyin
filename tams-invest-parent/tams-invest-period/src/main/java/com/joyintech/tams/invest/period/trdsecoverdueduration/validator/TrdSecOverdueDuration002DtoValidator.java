package com.joyintech.tams.invest.period.trdsecoverdueduration.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdsecoverdueduration.dto.TrdSecOverdueDuration002Dto;
import com.joyintech.tams.invest.period.trdsecoverdueduration.service.TrdSecOverdueDuration002Service;

/**
 * @author : wangpeng
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.validator
 * @Description: TODO
 * @date Date : 2019年12月27日 
 */
@Component
public class TrdSecOverdueDuration002DtoValidator implements PeriodCommonValidator {

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;
    
    @Autowired
    TrdSecOverdueDuration002Service trdSecOverdueDuration002Service;

    @Override
    public List<ValidMessage> bussValid(Object value) {
        List<ValidMessage> errorList = new ArrayList<>();
        TrdSecOverdueDuration002Dto dto = (TrdSecOverdueDuration002Dto)value;
        if (!BussinessConstant.REVOKE.equals(dto.getOperateType())) {
            errorList.addAll(bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder()));
//            校验其前置的现金流信息所对应的交易 是否在流程中
			getValidMessage(errorList, dto);
			//空值校验
            if(!StringUtil.isNotEmpty(dto.getIntAmt())||!StringUtil.isNotEmpty(dto.getPrinAmt())||!StringUtil.isNotEmpty(dto.getTradeAmt())){
            	return errorList;
            }
            if(dto.getIntAmt().compareTo(BigDecimal.ZERO)<0){
            	errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_002",this.getMessage("TrdSecOverdueDuration002.intAmt"))));
            }
            if(dto.getPenaltyIntamt().compareTo(BigDecimal.ZERO)<0){
            	errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_002",this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"))));
            }
            if(dto.getTradeAmt().compareTo(BigDecimal.ZERO)<0){
            	errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_002",this.getMessage("TrdSecOverdueDuration002.tradeAmt"))));
            }
            if(dto.getPrinAmt().compareTo(BigDecimal.ZERO)<0){
            	errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_002",this.getMessage("TrdSecOverdueDuration002.prinAmt"))));
            }
            if(StringUtil.isNotEmpty(dto.getChannelFee())&&dto.getChannelFee().compareTo(BigDecimal.ZERO)<0){
            	errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_002",this.getMessage("invest.channelId")+this.getMessage("TrdDepoDuration002.channelFee"))));
            }
            //画面选择已结清  实付本金需等于未支付本金
            if(StringUtil.isNotEmpty(dto.getIsClean())&&StringUtil.equals(dto.getIsClean(),DictCodeList.是否_是.getValue())){
            	if(dto.getPrinAmt().compareTo(dto.getTheoryPrin())!=0){
            		errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_005",this.getMessage("TrdSecOverdueDuration002.prinAmt"))));
            	}
            }
            String message = null;
            //校验 组合信息
            if(!StringUtil.isEmptyList(dto.getFinAssetIntInfoList())){
            	BigDecimal prinAmt = BigDecimal.ZERO;
            	BigDecimal intAmt = BigDecimal.ZERO;
            	BigDecimal penaltyIntamt = BigDecimal.ZERO;
            	BigDecimal tradeAmt = BigDecimal.ZERO;
            	BigDecimal channelFee = BigDecimal.ZERO;
            	for(FinAssetIntInfo item:dto.getFinAssetIntInfoList()){
            		prinAmt = ArithmeticUtil.doAdd(prinAmt,item.getPrinAmt());
            		intAmt = ArithmeticUtil.doAdd(intAmt,item.getIntAmt());
            		penaltyIntamt = ArithmeticUtil.doAdd(penaltyIntamt,item.getPenaltyIntamt());
            		tradeAmt = ArithmeticUtil.doAdd(tradeAmt,item.getTradeAmt());
            		channelFee = ArithmeticUtil.doAdd(channelFee,item.getChannelFee());
            	}
            	if(dto.getIntAmt().compareTo(intAmt)!=0){
            		message =this.getMessage("TRD_SEC_OVER_003",this.getMessage("TrdSecOverdueDuration002.intAmt"),this.getMessage("TrdSecOverdueDuration002.intAmt"));
            		errorList.add(new ValidMessage(message));
            	}
            	if(dto.getPenaltyIntamt().compareTo(penaltyIntamt)!=0){
            		message =this.getMessage("TRD_SEC_OVER_003",this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"));
            		errorList.add(new ValidMessage(message));
            	}
            	if(dto.getTradeAmt().compareTo(tradeAmt)!=0){
            		message =this.getMessage("TRD_SEC_OVER_003",this.getMessage("TrdSecOverdueDuration002.tradeAmt"),this.getMessage("TrdSecOverdueDuration002.tradeAmt"));
            		errorList.add(new ValidMessage(message));
            	}
            	if((StringUtil.isNotEmpty(dto.getChannelFee())&&dto.getChannelFee().compareTo(channelFee)!=0)||(channelFee.compareTo(BigDecimal.ZERO)>0&&!StringUtil.isNotEmpty(dto.getChannelFee()))){
            		message =this.getMessage("TRD_SEC_OVER_003",this.getMessage("invest.channelId")+this.getMessage("TrdDepoDuration002.channelFee"),this.getMessage("invest.channelId")+this.getMessage("TrdDepoDuration002.channelFee"));
            		errorList.add(new ValidMessage(message));
            	}
            	
            }
            //校验子组合信息
            if(!StringUtil.isEmptyList(dto.getChildPortfolioInfo())){
            	for(FinAssetIntInfo item:dto.getFinAssetIntInfoList()){
            		//无子组合信息
            		if(StringUtil.equals(item.getInvestYesorno(), DictCodeList.是否_是.getValue())){
            			continue;
            		}
            		String portfolioIdInvAim = item.getPortfolioId()+item.getInvAim();
            		List<FinAssetIntInfo> childList = new ArrayList<>();
            		for(FinAssetIntInfo childItem:dto.getChildPortfolioInfo()){
            			//此为该母组合下子组合
            			if(StringUtil.equals(childItem.getPortfolioIdInvAim(), portfolioIdInvAim)){
            				childList.add(childItem);
            			}
            		}
            		//页面没打开子组合信息
            		if(StringUtil.isEmptyList(childList)){
            			//理论罚息为空，实收罚息不为空
                    	if(StringUtil.isNotEmpty(dto.getTheoryPenaltyInt())&&dto.getTheoryPenaltyInt().compareTo(BigDecimal.ZERO)==0){
                    		if(StringUtil.isNotEmpty(dto.getPenaltyIntamt())&&dto.getPenaltyIntamt().compareTo(BigDecimal.ZERO)!=0){
                    			message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"));
                        		errorList.add(new ValidMessage(message));
                    		}
                    	}
            			continue;
            		}
            		BigDecimal prinAmt = BigDecimal.ZERO;
                	BigDecimal intAmt = BigDecimal.ZERO;
                	BigDecimal penaltyIntamt = BigDecimal.ZERO;
                	BigDecimal tradeAmt = BigDecimal.ZERO;
                	BigDecimal channelFee = BigDecimal.ZERO;
            		for(FinAssetIntInfo childItem:childList){
                		prinAmt = ArithmeticUtil.doAdd(prinAmt,childItem.getPrinAmt());
                		intAmt = ArithmeticUtil.doAdd(intAmt,childItem.getIntAmt());
                		penaltyIntamt = ArithmeticUtil.doAdd(penaltyIntamt,childItem.getPenaltyIntamt());
                		tradeAmt = ArithmeticUtil.doAdd(tradeAmt,childItem.getTradeAmt());
                		channelFee = ArithmeticUtil.doAdd(channelFee,childItem.getChannelFee());
                	}
                	if(item.getIntAmt().compareTo(intAmt)!=0){
                		message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("TrdSecOverdueDuration002.intAmt"),this.getMessage("TrdSecOverdueDuration002.intAmt"));
                		errorList.add(new ValidMessage(message));
                	}
                	if(item.getPenaltyIntamt().compareTo(penaltyIntamt)!=0){
                		message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"));
                		errorList.add(new ValidMessage(message));
                	}
                	if(item.getTradeAmt().compareTo(tradeAmt)!=0){
                		message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("TrdSecOverdueDuration002.tradeAmt"),this.getMessage("TrdSecOverdueDuration002.tradeAmt"));
                		errorList.add(new ValidMessage(message));
                	}
                	if((StringUtil.isNotEmpty(item.getChannelFee())&&item.getChannelFee().compareTo(channelFee)!=0)||(channelFee.compareTo(BigDecimal.ZERO)>0&&!StringUtil.isNotEmpty(item.getChannelFee()))){
                		message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("invest.channelId")+this.getMessage("TrdDepoDuration002.channelFee"),this.getMessage("invest.channelId")+this.getMessage("TrdDepoDuration002.channelFee"));
                		errorList.add(new ValidMessage(message));
                	}
            	}
            }else{
            	//页面没打开子组合信息
            	//理论罚息为空，实收罚息不为空
            	if(StringUtil.isNotEmpty(dto.getTheoryPenaltyInt())&&dto.getTheoryPenaltyInt().compareTo(BigDecimal.ZERO)==0){
            		if(StringUtil.isNotEmpty(dto.getPenaltyIntamt())&&dto.getPenaltyIntamt().compareTo(BigDecimal.ZERO)!=0){
            			for(FinAssetIntInfo item:dto.getFinAssetIntInfoList()){
                    		//无子组合信息
                    		if(StringUtil.equals(item.getInvestYesorno(), DictCodeList.是否_是.getValue())){
                    			continue;
                    		}
                    		message =this.getMessage("TRD_SEC_OVER_004",item.getPortfolioId(),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"),this.getMessage("TrdSecOverdueDuration002.penaltyIntamt"));
                    		errorList.add(new ValidMessage(message));
            			}
            		}
            	}
            }
        }
        else {
           /* periodCommonService.periodCommonRevokeCheck(dto.getStatusValid(), dto.getBusiCode(), errorList);
            if (errorList.isEmpty()) {
                Map<String, Object> params = new HashMap<>();
                params.put("portfolioId",  dto.getPortfolioId());
                params.put("bizType", DictCodeListExt1.金融产品类型_投管用_债券);
                params.put("finprodId", dto.getFinprodId());
                params.put("secManageAcctId", !dto.isChannel() ? dto.getSecManageAcctId() : null);
                params.put("mDate", dto.getmDate());
                params.put("chlId", dto.isChannel()?dto.getSecManageAcctId():null);
                if (!periodCommonService.checkIfMaxDate(params)) {
                    errorList.add(new ValidMessage(getMessage("PERIOD_E_0001")));
                }
            }*/
        }
        return errorList;
    }

	private List<ValidMessage> getValidMessage(List<ValidMessage> errorList, TrdSecOverdueDuration002Dto dto) {
		WflValidInputParameter parameter = new WflValidInputParameter();
		parameter.setPageId("TrdSecOverdueDuration002");
		parameter.setBusinessDataPk2(dto.getFinprodId());
		parameter.setBusinessDataPk3(dto.getSecAcctId());
		parameter.setBusinessDataPk4(dto.getBranchStr());
		boolean isInWfl = mstWorkflowCommonService.isInWflProcess(parameter);
		if (isInWfl){
			if(StringUtil.isNotEmpty(dto.getWorkflowId())){
				TrdSecOverdueDuration002Dto workFlow = trdSecOverdueDuration002Service.getBussinessEntityByWorkflowId(dto.getWorkflowId());
				if(!StringUtil.equals(dto.getBusiCode(), workFlow.getBusiCode())){
					errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_001")));
				}
			}else{
				errorList.add(new ValidMessage(this.getMessage("TRD_SEC_OVER_001")));
			}
		}
		return errorList;
	}

}
