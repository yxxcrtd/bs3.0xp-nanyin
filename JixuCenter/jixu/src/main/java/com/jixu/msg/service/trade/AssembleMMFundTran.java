package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				StockTran(Operation,TaNum,TranType,SecID(),TradeDate,SetDate,TradeMarket,Broker(),BNK(),MANAGER(),PORTFOLIO(),UNITES,PRINCIPAL,ACCRUED,TRANFEE(),CASH,FXRate,INTATMAT,SETTLECASH())  
 */

@Service("TranMMFundMsg")
public class AssembleMMFundTran extends MsgExecuteService{
	@Override
	public String msgHeader() {
		return MsgTemplate.TranMMFund_Header;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
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
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));
		
		String[] secids=getFieldValue(fieldsHM, MsgFields.secid).split("\\|");
		String secid="";
		if(secids.length==3){
			secid=secids[1];
		}else{
			secid=secids[0];
		}
		IssueInfo sec=getSecurity(secid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		int tradeMarket=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeMarket));
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String secacctid=getFieldValue(fieldsHM, MsgFields.secAcct);
		SecAcct secAcct=getSecAcct(portnum, secacctid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct bkAcct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double units=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		double principal=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		
		double price=0;
		if(fieldsHM.containsKey(MsgFields.price)){
			price=Double.valueOf(getFieldValue(fieldsHM, MsgFields.price));
		}else if(units!=0){
			price=principal/units;
		}
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeFxCurID=getFieldValue(fieldsHM, MsgFields.fxCur);
		getCurrency(tradeFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double cash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
		String fxRate=getFieldValue(fieldsHM, MsgFields.tradeFX);
		double fxrate=0;
		if(fxRate==null){
			if(tradeFxCurID.equals(portfolio.getCurID())){
				fxrate=1;
			}else{
				fxrate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(),tradeFxCurID, tradeDate);
			}
		}else{
			fxrate=Double.valueOf(fxRate);
		}
		
		
		String settleFxCurID=getFieldValue(fieldsHM, MsgFields.settleFxCur);
		getCurrency(settleFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double settleCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.settleCash));
		
		String settleFxRateStr=getFieldValue(fieldsHM, MsgFields.settleFxRate);
		double settleFxRate=0;
		if(settleFxRateStr==null){
			if(settleFxCurID.equals(portfolio.getCurID())){
				settleFxRate=1;
			}else{
				settleFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), settleFxCurID, tradeDate);
			}
		}else{
			settleFxRate=Double.valueOf(settleFxRateStr);
		}
	
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBroker(brokerid);
		tran.setBkCashacctNo(bkAcct);
		tran.setManager(managers.getId());
		tran.setPortfolio(portfolio.getId());
		tran.setAmount(units);
		tran.setPrice(price);
		tran.setPrincipal(principal);
		tran.setSecacct(secacctid);
		tran.setCash(cash);
		tran.setIntatmat(0);
		tran.setFxrate(fxrate);
		tran.setFxcurrency(sec.getPriCur());
		tran.setSettleCash(settleCash);
		tran.setSettleCurrency(settleFxCurID);
		tran.setSettleFxRate(settleFxRate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTradeMarket(tradeMarket);
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
			
			tranBasicDao.updateEntity(oldTran);
			
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		msgcontainer.setPortid(portfolio.getId());
	}

	@Override
	public String messageForm() {
		return "/forms/trade/tradeForms/mmfund_all_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "货币基金操作";
	}
}
