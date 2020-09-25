package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LotSelectionType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		AssembleTARepoLend
 * @author  		Pan Wei 
 * @Date 			2016年8月20日 下午8:26:37 	
 * @Description		[]  
 */

@Service
public class TranBankZiGuanBorrowRedMsg extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return null;
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
		
		
		//获取合同号
		int lotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
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
		
		if(!sec.getIssueType().equals(AssetTypes.BankZiGuan)){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg("证券类型不正确: " + AssetTypes.getPortAsset(false).get(sec.getIssueType()) +"/"+AssetTypes.getPortAsset(false).get(AssetTypes.BankZiGuan));	
		}
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgFields(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgFields(),settleDateStr);
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

		//利息收入
		double accrued=Double.valueOf(getFieldValue(fieldsHM, MsgFields.accrued));
		//绩效费用
		double performanceFee=Double.valueOf(getFieldValue(fieldsHM, "performanceFee"));
		//回拨金额
		double clawBack=Double.valueOf(getFieldValue(fieldsHM, "clawBack"));
		
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
		
		double units=0;
		if(fieldsHM.containsKey(MsgFields.shares)){
			String amouuntStr=getFieldValue(fieldsHM, MsgFields.shares);
			units=Double.valueOf(amouuntStr);
		}
		
		double principal=Double.valueOf(getFieldValue(fieldsHM, MsgFields.principal));
		//double tradeCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
		if(principal!=0 && units==0){
			TranClo tclo=tranCloDao.loadTranClo(portnum, portfolio.getLastGLClose(), sec.getId(), lotnum);
			if(tclo!=null){
				double totalAmt=Math.abs(tclo.getAmount());
				double tcost=Math.abs(tclo.getgCost());
				units=RoundUtil.roundMoney(totalAmt*principal/tcost,0);
			}else
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.record_not_exist)+" 日期: "+portfolio.getLastGLClose()+" 批次："+lotnum);
		}
		
		//费用计算
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double price=0;
		if(fieldsHM.containsKey(MsgFields.price)){
			price=Double.valueOf(getFieldValue(fieldsHM, MsgFields.price));
		}else if(units!=0){
			price=principal/units;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic newTran=new TranBasic();
		newTran.setTrantype(tranType);
		newTran.setPortfolio(portfolio.getId());
		newTran.setTradedate(tradeDate);
		newTran.setManager(managers.getId());
		newTran.setIntatmat(accrued);
		newTran.setSettledate(settleDate);
		newTran.setLedgerdate(ledgerDate);
		newTran.setPostStatus(PostStatusType.unpost);
		newTran.setSettleStatus(PostStatusType.unpost);
		newTran.setCusip(sec);
		newTran.setSecacct(secacctid);
		newTran.setAmount(units);
		newTran.setPrice(price);
		newTran.setPrincipal(principal);
		newTran.setCash(settleCash);
		newTran.setFxrate(settleFxRate);
		newTran.setBkCashacctNo(bkAcct);
		newTran.setFxcurrency(settleFxCurID);
		newTran.setSettleCash(settleCash); 
		newTran.setSettleCurrency(settleFxCurID);
		newTran.setSettleFxRate(settleFxRate);
		newTran.setHiloCode(LotSelectionType.SpecificLot);
		newTran.setSpecificLotID(lotnum);
		
		if(performanceFee>0){
			newTran.setPrincipal2(performanceFee);//用来存放绩效费
		}else if(clawBack>0){
			newTran.setPrincipal2(clawBack*-1);//用来存放回拨费用
		}
		
		newTran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(newTran.getTrantype()));
		
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				newTran.setId(tanum);
			}
			newTran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(newTran);
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(newTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			msgcontainer.setEntityObj(newTran);
			msgcontainer.setEntityID(newTran.getId());
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(newTran, oldTran, "id");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "组合批次到期";
	}
}

