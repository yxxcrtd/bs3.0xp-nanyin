package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.TransTypes;
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.common.util.ReturnResult;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.dto.ForwardCurrencyOpenDto;
import com.jixu.secTran.SecurityBasic;
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
 * @ClassName 		ForwardCurrencyOpenDtoMsg
 * @author  		Wang Zhongyuan 
 * @Date 			2019年06月26日 下午15:48:09 	
 * @Description		[]  
 */

@Service("jxForwardCurrencyOpenDtoMsg")
public class ForwardCurrencyOpenDtoMsg extends MsgTranBaseExecuteService<ForwardCurrencyOpenDto>{
	
	@Resource(name="tranForwardCurrencyOpen")
	private SecurityBasic secBasic;
	
	
	@Override
	public String messageName() {
		return "开仓交易";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer)  throws NumberFormatException, MsgFieldLackException {
		
		ForwardCurrencyOpenDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), ForwardCurrencyOpenDto.class);
		
		// 获取operation
		String operation = dto.getOperation();		

		// 根据operation，检查以前的交易是否存在		
		msgcontainer = checkOldTran(operation, dto.getId(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		if (operation.equalsIgnoreCase(delete)) {
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

		// 验证证券输入		
		if(operation.equalsIgnoreCase(update)){
			//外汇远期 开仓交易后面如果存在交割   平仓  则不能修改
			TranBasic oldTran=tranBasicDao.loadEntity(TranBasic.class,dto.getId());
			TranBasic tb=tranBasicDao.loadForwardListByTranType(oldTran.getPortfolio(), oldTran.getCusip().getId(), oldTran.getTrantype());
			//自动到期的可以修改开仓交易  因为自动到期的会删除重新生成
			if(tb!=null && tb.getImportType()!=JixuDictCode.IMP_TRADE_TYPE_AUTO){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorCode(ReturnResult.tranfw_exist_after_tranwfw);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.tranfw_exist_after_tranwfw));
				return;
			}
			getSecurity(dto.getSecid(), msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
		// 交易日期		
		checkDateFormat(msgcontainer, msgHeader(), dto.getTradeDate());
		if (!msgcontainer.isCorrectFlag())
			return;		
				
		
		//投资组合
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
		
		// 券商
		getBroker(dto.getBroker(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//证券主文件资产类型
		int rpSecTypeID=Integer.valueOf(dto.getRpSecType());
		getRPSecType(rpSecTypeID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//结算日
		String settleDateStr=dto.getSettleDate();
		checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		
		//空头现金账户
		getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		if (!msgcontainer.isCorrectFlag()) {
			return;
		}
		
		//空头货币
		getCurrency(dto.getShortCurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}		
		
		//多头现金账户
		getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//多头货币
		getCurrency(dto.getLongCurrency(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
				
		
		//获取数据源
//		int sourceID = Integer.valueOf(dto.getSource());
//		getInterfaceSource(sourceID, msgcontainer);
//		if (!msgcontainer.isCorrectFlag()) {
//			return;
//		}
		//费用
		genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//新建时才判断证券主文件是否被交易post 因为更新一定会被交易post
		if(operation.equalsIgnoreCase(add)) {
			//判断证券主文件是否在交易中post
			checkIssueInfoPost(msgcontainer,dto.getPort(),dto.getSecid());
			if(!msgcontainer.isCorrectFlag()){
				return;
			}		
		}	
		
		CoaaGroup group=coaaGroupDao.loadForwardFxOpenGlGroup(dto.getShortCurrency(),dto.getLongCurrency());
		if(group==null) {
			msgcontainer.setErrorMsg(false, "尚未配置相关总账栏: " + dto.getShortCurrency());
			return;
		}
		dto.setGlgroup(String.valueOf(group.getGroupNum()));
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
	
	}


	@Override
	public TranBasic dto2TranBasic(ForwardCurrencyOpenDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		BkCashAcct longCashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		BkCashAcct shortCashacct=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);;
		TranBasic tran=new TranBasic();
		tran.setTrantype(secBasic.tranType());
		tran.setPortfolio(dto.getPort());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setBroker(dto.getBroker());
		tran.setManager(Integer.parseInt(dto.getManager()));
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setValuationType(Integer.parseInt(dto.getValuationMethod()));//估值方法
		
		
		tran.setAmount(Double.parseDouble(dto.getTradeCash()));
		tran.setCash(Double.parseDouble(dto.getTradeCash()));
		tran.setBkCashacctNo(longCashacct);
		tran.setFxcurrency(longCashacct.getFxcurrency());
		tran.setFxrate(Double.parseDouble(dto.getLongFX()));
		tran.setAmount2(Double.parseDouble(dto.getTradeCash2()));
		tran.setCash2(Double.parseDouble(dto.getTradeCash2()));
		tran.setBkCashacctNo2(shortCashacct);
		tran.setFxcurrency2(shortCashacct.getFxcurrency());
		tran.setFxrate2(Double.parseDouble(dto.getShortFX()));
		//按照空方估值
		int valuationSide=Integer.valueOf(dto.getValuationSide());
		if(valuationSide==ValuationType.VALSIDE_SHORT){
			tran.setAmount2(-1*tran.getAmount2());
			tran.setCash2(-1*tran.getCash2());
		}
		tran.setGlGroup(Integer.parseInt(dto.getGlgroup()));
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}
	
	@Override
	protected MsgContainer completeTranEntity(ForwardCurrencyOpenDto dto, TranBasic tran, TranBasic oldTran, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
		List<TranBasicFee> feeList=genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		Date maturityDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		//远期换汇，每个合同创建一个证券主文件
		IssueInfo ii=new IssueInfo();
		ii.setSecurityid(dto.getSecid());
		ii.setSecurityname(dto.getContract());
		ii.setIssueType(AssetTypes.FORWARD_CURRENCY);
		ii.setPriCur(portfolio.getCurID());
		ii.setIncomeCur(tran.getFxcurrency());
		ii.setIncomeCur2(tran.getFxcurrency2());
		ii.setCoaaGroup(tran.getGlGroup());
		int rpSecTypeID=Integer.parseInt(dto.getRpSecType());
		ii.setRpSecType(new RPSecType(rpSecTypeID));
		ii.setActiveDate(new Date());
		//InterfaceSource interfaceSource=getInterfaceSource(dto.getSource(), msgcontainer);
		ii.setInterfaceSourceID(dto.getSource());
		ii.setExpireDate(maturityDate);
		ii.setOptionType(dto.getSettleType());
		//估值方向暂时存入到这里
		ii.setSwapExchangeInd(Integer.valueOf(dto.getValuationSide()));
		
		//TODO 新建的证券主文件入兆尹金融产品类型表作记录,上线时需要确认
		FinProductType fpd=returnFinProductType(dto.getSecid(),rpSecTypeID);
		
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
					issueInfoDao.updateEntity(oldfpd);
				}else{
					issueInfoDao.saveEntity(fpd);
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
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}

}
