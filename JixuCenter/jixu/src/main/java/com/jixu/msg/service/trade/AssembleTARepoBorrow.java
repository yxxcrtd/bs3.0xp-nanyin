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
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;

/** 
 * @ClassName 		AssembleTARepoLend
 * @author  		Pan Wei 
 * @Date 			2016年8月20日 下午8:26:37 	
 * @Description		[]  
 */

@Service
public class AssembleTARepoBorrow extends MsgExecuteService{
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return "TranRepoBor";
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
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String maturityDateStr=getFieldValue(fieldsHM, MsgFields.maturityDate).replaceAll("-", "");
		Date maturityDate=checkDateFormat(msgcontainer,msgFields(),maturityDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int tradeMarket=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeMarket));
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
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
		
		double amount=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal))*-1;
		double alrate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.repoRate));
		
		double intatmat=0;
		String intatmatStr=getFieldValue(fieldsHM, MsgFields.intatmat);
		if(intatmatStr==null || intatmatStr.equals("")){
			String cashflowid=sec.getCashFlowCfgID();
			CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(cashflowid);
			intatmat=incomeExpenseCal.incomeCal(cfc.getIntCode(), tradeDate, maturityDate, alrate, amount);
		}else{
			intatmat=Double.valueOf(intatmatStr);
		}
		
		intatmat=intatmat*-1;
		
		double amount2=Double.valueOf(getFieldValue(fieldsHM, MsgFields.maturityTotal))*-1;
		String tradeFxCurID=getFieldValue(fieldsHM, MsgFields.fxCur);
		getCurrency(tradeFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double fxrate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeFX));
		
		String settleFxCurID=getFieldValue(fieldsHM, MsgFields.settleFxCur);
		getCurrency(settleFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double settleCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.settleCash))*-1;
		
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
		
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}

		List<TranCollateral> collateralList=genTranBasicCollaterals(msgcontainer, fieldsHM, portnum);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic newTran=new TranBasic();
		newTran.setTrantype(tranType);
		newTran.setPortfolio(portfolio.getId());
		newTran.setTradedate(tradeDate);
		newTran.setSettledate(settleDate);
		newTran.setLedgerdate(ledgerDate);
		newTran.setPostStatus(PostStatusType.unpost);
		newTran.setSettleStatus(PostStatusType.unpost);
		newTran.setCusip(sec);
		newTran.setAltDate(maturityDate);
		newTran.setAltRate(alrate);
		newTran.setSecacct(secacctid);
		newTran.setBroker(brokerid);
		newTran.setManager(managers.getId());
		newTran.setAmount(amount);
		//到期利息+金额
		newTran.setAmount2(amount2);
		newTran.setCash2(amount2);
		newTran.setPrincipal(amount);
		newTran.setCash(amount);
		newTran.setIntatmat2(intatmat);
		newTran.setFxrate(fxrate);
		newTran.setBkCashacctNo(bkAcct);
		newTran.setFxcurrency(tradeFxCurID);
		newTran.setSettleCash(settleCash); 
		newTran.setSettleCurrency(settleFxCurID);
		newTran.setSettleFxRate(settleFxRate);
		newTran.setTradeMarket(tradeMarket);
		newTran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(newTran.getTrantype()));
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				newTran.setId(tanum);
			}
			newTran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(newTran);
			
			for(TranCollateral collateral:collateralList){
				collateral.setTanum(newTran);
				collateral.setPortfolio(portfolio);
				collateral.setLocation(secAcct.getAcctID());
				collateral.setTranType(tranType);
				tranCollateralDao.saveEntity(collateral);
			}
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(newTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			
			msgcontainer.setEntityObj(newTran);
			msgcontainer.setEntityID(newTran.getId());
		}else if(operation.equalsIgnoreCase(update)){
			
			BeanUtils.copyProperties(newTran, oldTran, "id");
			
			tranBasicDao.updateEntity(oldTran);
			
			for(TranCollateral collateral:collateralList){
				collateral.setTanum(newTran);
				collateral.setPortfolio(portfolio);
				collateral.setLocation(secAcct.getAcctID());
				collateral.setTranType(tranType);
				tranCollateralDao.saveEntity(collateral);
			}
			
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
		return "/forms/trade/tradeForms/repo_borrow_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "回购交易";
	}
}

