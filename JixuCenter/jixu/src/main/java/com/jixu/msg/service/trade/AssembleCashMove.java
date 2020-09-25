package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssembleCashMove
 * @author  		Pan Wei 
 * @Date 			2016年8月19日 下午3:29:12 	
 * @Description		[]  
 */

@Service("cashMoveMsg")
public class AssembleCashMove extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.CashMove_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
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
		
		int cashacct2=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct2));
		BkCashAcct shortCashacct=getCashAcct(cashacct2, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double shortcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash2));
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct longCashacct=getCashAcct(cashacct,  portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double longcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
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
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(tranType);
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setSecacct(secacctid);
		tran.setBkCashacctNo(longCashacct);
		tran.setFxcurrency(longCashacct.getFxcurrency());
		tran.setAmount(longcash);
		tran.setPrincipal(longcash);
		tran.setCash(longcash);
		tran.setSecacct2(secacctid2);
		tran.setBkCashacctNo2(shortCashacct);
		tran.setFxcurrency2(shortCashacct.getFxcurrency());
		tran.setAmount2(shortcash);
		tran.setPrincipal2(shortcash);
		tran.setCash2(shortcash);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
		double shortFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), shortCashacct.getFxcurrency(), tradeDate);
		double longFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), longCashacct.getFxcurrency(), tradeDate);
		tran.setFxrate(longFxRate);
		tran.setFxrate2(shortFxRate);
		
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
		return "/forms/trade/tradeForms/cashMove_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "现金划拨";
	}
}
