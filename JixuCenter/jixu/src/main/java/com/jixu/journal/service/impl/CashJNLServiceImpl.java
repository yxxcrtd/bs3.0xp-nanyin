package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.dao.intf.BkCashFlowDao;
import com.jixu.journal.obj.CashFlowObj;
import com.jixu.journal.obj.CashFlowWrapObj;
import com.jixu.journal.service.CashJNLService;
import com.orm.obj.BkCashFlow;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranBasic;

@Service("cashjnlService")
public class CashJNLServiceImpl implements CashJNLService {

	private static final Logger logger = LoggerFactory.getLogger(CashJNLServiceImpl.class);
	
	@Autowired
	private BkCashFlowDao bkCashFlowDao;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	
	public CashFlowWrapObj loadCashFlowWrap(PortfolioHdr portfolio, Date date) throws JNLException {
		CashFlowWrapObj cashflowWrapObj = new CashFlowWrapObj();
		HashMap<TranBasic, List<CashFlowObj>> cashFlowHM=new HashMap<TranBasic, List<CashFlowObj>>();
		
		try {
			Map<String, Double> rateMap = new HashMap<String, Double>();
			Map<String, Double> bkavgrateMap = new HashMap<String, Double>();
			
			List<BkCashFlow> cashFlowList = bkCashFlowDao.loadCashFlowRecords(date, portfolio.getId());
			
			if(cashFlowList.size()>0){
				cashflowWrapObj.setPortfolioHdr(portfolio);
				
				for (BkCashFlow cashObj : cashFlowList) {
					CashFlowObj cashflowObj = new CashFlowObj();
					cashflowObj.setOrigcash(cashObj.getOrigbal());	//存本币
					cashflowObj.setCash(cashObj.getFxorigbal());	//存原币
					cashflowObj.setTranType(cashObj.getRcpyid().getTrantype());
					cashflowObj.setPostType(cashObj.getRcpyid().getPosttype());
					
					if (cashObj.getCusip() != null) {
						cashflowObj.setCusip(cashObj.getCusip());
					}
					cashflowObj.setBkCashRcpy(cashObj.getRcpyid());
					cashflowObj.setBkCashAcct(cashObj.getBkCashAcct());
					cashflowObj.setDbcr(cashObj.getDbcr());
					
					if (rateMap.containsKey(cashObj.getFxcurrency())) {
						cashflowObj.setAsofdayRate(rateMap.get(cashObj.getFxcurrency()));
					} else {
						cashflowObj.setAsofdayRate(commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), cashObj.getFxcurrency(), date));
						rateMap.put(cashObj.getFxcurrency(), cashflowObj.getAsofdayRate());
					}
					
					//!!!!!目前的平均汇率,就是取银行当天汇率
					if (bkavgrateMap.containsKey(cashObj.getFxcurrency())) {
						cashflowObj.setBkavgrate(bkavgrateMap.get(cashObj.getFxcurrency()));
					} else {
						cashflowObj.setBkavgrate(commService.loadFxrate(portfolio.getId(),portfolio.getCurID(), cashObj.getFxcurrency(), date));
						bkavgrateMap.put(cashObj.getFxcurrency(), cashflowObj.getAsofdayRate());
					}
					
					TranBasic tranBasic=cashObj.getRcpyid().getTanum();
					
					if(cashFlowHM.containsKey(tranBasic)){
						//目前,如果是现金划转,两笔现金划转会匹配同一个交易编号
						cashFlowHM.get(tranBasic).add(cashflowObj);
					}else{
						List<CashFlowObj> cashflowObjList=new ArrayList<CashFlowObj>();
						cashflowObjList.add(cashflowObj);
						cashFlowHM.put(tranBasic, cashflowObjList);
					}
				}
				cashflowWrapObj.setCashFlowHM(cashFlowHM );
			}
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e);
		}
		return cashflowWrapObj;
	}

}
