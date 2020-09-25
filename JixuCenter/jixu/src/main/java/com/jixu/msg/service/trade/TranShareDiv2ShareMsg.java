package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleSubScription
 * @author  		Pan Wei 
 * @Date 			2016年8月19日 下午12:20:54 	
 * @Description		[Operation,Tanum,TranType,TradeDate,SettleDate,PORTFOLIO(),SecAcct(),BNK(),NAV,Shares,Cash,TRANFEE(),SETTLECASH()]  
 */

@Service
public class TranShareDiv2ShareMsg extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return "tranShareDiv2ShareMsg";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);

		Object tanumStr=fieldsHM.get(MsgFields.tanum);
		
		msgcontainer=checkTanum(operation, tanumStr, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tanum=msgcontainer.getEntityID();
		TranBasic oldTran=(TranBasic)msgcontainer.getEntityObj();
		
		if(operation.equalsIgnoreCase(delete)){
			return;
		}
		
		int tranType=TransTypes.Share_Div2Share;
		
		String tradedateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradedate=checkDateFormat(msgcontainer,msgHeader(),tradedateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		Date settleDate=tradedate;
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String secacctid=portfolio.getDefaultSecAcct();
		SecAcct secAcct=getSecAcct(portnum, secacctid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int cashacct=secAcct.getDefaultCashAcct();
		BkCashAcct bkAcct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double units=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		
		String fxcurrency=bkAcct.getFxcurrency();
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(tranType);
		tran.setTradedate(tradedate);
		tran.setSettledate(settleDate);
		tran.setPortfolio(portfolio.getId());
		tran.setAmount(units);
		tran.setPrincipal(units);
		tran.setCash(units);
		tran.setBkCashacctNo(bkAcct);
		tran.setFxcurrency(fxcurrency);
		
		double fxrate=0;
		if(fxcurrency.equals(portfolio.getCurID())){
			fxrate=1;
		}else{
			fxrate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), fxcurrency, tradedate);
		}
				
		tran.setFxrate(fxrate);
		tran.setSettleCurrency(fxcurrency);
		tran.setSecacct(secacctid);
		tran.setSettleFxRate(fxrate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setLedgerdate(getLedgerDate(tradedate, settleDate, portfolio));
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				tran.setId(tanum);
			}
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(tran);
				tranBasicFeeDao.saveEntity(fee);
			}
			
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(tran, oldTran, "id");
			
			tranBasicDao.updateEntity(oldTran);
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(oldTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		msgcontainer.setPortid(portfolio.getId());
	}

	@Override
	public String messageForm() {
		return "/forms/trade/tradeForms/share_div2share_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "红利再投";
	}
}

