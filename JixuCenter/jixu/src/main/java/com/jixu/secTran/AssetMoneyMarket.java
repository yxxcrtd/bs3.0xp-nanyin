package com.jixu.secTran;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgFields;
import com.jixu.job.service.AssetJobService;
import com.jixu.mvc.control.model.MarketStockDetailModel;

/** 
 * @ClassName 		AssetStock
 * @author  		Pan Wei 
 * @Date 			2017年5月16日 上午9:20:27 	
 * @Description		[]  
 */

/**
 * 普通股
 * @author pan wei
 *
 */

@Service("assetMoneyMarket")
public class AssetMoneyMarket extends AssetSecurity{
	
	@Resource(name="assetJobMoneyMarket")
	private AssetJobService jobService;
	
	@Override
	public String assetType() {
		return AssetTypes.MONEY_MARKET_FUND;
	}
	
	@Override
	public String assetDesc() {
		return "货币基金";
	}
	
	@Override
	public AssetJobService createJobService() {
		return jobService;
	}
	
	@Override
	public String assetFormURL() {
		return "/forms/trade/assetForms/money_market_fund";
	}
	
	@Override
	public String assetCreateSMFMsg(String action, String[] smfFields) {
		String secid=smfFields[0];
		String secName=smfFields[1];
		String smfType=smfFields[2];
		String issuerCode=smfFields[3].split("\\|")[0];
		String countryCode=smfFields[4];
		String exchangeCode=smfFields[5];
		String priCurCode=smfFields[6];
		String incomeCurCode=smfFields[7];
		String coaGroupCode=smfFields[8];
		String assetType=smfFields[9];
		String sourceCode=smfFields[12];
		String cashFlowCfgID=smfFields[13];
		
		MarketStockDetailModel mm=new MarketStockDetailModel();
		mm.setSecID(secid);
		mm.setSecName(secName);
		mm.setSecType(smfType);
		mm.setIssuerCode(issuerCode);
		mm.setCountryID(countryCode);
		mm.setExchangeID(exchangeCode);
		mm.setPrinCurID(priCurCode);
		mm.setIncomeCurID(incomeCurCode);
		mm.setCoaNum(Integer.valueOf(coaGroupCode));
		mm.setAssetType3(Integer.valueOf(assetType));
		mm.setSourceID(Integer.valueOf(sourceCode));
		mm.setCashFlowCfgID(cashFlowCfgID);
		String msg=genSMFMsg("ADD",mm);
		
		return msg;
	}
	
	@Override
	public String newAssetFormURL(String operation) {
		if(operation.equalsIgnoreCase(MsgFields.update))
			return "smf_money_fund_edit";
		else
			return "smf_money_fund_new";
	}
}
