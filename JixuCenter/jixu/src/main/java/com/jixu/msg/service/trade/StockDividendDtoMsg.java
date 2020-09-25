package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.StockDividendDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		StockDividendsDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年5月30日 上午9:59:04 	
 * @Description		[]
 */

@Service("jxStockDividendDtoMsg")
public class StockDividendDtoMsg extends MsgTranBaseExecuteService<StockDividendDto>{
	
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
		return "股票股利";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		StockDividendDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), StockDividendDto.class);
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
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}
	
	@Override
	public TranBasic dto2TranBasic(StockDividendDto dto, MsgContainer msgcontainer) {
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=tradeDate;
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		//股利登记日
		Date recordDate=checkDateFormat(msgcontainer,msgHeader(),dto.getDivDate());
		IssueInfo sec=getSecurity(dto.getSecid(), msgcontainer);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(sec);
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(dto.getPort());
		tran.setAltRate(Double.parseDouble(dto.getAlRate()));
		tran.setAmount(dto.getAmount());
		tran.setAmount2(dto.getBaseAmount());
		tran.setSecacct(dto.getSecAcct());
		tran.setDivBasicDate(recordDate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setFxcurrency(sec.getPriCur());
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}
}
