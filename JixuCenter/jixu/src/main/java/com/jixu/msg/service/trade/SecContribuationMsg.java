package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.func.IncomeExpenseCal;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;
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
 * @Msg				  
 */

@Service
public class SecContribuationMsg extends MsgExecuteService{
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Autowired
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.SecContribution_Header;
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
		
		String assetType=sec.getIssueType();
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		Date settleDate=DateUtil.getNextDay(tradeDate);
		
		
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
		if(principal==0){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg("成本本金为0,目前不支持");
			return;
		}
		
		double price=0;
		if(units!=0){
			price=principal/units;
		}
		
		//带过来的利息收入
		String accruedStr=getFieldValue(fieldsHM, MsgFields.accrued);
		double accrued=0;
		if(!accruedStr.equals("")){
			accrued=Double.valueOf(accruedStr);	
		}
		double cash=principal;
		
		
		
		//String settleFxCurID="";
		double settleCash=principal;
		double fxrate=settleCash/principal;
	
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		double alRate=0;
		if(fieldsHM.containsKey(MsgFields.alRate)){
			alRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.alRate));//有效利率
		}
		
		Date maturityDate=DateUtil.convertStr2Date("20000101");
		if(assetType.equals(AssetTypes.Bond) || assetType.equals(AssetTypes.SEC_LENDING) || assetType.equals(AssetTypes.TimeDeposit) || assetType.equals(AssetTypes.TERM_REPO)){
			if(fieldsHM.containsKey(MsgFields.maturityDate)){
				String maturityDateStr=getFieldValue(fieldsHM, MsgFields.maturityDate).replaceAll("-", "");
				maturityDate=checkDateFormat(msgcontainer,msgFields(),maturityDateStr);
				if(!msgcontainer.isCorrectFlag())
					return;
			}
		}
		
		int valuationMethod=ValuationType.FV;
		if(fieldsHM.containsKey(MsgFields.valuationMethod)){
			valuationMethod=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.valuationMethod));//核算方法
			if(valuationMethod==ValuationType.AMORTIZE && alRate==0){//判断如果使用摊余成本法
				CashFlowCfg cfc=cashFlowCfgDao.loadCashFlowCfg(sec.getCashFlowCfgID());
				if(cfc==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg("利息现金流尚未配置: " + sec.getSecurityid());
					return;
				}
				double perValueMk=units*sec.getContractSize();
				List<Date> holidayList=holidayLSDao.loadHolidayList(portfolio.getHolidaySD());
				alRate=incomeExpenseCal.calAmortRateByTrade(cfc, tradeDate, settleDate, sec.getExpireDate(), principal, perValueMk, holidayList);
				maturityDate=sec.getExpireDate();
			}
		}
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
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
		tran.setCash(cash);
		tran.setIntatmat(accrued);
		tran.setFxrate(fxrate);
		tran.setFxcurrency(sec.getPriCur());
		tran.setSettleCash(settleCash);
		tran.setSettleCurrency(sec.getPriCur());
		tran.setSettleFxRate(fxrate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTradeMarket(tradeMarket);
		tran.setValuationType(valuationMethod);
		tran.setAltRate(alRate);
		tran.setAltDate(maturityDate);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				tran.setId(tanum);
			}
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
		return "/forms/trade/tradeForms/sec_contribution_form";
	}

	@Override
	public String messageName() {
		return "头寸初始化";
	}
}
