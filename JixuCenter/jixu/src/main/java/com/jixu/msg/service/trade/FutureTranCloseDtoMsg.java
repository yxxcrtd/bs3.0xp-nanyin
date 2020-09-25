package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.msg.dto.FutureTranCloseDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		FutureTranBuyDtoMsg
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jixuFutureTranCloseDtoMsg")
public class FutureTranCloseDtoMsg extends MsgTranExecuteService<FutureTranCloseDto>{
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		FutureTranCloseDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), FutureTranCloseDto.class);
		validDtoDataFunc(dto, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		IssueInfo sec=getSecurity(dto.getSecid(), msgcontainer);
		double units=Double.valueOf(dto.getShares());
		double principal=Double.valueOf(dto.getPrincipal());
		
		//价格
		double price = Double.parseDouble(dto.getPrice());
		if (price == 0) {
			// 如果没有提供价格,反向计算价格
			price = principal/units/sec.getContractSize();
			dto.setPrice(String.valueOf(price));
		}
		
		
		//交易货币
		String tradeFxCurID = dto.getTradeCurrency();
		getCurrency(tradeFxCurID, msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//交易日期
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		if(!msgcontainer.isCorrectFlag())
			return;
		
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		//交易汇率
		double fxrate = Double.parseDouble(dto.getTradeFX());
		if (fxrate == 0) {
			fxrate = commService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tradeFxCurID, tradeDate);
			dto.setTradeFX(String.valueOf(fxrate));
		}
		
		//结算货币
		String settleFxCurID = dto.getSettleCurrency();
		getCurrency(settleFxCurID, msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//结算汇率
		double settleFxRate = Double.parseDouble(dto.getSettleFX());
		if (settleFxRate == 0) {
			settleFxRate = commService.loadFxrate(portfolio.getId(), portfolio.getCurID(), settleFxCurID, tradeDate);
			dto.setSettleFX(String.valueOf(settleFxRate));
		}
		
		//验证费用配置是否正确
		List<TranBasicFee> feeList = genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//交易金额
		double cash=Double.parseDouble(dto.getTradeCash());
		if(cash==0){
			cash=principal;
			cash=commService.calTranCashWithFee(Integer.parseInt(dto.getTradeType()), cash, feeList);
			dto.setTradeCash(String.valueOf(cash));
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	
	}

	@Override
	public TranBasic dto2TranBasic(FutureTranCloseDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(getSecurity(dto.getSecid(), msgcontainer));
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBroker(dto.getBroker());
		tran.setSecacct(dto.getSecAcct());
		tran.setBkCashacctNo(new BkCashAcct(Integer.parseInt(dto.getBankAcct())));
		tran.setManager(Integer.parseInt(dto.getManager()));
		tran.setPortfolio(portfolio.getId());
		tran.setAmount(Double.parseDouble(dto.getShares()));
		tran.setPrincipal(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getPrincipal())));
		tran.setPrice(Double.parseDouble(dto.getPrice()));
		tran.setCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash())));
		tran.setFxrate(Double.parseDouble(dto.getTradeFX()));
		tran.setFxcurrency(dto.getTradeCurrency());
		tran.setSettleCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getSettleCash())));
		tran.setSettleCurrency(dto.getSettleCurrency());
		tran.setSettleFxRate(Double.parseDouble(dto.getSettleFX()));
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTradeMarket(Integer.parseInt(dto.getMarket()));
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setContract(dto.getContract());
		tran.setHiloCode(Integer.valueOf(dto.getLotMethod()));
		return tran;
		
	}
	
	@Override
	public String messageName() {
		return "期货平仓";
	}
}
