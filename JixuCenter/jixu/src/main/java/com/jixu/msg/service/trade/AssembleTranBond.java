package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.RoundUtil;
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

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				StockTran(Operation,TaNum,TranType,SecID(),TradeDate,SetDate,TradeMarket,Broker(),BNK(),MANAGER(),PORTFOLIO(),UNITES,PRINCIPAL,ACCRUED,TRANFEE(),CASH,FXRate,INTATMAT,SETTLECASH())  
 */

@Service("AssembleTranBond")
public class AssembleTranBond extends MsgExecuteService{
	
	@Autowired
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.TranBond_Header;
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
		
		int valuationMethod=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.valuationMethod));//核算方法
		
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
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
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
		
		double units=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		double principal=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		
		double price=0;
		if(fieldsHM.containsKey(MsgFields.price)){
			price=Double.valueOf(getFieldValue(fieldsHM, MsgFields.price));
		}else if(units!=0){
			price=principal/units;
		}
		
		//带过来的利息收入
		String accruedStr=getFieldValue(fieldsHM, MsgFields.accrued);
		double accrued=0;
		if(!accruedStr.equals("")){
			accrued=Double.valueOf(accruedStr);	
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
		
		int hiloCode=1;
		int selectlotnum=0;
		if(tranType==TransTypes.Bond_Sale || tranType==TransTypes.Bond_Hbfx || tranType==TransTypes.Bond_Hbfx_Shares){
			if(fieldsHM.containsKey("lotMethod")){
				hiloCode=Integer.valueOf(getFieldValue(fieldsHM, "lotMethod"));
				if(hiloCode==LotSelectionType.SpecificLot){
					//直接传送合同编号过来
					selectlotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
				}
			}
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBroker(brokerid);
		tran.setSecacct(secacctid);
		tran.setBkCashacctNo(bkAcct);
		tran.setManager(managers.getId());
		tran.setPortfolio(portfolio.getId());
		tran.setAmount(units);
		tran.setPrincipal(principal);
		tran.setPrice(price);
		tran.setCash(RoundUtil.roundMoney2Dec(cash));
		tran.setIntatmat(RoundUtil.roundMoney2Dec(accrued));
		tran.setFxrate(fxrate);
		tran.setFxcurrency(sec.getPriCur());
		tran.setSettleCash(RoundUtil.roundMoney2Dec(settleCash));
		tran.setSettleCurrency(settleFxCurID);
		tran.setSettleFxRate(settleFxRate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setTradeMarket(tradeMarket);
		tran.setHiloCode(hiloCode);
		tran.setSpecificLotID(selectlotnum);
		if(valuationMethod==ValuationType.AMORTIZE){//判断如果使用摊余成本法
			double effectiveRate=0;
			if(fieldsHM.containsKey(MsgFields.effectiveRate)){
				 effectiveRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.effectiveRate));//有效利率
			}
			
			if(effectiveRate==0){
				CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
				List<Date> holidayList=commService.getHolidays(portfolio.getId());
				effectiveRate=incomeExpenseCal.calAmortRateByTrade(cfc, tradeDate, settleDate, 
						sec.getExpireDate(), price, sec.getContractSize(), holidayList);
			}
			tran.setValuationType(ValuationType.AMORTIZE);//使用摊余成本来标记
			tran.setAltRate(effectiveRate);
			tran.setAltDate(sec.getExpireDate());
		}
		
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
		return "/forms/trade/tradeForms/bond_buy_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "债券交易";
	}
}
