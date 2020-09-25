package com.jixu.msg.service.trade;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.DBCR;
import com.jixu.common.types.PostStatusType;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.ForwardCurrencyCuttingDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		ForwardCurrencyCuttingDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年06月26日 下午15:48:09 	
 * @Description		[]  
 */

@Service("jxForwardCurrencyCuttingDtoMsg")
public class ForwardCurrencyCuttingDtoMsg extends MsgTranExecuteService<ForwardCurrencyCuttingDto>{
	
	@Override
	public String messageName() {
		return "轧差交割";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer)  throws NumberFormatException, MsgFieldLackException {
		
		ForwardCurrencyCuttingDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ForwardCurrencyCuttingDto.class);
		//基本信息验证
		validDtoDataFunc(dto, msgcontainer);
		if(!msgcontainer.isCorrectFlag() || dto.getOperation().equalsIgnoreCase(delete)){
			return;
		}
		
		//结算日
		String settleDateStr=dto.getSettleDate();
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		IssueInfo ii=getSecurity(dto.getSecid(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		TranBasic tb=tranBasicDao.getFCOpenTranBasic(dto.getPort(), ii.getId(),TransTypes.FORWORD_CURRENCY_OPEN);
		if(!tb.getLedgerdate().before(settleDate) || tb.getSettledate().before(settleDate)){
			msgcontainer.setErrorMsg(false, MsgError.error23);;
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
	public TranBasic dto2TranBasic(ForwardCurrencyCuttingDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		
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
		//判断轧差金额收付方向
		if(dto.getDiffSide()==DBCR.receive){
			tran.setCash(Double.parseDouble(dto.getDiffCash()));
		}else{
			tran.setCash(-1*Double.parseDouble(dto.getDiffCash()));
		}
		tran.setFxcurrency(dto.getLongCurrency());
		tran.setFxcurrency2(dto.getShortCurrency());
		tran.setAmount2(Double.parseDouble(dto.getTradeCash2()));
		tran.setBkCashacctNo(bkcashacct);
		
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
