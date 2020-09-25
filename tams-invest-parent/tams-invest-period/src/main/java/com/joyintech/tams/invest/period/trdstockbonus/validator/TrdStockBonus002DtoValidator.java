/**
 * 
 */
package com.joyintech.tams.invest.period.trdstockbonus.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdstockbonus.dto.TrdStockBonus002Dto;
import com.joyintech.tams.invest.period.trdstockbonus.service.TrdStockBonus001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panbin
 * @version 2019年6月24日
 * @see TrdStockBonus002DtoValidator
 */
@Component
public class TrdStockBonus002DtoValidator implements PeriodCommonValidator{

	@Autowired
	TrdStockBonus001Service trdStockBonus001Service;
	
	
	@Override
	public List<ValidMessage> bussValid(Object value) {
		List<ValidMessage> messageList = new ArrayList<>();
		TrdStockBonus002Dto dto = (TrdStockBonus002Dto) value;
		//撤销check
        if(StringUtil.equals(dto.getOperateType(), BussinessConstant.REVOKE)){
        	messageList.addAll(trdStockBonus001Service.revokeCheck(dto));
        }else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
        	//证券账户、通道2选1
        	/*if(!StringUtil.isNotEmpty(dto.getSecManageAcctId())&& !StringUtil.isNotEmpty(dto.getChlId())){
        		 messageList.add(new ValidMessage("secManageAcctId",getMessage("STOCK_BONUS_0001",getMessage("TrdStockBonus001.secManageAcctId"),getMessage("invest.channelId"))));
        	}*/
        	if(StringUtil.equals(dto.getPs(), "01")){
        		//现金分红，分红金额必填
//        		if(!StringUtil.isNotEmpty(dto.getFpriceAmt())){
//        			messageList.add(new ValidMessage("fpriceAmt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividAmount"))));
//        		}
        		//派息日
        		if(!StringUtil.isNotEmpty(dto.getSettleDate())){
        			messageList.add(new ValidMessage("settleDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividDate"))));
        		}
        		//每股派息
        		if(!StringUtil.isNotEmpty(dto.getUnitInt())){
        			messageList.add(new ValidMessage("unitInt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.perDividends"))));
        		}
        	}
        	else if(StringUtil.equals(dto.getPs(), "02")){
        		//送转，红股上市日、分红股本必填
        		if(!StringUtil.isNotEmpty(dto.getRedStockDate())){
        			messageList.add(new ValidMessage("redStockDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus002.redStockDate"))));
        		}
//        		if(!StringUtil.isNotEmpty(dto.getShareAmt())){
//        			messageList.add(new ValidMessage("shareAmt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividShare"))));
//        		}
        		//每股送转
        		if(!StringUtil.isNotEmpty(dto.getUnitCprice())){
        			messageList.add(new ValidMessage("unitCprice",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.perTransfer"))));
        		}
        	}
        	else{
        		//每股派息
        		if(!StringUtil.isNotEmpty(dto.getUnitInt())){
        			messageList.add(new ValidMessage("unitInt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.perDividends"))));
        		}
        		//分红金额
//        		if(!StringUtil.isNotEmpty(dto.getFpriceAmt())){
//        			messageList.add(new ValidMessage("fpriceAmt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividAmount"))));
//        		}
        		//每股送转
        		if(!StringUtil.isNotEmpty(dto.getUnitCprice())){
        			messageList.add(new ValidMessage("unitCprice",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.perTransfer"))));
        		}
        		//分红股本
//        		if(!StringUtil.isNotEmpty(dto.getShareAmt())){
//        			messageList.add(new ValidMessage("shareAmt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividShare"))));
//        		}
        		//除权除息日
        		if(!StringUtil.isNotEmpty(dto.getTradeDate())){
        			messageList.add(new ValidMessage("tradeDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.exRightsDate"))));
        		}
        		//派息日
        		if(!StringUtil.isNotEmpty(dto.getSettleDate())){
        			messageList.add(new ValidMessage("settleDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus001.dividDate"))));
        		}
        	}
        	//分红明细
			if(StringUtil.isEmptyList(dto.getDividDetailInfoList())){
				messageList.add(new ValidMessage("",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockBonus002.dividInfoList"))));
			}
        }
		return messageList;
	}

}
