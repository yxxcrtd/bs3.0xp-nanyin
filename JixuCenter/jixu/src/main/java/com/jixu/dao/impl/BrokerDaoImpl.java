package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BrokerDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BkInfo;
import com.orm.obj.Broker;

/**
 * @ClassName BrokerDaoImpl
 * @author Pan Wei
 * @Date 2018年10月10日 下午23:29:03
 * @Description []
 */

@Repository("brokerDao")
public class BrokerDaoImpl extends CommFuncDAOImpl implements BrokerDao {
	//根据席位号获取券商
	@Override
	public Broker loadBrokerBySeatNumber(String seatNumber) {
		String hql = "select br from Broker br,SeatNumber sn where br.brokerID=sn.brokerID and sn.seatNumberAcct=:seatNumber";
		String[] params = { "seatNumber" };
		Object[] values = { seatNumber };
		return (Broker) this.executeHQLNo1Record(hql, params, values);
	}
	
	@Override
	public Broker loadBroker(String brokerCode) {
		return this.loadEntity(Broker.class,brokerCode);
	}
	
	@Override
	public List<Broker> loadBrokerList(String countryID) {
		String hql="from Broker where country=:countryID";
		String[] params={"countryID"};
		Object[] values={countryID};
		List<Broker> brokerList=this.executeHQL(hql, params, values);
		return brokerList;
	}
	
	@Override
	public List<Broker> loadBrokerListByPortnum(String portnum) {
		String hql="select bk FROM Broker bk, SecAcct sc where sc.portfolio=:portnum and sc.brokerCode=bk.brokerID";
		return this.executeHQL(hql, "portnum", portnum);
	}
	
	@Override
	public List<Broker> loadAllBrokerList() {
		String hql="from Broker";
		return this.executeHQL(hql);
	}
	
	@Override
	public boolean checkBrokerCodeOrNameExist(String brokerCode, String brokerName) {
		String hql="from Broker where brokerCode=:brokerCode or brokerName=:brokerName";
		String[] params={"brokerCode", "brokerName"};
		Object[] values={brokerCode, brokerName};
		List<Broker> bList=this.executeHQL(hql, params, values);
		if(bList.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Broker loadDefaultBroker(){
		String hql="from Broker";
		return (Broker) this.executeHQLNo1Record(hql);
	}
}
