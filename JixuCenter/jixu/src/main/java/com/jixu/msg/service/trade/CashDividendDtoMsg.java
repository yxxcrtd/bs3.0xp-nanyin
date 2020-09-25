package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.CashDividendDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		CashDividendDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年5月30日 上午9:59:04 	
 * @Description		[]
 */

@Service("jxCashDividendDtoMsg")
public class CashDividendDtoMsg extends MsgTranBaseExecuteService<CashDividendDto>{
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public String messageName() {
		return "现金股利";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		CashDividendDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), CashDividendDto.class);
		//基本信息验证
		validDtoDataFunc(dto, msgcontainer);
		if (!msgcontainer.isCorrectFlag() || dto.getOperation().equalsIgnoreCase(delete))
			return;
		
		//股利登记日
		checkDateFormat(msgcontainer, msgHeader(), dto.getDivDate());
		if (!msgcontainer.isCorrectFlag())
			return;
		
		//证券账户/托管账户
		getSecAcct(dto.getPort(), dto.getSecAcct(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//获取现金账户，如果没有提供，则用默认现金账户
		getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}
	
	@Override
	public TranBasic dto2TranBasic(CashDividendDto dto, MsgContainer msgcontainer) {
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		//付款日
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		//股利登记日
		Date recordDate=checkDateFormat(msgcontainer,msgHeader(),dto.getDivDate());
		//当天汇率
		IssueInfo sec=getSecurity(dto.getSecid(), msgcontainer);
		double fxrate = commService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getPriCur(), tradeDate);
		
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(dto.getPort());
		tran.setAltRate(Double.parseDouble(dto.getAlRate()));
		tran.setDivBasicDate(recordDate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setFxcurrency(sec.getPriCur());
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setFxrate(fxrate);
		tran.setSecacct(dto.getSecAcct());
		tran.setBkCashacctNo(bkcashacct);
		tran.setAmount2(dto.getBaseAmount());
		tran.setCash(dto.getTradeCash());
		return tran;
	}
}
