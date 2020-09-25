package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgError;
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
 * @ClassName TradeOperation
 * @author Pan Wei
 * @Date 2017年6月27日 下午9:56:22
 * @Description []
 */

@Service("MsgTradeOperation")
public class TradeOperation extends MsgExecuteService {

	private String header = "tradeOperation";

	@Override
	public String msgHeader() {
		return header;
	}

	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		//String actionType=getFieldValue(fieldsHM, MsgFields.operatoin);
		String operation=add;
		
		Object tanumStr=fieldsHM.get(MsgFields.tanum);
		
		msgcontainer=checkTanum(operation, tanumStr, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tanum=msgcontainer.getEntityID();
		TranBasic oldTran=(TranBasic)msgcontainer.getEntityObj();
		
		if(operation.equals(delete)){
			return;
		}
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));

		String assetType=getFieldValue(fieldsHM, MsgFields.assetType);
		if(assetType!=null){
			if(!SecurityCoreFactory.getAssetTypeHM().get(assetType).containsKey(tranType)){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error13,header,assetType+":"+tranType));	
			}
		}
		
		String secidStr=getFieldValue(fieldsHM, MsgFields.secid);
		IssueInfo sec=null;
		if(secidStr!=null){
			String[] secids=getFieldValue(fieldsHM, MsgFields.secid).split("\\|");
			String secid="";
			if(secids.length==3){
				secid=secids[1];
			}else{
				secid=secids[0];
			}
			sec=getSecurity(secid, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
			
		String tradedateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,header,tradedateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,header,settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String tradeMarketStr=getFieldValue(fieldsHM, MsgFields.tradeMarket);
		int tradeMarket=0;
		if(tradeMarketStr!=null){
			tradeMarket=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeMarket));
		}
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=null;
		if(brokerid!=null){
			broker=getBroker(brokerid, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
		//目前该参数用在现金冲销和现金期初上
		boolean receiveFlag=true;
		String dbcrStr=getFieldValue(fieldsHM, MsgFields.offsetType);
		if(dbcrStr!=null){
			if(Integer.valueOf(dbcrStr)==IncomeExpenseType.expense){
				receiveFlag=false;
			}
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
		
		String amountStr=getFieldValue(fieldsHM, MsgFields.shares);
		double amount=0;
		if(amountStr!=null){
			amount=Double.valueOf(amountStr);
		}
		
		String priceStr=getFieldValue(fieldsHM, MsgFields.price);
		double price=0;
		if(priceStr!=null){
			price=Double.valueOf(priceStr);
		}
		
		String principalStr=getFieldValue(fieldsHM, MsgFields.principal);
		double principal=0;
		if(principalStr!=null){
			principal=Double.valueOf(principalStr);
		}
		
		String accruedStr=getFieldValue(fieldsHM, MsgFields.intatmat);
		double accrued=0;
		if(accruedStr!=null){
			accrued=Double.valueOf(accruedStr);
		}
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double cash=0;
		if(getFieldValue(fieldsHM, MsgFields.tradeCash)!=null){
			cash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		}
		
		
		String tradeFxCurID=getFieldValue(fieldsHM, MsgFields.fxCur);
		getCurrency(tradeFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String fxRate=getFieldValue(fieldsHM, MsgFields.tradeFX);
		double fxrate=0;
		if(fxRate==null){
			if(tradeFxCurID.equals(portfolio.getCurID())){
				fxrate=1;
			}else{
				fxrate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tradeFxCurID, tradeDate);
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
	
		if(cash==0){
			cash=principal+accrued;
			cash=commService.calTranCashWithFee(tranType, cash, feeList);
		}
		
		String glacct=getFieldValue(fieldsHM, MsgFields.glacct);
		if(glacct!=null){
			String[] glaccts=glacct.split("\\|");
			if(glaccts.length==3){
				glacct=glaccts[1];
			}
		}
		
		String tranDesc=getFieldValue(fieldsHM, MsgFields.tranDesc);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setBroker(brokerid);
		tran.setManager(managers.getId());
		tran.setPortfolio(portfolio.getId());
		tran.setPrice(price);
		tran.setIntatmat(accrued);
		if(receiveFlag){
			tran.setSecacct(secacctid);
			tran.setBkCashacctNo(bkAcct);
			tran.setAmount(amount);
			tran.setPrincipal(RoundUtil.roundMoney2Dec(principal));
			tran.setCash(RoundUtil.roundMoney2Dec(cash));
			tran.setFxrate(fxrate);
			tran.setFxcurrency(tradeFxCurID);
		}else{
			tran.setSecacct2(secacctid);
			tran.setBkCashacctNo2(bkAcct);
			tran.setAmount2(amount);
			tran.setPrincipal2(RoundUtil.roundMoney2Dec(principal));
			tran.setCash2(RoundUtil.roundMoney2Dec(cash));
			tran.setFxrate2(fxrate);
			tran.setFxcurrency2(tradeFxCurID);
		}
		tran.setSettleCash(RoundUtil.roundMoney2Dec(settleCash));
		tran.setSettleCurrency(settleFxCurID);
		tran.setSettleFxRate(settleFxRate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTradeMarket(tradeMarket);
		tran.setGlacct(glacct);
		tran.setTranDesc(tranDesc);
		
		if(!receiveFlag){
			
		}
		
		if(operation.equals(add)){
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
		}else if(operation.equals(update)){
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return null;
	}
}
