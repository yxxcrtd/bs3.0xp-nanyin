package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.SwapFXOpenDto;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.CoaaGroup;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.RPSecType;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		SwapFXOpenDtoMsg
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 */

@Service("jxSwapFXOpenDtoMsg")
public class SwapFXOpenDtoMsg extends MsgTranBaseExecuteService<SwapFXOpenDto>{
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		SwapFXOpenDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), SwapFXOpenDto.class);
		// 获取operation
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
		
		//基金经理
		int managerID=Integer.valueOf(dto.getManager());
		getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//Long端现金账户
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//费用
		genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
	
		//Short端现金账户
		BkCashAcct bkcashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//获取数据源
//		int sourceID = Integer.valueOf(dto.getSource());
//		getInterfaceSource(sourceID, msgcontainer);
//		if (!msgcontainer.isCorrectFlag()) {
//			return;
//		}
		
		int rpSecTypeID=Integer.parseInt(dto.getRpSecType());
		getRPSecType(rpSecTypeID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String curcode=bkcashacct.getFxcurrency();
		String curcode2=bkcashacct2.getFxcurrency();
		CoaaGroup group=coaaGroupDao.loadSwapFxOpenGlGroup(curcode2, curcode);
		if(group==null) {
			msgcontainer.setErrorMsg(false, "尚未配置相关总账栏: " + curcode2+"/"+curcode);
			return;
		}
		dto.setGlgroup(String.valueOf(group.getGroupNum()));
		
		//新建时才判断证券主文件是否被交易post 因为更新一定会被交易post
		if(operation.equalsIgnoreCase(add)) {
			//判断证券主文件是否在交易中post
			checkIssueInfoPost(msgcontainer,dto.getPort(),dto.getSecid());
			if(!msgcontainer.isCorrectFlag()){
				return;
			}		
		}
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	}
	
	@Override
	public TranBasic dto2TranBasic(SwapFXOpenDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		Date maturityDate=checkDateFormat(msgcontainer,msgHeader(),dto.getMaturityDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(Integer.parseInt(dto.getTradeType()));
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setManager(Integer.parseInt(dto.getManager()));
		tran.setPortfolio(portfolio.getId());
		
		//long端
		BkCashAcct cashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		tran.setBkCashacctNo(cashacct);
		tran.setAmount(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getPrincipal())));
		tran.setPrincipal(tran.getAmount());
		tran.setCash(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash())));
		tran.setFxrate(Double.parseDouble(dto.getTradeFX()));
		tran.setSettleFxRate(tran.getFxrate());
		tran.setFxcurrency(cashacct.getFxcurrency());
		tran.setSettleCurrency(tran.getFxcurrency());
		
		//short端
		BkCashAcct cashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		tran.setBkCashacctNo2(cashacct2);
		tran.setAmount2(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getPrincipal2())));
		tran.setPrincipal2(tran.getAmount2());
		tran.setCash2(RoundUtil.roundMoney2Dec(Double.parseDouble(dto.getTradeCash2())));
		tran.setFxrate2(Double.parseDouble(dto.getTradeFX2()));
		tran.setSettleFxRate2(tran.getFxrate2());
		tran.setFxcurrency2(cashacct2.getFxcurrency());
		tran.setSettleCurrency2(tran.getFxcurrency2());
		
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setAltDate(maturityDate);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		tran.setValuationType(ValuationType.FV);
		tran.setGlGroup(Integer.parseInt(dto.getGlgroup()));
		return tran;
	}
	
	@Override
	protected MsgContainer completeTranEntity(SwapFXOpenDto dto, TranBasic tran, TranBasic oldTran, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
		Date maturityDate=checkDateFormat(msgcontainer,msgHeader(),dto.getMaturityDate());
		List<TranBasicFee> feeList=genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());

		PortfolioHdr portfolio=commService.loadPortfolio(tran.getPortfolio());
		
		//利率互换，每个合同创建一个证券主文件
		IssueInfo ii=new IssueInfo();
		ii.setSecurityid(dto.getSecid());
		ii.setIssueType(dto.getAssetType());
		ii.setIncomeCur(portfolio.getCurID());
		ii.setIncomeCur2(portfolio.getCurID());
		ii.setCoaaGroup(Integer.parseInt(dto.getGlgroup()));
		ii.setRpSecType(new RPSecType(Integer.parseInt(dto.getRpSecType())));
		ii.setActiveDate(new Date());

		//InterfaceSource interfaceSource=getInterfaceSource(dto.getSource(), msgcontainer);
		ii.setInterfaceSourceID(dto.getSource());
		ii.setPriCur(portfolio.getCurID());
		ii.setExpireDate(maturityDate);
		ii.setSecurityname(dto.getSecid());		
		ii.setSwapExchangeInd(dto.getSwapExchangeInd());//本金交换方式
		
		//TODO 新建的证券主文件入兆尹金融产品类型表作记录,上线时需要确认
		FinProductType fpd=returnFinProductType(dto.getSecid(),Integer.parseInt(dto.getRpSecType()));
		
		if(operation.equalsIgnoreCase(add)){
			if(tanum!=null && !tanum.equals("")){
				tran.setId(tanum);
			}
			//先保存证券主文件  然后存入到交易中     获取原先的证券,如果存在就更新证券,不存在就新建
			IssueInfo oldii=issueInfoDao.loadSecurity(dto.getSecid());
			if(oldii!=null){
				BeanUtils.copyProperties(ii, oldii, new String[]{"id"});
				tranBasicDao.updateEntity(oldii);
				tran.setCusip(oldii);
			}else{
				tranBasicDao.saveEntity(ii);
				tran.setCusip(ii);
			}
			
			if(JixuUserUtil.isJoyinTablesFlag() && commService.isJoyinSingleValSystem()){
				//如果FinProductType表中存在数据则更新
				FinProductType oldfpd=issueInfoDao.loadFinProductType(dto.getSecid());
				if(oldfpd!=null){
					BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
					issueInfoDao.updateEntity(oldfpd);
				}else{
					issueInfoDao.saveEntity(fpd);
				}
			}
			
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			for(TranBasicFee fee:feeList){
				fee.setTanum(tran);
				tranBasicFeeDao.saveEntity(fee);
			}
			
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			//获取原先的证券
			IssueInfo oldii=getSecurity(dto.getSecid(), msgcontainer);
			
			BeanUtils.copyProperties(ii, oldii, new String[]{"id"});
			tranBasicDao.updateEntity(oldii);
			tran.setCusip(oldii);
			
			if(JixuUserUtil.isJoyinTablesFlag() && commService.isJoyinSingleValSystem()){
				//更新金融产品表
				FinProductType oldfpd=issueInfoDao.loadFinProductType(dto.getSecid());
				if(oldfpd!=null){
					BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
					tranBasicDao.updateEntity(oldfpd);
				}
			}
			
			BeanUtils.copyProperties(tran, oldTran, new String[]{"id"});
			tranBasicDao.updateEntity(oldTran);
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(oldTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		
		msgcontainer.setPortid(dto.getPort());
		return msgcontainer;
	}
	
	@Override
	public String messageForm() {
		return "/forms/trade/tradeForms/bond_buy_form";
	}

	@Override
	public String messageName() {
		return "外汇掉期";
	}
}
