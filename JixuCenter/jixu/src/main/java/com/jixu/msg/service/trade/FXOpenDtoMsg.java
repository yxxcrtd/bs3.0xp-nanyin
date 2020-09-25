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
import com.jixu.common.types.JixuDictCode;
import com.jixu.common.types.PostStatusType;
import com.jixu.msg.dto.FXOpenDto;
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
 * @ClassName 		AssembleStockTran
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 上午9:59:04 	
 * @Description		[]
 * @Msg				
 */

@Service("jxFXOpenDtoMsg")
public class FXOpenDtoMsg extends MsgTranBaseExecuteService<FXOpenDto>{
	
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
		FXOpenDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), FXOpenDto.class);
		//获得交易类型 add/update/delete
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
		
		validateDto(msgcontainer, dto);
		//dto验证结果判断
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//交易如期
		checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//结算如期
		checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//获取现金账户，如果没有提供，则用默认现金账户
		BkCashAcct bkcashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}

		BkCashAcct bkcashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//产品/投资组合
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
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
		
		String curcode=bkcashacct.getFxcurrency();
		String curcode2=bkcashacct2.getFxcurrency();
		CoaaGroup group=coaaGroupDao.loadFxOpenGlGroup(curcode2, curcode);
		if(group==null) {
			msgcontainer.setErrorMsg(false, "尚未配置相关总账栏: " + curcode2+"/"+curcode);
			return;
		}
		dto.setGlgroup(group.getGroupNum());
		
		
		if(msgcontainer.isCorrectFlag()) {
			msgcontainer.setEntityObj(dto);
		}
		msgcontainer.setPortid(portfolio.getId());
	}
	
	@Override
	public TranBasic dto2TranBasic(FXOpenDto dto, MsgContainer msgcontainer) {
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),dto.getTradeDate());
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),dto.getSettleDate());
		PortfolioHdr portfolio=getPortfolio(dto.getPort(), msgcontainer);
		
		Date ledgerDate=getLedgerDate(tradeDate, settleDate, portfolio);
		BkCashAcct cashacct=getCashAcct(dto.getBankAcct(), dto.getPort(), msgcontainer);
		BkCashAcct cashacct2=getCashAcct(dto.getBankAcct2(), dto.getPort(), msgcontainer);
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(Integer.valueOf(dto.getTradeType()));
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(ledgerDate);
		tran.setBkCashacctNo(cashacct);
		tran.setFxcurrency(cashacct.getFxcurrency());
		tran.setAmount(Double.parseDouble(dto.getTradeCash()));
		tran.setCash(tran.getAmount());
		tran.setBkCashacctNo2(cashacct2);
		tran.setFxcurrency2(cashacct2.getFxcurrency());
		tran.setAmount2(Double.parseDouble(dto.getTradeCash2()));
		tran.setCash2(tran.getAmount2());
		tran.setBroker(JixuDictCode.DEFAULT_BROKER_CODE);
		tran.setPostStatus(PostStatusType.unpost);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setGlGroup(dto.getGlgroup());
		
		double shortFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), cashacct2.getFxcurrency(), tradeDate);
		double longFxRate=commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), cashacct.getFxcurrency(), tradeDate);
		tran.setFxrate(longFxRate);
		tran.setFxrate2(shortFxRate);
		
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		return tran;
	}
	
	@Override
	protected MsgContainer completeTranEntity(FXOpenDto dto, TranBasic tran, TranBasic oldTran, MsgContainer msgcontainer) {
		//获取操作类型，新建、更新、删除
		String operation=dto.getOperation();
		String tanum=dto.getId();
		List<TranBasicFee> feeList=genTranBasicFeeDto(msgcontainer, dto.getPort(), dto.getExpenses());

		PortfolioHdr portfolio=commService.loadPortfolio(tran.getPortfolio());
		
		//利率互换，每个合同创建一个证券主文件
		IssueInfo ii=new IssueInfo();
		ii.setSecurityid(dto.getSecid());
		ii.setIssueType(dto.getAssetType());
		ii.setIncomeCur(portfolio.getCurID());
		ii.setIncomeCur2(portfolio.getCurID());
		ii.setCoaaGroup(dto.getGlgroup());
		ii.setRpSecType(new RPSecType(JixuDictCode.DEFAULT_RPSECTYPE_FXSPOT));
		ii.setActiveDate(new Date());

		//InterfaceSource interfaceSource=getInterfaceSource(0, msgcontainer);
		ii.setInterfaceSourceID(0);
		ii.setPriCur(portfolio.getCurID());
		ii.setSecurityname(dto.getSecid());		
		
		//TODO 新建的证券主文件入兆尹金融产品类型表作记录,上线时需要确认
		FinProductType fpd=returnFinProductType(dto.getSecid(),JixuDictCode.DEFAULT_RPSECTYPE_FXSPOT);
		
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
	public String messageName() {
		return "即期换汇开仓";
	}
}
