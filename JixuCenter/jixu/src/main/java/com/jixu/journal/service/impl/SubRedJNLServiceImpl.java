package com.jixu.journal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.JNLException;
import com.jixu.dao.intf.TranBasicFeeDao;
import com.jixu.dao.intf.TranSubRedDao;
import com.jixu.journal.obj.SubRedWrapObj;
import com.jixu.journal.service.SubRedJNLService;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranSubRed;

@Service("subredJNLService")
public class SubRedJNLServiceImpl implements SubRedJNLService{
	
	@Autowired
	private TranSubRedDao tranSubRedDao;
	
	@Autowired
	private TranBasicFeeDao tranBasicFeeDao;
	
	public List<SubRedWrapObj> loadSubReadTran(Date tradedate, String portfolio) throws JNLException {
		List<SubRedWrapObj>  subRedWrapObjList = new ArrayList<SubRedWrapObj>();
		SubRedWrapObj subredWrapObj=null;
		
		try {
			List<TranSubRed> tranSubRedList = tranSubRedDao.loadTranSubRed(portfolio, tradedate);
			
			HashMap<String, List<TranBasicFee>> feeMap=new HashMap<String, List<TranBasicFee>>();
			List<TranBasicFee> feeList=tranBasicFeeDao.loadSubRedTranFeeList(portfolio, tradedate);
			for(TranBasicFee fee:feeList){
				String key=fee.getTanum().getId();
				if(feeMap.containsKey(key)){
					feeMap.get(key).add(fee);
				}else{
					List<TranBasicFee> tanumFeeList=new ArrayList<TranBasicFee>();
					tanumFeeList.add(fee);
					feeMap.put(key, tanumFeeList);
				}
			}
			for(TranSubRed tranSubRed:tranSubRedList){
				subredWrapObj=new SubRedWrapObj();
				List<TranBasicFee> tranFeeList=new ArrayList<TranBasicFee>();
				if(feeMap.containsKey(tranSubRed.getTanum().getId())){
					tranFeeList=feeMap.get(tranSubRed.getTanum().getId());
				}
				subredWrapObj.setTranSubRed(tranSubRed);
				subredWrapObj.setTranFeeList(tranFeeList);
				
				subRedWrapObjList.add(subredWrapObj);
			}

		} catch (Exception e) {
			throw new JNLException(e.toString());
		}

		return subRedWrapObjList;
	}
}
