package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
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

@Service("AssembleTranTimeDepositSale")
public class AssembleTranTimeDepositSale extends MsgExecuteService{
	@Override
	public String msgHeader() {
		return "TranTimDepositeSale";
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
		
		int tranType=TransTypes.TimeDep_sale;
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

		//直接传送合同编号过来
		int lotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		Date settleDate=tradeDate;
		if(fieldsHM.containsKey(MsgFields.settleDate)){
			String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
			settleDate=checkDateFormat(msgcontainer,msgFields(),settleDateStr);
			if(!msgcontainer.isCorrectFlag())
				return;
		}
		
		double intatmat=Double.valueOf(getFieldValue(fieldsHM, MsgFields.accrued));
		
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
		
		double principal=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		double fxrate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeFX));
		double tradeCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
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
		tran.setAmount(principal);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setFxrate(fxrate);
		tran.setPrincipal(principal);
		tran.setCash(tradeCash);
		tran.setHiloCode(LotSelectionType.SpecificLot);
		tran.setSpecificLotID(lotnum);
		tran.setIntatmat(intatmat);
		
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
		return "/forms/trade/tradeForms/deposit_withdraw_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "协议存款取出";
	}
}
