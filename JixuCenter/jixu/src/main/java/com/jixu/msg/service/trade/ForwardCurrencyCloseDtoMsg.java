package com.jixu.msg.service.trade;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.ForwardCurrencyCloseDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		ForwardCurrencyCloseDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年06月26日 下午15:48:09 	
 * @Description		[]  
 */

@Service("jxForwardCurrencyCloseDtoMsg")
public class ForwardCurrencyCloseDtoMsg extends MsgTranExecuteService<ForwardCurrencyCloseDto>{
	
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
		
		ForwardCurrencyCloseDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ForwardCurrencyCloseDto.class);
		//基本信息验证
		validDtoDataFunc(dto, msgcontainer);
		if(!msgcontainer.isCorrectFlag() || dto.getOperation().equalsIgnoreCase(delete)){
			return;
		}
		
		//结算日
		String settleDateStr=dto.getSettleDate();
		checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//多头货币
		getCurrency(dto.getLongCurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//空头货币
		getCurrency(dto.getShortCurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}		
		
		//结算货币
		getCurrency(dto.getSettleCurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}		
		
		//结算账户
		getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//交易费用
		genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	
	}


	@Override
	public TranBasic dto2TranBasic(ForwardCurrencyCloseDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct longCashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		TranBasic tran=new TranBasic();
		tran.setTrantype(Integer.valueOf(dto.getTradeType()));
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setBroker(dto.getBroker());
		tran.setManager(Integer.parseInt(dto.getManager()));
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		
		tran.setAmount(Double.parseDouble(dto.getTradeCash()));
		tran.setCash(Double.parseDouble(dto.getTradeCash()));
		tran.setBkCashacctNo(longCashacct);
		tran.setBkCashacctNo2(longCashacct);
		tran.setFxcurrency(dto.getLongCurrency());
		tran.setFxcurrency2(dto.getShortCurrency());
		tran.setFxrate(Double.parseDouble(dto.getLongFX()));
		tran.setAmount2(Double.parseDouble(dto.getTradeCash2()));
		tran.setCash2(Double.parseDouble(dto.getTradeCash2()));
		tran.setFxrate2(Double.parseDouble(dto.getShortFX()));
		
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setCusip(getSecurity(dto.getSecid(), msgcontainer));
		tran.setSettleCurrency(dto.getSettleCurrency());
		return tran;
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
