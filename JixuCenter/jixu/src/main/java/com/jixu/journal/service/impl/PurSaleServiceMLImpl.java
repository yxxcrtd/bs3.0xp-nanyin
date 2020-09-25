package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.common.func.CommFuncService;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.dao.intf.TranDao;
import com.jixu.journal.obj.TransactionWrapObj;
import com.jixu.journal.service.PurSaleService;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.Tran;
import com.orm.obj.TranBasic;

@Service("purSaleServiceML")
public class PurSaleServiceMLImpl implements PurSaleService {

	private static final Logger logger = LoggerFactory.getLogger(PurSaleServiceMLImpl.class);
	
	@Resource(name="commfuncService")
	private CommFuncService commfuncService;
	
	@Autowired
	private TranDao tranDao;
	
	@Autowired
	private TranBasicFeeDao tranBasicFeeDao;
	

	public List<TransactionWrapObj> loadTransData(Date tradedate,
			PortfolioHdr portfolio, String cusip) throws JNLException {
		List<TransactionWrapObj> transWrapList = new ArrayList<TransactionWrapObj>();
		TransactionWrapObj transWrap = null;

		Map<String, Double> ratesMap = new HashMap<String, Double>();

		try {
			List<Tran> transList = new ArrayList<Tran>();
			if (cusip.equals("")) {
				transList = tranDao.loadPurSaleTrans(tradedate, portfolio.getId());
			} else {
				transList = tranDao.loadPurSaleTransWithCusip(tradedate, portfolio.getId(), cusip);
			}

			Tran trandata = null;
			String tempCmpStr = null;
			Iterator<Tran> ittran = transList.iterator();
			String tanum = "";
			String currId= "";
			while (ittran.hasNext()) {
				trandata = ittran.next();
				
				
				if (tempCmpStr == null) {
					tempCmpStr = trandata.getTanum() + "," + trandata.getCusip();
					transWrap = new TransactionWrapObj();
					transWrap.getTrandataList().add(trandata);
					continue;
				} else if(tempCmpStr.equals(trandata.getTanum() + "," + trandata.getCusip())) {
					//如果是同一个tanum,cusip.放到同一个list
					
				} else if (tempCmpStr != null) {//另外一组cusip.
					transWrap.setTransFeeList(tranBasicFeeDao.loadTranBasicFee(tanum));
					
					if (ratesMap.containsKey(currId)) {
						transWrap.setFxrate(ratesMap.get(currId));
					} else {
						ratesMap.put(currId, commfuncService.loadFxrate(portfolio.getId(),portfolio.getCurID(),currId, tradedate));
						
					}
					transWrap.setFxrate(ratesMap.get(currId));
					
					
					transWrapList.add(transWrap);
					
					tempCmpStr = trandata.getTanum() + "," + trandata.getCusip();
					transWrap = new TransactionWrapObj();
					//transWrap.getTrandataList().add(trandata);
				} 
				transWrap.getTrandataList().add(trandata);
				tanum = trandata.getTanum().getId();
				currId = trandata.getCusip().getPriCur();
			}
			//处理最后一组
			if (transWrap != null && transWrap.getTrandataList().size() > 0) {
				transWrap.setTransFeeList(tranBasicFeeDao.loadTranBasicFee(tanum));
				transWrapList.add(transWrap);
			}

		} catch (Exception e) {
			logger.error("Error", e);
			throw new JNLException(e.toString());
		}

		return transWrapList;
	}
	
	@Override
	public List<TranBasic> loadTranIncDate(String portnum, Date tradeDate, String cusup) {
		// TODO Auto-generated method stub
		return null;
	}
}
