package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CashFlowCfg;
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
 * @Msg				
 */

@Service("AssembleTranTimeDepositBuy")
public class AssembleTranTimeDepositBuy extends MsgExecuteService{
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return "TranTimDepositeBuy";
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
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));
		
		String assetType=getFieldValue(fieldsHM, MsgFields.assetType);
		if(!SecurityCoreFactory.getAssetTypeHM().get(assetType).containsKey(tranType)){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error13,msgHeader(),assetType+":"+tranType));	
		}
		
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
		
		
		/*String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}*/
		
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
		if(cashacct==0){
			cashacct=secAcct.getDefaultCashAcct();
		}
		BkCashAcct bkAcct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeFxCurID=getFieldValue(fieldsHM, MsgFields.fxCur);
		getCurrency(tradeFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String contract=getFieldValue(fieldsHM, MsgFields.contract);
		double amount=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		
		String divdateStr=getFieldValue(fieldsHM, MsgFields.maturityDate).replaceAll("-", "");
		Date divdate=checkDateFormat(msgcontainer,msgHeader(),divdateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//合同利率
		double divRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.repoRate));
		//应收利息
		double intatmat=0;
		String intatmatStr=getFieldValue(fieldsHM, MsgFields.intatmat);
		if(intatmatStr==null || intatmatStr.equals("")){
			String cashflowid=sec.getCashFlowCfgID();
			CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowid);
			intatmat=incomeExpenseCal.incomeCal(cfc.getIntCode(), tradeDate, divdate, divRate, amount);
		}else{
			intatmat=Double.valueOf(intatmatStr);
		}
		
		double fxrate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeFX));
		
		double amount2=0;
		String maturityStr=getFieldValue(fieldsHM, MsgFields.maturityTotal);
		if(maturityStr==null || maturityStr.equals("")){
			amount2=amount+intatmat;
		}else{
			amount2=Double.valueOf(getFieldValue(fieldsHM, MsgFields.maturityTotal));
			if(amount2!=RoundUtil.roundMoney2Dec(amount+intatmat))
				amount2=RoundUtil.roundMoney2Dec(amount+intatmat);
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
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date settleDate=tradeDate;
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(portfolio.getId());
		tran.setManager(managers.getId());
		tran.setBkCashacctNo(bkAcct);
		tran.setSecacct(secacctid);
		tran.setFxcurrency(tradeFxCurID);
		tran.setAmount(amount);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setAltDate(divdate);
		tran.setAltRate(divRate);
		tran.setIntatmat2(intatmat);
		tran.setFxrate(fxrate);
		tran.setPrincipal(amount);
		tran.setCash(amount);
		tran.setAmount2(amount2);
		tran.setContract(contract.trim());
		
		tran.setSettleCash(settleCash); 
		tran.setSettleCurrency(settleFxCurID);
		tran.setSettleFxRate(settleFxRate);
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
			
			if(tran.getContract().equals("")){
				//用于产生唯一合同编号
				tran.setContract("TimeDeposit_"+tran.getId());
				tranBasicDao.updateEntity(tran);
			}
			
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(tran, oldTran, "id");
			if(oldTran.getContract().equals("")){
				//用于产生唯一合同编号
				oldTran.setContract("TimeDeposit_"+oldTran.getId());
			}
			
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
		return "/forms/trade/tradeForms/deposit_buy_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "协议存款存入";
	}
}
