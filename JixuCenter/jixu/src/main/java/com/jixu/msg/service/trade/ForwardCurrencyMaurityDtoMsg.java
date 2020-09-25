package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.ForwardCurrencyMaurityDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		ForwardCurrencyMaurityDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年06月26日 下午15:48:09 	
 * @Description		[]  
 */

@Service("jxForwardCurrencyMaurityDtoMsg")
public class ForwardCurrencyMaurityDtoMsg extends MsgTranExecuteService<ForwardCurrencyMaurityDto>{
	
	@Override
	public String messageName() {
		return "合约到期";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer)  throws NumberFormatException, MsgFieldLackException {
		
		ForwardCurrencyMaurityDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ForwardCurrencyMaurityDto.class);
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
	public TranBasic dto2TranBasic(ForwardCurrencyMaurityDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		BkCashAcct bkcashacct2=null;
		if(dto.getBankAcct2()!=null && dto.getBankAcct2()!=""){
			bkcashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		}
		TranBasic tran=new TranBasic();
		tran.setTrantype(Integer.valueOf(dto.getTradeType()));
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(tradeDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, tradeDate, portfolio));
		tran.setManager(Integer.parseInt(dto.getManager()));
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		
		tran.setAmount(Double.parseDouble(dto.getTradeCash()));
		tran.setCash(Double.parseDouble(dto.getTradeCash()));
		tran.setFxcurrency(dto.getLongCurrency());
		tran.setFxcurrency2(dto.getShortCurrency());
		tran.setFxrate(Double.parseDouble(dto.getTradeFX()));
		tran.setAmount2(Double.parseDouble(dto.getTradeCash2()));
		tran.setCash2(Double.parseDouble(dto.getTradeCash2()));
		tran.setFxrate2(Double.parseDouble(dto.getSettleFX()));
		tran.setBkCashacctNo(bkcashacct);
		tran.setBkCashacctNo2(bkcashacct2);
		
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setCusip(getSecurity(dto.getSecid(), msgcontainer));
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
