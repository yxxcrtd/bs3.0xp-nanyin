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
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.RoundUtil;
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

/** 
 * @ClassName 		AssembleFxOpenTran
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 下午10:25:09 	
 * @Description		[]  
 */

@Service("assemblePortfolioInternalTran")
public class AssemblePortfolioInternalTran extends MsgExecuteService{
	
	@Autowired
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return "PortfolioInternalTran";
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer)  throws NumberFormatException, MsgFieldLackException {
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
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String portnum2=getFieldValue(fieldsHM, MsgFields.portnum2);
		PortfolioHdr portfolio2=getPortfolio(portnum2, msgcontainer);
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
		
		int cashacct2=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct2));
		BkCashAcct shortCashacct=getCashAcct(cashacct2, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct longCashacct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double cash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
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
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int tradeMarket=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeMarket));
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		int valuationMethod=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.valuationMethod));//核算方法
		
		double units=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		double principal=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		double settleCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.settleCash));
		
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
		
		String lotnumdesc="";
		if(fieldsHM.containsKey(MsgFields.contract)){
			lotnumdesc=getFieldValue(fieldsHM, MsgFields.contract);
		}
		
		int hiloCode=1;
		int selectlotnum=0;
		if(fieldsHM.containsKey("lotMethod")){
			hiloCode=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.lotMethod));
			if(hiloCode==LotSelectionType.SpecificLot){
				//直接传送合同编号过来
				selectlotnum=Integer.valueOf(lotnumdesc);
			}
		}
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTradeMarket(tradeMarket);
		tran.setManager(managers.getId());
		tran.setTrantype(tranType);
		tran.setPrice(price);
		tran.setPortfolio(portfolio.getId());
		tran.setPortfolio2(portfolio2.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBkCashacctNo(longCashacct);
		tran.setSecacct(secacctid);
		tran.setFxcurrency(longCashacct.getFxcurrency());
		tran.setAmount(units);
		tran.setCash(cash);
		tran.setIntatmat(RoundUtil.roundMoney2Dec(accrued));
		tran.setBkCashacctNo2(shortCashacct);
		tran.setSecacct2(secacctid2);
		tran.setFxcurrency2(shortCashacct.getFxcurrency());
		tran.setAmount2(units);
		tran.setCash2(cash);
		tran.setBroker(brokerid);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setContract(lotnumdesc);
		tran.setPrincipal(principal);
		tran.setPrincipal2(principal);
		tran.setSettleCash(settleCash);
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
		
		double shortFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), shortCashacct.getFxcurrency(), tradeDate);
		double longFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), longCashacct.getFxcurrency(), tradeDate);
		tran.setFxrate(longFxRate);
		tran.setFxrate2(shortFxRate);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				tran.setId(tanum);
			}
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(tran, oldTran, "id");
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
		return "投资组合间交易";
	}
}
