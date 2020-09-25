package com.jixu.secTran;

import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgTemplate;
import com.jixu.mvc.control.model.MarketStockDetailModel;

/** 
 * @ClassName 		AssetSecurity
 * @author  		Pan Wei 
 * @Date 			2017年6月14日 上午12:04:15 	
 * @Description		[]  
 */

public abstract class AssetSecurity implements SecurityAssetService{
	protected String genSMFMsg(String action,MarketStockDetailModel stockDetail){
		//SMFSTOCK(Add,IssuerCode,SecID(),SecName,SmfType,StockType(),PriCur(),IncomeCur(),Country(),Exchange(),COAGROUP(),Source())
		StringBuilder msg=new StringBuilder();
		if(stockDetail.getSecType().equals(AssetTypes.COMMON_STOCK)){
			msg.append(MsgTemplate.SMFStock_Header);
		}else if(stockDetail.getSecType().equals(AssetTypes.TERM_REPO)){
			msg.append(MsgTemplate.SmfBond_Header);
		}else if (stockDetail.getSecType().equals(AssetTypes.MONEY_MARKET_FUND)){
			msg.append(MsgTemplate.SMFMMFund_Header);
		}else if(stockDetail.getSecType().equals(AssetTypes.TimeDeposit)){
			msg.append(MsgTemplate.TimeDeposit_Header);
		}else{
			return null;
		}
		msg.append("(");
		msg.append(action);
		msg.append(",");
		msg.append(stockDetail.getCusip());
		msg.append(",");
		msg.append(stockDetail.getIssuerCode());
		msg.append(",");
		msg.append(MsgTemplate.SECID_Header);
		msg.append("(");
		msg.append(stockDetail.getSecID());
		msg.append("),");
		msg.append(stockDetail.getSecName());
		msg.append(",");
		msg.append(stockDetail.getSecType());
		msg.append(",");
		msg.append(MsgTemplate.StockType_Header);
		msg.append("(");
		msg.append(stockDetail.getAssetType3());
		msg.append("),");
		msg.append(MsgTemplate.PRICUR_Header);
		msg.append("(");
		msg.append(stockDetail.getPrinCurID());
		msg.append("),");
		msg.append(MsgTemplate.INCOMECUR_Header);
		msg.append("(");
		msg.append(stockDetail.getIncomeCurID());
		msg.append("),");
		msg.append(MsgTemplate.Country_Header);
		msg.append("(");
		msg.append(stockDetail.getCountryID());
		msg.append("),");
		
		if(stockDetail.getExchangeID()!=null && !stockDetail.getExchangeID().equals("")){
			msg.append(MsgTemplate.EXCHANGE_Header);
			msg.append("(");
			msg.append(stockDetail.getExchangeID());
			msg.append("),");
		}
		
		msg.append(MsgTemplate.COAGROUP_Header);
		msg.append("(");
		msg.append(stockDetail.getCoaNum());
		msg.append("),");
		msg.append(MsgTemplate.SOURCE_Header);
		msg.append("(");
		msg.append(stockDetail.getSourceID());
		msg.append(")");
		
		if(!stockDetail.getCashFlowCfgID().equals("0")){
			msg.append(",");
			msg.append(MsgTemplate.CashFlowCfgID_Header);
			msg.append("(");
			msg.append(stockDetail.getCashFlowCfgID());
			msg.append(")");
		}
		msg.append(")");
		
		return msg.toString();
	}
}
