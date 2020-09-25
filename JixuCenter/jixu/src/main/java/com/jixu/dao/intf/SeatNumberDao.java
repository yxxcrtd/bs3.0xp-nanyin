package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.SeatNumber;

/**
 * @ClassName 		SeatNumberDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface SeatNumberDao extends CommFuncDAO{
	
	public List<SeatNumber> loadSeatNumbersByBrokerID(@Param("brokerID")String brokerID);
	
	public boolean loadSeatNumberByCodeName(String brokerID,String acctNumber);
	
}
