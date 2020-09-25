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
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;

/** 
 * @ClassName 		AssembleTARepoBorrowMaturity
 * @author  		Pan Wei 
 * @Date 			2016年11月7日 下午2:34:51 	
 * @Description		[]
 * @Msg				Operation,Tanum,SecID(),TradeType,Portfolio(),TradeDate,SettleDate,SecAcct(),
 * 					BNK(),Amount,ALDate,ALRate,Intatmat,MaturityAmount,FXRate,SETTLECASH(),Collat()  
 */

@Service("AssembleTARepoBorrowMaturity")
public class AssembleTARepoBorrowMaturity extends MsgExecuteService{
	
	@Resource(name="SecTranRepoLendMaturity")
	private SecurityBasic secBasic;
	
	@Resource(name="incomeExpenseCal")
	private IncomeExpenseCal incomeExpenseCal;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.TARepoBorrow_Maturity_Header;
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

		double amount=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shares));
		double intatmat=Double.valueOf(getFieldValue(fieldsHM, MsgFields.intatmat));
		double fxrate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeFX));
		
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

		List<TranCollateral> collateralList=genTranBasicCollaterals(msgcontainer, fieldsHM, portnum);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		int lotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic newTran=new TranBasic();
		newTran.setTrantype(TransTypes.REPO_BORROW_MATURITY);
		newTran.setPortfolio(portfolio.getId());
		newTran.setTradedate(tradeDate);
		newTran.setSettledate(settleDate);
		newTran.setLedgerdate(ledgerDate);
		newTran.setPostStatus(PostStatusType.unpost);
		newTran.setSettleStatus(PostStatusType.unpost);
		newTran.setCusip(sec);
		newTran.setSecacct(secacctid);
		newTran.setAmount(amount);
		newTran.setPrincipal(amount);
		newTran.setCash(RoundUtil.roundMoney2Dec(amount+intatmat));
		newTran.setIntatmat(intatmat);
		newTran.setFxrate(fxrate);
		newTran.setBkCashacctNo(bkAcct);
		newTran.setFxcurrency(sec.getPriCur());
		newTran.setSettleCash(settleCash); 
		newTran.setSettleCurrency(settleFxCurID);
		newTran.setSettleFxRate(settleFxRate);
		newTran.setHiloCode(LotSelectionType.SpecificLot);
		newTran.setSpecificLotID(lotnum);
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
				collateral.setTranType(secBasic.tranType());
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
				collateral.setTranType(secBasic.tranType());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String messageName() {
		return "正回购到期";
	}
}
