package com.jixu.msg.service.config;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.dao.intf.PortExpenseCfgDao;
import com.jixu.dao.intf.PortfolioHdrDao;
import com.jixu.dao.intf.PortfolioInterfaceMapDao;
import com.jixu.dao.intf.SecAcctDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.Broker;
import com.orm.obj.PortExpenseCfg;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.PortfolioInterfaceMap;
import com.orm.obj.SecAcct;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class PortfolioCopyMsg extends MsgExecuteService{
	
	@Autowired
	private SecAcctDao secAcctDao;
	
	@Autowired
	private BkInfoDao bkInfoDao;
	
	@Autowired
	private PortExpenseCfgDao portExpenseCfgDao;
	
	@Autowired
	private PortfolioHdrDao portfolioHdrDao;
	
	@Autowired
	private PortfolioInterfaceMapDao portfolioInterfaceMapDao;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String portnum=getFieldValue(fieldsHM, MsgFields.portnum);
		PortfolioHdr souretPort=getPortfolio(portnum, msgcontainer);
		if(!msgcontainer.isCorrectFlag()){
			return;
		}
		
		String newPortname=getFieldValue(fieldsHM, "portname");
		boolean portexistFlag=portfolioHdrDao.checkSamePortNameExist(newPortname);
		if(portexistFlag){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorCode(ReturnResult.port_sameCode_exist);
			msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.port_sameCode_exist) + ": " + newPortname);
			return;
		}
		
		//投资组合复制
		PortfolioHdr newPortfolio=new PortfolioHdr();
		String[] ignores={"id","tranBasic","tran","tranSum","bkCashRcpies","vouchers","portExpenseCfg","bkCashAcct","interfaceMap"};
		BeanUtils.copyProperties(souretPort, newPortfolio, ignores);
		newPortfolio.setShortCode(newPortname);
		newPortfolio.setShortName(newPortname);
		newPortfolio.setPortName(newPortname);
		newPortfolio.setInceptionDate(souretPort.getLastGLClose());
		newPortfolio.setLastTranClose(souretPort.getLastGLClose());
		newPortfolio.setLastGLClose(souretPort.getLastGLClose());
		newPortfolio.setNextTranClose(souretPort.getLastGLClose());
		newPortfolio.setNextGLClose(souretPort.getLastGLClose());
		newPortfolio.setNextYealClose(souretPort.getNextYealClose());
		portfolioHdrDao.saveEntity(newPortfolio);
		
		//投资组合费用的复制
		List<PortExpenseCfg> expenseCfgList=portExpenseCfgDao.loadExpenseListByPortnum(portnum);
		String[] ignores1={"portFeeID","portfolio"};
		for(PortExpenseCfg expCfg:expenseCfgList){
			PortExpenseCfg newExp=new PortExpenseCfg();
			BeanUtils.copyProperties(expCfg, newExp,ignores1);
			newExp.setPortfolio(newPortfolio);
			portExpenseCfgDao.saveEntity(newExp);
		}
		
		//复制接口的
		List<PortfolioInterfaceMap> interfaceMapList=portfolioInterfaceMapDao.loadPortInterfaceMapListByPortNumExport(portnum);
		String[] ignores2={"id","portfolio"};
		for(PortfolioInterfaceMap intMap:interfaceMapList){
			PortfolioInterfaceMap newIntMap=new PortfolioInterfaceMap();
			BeanUtils.copyProperties(intMap, newIntMap, ignores2);
			newIntMap.setPortfolio(newPortfolio);
			portfolioInterfaceMapDao.saveEntity(newIntMap);
		}

		
		//复制证券账户
		SecAcct defaultSecAcct=secAcctDao.loadSecAcct(souretPort.getDefaultSecAcct());
		//获取新的券商, 提供新的券商的，用新券商，否则用复制的券商
		String brokerid=defaultSecAcct.getBrokerCode();
		if(fieldsHM.containsKey(MsgFields.broker)){
			brokerid=getFieldValue(fieldsHM, MsgFields.broker);
			getBroker(brokerid, msgcontainer);
			if(!msgcontainer.isCorrectFlag()){
				return;
			}
		}
		
		SecAcct newSecAcct=new SecAcct();
		String[] ignores3={"acctID","cashAccts","tranBasics"};
		BeanUtils.copyProperties(defaultSecAcct, newSecAcct, ignores3);
		newSecAcct.setAcctCode(newPortname);
		newSecAcct.setAcctDesc(newPortname);
		newSecAcct.setBrokerCode(brokerid);
		secAcctDao.saveEntity(newSecAcct);

		//复制银行账户
		BkCashAcct defaultCashAcct=bkCashAcctDao.loadBankCashAcct(defaultSecAcct.getDefaultCashAcct());
		BkInfo bkinfo=defaultCashAcct.getBkinfo();
		if(fieldsHM.containsKey("bank")){
			String bankID=getFieldValue(fieldsHM, "bank");
			bkinfo=bkInfoDao.loadEntity(BkInfo.class, bankID);
		}
		
		BkCashAcct newBkCashAcct=new BkCashAcct();
		String[] ignores4={"id","interestRcpies","bkCashFlows","bkCashRcpies","portfolio"};
		BeanUtils.copyProperties(defaultCashAcct, newBkCashAcct, ignores4);
		newBkCashAcct.setBankdesc(newPortname);
		newBkCashAcct.setPortfolio(newPortfolio);
		newBkCashAcct.setSecAcctCode(newSecAcct.getAcctCode());
		newBkCashAcct.setBkinfo(bkinfo);
		defaultCashAcct.setLastBalance(0);
		defaultCashAcct.setLastBalDate(souretPort.getLastGLClose());
		defaultCashAcct.setLastclose(defaultCashAcct.getLastBalDate());
		bkCashAcctDao.saveEntity(newBkCashAcct);
		
		newSecAcct.setDefaultCashAcct(newBkCashAcct.getId());
	}
	
	
	@Override
	public String messageForm() {
		return null;
	}
	
	@Override
	public String messageName() {
		return "复制产品";
	}
	
	@Override
	public String msgHeader() {
		return null;
	}
}
