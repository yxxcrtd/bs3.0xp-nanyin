package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.TranSumDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranSum;

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				Operation,TaNum,TranType,SecID(),DivDate,TradeDate,Portfolio(),DivRate,TRANFEE()  
 */

@Service("AssembleStockDividend")
public class AssembleStockDividend extends MsgExecuteService{
	
	@Autowired
	private TranSumDao tranSumDao;
	
	@Override
	public String msgHeader() {
		return "TranStockDividend";
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
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.exDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String divdateStr=getFieldValue(fieldsHM, MsgFields.recordDate).replaceAll("-", "");
		Date divdate=checkDateFormat(msgcontainer,msgHeader(),divdateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double divRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.alRate));
		
		List<TranSum> tsumList=tranSumDao.loadTSumByCusip(portfolio.getId(), sec.getId());
		double units=0;
		for(TranSum tsum:tsumList){
			if(tsum.getLsttradeDate().compareTo(tradeDate)==1){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.tran_exist_after_tradeDate)+": "+tradeDate);
				return;
			}
		}
		
		double divUnits=Double.valueOf(units*divRate);
		Date settleDate=tradeDate;
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(tradeDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(portfolio.getId());
		tran.setAmount(divUnits);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setDivBasicDate(divdate);
		tran.setAltRate(divRate);
		tran.setFxcurrency(portfolio.getCurID());
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
		return "/forms/trade/tradeForms/stock_stockdiv_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "股票股利";
	}
}
