package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.msg.dto.StockNewReceiveDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		StockNewReceiveDtoMsg
 * @author  		Wang  Zhongyuan 
 * @Date 			2019年5月28日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jixuStockNewReceiveDtoMsg")
public class StockNewReceiveDtoMsg extends MsgTranExecuteService<StockNewReceiveDto>{
	
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
		return "新股中签";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		
		StockNewReceiveDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), StockNewReceiveDto.class);
		//基本信息验证
		validDtoDataFunc(dto, msgcontainer);
		if (!msgcontainer.isCorrectFlag())
			return;
		
		// 投资组合
		PortfolioHdr portfolio = getPortfolio(dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		// 交易货币
		String tradeFxCurID = dto.getTradeCurrency();
		getCurrency(tradeFxCurID, msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		double units=Double.valueOf(dto.getShares());
		double principal=Double.valueOf(dto.getPrincipal());
		// 价格
		double price = Double.parseDouble(dto.getPrice());
		if (price == 0) {
			// 如果没有提供价格,反向计算价格
			price = principal / units ;
			dto.setPrice(String.valueOf(price));
		}
		
		// 交易日期
		Date tradeDate = checkDateFormat(msgcontainer, msgHeader(), dto.getTradeDate());
		if (!msgcontainer.isCorrectFlag())
			return;
		
		// 交易汇率
		double fxrate = Double.parseDouble(dto.getTradeFX());
		if (fxrate == 0) {
			fxrate = commService.loadFxrate(portfolio.getId(), portfolio.getCurID(), tradeFxCurID, tradeDate);
			dto.setTradeFX(String.valueOf(fxrate));
		}
		
		// 交易金额
		double cash = Double.parseDouble(dto.getTradeCash());
		if (cash == 0) {
			dto.setTradeCash(String.valueOf(principal));
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
	public TranBasic dto2TranBasic(StockNewReceiveDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setCusip(getSecurity(dto.getSecid(), msgcontainer));
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBroker(dto.getBroker());
		tran.setSecacct(dto.getSecAcct());
		tran.setBkCashacctNo(bkcashacct);
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
		return tran;
	}
}
