package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.types.PostStatusType;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;

/** 
 * @ClassName 		AssembleTranBorrowBuy
 * @author  		Pan Wei 
 * @Date 			2016年8月20日 下午8:26:37 	
 * @Description		[]  
 */

@Service("AssembleTranSecLendReturn")
public class AssembleTranSecLendReturn extends MsgExecuteService{
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return "TranSecReturn";
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
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
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
		Date tradeDate=checkDateFormat(msgcontainer,msgFields(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		Date settleDate=tradeDate;
		
		int lotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		/*String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}*/
		
		String secacctid=getFieldValue(fieldsHM, MsgFields.secAcct);
		SecAcct secAcct=getSecAcct(portnum, secacctid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String secacctid2=getFieldValue(fieldsHM, MsgFields.secAcct2);
		SecAcct secAcct2=getSecAcct(portnum, secacctid2, msgcontainer);
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
		
		double amount=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		double accrued=Double.valueOf(getFieldValue(fieldsHM, MsgFields.accrued));
		
		List<TranCollateral> collateralList=genTranBasicCollaterals(msgcontainer, fieldsHM, portnum);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		double fxrate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), bkAcct.getFxcurrency(), tradeDate);
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic newTran=new TranBasic();
		newTran.setTrantype(tranType);
		newTran.setPortfolio(portfolio.getId());
		newTran.setTradedate(tradeDate);
		newTran.setSettledate(settleDate);
		newTran.setLedgerdate(ledgerDate);
		newTran.setPostStatus(PostStatusType.unpost);
		newTran.setSettleStatus(PostStatusType.unpost);
		newTran.setCusip(sec);
		
		newTran.setSecacct(secacctid);
		newTran.setBkCashacctNo(bkAcct);
		//newtran.setBroker(brokerid);
		newTran.setManager(managers.getId());
		newTran.setAmount(amount);
		newTran.setAmount2(amount);
		newTran.setSecacct2(secacctid2);
		newTran.setIntatmat(accrued);
		newTran.setCash(accrued);
		newTran.setTradeMarket(TradeMarket.level2);
		newTran.setHiloCode(LotSelectionType.SpecificLot);
		newTran.setSpecificLotID(lotnum);
		newTran.setFxcurrency(bkAcct.getFxcurrency());
		newTran.setSettleCurrency(bkAcct.getFxcurrency());
		newTran.setSettleFxRate(fxrate);
		newTran.setSettleCash(accrued);
		newTran.setFxrate(fxrate);
		newTran.setFxrate2(fxrate);
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
		return "/forms/trade/tradeForms/sec_return_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "融券偿还";
	}
}

