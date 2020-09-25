package com.jixu.msg.service.trade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.types.ValuationType;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityBasic;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.BkCashAcct;
import com.orm.obj.Broker;
import com.orm.obj.IssueInfo;
import com.orm.obj.Managers;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.RPSecType;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

/** 
 * @ClassName 		AssembleFxOpenTran
 * @author  		Pan Wei 
 * @Date 			2016年10月3日 下午10:25:09 	
 * @Description		[]  
 */

@Service
public class AssembleForwardCurrencyOpen extends MsgExecuteService{
	
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
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		Object tanumStr=fieldsHM.get(MsgFields.tanum);
		
		msgcontainer=checkTanum(operation, tanumStr, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tanum=msgcontainer.getEntityID();
		TranBasic oldTran=(TranBasic)msgcontainer.getEntityObj();
		
		if(operation.equalsIgnoreCase(delete)){
			return;
		}
		
		String[] secids=getFieldValue(fieldsHM, MsgFields.secid).split("\\|");
		String secid="";
		if(secids.length==3){
			secid=secids[1];
		}else{
			secid=secids[0];
		}
		
		IssueInfo oldii=null;
		if(operation.equalsIgnoreCase(update)){
			oldii=getSecurity(secid, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
		
		String contract=getFieldValue(fieldsHM, MsgFields.contract);
		
		int rpSecTypeID=Integer.valueOf(getFieldValue(fieldsHM, "rpSecType"));
		RPSecType rpsectype=getRPSecType(rpSecTypeID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr portfolio=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.manager));
		Managers managers=getManagers(portfolio.getManagerGroup(), managerID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String tradeDateStr=getFieldValue(fieldsHM, MsgFields.tradeDate).replaceAll("-", "");
		Date tradeDate=checkDateFormat(msgcontainer,msgHeader(),tradeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		Date settleDate=checkDateFormat(msgcontainer,msgHeader(),settleDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		int cashacct2=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct2));
		BkCashAcct shortCashacct=getCashAcct(cashacct2, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		double shortcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash2));
		
		int cashacct=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.bankAcct));
		BkCashAcct longCashacct=getCashAcct(cashacct, portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		double longcash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.tradeCash));
		
		String brokerid=getFieldValue(fieldsHM, MsgFields.broker);
		Broker broker=getBroker(brokerid, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		int glgroup=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.glgroup));
		
		int valuationMethod=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.valuationMethod));//估值方法
		
		int valuationSide=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.valuationSide));//估值方向
		
		double longFxRate=Double.valueOf(getFieldValue(fieldsHM, "longFX"));
		double shortFxRate=Double.valueOf(getFieldValue(fieldsHM, "shortFX"));
		
		//获取数据源
//		int sourceID = Integer.valueOf(getFieldValue(fieldsHM, MsgFields.sourceID));
//		InterfaceSource interfaceSource = getInterfaceSource(sourceID, msgcontainer);
//		if (!msgcontainer.isCorrectFlag()) {
//			return;
//		}
		
		List<TranBasicFee> feeList=genTranBasicFee(msgcontainer, fieldsHM);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String postStatus=PostStatusType.unpost;
		if(fieldsHM.containsKey("postStatus")){
			postStatus=String.valueOf(getFieldValue(fieldsHM, "postStatus"));;
		}
		
		IssueInfo ii=new IssueInfo();
		ii.setSecurityid(secid);
		ii.setSecurityname(contract);
		ii.setIssueType(AssetTypes.FORWARD_CURRENCY);
		ii.setPriCur(shortCashacct.getFxcurrency());
		ii.setIncomeCur(longCashacct.getFxcurrency());
		ii.setIncomeCur2(shortCashacct.getFxcurrency());
		ii.setCoaaGroup(glgroup);
		ii.setRpSecType(rpsectype);
		ii.setActiveDate(new Date());
		ii.setInterfaceSourceID(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.sourceID)));
		//估值方向暂时存入到这里
		ii.setSwapExchangeInd(valuationSide);
		
		TranBasic tran=new TranBasic();
		tran.setTrantype(secBasic.tranType());
		tran.setPortfolio(portfolio.getId());
		tran.setTradedate(tradeDate);
		tran.setSettledate(settleDate);
		tran.setLedgerdate(getLedgerDate(tradeDate, settleDate, portfolio));
		tran.setBroker(brokerid);
		tran.setManager(managers.getId());
		tran.setPostStatus(postStatus);
		tran.setSettleStatus(PostStatusType.unpost);
		tran.setValuationType(valuationMethod);//估值方法
		
		
		tran.setAmount(longcash);
		tran.setCash(longcash);
		tran.setBkCashacctNo(longCashacct);
		tran.setFxcurrency(longCashacct.getFxcurrency());
		tran.setFxrate(longFxRate);
		tran.setAmount2(shortcash);
		tran.setCash2(shortcash);
		tran.setBkCashacctNo2(shortCashacct);
		tran.setFxcurrency2(shortCashacct.getFxcurrency());
		tran.setFxrate2(shortFxRate);
		//按照空方估值
		if(valuationSide==ValuationType.VALSIDE_SHORT){
			tran.setAmount2(-1*shortcash);
			tran.setCash2(-1*shortcash);
		}
		tran.setGlGroup(glgroup);
		tran.setTranDesc(SecurityCoreFactory.getAllTranTypeHM().get(tran.getTrantype()));
		
		
		if(operation.equalsIgnoreCase(add)){
			if(!tanum.equals("")){
				tran.setId(tanum);
			}
			//先保存证券主文件  然后存入到交易中
			tranBasicDao.saveEntity(ii);
			tran.setCusip(ii);
			tran.setImportType(msgcontainer.getTradeImportType());
			tranBasicDao.saveEntity(tran);
			for(TranBasicFee fee:feeList){
				fee.setTanum(tran);
				tranBasicFeeDao.saveEntity(fee);
			}
			msgcontainer.setEntityID(tran.getId());
			msgcontainer.setEntityObj(tran);
		}else if(operation.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(ii, oldii, new String[]{"id"});
			tranBasicDao.updateEntity(oldii);
			tran.setCusip(oldii);
			BeanUtils.copyProperties(tran, oldTran, new String[]{"id"});
			tranBasicDao.updateEntity(oldTran);
			
			for(TranBasicFee fee:feeList){
				fee.setTanum(oldTran);
				tranBasicFeeDao.saveEntity(fee);
			}
			msgcontainer.setEntityID(oldTran.getId());
			msgcontainer.setEntityObj(oldTran);
		}
		msgcontainer.setPortid(portfolio.getId());
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
