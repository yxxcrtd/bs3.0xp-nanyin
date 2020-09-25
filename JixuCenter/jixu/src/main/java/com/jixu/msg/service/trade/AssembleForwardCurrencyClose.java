package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleFxOpenTran
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 下午10:25:09 	
 * @Description		[]  
 */

@Service
public class AssembleForwardCurrencyClose extends MsgExecuteService{
	
	@Override
	public String messageName() {
		return "平仓交易";
	}
	
	@Override
	public String msgHeader() {
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
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		double shortcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash2));
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct bkcashacct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		double longcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int tradeType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.tradeType));//交易类型
		
		double longFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), bkcashacct.getFxcurrency(), tradeDate);
	
		double shortFxRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shortFX));
		
		String longCurrency=getFieldValue(fieldsHM, MsgFields.longCurID);
		
		String shortCurrency=getFieldValue(fieldsHM, MsgFields.shortCurID);
		
		String settleCurrency=getFieldValue(fieldsHM, MsgFields.settleFxCur);
		
		
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String postStatus=PostStatusType.unpost;
		if(fieldsHM.containsKey("postStatus")){
			postStatus=String.valueOf(getFieldValue(fieldsHM, "postStatus"));;
		}
		
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(tradeType);
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setBroker(brokerid);
		tran.setManager(managers.getId());
		tran.setPostStatus(postStatus);
		tran.setSettleStatus(PostStatusType.unpost);
		
		tran.setAmount(longcash);
		tran.setCash(longcash);
		tran.setBkCashacctNo(bkcashacct);
		tran.setBkCashacctNo2(bkcashacct);
		tran.setFxcurrency(longCurrency);
		tran.setFxcurrency2(shortCurrency);
		tran.setFxrate(longFxRate);
		tran.setAmount2(shortcash);
		tran.setCash2(shortcash);
		tran.setFxrate2(shortFxRate);
		
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setCusip(sec);
		tran.setSettleCurrency(settleCurrency);
		
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
	public String msgFields() {
		return null;
	}
}
