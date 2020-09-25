package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.SecAcct;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleFxOpenTran
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 下午10:25:09 	
 * @Description		[]  
 */

@Service("fxOpenMsg")
public class AssembleFxOpenTran extends MsgExecuteService{
	
	@Resource(name="SecTranFXOpen")
	private SecurityBasic secBasic;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.FxOpenTran_Header;
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
		
		int cashacct2=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct2));
		BkCashAcct shortCashacct=getCashAcct(cashacct2, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double shortcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash2));
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct longCashacct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double longcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
		String secacctid=getFieldValue(fieldsHM, MsgFields.secAcct);
		getSecAcct(portnum, secacctid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String secacctid2=getFieldValue(fieldsHM, MsgFields.secAcct2);
		getSecAcct(portnum, secacctid2, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//fee
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(secBasic.tranType());
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setCusip(sec);
		tran.setBkCashacctNo(longCashacct);
		tran.setSecacct(secacctid);
		tran.setFxcurrency(longCashacct.getFxcurrency());
		tran.setAmount(longcash);
		tran.setCash(longcash);
		tran.setBkCashacctNo2(shortCashacct);
		tran.setSecacct2(secacctid2);
		tran.setFxcurrency2(shortCashacct.getFxcurrency());
		tran.setAmount2(shortcash);
		tran.setCash2(shortcash);
		tran.setBroker(brokerid);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		
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
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "即期换汇";
	}
}
