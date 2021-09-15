package com.jixu.msg.service.market;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.types.PostStatusType;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.SecContractDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.secTran.SecurityCoreFactory;
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CoaaGroup;
import com.orm.obj.ExchangeCfg;
import com.orm.obj.FinProductType;
import com.orm.obj.IssueInfo;
import com.orm.obj.Issuer;
import com.orm.obj.RPSecType;
import com.orm.obj.SecContract;
import com.orm.obj.TranBasic;

/** 
 * @ClassName 		AssembleCreateSMF
 * @author  		Pan Wei 
 * @Date 			2017年6月26日 下午2:11:19 	
 * @Description		[]  
 */
@Service("MsgSMFOperation")
public class SMFOperation extends MsgExecuteService {
	
	@Autowired
	private SecContractDao secContractDao;
	
	private String header="smfOperation";
	
	@Override
	public String msgHeader() {
		return header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException{
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String actionType=getFieldValue(fieldsHM, MsgFields.operation);
		
		String cusip="";
		SecContract oldSc=null;
		if(fieldsHM.containsKey(MsgFields.cusip) && fieldsHM.get(MsgFields.cusip)!=null){
			cusip=getFieldValue(fieldsHM, MsgFields.cusip);
			oldSc=secContractDao.loadSecContract(cusip);
		}
		
		if(StringUtils.isEmpty(cusip) && actionType.equalsIgnoreCase(update) && JixuUserUtil.isJoyinTablesFlag()) {
			//投管传过来没有id，用secid去获取id, secid是固定不能更改的值
			String secid=getFieldValue(fieldsHM, MsgFields.secid);
			IssueInfo tmpIssue=issueInfoDao.loadSecurity(secid);
			cusip=tmpIssue.getId();
		}
		
		//TODO 直接写入 type1(01) type3(K01),上线时需要确认
		FinProductType fpd=null;
		IssueInfo oldIssue=null;
		//如果有cusip字段，删除特殊处理
		if(!cusip.equals("") && (actionType.equalsIgnoreCase(delete) ||actionType.equalsIgnoreCase(update))){
			oldIssue=issueInfoDao.loadIssueByCusip(cusip);
			if(oldIssue == null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,header,String.valueOf(cusip)));
			}else{
				if(oldIssue.getTranBasic().size()>0 && actionType.equalsIgnoreCase(delete)){
					//有交易数据就不能删除
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.smf_exist));
				}else if(actionType.equalsIgnoreCase(delete)){
					issueInfoDao.deleteIssue(oldIssue);
					
					if(JixuUserUtil.isJoyinTablesFlag() && commService.isJoyinSingleValSystem()) {
						//jixu证券主文件删除时  同时删除兆尹数据
						fpd=issueInfoDao.loadFinProductType(oldIssue.getSecurityid());
						if(fpd!=null){
							issueInfoDao.deleteEntity(fpd);
						}
					}
					
					issueInfoDao.deleteEntity(oldSc);
					
					IssueInfo deleteIssue=new IssueInfo();
					BeanUtils.copyProperties(oldIssue, deleteIssue);
					deleteIssue.setId(-1+deleteIssue.getId());
					outDataCenter.exePublicDataSender(deleteIssue);
					return;
				}
			}
			if(!msgcontainer.isCorrectFlag())
				return;
		}
		
		//SmfType	股票/债券等类型
		String smfType=getFieldValue(fieldsHM, MsgFields.issuetype);
//		if(!AssetTypes.getAllAssets(false).containsKey(smfType)){
		if(!SecurityCoreFactory.getAllAssetTypeHM().containsKey(smfType)){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error13,header,String.valueOf(smfType)));
			return;
		}
		
		Issuer issuer=null;
		String exchangeCode="";
		String countryID="";
		if(!smfType.equals(AssetTypes.SWAP_INTEREST)){
			if(fieldsHM.containsKey(MsgFields.issuer)){
				String issuerCode=getFieldValue(fieldsHM, MsgFields.issuer).split("\\|")[0];
				//如果有issuer字段，删除特殊处理
				issuer=issuerDao.loadIssuer(issuerCode);
				if(issuer==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,header,issuerCode));
					return;
				}
			}
			
			
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				//获取国家
				countryID=getFieldValue(fieldsHM, MsgFields.countryCode);
				getCountry(countryID, msgcontainer);
				if(!msgcontainer.isCorrectFlag()){
					return;
				}
			}else {
				countryID=getFieldValue(fieldsHM, MsgFields.countryCode);
			}
			
			//Exchange()
			//交易所
			if(fieldsHM.containsKey(MsgFields.exchangeCode)){
				exchangeCode=getFieldValue(fieldsHM, MsgFields.exchangeCode);
				ExchangeCfg exchange=getExchange(exchangeCode, msgcontainer);
				if(!msgcontainer.isCorrectFlag()){
					return;
				}
			}
		}
		
		//获取股票
		String secid=getFieldValue(fieldsHM, MsgFields.secid);
		//根据securityid获取证券主文件
		IssueInfo info=issueInfoDao.loadSecurity(secid);
		
		if(actionType.equalsIgnoreCase(update)){
			//查到的证券主文件和更新的证券主文件的cusip不一致   表示secid重复
			if(info!=null && !info.getId().equals(cusip)){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.smf_secid_exist));
				return;
			}
			//如果已经存在post的交易  则证券主ID不能被修改
			if(oldIssue != null && oldIssue.getTranBasic().size()>0 && info==null){
				List<String> list =new ArrayList<String>();
				Set<TranBasic> tlist=oldIssue.getTranBasic();
				for (TranBasic tranBasic : tlist) {
					list.add(tranBasic.getPostStatus());
				}
				
				if(list.contains(PostStatusType.post)){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.smf_exist_tran_post));
					return;
				}
			}
		//判断secid是否已经存在    无论是新建还是更新  证券主ID都不能重复
		}else if(actionType.equalsIgnoreCase(add) && info !=null){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.smf_secid_exist));
			return;
		}
		
		String securityname=getFieldValue(fieldsHM, MsgFields.securityName);
		
		//stockType 获取股票自定义类型	行业等信息
		int rpSecTypeID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.rpSecType));
		RPSecType rpsectype=getRPSecType(rpSecTypeID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//PriCur()
		String pricurNo="";
		if(fieldsHM.containsKey(MsgFields.priCurNo)){
			pricurNo=getFieldValue(fieldsHM, MsgFields.priCurNo);
			getCurrency(pricurNo, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
	
		String pricurNo2="";
		if(fieldsHM.containsKey(MsgFields.priCurNo2) && fieldsHM.get(MsgFields.priCurNo2)!=null){
			pricurNo2=getFieldValue(fieldsHM, MsgFields.priCurNo2);
			getCurrency(pricurNo2, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
				
		//IncomeCur()
		String incomeCurNo=getFieldValue(fieldsHM, MsgFields.incomeCurNo);
		getCurrency(incomeCurNo, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//IncomeCur()
		String incomeCurNo2="";
		if(fieldsHM.containsKey(MsgFields.incomeCurNo2) && fieldsHM.get(MsgFields.incomeCurNo2)!=null){
			incomeCurNo2=getFieldValue(fieldsHM, MsgFields.incomeCurNo2);
			getCurrency(incomeCurNo2, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
		Date secExpireDate=DateUtil.convertStr2Date2("2999-12-31");
		if(fieldsHM.containsKey(MsgFields.secExpireDate) && fieldsHM.get(MsgFields.secExpireDate)!=null){
			secExpireDate=DateUtil.convertStr2Date2(getFieldValue(fieldsHM, MsgFields.secExpireDate));
		}

		double secContractSize=1;
		if(fieldsHM.containsKey(MsgFields.secContractSize) && fieldsHM.get(MsgFields.secContractSize)!=null){
			secContractSize=Double.valueOf(getFieldValue(fieldsHM, MsgFields.secContractSize));
		}
		
		//COAGROUP
		int coaGroupID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.glgroup));
		CoaaGroup coaaGroup=getCoaaGroup(coaGroupID, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		//Source() 
		//获取数据源
		int sourceID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.sourceID));
//		InterfaceSource interfaceSource=getInterfaceSource(sourceID, msgcontainer);
//		if(!msgcontainer.isCorrectFlag()){
//			return;
//		}
		
		//债券 认购认沽
		int optionType=0;
		if(fieldsHM.containsKey(MsgFields.optionType) && fieldsHM.get(MsgFields.optionType)!=null){
			optionType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.optionType));
		}
		
		String underCusip="";
		if(fieldsHM.containsKey(MsgFields.underCusip) && fieldsHM.get(MsgFields.underCusip)!=null){
			String underSecidStr=getFieldValue(fieldsHM, MsgFields.underCusip);
			String[] underSecids=underSecidStr.split("\\|");
			String underSecid="";
			if(underSecids.length==3){
				underSecid=underSecids[1];
			}else{
				underSecid=underSecids[0];
			}
			IssueInfo underSec=getSecurity(underSecid, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}else{
				underCusip=underSec.getId();
			}
		}
		
		
		CashFlowCfg cashcfg=null;
		if(fieldsHM.containsKey(MsgFields.cashflowcfg) && fieldsHM.get(MsgFields.cashflowcfg)!=null){
			String cashflowcfgCode=getFieldValue(fieldsHM, MsgFields.cashflowcfg);
			cashcfg=getCashFlowCfgo(cashflowcfgCode, msgcontainer);
		}
		
		//互换用的空方现金流配置
		CashFlowCfg cashcfg2=null;
		if(fieldsHM.containsKey(MsgFields.cashflowcfg2) && fieldsHM.get(MsgFields.cashflowcfg2)!=null){
			String cashflowcfgCode2=getFieldValue(fieldsHM, MsgFields.cashflowcfg2);
			cashcfg2=getCashFlowCfgo(cashflowcfgCode2, msgcontainer);
		}
		
		String activeDateStr="20000101";
		if(fieldsHM.containsKey(MsgFields.activeDate)){
			activeDateStr=getFieldValue(fieldsHM, MsgFields.activeDate).replaceAll("-", "");
			activeDateStr=activeDateStr.replace("/", "");
		}
		
		checkDateFormat(msgcontainer,msgHeader(),activeDateStr);
		if(!msgcontainer.isCorrectFlag())
			return;
		Date activeDate=DateUtil.convertStr2Date(activeDateStr);
		
		//固收类型
		int fixIncomeType=0;
		if(fieldsHM.get(MsgFields.fixIncomeType)!=null && !getFieldValue(fieldsHM, MsgFields.fixIncomeType).equals("")){
			fixIncomeType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.fixIncomeType));
		}
		//发行价
		double startPrice=0;
		if(fieldsHM.get(MsgFields.startPrice)!=null && !getFieldValue(fieldsHM, MsgFields.startPrice).equals("")){
			startPrice=Double.valueOf(getFieldValue(fieldsHM, MsgFields.startPrice));
		}
		//到期价
		double endPrice=0;
		if(fieldsHM.get(MsgFields.endPrice)!=null && !getFieldValue(fieldsHM, MsgFields.endPrice).equals("")){
			endPrice=Double.valueOf(getFieldValue(fieldsHM, MsgFields.endPrice));
		}
		
		int swapExchangeInd=0;
		if(fieldsHM.containsKey(MsgFields.swapExchangeInd) && fieldsHM.get(MsgFields.swapExchangeInd)!=null){
			swapExchangeInd=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.swapExchangeInd));
		}
		
		//默认人民币
		String longCurID="";
		if(fieldsHM.get(MsgFields.longCurID)!=null && !getFieldValue(fieldsHM, MsgFields.longCurID).equals("")){
			longCurID=getFieldValue(fieldsHM, MsgFields.longCurID);
		}
		double longCash=0;
		if(fieldsHM.get(MsgFields.longCash)!=null && !getFieldValue(fieldsHM, MsgFields.longCash).equals("")){
			longCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.longCash));
		}
		String shortCurID="";
		if(fieldsHM.get(MsgFields.shortCurID)!=null && !getFieldValue(fieldsHM, MsgFields.shortCurID).equals("")){
			shortCurID=getFieldValue(fieldsHM, MsgFields.shortCurID);
		}
		double shortCash=0;
		if(fieldsHM.get(MsgFields.shortCash)!=null && !getFieldValue(fieldsHM, MsgFields.shortCash).equals("")){
			shortCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.shortCash));
		}
		
		if(fieldsHM.containsKey(MsgFields.productType)){
			//银行资管的smf中，用该字段借用做区分净值性产品还是固定收益型产品
			optionType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.productType));
		}
		
		int futuresType=0;
		if(fieldsHM.get(MsgFields.futuresType)!=null && !getFieldValue(fieldsHM, MsgFields.futuresType).equals("")){
			futuresType=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.futuresType));
		}
		int futuresTarget=0;
		if(fieldsHM.get(MsgFields.futuresTarget)!=null && !getFieldValue(fieldsHM, MsgFields.futuresTarget).equals("")){
			futuresTarget=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.futuresTarget));
		}
		double marginRate=0;
		if(fieldsHM.get(MsgFields.marginRate)!=null && !getFieldValue(fieldsHM, MsgFields.marginRate).equals("")){
			marginRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.marginRate));
		}
		
		IssueInfo sec=new IssueInfo();
		sec.setSecurityid(secid);
		sec.setSecurityname(securityname);
		if(issuer==null)
			sec.setIssuer(null);
		else
			sec.setIssuer(issuer.getIssuerID());
		sec.setIssueType(smfType);
		sec.setRpSecType(rpsectype);
		if(!pricurNo.equals("")){
			sec.setPriCur(pricurNo);
		}else{
			sec.setPriCur(longCurID);
		}
		sec.setIncomeCur(incomeCurNo);
		
		if(!pricurNo2.equals("")){
			sec.setPriCur(pricurNo2);
		}
		
		sec.setIncomeCur2(incomeCurNo2);
		sec.setExchangeCode(exchangeCode);
		sec.setCoaaGroup(coaaGroup.getGroupNum());
		sec.setInterfaceSourceID(sourceID);
		sec.setCountry(countryID);
		sec.setFixIncomeType(fixIncomeType);
		sec.setStartPrice(startPrice);
		sec.setEndPrice(endPrice);
		sec.setSwapExchangeInd(swapExchangeInd);
		if(cashcfg==null){
			sec.setCashFlowCfgID("0");
		}else{
			sec.setCashFlowCfgID(cashcfg.getCashFlowID());
		}
		if(cashcfg2==null){
			sec.setCashFlowCfgID2("0");
		}else{
			sec.setCashFlowCfgID2(cashcfg2.getCashFlowID());
		}
		sec.setExpireDate(secExpireDate);
		sec.setContractSize(secContractSize);
		sec.setOptionType(optionType);
		sec.setUnderCusip(underCusip);
		sec.setActiveDate(activeDate);
		
		//smf辅助表
		SecContract sc=new SecContract();
		sc.setLongCurID(longCurID);
		sc.setLongCash(longCash);
		sc.setShortCurID(shortCurID);
		sc.setShortCash(shortCash);
		sc.setFuturesType(futuresType);
		sc.setFuturesTarget(futuresTarget);
		sc.setMarginRate(marginRate);
		
		//金融产品表
		fpd=returnFinProductType(sec.getSecurityid(),rpSecTypeID);
		
		if(actionType.equalsIgnoreCase(add)){
			issueInfoDao.saveSecurity(sec);
			msgcontainer.setEntityID(sec.getId());
			msgcontainer.setEntityObj(sec);
			sc.setCusip(sec.getId());
			secContractDao.saveEntity(sc);
			
			if(JixuUserUtil.isJoyinTablesFlag() && commService.isJoyinSingleValSystem()) {
				//如果FinProductType表中存在数据则更新
				FinProductType oldfpd=issueInfoDao.loadFinProductType(sec.getSecurityid());
				if(oldfpd!=null){
					BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
					issueInfoDao.updateEntity(oldfpd);
				}else{
					issueInfoDao.saveEntity(fpd);
				}
			}
			
			outDataCenter.exePublicDataSender(sec);
		}else if(actionType.equalsIgnoreCase(update)){
			BeanUtils.copyProperties(sec, oldIssue, "id");
			issueInfoDao.updateIssue(oldIssue);
			if (oldIssue != null){
				msgcontainer.setEntityID(oldIssue.getId());
			}
			//smf辅助表
			if(oldSc!=null && oldIssue != null){
				sc.setCusip(oldIssue.getId());
				BeanUtils.copyProperties(sc, oldSc, "id");
				issueInfoDao.updateEntity(oldSc);
			}
			if(JixuUserUtil.isJoyinTablesFlag() && fpd!=null && oldIssue != null && commService.isJoyinSingleValSystem()){
				FinProductType oldfpd=issueInfoDao.loadFinProductType(oldIssue.getSecurityid());
				if(oldfpd!=null){
					BeanUtils.copyProperties(fpd,oldfpd,new String[]{"finprod_id","createDate","createUser"});
					issueInfoDao.updateEntity(oldfpd);
				}else{
					issueInfoDao.saveEntity(fpd);
				}
			}
			outDataCenter.exePublicDataSender(oldIssue);
		}else{
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error8,header,actionType));
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "证券主文件交易";
	}

}
