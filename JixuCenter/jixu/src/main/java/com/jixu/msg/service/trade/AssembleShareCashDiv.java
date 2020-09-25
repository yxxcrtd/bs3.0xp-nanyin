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
import com.jixu.common.util.DateUtil;
import com.jixu.dao.intf.NavDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Nav;
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

@Service("AssembleShareCashDiv")
public class AssembleShareCashDiv extends MsgExecuteService{
	
	@Autowired
	private NavDao navDao;
	
	@Override
	public String msgHeader() {
		return "TranShareDiv";
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
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.exDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String divdate=getFieldValue(fieldsHM, MsgFields.recordDate).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),divdate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double divRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.alRate));
	
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String portCurID=portfolio.getCurID();
		String curID=portCurID;
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		double fxrate=commService.loadFxrate(portfolio.getId(),portCurID, portCurID, tradeDate);
		Date divDate=DateUtil.convertStr2Date(divdate);
		
		Nav nav = navDao.loadLatestNAV(portnum, divDate);
		double amount=nav.getSharesSettled();
		
		String secacctno=portfolio.getDefaultSecAcct();
		SecAcct secacct=getSecAcct(portnum, secacctno, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int defaultCashAcct=secacct.getDefaultCashAcct();
		getCashAcct(defaultCashAcct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(tranType);
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(portfolio.getId());
		tran.setSecacct(secacctno);
		tran.setBkCashacctNo(new BkCashAcct(defaultCashAcct));
		tran.setAmount(amount);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setDivBasicDate(divDate);
		tran.setAltRate(divRate);
		tran.setFxrate(fxrate);
		tran.setFxcurrency(curID);
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
		return "/forms/trade/tradeForms/share_cashdiv_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "现金股利";
	}
}
