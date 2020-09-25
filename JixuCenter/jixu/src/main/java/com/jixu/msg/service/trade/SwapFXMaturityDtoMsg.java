package com.jixu.msg.service.trade;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.SwapFXMaturityDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 */

@Service("jxSwapFXMaturityDtoMsg")
public class SwapFXMaturityDtoMsg extends MsgTranBaseExecuteService<SwapFXMaturityDto>{
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		SwapFXMaturityDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), SwapFXMaturityDto.class);
		
		String operation=dto.getOperation();
		
		//根据operation，检查以前的交易是否存在
		msgcontainer=checkOldTran(operation, dto.getId(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
				
		if(operation.equalsIgnoreCase(delete)){
			msgcontainer.setEntityObj(dto);
			return;
		}

		//验证资产类型下是否存在该交易类型
		String assetType = dto.getAssetType();
		int tranType = Integer.valueOf(dto.getTradeType());
		if (!SecurityCoreFactory.getAssetTypeHM().get(assetType).containsKey(tranType)) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(
			MsgError.genErrorMsg(MsgError.error13, msgHeader(), assetType + ":" + dto.getTradeType()));
		}
		
		if(operation.equalsIgnoreCase(update)) {
			// 验证证券输入
			getSecurity(dto.getSecid(), msgcontainer);
			if (!msgcontainer.isCorrectFlag()) {
				return;
			}
		}
		
		// 产品/投资组合
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//验证证券输入
		getSecurity(dto.getSecid(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//收入端现金账户
		getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//费用
		genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
	
		//支出端现金账户
		getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//收入端货币
		getCurrency(dto.getIncurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
				
		//支出端货币
		getCurrency(dto.getIncurrency2(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}	
		
		//上期利息截止日
		Date divDate=checkDateFormat(msgcontainer, msgHeader(), dto.getDivBasicDate());
		if (!msgcontainer.isCorrectFlag())
			return;
		
		Date tradeDate=checkDateFormat(msgcontainer, msgHeader(), dto.getTradeDate());
		if (!msgcontainer.isCorrectFlag())
			return;
		
		if(!divDate.before(tradeDate)) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg("计息结束日必须在交易日之前");
			return;
		}
		
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
		msgcontainer.setPortid(portfolio.getId());
	}
	
	@Override
	public TranBasic dto2TranBasic(SwapFXMaturityDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		Date divDate=checkDateFormat(msgcontainer,msgHeader(),dto.getDivBasicDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct cashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		BkCashAcct cashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		IssueInfo sec=getSecurity(dto.getSecid(), msgcontainer);
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setDivBasicDate(divDate);	//冲抵哪天的利息
		tran.setPortfolio(portfolio.getId());
		tran.setCusip(sec);
		
		//收入端
		tran.setBkCashacctNo(cashacct);
		tran.setAmount(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getPrincipal())));
		tran.setPrincipal(tran.getAmount());
		tran.setCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash())));
		tran.setFxrate(Double.parseDouble(dto.getTradeFX()));
		tran.setSettleFxRate(tran.getFxrate());
		tran.setFxcurrency(dto.getIncurrency());
		tran.setSettleCurrency(tran.getFxcurrency());
		
		//支出端
		tran.setBkCashacctNo2(cashacct2);
		tran.setAmount2(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getPrincipal2())));
		tran.setPrincipal2(tran.getAmount2());
		tran.setCash2(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash2())));
		tran.setFxrate2(Double.parseDouble(dto.getTradeFX2()));
		tran.setSettleFxRate2(tran.getFxrate2());
		tran.setFxcurrency2(dto.getIncurrency2());
		tran.setSettleCurrency2(tran.getFxcurrency2());

		
		
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}

	@Override
	public String messageForm() {
		return "/forms/trade/tradeForms/bond_buy_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "利率互换-到期";
	}
}
