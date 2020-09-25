package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.StockResverseSplitDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		StockResverseSplitDtoMsg
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jxStockResverseSplitDtoMsg")
public class StockResverseSplitDtoMsg extends MsgTranBaseExecuteService<StockResverseSplitDto>{
	
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
		return "股票合并";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		StockResverseSplitDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), StockResverseSplitDto.class);
		//基本信息验证
		validDtoDataFunc(dto, msgcontainer);
		if (!msgcontainer.isCorrectFlag())
			return;
		
		//股权登记日
		checkDateFormat(msgcontainer, msgHeader(), dto.getDivDate());
		if (!msgcontainer.isCorrectFlag())
			return;
		
		double alrate=Double.parseDouble(dto.getAlRate());
		if(alrate>1 || alrate<0) {
			msgcontainer.setErrorMsg(false, "股票合并比例必须大于0并且小于1");
			return;
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}
	
	@Override
	public TranBasic dto2TranBasic(StockResverseSplitDto dto, MsgContainer msgcontainer) {
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=tradeDate;
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		//股权登记日
		Date divDate=checkDateFormat(msgcontainer,msgHeader(),dto.getDivDate());
		
		TranBasic tran=new TranBasic();
		tran.setCusip(getSecurity(dto.getSecid(), msgcontainer));
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setPortfolio(dto.getPort());
		tran.setAltRate(Double.parseDouble(dto.getAlRate()));
		tran.setDivBasicDate(divDate);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setFxcurrency(portfolio.getCurID());
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}
}
