package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.TradeMarket;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.msg.dto.StockTranDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jixuStockTranDtoMsg")
public class StockTranDtoMsg extends MsgTranExecuteService<StockTranDto>{
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String messageForm() {
		return "/forms/trade/tradeForms/stock_all_form";
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		StockTranDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), StockTranDto.class);
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
				
		//根据operation，检查以前的交易是否存在
		msgcontainer=checkOldTran(operation, dto.getId(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		if(operation.equalsIgnoreCase(delete)){
			//外汇远期 开仓交易后面如果存在交割  到期 平仓  则不能删除开仓
			TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,dto.getId());
			if(oldTran.getTrantype()==TransTypes.FORWORD_CURRENCY_OPEN){
				TranBasic tb=tranBasicDao.loadForwardListByTranType(oldTran.getPortfolio(), oldTran.getCusip().getId(), oldTran.getTrantype());
				if(tb!=null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorCode(ReturnResult.tranfw_exist_after_tranwfw);
					msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.tranfw_exist_after_tranwfw));
					return;
				}
			}
			msgcontainer.setEntityObj(dto);
			return;
		}
		
		validateDto(msgcontainer, dto);
		//dto验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//交易如期
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//券商
		getBroker(dto.getBroker(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
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
		
		//产品/投资组合
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//投资经理
		getManagers(portfolio.getManagerGroup(), Integer.parseInt(dto.getManager()), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double units=Double.valueOf(dto.getShares());
		double principal=Double.valueOf(dto.getPrincipal());
		
		//价格
		double price=Double.parseDouble(dto.getPrice());
		if(price==0){
			//如果没有提供价格,反向计算价格。在交易文件里，往往只提供成交金额和数量，价格是计算之后的一个平均成交价。股票是逐笔分笔成交的
			price=principal/units;
			dto.setPrice(String.valueOf(price));
		}
		
		//验证费用配置是否正确
		List<TranBasicFee> feeList=genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		double cash=Double.parseDouble(dto.getTradeCash());
		if(cash==0){
			//如果没有给汇总金额，总金额=成本+应收利息+-费用
			cash=RoundUtil.roundMoney2Dec(principal+Double.parseDouble(dto.getAccruedIncome()));
			cash=commService.calTranCashWithFee(Integer.parseInt(dto.getTradeType()), cash, feeList);
			dto.setTradeCash(String.valueOf(cash));
		}
	
		//交易货币
		String tradeFxCurID=dto.getTradeCurrency();
		getCurrency(tradeFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//交易汇率
		double fxrate=Double.parseDouble(dto.getTradeFX());
		if(fxrate==0){
			fxrate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), tradeFxCurID, tradeDate);
			dto.setTradeFX(String.valueOf(fxrate));
		}
		
		//结算货币
		String settleFxCurID=dto.getSettleCurrency();
		getCurrency(settleFxCurID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//结算汇率
		double settleFxRate=Double.parseDouble(dto.getSettleFX());
		if(settleFxRate==0){
			settleFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), settleFxCurID, tradeDate);
			dto.setSettleFX(String.valueOf(settleFxRate));
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}
	
	@Override
	public TranBasic dto2TranBasic(StockTranDto dto, MsgContainer msgcontainer) {
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
		tran.setIntatmat(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getAccruedIncome())));
		tran.setCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash())));
		tran.setFxrate(Double.parseDouble(dto.getTradeFX()));
		tran.setFxcurrency(dto.getTradeCurrency());
		tran.setSettleCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getSettleCash())));
		tran.setSettleCurrency(dto.getSettleCurrency());
		tran.setSettleFxRate(Double.parseDouble(dto.getSettleFX()));
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		if(dto.getMarket()!=null && !dto.getMarket().equals("")) {
			tran.setTradeMarket(Integer.parseInt(dto.getMarket()));
		}else {
			tran.setTradeMarket(TradeMarket.level2);
		}
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}

	@Override
	public String messageName() {
		return "普通股交易";
	}
}
