package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.types.IncomeExpenseType;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssembleCashOffSet
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 上午10:29:11 	
 * @Description		[]  
 */

@Service("cashContributionMsg")
public class AssembleCashContribution extends MsgExecuteService{
	
	@Resource(name="SecTranCashContribution")
	private SecurityBasic secBasic;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.CashContrib_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		//CASHOFF(Operation,OFFID,PORTFOLIO(),TradeDate,SettleDate,BNK(),FuncAmount,LocalAmount,DBCR,Glacct,Desc)
		//String header = msgcontainer.getHeader();
		List<String> fields = msgcontainer.getFields();
		
		String operation=fields.get(0);
		String tanumStr=fields.get(1);
		
		msgcontainer=checkTanum(operation, tanumStr, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tanum=msgcontainer.getEntityID();
		TranBasic oldTran=(TranBasic)msgcontainer.getEntityObj();
		
		if(operation.equalsIgnoreCase(delete)){
			return;
		}
		
		msgAssemble.genMsgContainer(MsgTemplate.Portfolio_Header,fields.get(2), msgcontainer);
		PortfolioHdr portfolio=null;
		if(msgcontainer.isCorrectFlag()){
			portfolio=(PortfolioHdr)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		String tradeDateStr=fields.get(3).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=fields.get(4).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		msgAssemble.genMsgContainer(MsgTemplate.BNK_Header,fields.get(5), msgcontainer);
		BkCashAcct cashacct=null;
		if(msgcontainer.isCorrectFlag()){
			cashacct=(BkCashAcct)msgcontainer.getEntityObj();
		}else{
			return;
		}
		
		double localamt=Double.valueOf(fields.get(6));
		double funcamt=Double.valueOf(fields.get(7));
		String dbcr=fields.get(8);
		
		boolean receiveFlag=true;
		if(Integer.valueOf(dbcr)==IncomeExpenseType.expense){
			receiveFlag=false;
		}
		
		TranBasic tran=new TranBasic();
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		if(receiveFlag){
			tran.setBkCashacctNo(cashacct);
			tran.setPrincipal(localamt);
			tran.setCash(localamt);
			tran.setAmount(localamt);
			tran.setFxcurrency(cashacct.getFxcurrency());
			tran.setFxrate(funcamt/localamt);
		}else{
			tran.setBkCashacctNo2(cashacct);
			tran.setPrincipal2(localamt);
			tran.setCash2(localamt);
			tran.setAmount2(localamt);
			tran.setFxcurrency2(cashacct.getFxcurrency());
			tran.setFxrate2(funcamt/localamt);
		}
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTrantype(secBasic.tranType());
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
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
			tranBasicDao.updateEntity(tran);
			
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
		return "现金初始化";
	}
}
