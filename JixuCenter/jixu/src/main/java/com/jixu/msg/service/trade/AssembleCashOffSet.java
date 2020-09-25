package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityBasic;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssembleCashOffSet
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 上午10:29:11 	
 * @Description		[]  
 */

@Service("cashOffMsg")
public class AssembleCashOffSet extends MsgExecuteService{
	
	@Resource(name="SecTranCashOff")
	private SecurityBasic secBasic;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.CashOff_Header;
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
		
		int tranType=TransTypes.CASHOFF;
		
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

		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct bkAcct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		
		double localamt=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		double funcamt=Double.valueOf(getFieldValue(fieldsHM, MsgFields.settleCash));
		
		String dbcr=getFieldValue(fieldsHM, MsgFields.offsetType);
		
		boolean receiveFlag=true;
		/*if(IncomeExpenseType.getDbcrOptionHM().get(IncomeExpenseType.expense).equals(dbcr)){
			receiveFlag=false;
		}*/
		//界面传来的dbcr是integer 0收入 1支出   IncomeExpenseType.getDbcrOptionHM().get(IncomeExpenseType.expense)取的是C 没法比较
		if(IncomeExpenseType.expense==Integer.valueOf(dbcr)){
			receiveFlag=false;
		}
	
		String glacct=getFieldValue(fieldsHM, MsgFields.glacct);
		String[] glaccts=glacct.split("\\|");
		if(glaccts.length==3){
			glacct=glaccts[1];
		}
		
		String desc=getFieldValue(fieldsHM, MsgFields.tranDesc);
		
		//cash off，如果是债券，期货等利息、无负债的结转，传入证券代码
		IssueInfo sec=null;
		if(fieldsHM.containsKey(MsgFields.secid)) {
			Object secid=fieldsHM.get(MsgFields.secid);
			if(secid!=null) {
				sec=getSecurity(secid.toString().trim(), msgcontainer);
				if(!msgcontainer.isCorrectFlag()) 
					return;
			}
		}
		
		TranBasic tran=new TranBasic();
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setSettleCash(funcamt);
		if(receiveFlag){
			tran.setBkCashacctNo(bkAcct);
			tran.setPrincipal(localamt);
			tran.setCash(localamt);
			tran.setAmount(localamt);
			tran.setFxcurrency(bkAcct.getFxcurrency());
			tran.setFxrate(funcamt/localamt);
		}else{
			tran.setBkCashacctNo2(bkAcct);
			tran.setPrincipal2(localamt);
			tran.setCash2(localamt);
			tran.setAmount2(localamt);
			tran.setFxcurrency2(bkAcct.getFxcurrency());
			tran.setFxrate2(funcamt/localamt);
		}
		tran.setGlacct(glacct);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTrantype(tranType);
		//tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype())+"-"+desc);
		tran.setTranDesc(desc);
		tran.setCusip(sec);
		tran.setSecacct(bkAcct.getSecAcctCode() );
		
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
		return "/forms/trade/tradeForms/cashOffset_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "现金冲销";
	}
}
