package com.jixu.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.jixu.dao.intf.SeatNumberDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.SeatNumber;

/**
 * @ClassName 		SeatNumberDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("seatNumberDao")
public class SeatNumberDaoImpl extends CommFuncDAOImpl implements SeatNumberDao{
	
	@Override
	public List<SeatNumber> loadSeatNumbersByBrokerID(String brokerID) {
		String hql="from SeatNumber where brokerID=:brokerID";
		return this.executeHQL(hql, "brokerID", brokerID);
	}

	@Override
	public boolean loadSeatNumberByCodeName(String brokerID,String acctNumber) {
		String hql="from SeatNumber where seatNumberAcct=:acctNumber and brokerID=:brokerID ";
		String[] params={"acctNumber","brokerID"};
		Object[] values={acctNumber,brokerID};
		List<SeatNumber> list=this.executeHQL(hql, params, values); 
		if(list.size()>0)
			return false;
		else
			return true;
	}

}
