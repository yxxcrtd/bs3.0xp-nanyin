package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.dao.intf.IssueInfoDao;
import com.jixu.dao.intf.TranBasicDao;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.journal.obj.TransactionWrapObj;
import com.jixu.journal.service.PurSaleService;
import com.orm.obj.IssueInfo;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;

@Service("purSaleService")
public class PurSaleServiceImpl implements PurSaleService {

	private static final Logger logger = LoggerFactory.getLogger(PurSaleServiceImpl.class);

	@Autowired
	private TranBasicDao tranBasicDao;
	
	@Autowired
	private TranBasicFeeDao tranBasicFeeDao;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private IssueInfoDao issueInfoDao;

	public List<TransactionWrapObj> loadTransData(Date ledgerDate, PortfolioHdr portfolio, String cusip) throws JNLException {
		List<TransactionWrapObj> transWrapList = new ArrayList<TransactionWrapObj>();
		TransactionWrapObj transWrap = null;

		try {
			//获取做账日当天的所有交易
			List<Tran> transList = new ArrayList<Tran>();
			String portnum=portfolio.getId();
			if (cusip ==null || cusip.equals("")) {
				transList = tranDao.loadPurSaleTrans(ledgerDate, portfolio.getId());
			} else {
				transList = tranDao.loadPurSaleTransWithCusip(ledgerDate, portfolio.getId(), cusip);
			}
			
			//获取所有的基础交易
			List<TranBasic> tranBasicList=null;
			Date ledgerDateTmp=ledgerDate;
			if(cusip==null || cusip.equals("")){
				tranBasicList=tranBasicDao.loadTranBasic(portnum, ledgerDateTmp);
			}else{
				tranBasicList=tranBasicDao.loadTranBasicByCusip(portnum, cusip, ledgerDateTmp);
			}
			
			//key是tanum
			HashMap<String, TranBasic> tranBasicHM=new HashMap<String, TranBasic>();
			for(TranBasic tranbasic:tranBasicList){
				tranBasicHM.put(tranbasic.getId(), tranbasic);
			}
			//tranBasicList.forEach(tranbasic->(tranBasicHM.put(tranbasic.getId(), tranbasic)));
			
			//获取当天所有交易的费用列表
			//TODO 暂时缺失为单个cusip查询当天交易的费用列表
			List<TranBasicFee> tranFeeList=tranBasicFeeDao.loadTranFeeList(portnum, ledgerDateTmp);
			HashMap<String, List<TranBasicFee>> tranFeeHM=new HashMap<String, List<TranBasicFee>>();
			//key是tanum
			for(TranBasicFee tranfee:tranFeeList){
				String tanum=tranfee.getTanum().getId();
				if(tranFeeHM.containsKey(tanum)){
					tranFeeHM.get(tanum).add(tranfee);
				}else{
					List<TranBasicFee> innerfeeList=new ArrayList<TranBasicFee>();
					innerfeeList.add(tranfee);
					tranFeeHM.put(tanum, innerfeeList);
				}
			}
			
			//按照lot，把交易分组放在map里，传给交易日记账。有可能有些交易会根据前后的交易出值，比如卖出交易
			HashMap<String, List<Tran>> tranMap=new HashMap<String, List<Tran>>();
			for(Tran tran:transList){
				String key=tran.getCusip().getId()+"-"+tran.getLotnum();
				if(!tranMap.containsKey(key)) {
					List<Tran> lotTranList=new ArrayList<Tran>();
					lotTranList.add(tran);
					tranMap.put(key, lotTranList);
				}else {
					tranMap.get(key).add(tran);
				}
			}
			
			for(Tran tran:transList){
				//利率互换一笔tranbasic生成两笔tran  会导致显示两笔同样的费用  
				IssueInfo sec=issueInfoDao.loadIssueByCusip(tran.getCusip().getId());
				
				String tanum=tran.getTanum().getId();
				transWrap = new TransactionWrapObj();
				transWrap.getTrandataList().add(tran);
				transWrap.setTranBasic(tranBasicHM.get(tanum));
				
				if((sec.getIssueType().equals(AssetTypes.SWAP_INTEREST) || sec.getIssueType().equals(AssetTypes.SWAP_FX)) && tran.getLsindicator()==LongShortType.ShortTerm) {
					transWrap.setTransFeeList(new ArrayList<TranBasicFee>());
				}else {
					if(tranFeeHM.containsKey(tanum)){
						transWrap.setTransFeeList(tranFeeHM.get(tanum));
					}else{
						transWrap.setTransFeeList(new ArrayList<TranBasicFee>());
					}
				}
				transWrap.setTodayTranList(tranMap.get(tran.getCusip().getId()+"-"+tran.getLotnum()));
				transWrapList.add(transWrap);
			}
		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}

		return transWrapList;
	}
	
	@Override
	public List<TranBasic> loadTranIncDate(String portnum, Date tradeDate, String cusip) {
		List<TranBasic> incList=new ArrayList<TranBasic>();
		if(cusip.equals("")){
			incList=tranBasicDao.loadTranBasic(portnum, tradeDate);
		}else{
			incList=tranBasicDao.loadTranBasicByCusip(portnum, cusip, tradeDate);
		}
		return incList;
	}
}
