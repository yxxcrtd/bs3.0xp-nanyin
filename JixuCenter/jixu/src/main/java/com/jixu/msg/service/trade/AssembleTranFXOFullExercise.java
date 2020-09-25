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

/** 
 * @ClassName 		AssembleTranOption
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service
public class AssembleTranFXOFullExercise extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return "AssembleTranFXOFullExercise";
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
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		
		int longCashacctID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct longCashAcct=getCashAcct(longCashacctID, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int shortCashacctID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct2));
		BkCashAcct shortCashAcct=getCashAcct(shortCashacctID, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String longSecacctID=getFieldValue(fieldsHM, MsgFields.secAcct);
		SecAcct longSecAcct=getSecAcct(portnum, longSecacctID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String shortSecacctID=getFieldValue(fieldsHM, MsgFields.secAcct2);
		SecAcct shortSecAcct=getSecAcct(portnum, shortSecacctID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		int selectlotnum=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.contract));
		
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
		tran.setSecacct(longSecacctID);
		tran.setSecacct2(shortSecacctID);
		tran.setBkCashacctNo(longCashAcct);
		tran.setBkCashacctNo2(shortCashAcct);
		tran.setFxcurrency(longCashAcct.getFxcurrency());
		tran.setFxcurrency2(shortCashAcct.getFxcurrency());
		tran.setPortfolio(portfolio.getId());
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		
		
		tran.setTradeMarket(tradeMarket);
		tran.setManager(managers.getId());
		double shortFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), shortCashAcct.getFxcurrency(),tradeDate);
		double longFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), longCashAcct.getFxcurrency(), tradeDate);
		tran.setFxrate(longFxRate);
		tran.setFxrate2(shortFxRate);
		tran.setGlGroup(sec.getCoaaGroup());
		tran.setHiloCode(LotSelectionType.SpecificLot);
		tran.setSpecificLotID(selectlotnum);
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
			
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		msgcontainer.setPortid(portfolio.getId());
	}

	@Override
	public String messageForm() {
		return null;
	}

	@Override
	public String messageName() {
		return "货币期权全额行权";
	}
}
