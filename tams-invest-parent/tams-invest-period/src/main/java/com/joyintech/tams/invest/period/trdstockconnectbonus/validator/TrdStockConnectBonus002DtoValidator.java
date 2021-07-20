
package com.joyintech.tams.invest.period.trdstockconnectbonus.validator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.validator.PeriodCommonValidator;
import com.joyintech.tams.invest.period.trdstockconnectbonus.dto.TrdStockConnectBonus002Dto;
import com.joyintech.tams.invest.period.trdstockconnectbonus.service.TrdStockConnectBonus001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @see TrdStockConnectBonus002DtoValidator
 */
@Component
public class TrdStockConnectBonus002DtoValidator implements PeriodCommonValidator{

	@Autowired
	TrdStockConnectBonus001Service trdStockBonus001Service;
	
	
	@Override
	public List<ValidMessage> bussValid(Object value) {
		List<ValidMessage> messageList = new ArrayList<>();
		TrdStockConnectBonus002Dto dto = (TrdStockConnectBonus002Dto) value;
		//撤销check
        if(StringUtil.equals(dto.getOperateType(), BussinessConstant.REVOKE)){
        	messageList.addAll(trdStockBonus001Service.revokeCheck(dto));
        }
        //提交
        else if(StringUtil.equals("save",dto.getOperateType())||StringUtil.equals("submit",dto.getOperateType())){
			//分红明细
			if(StringUtil.isEmptyList(dto.getFinAssetIntInfoList())){
				messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus002.dividInfoList"))));
			}
			//待处理数据必须带入通知文件编号
			if(StringUtil.equals(dto.getMode(),"edit") && !StringUtil.isNotEmpty(dto.getbTradeId())){
				messageList.add(new ValidMessage("bTradeId",getMessage("待处理数据原交易编号不能为空")));
			}

        	//现金分红
        	if(StringUtil.equals(dto.getPs(), DictCodeList.股票分红类型_现金分红.getValue())){
        		//每股派息
        		if(!StringUtil.isNotEmpty(dto.getUnitInt())){
        			messageList.add(new ValidMessage("unitInt",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus001.unitInt"))));
        		}
				//是否含股利选择权
				if(!StringUtil.isNotEmpty(dto.getIfDividend())){
					messageList.add(new ValidMessage("ifDividend",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus001.ifDividend"))));
				}

				//汇率
				if(!StringUtil.isNotEmpty(dto.getExchangeRate())){
					messageList.add(new ValidMessage("exchangeRate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus002.exchangeRate"))));
				}

                //含股利选择权
				if(StringUtil.equals(dto.getIfDividend(),DictCodeList.是否_是.getValue())){
					//红股上市日
					if(!StringUtil.isNotEmpty(dto.getRedStockDate())){
						messageList.add(new ValidMessage("redStockDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus002.redStockDate"))));
					}

					//明细
					for(FinAssetIntInfo finDto : dto.getFinAssetIntInfoList()){
						if(finDto.getRegDateAmt()==null || finDto.getReplaceAmt()==null || finDto.getFpriceAmt()==null || finDto.getShareAmt() == null){
							messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("组合"+finDto.getPortfolioId()+":明细信息不正确")));
						}
					}
				}

                //不含股利选择权
				else if(StringUtil.equals(dto.getIfDividend(), DictCodeList.是否_否.getValue())){
					//明细
					for(FinAssetIntInfo finDto : dto.getFinAssetIntInfoList()){
						if(finDto.getRegDateAmt()==null || finDto.getReplaceAmt() !=null || finDto.getFpriceAmt()==null || finDto.getShareAmt() != null){
							messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("组合"+finDto.getPortfolioId()+":明细信息不正确")));
						}
					}
				}
        	}

        	//送转
        	else if(StringUtil.equals(dto.getPs(), DictCodeList.股票分红类型_送转.getValue())){
        		//红股上市日
        		if(!StringUtil.isNotEmpty(dto.getRedStockDate())){
        			messageList.add(new ValidMessage("redStockDate",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus002.redStockDate"))));
        		}
        		//每股送转
        		if(!StringUtil.isNotEmpty(dto.getUnitCprice())){
        			messageList.add(new ValidMessage("unitCprice",getMessage("TrdProjectBuyOffInterest001.error.notEmpty",getMessage("TrdStockConnectBonus001.unitCprice"))));
        		}
				//明细
				for(FinAssetIntInfo finDto : dto.getFinAssetIntInfoList()){
					if(finDto.getRegDateAmt()==null || finDto.getReplaceAmt() !=null || finDto.getFpriceAmt()!=null || finDto.getShareAmt() == null){
						messageList.add(new ValidMessage("finAssetIntInfoList",getMessage("组合"+finDto.getPortfolioId()+":明细信息不正确")));
					}
				}
        	}

        }
		return messageList;
	}

}
