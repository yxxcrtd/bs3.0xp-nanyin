package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Broker;

/**
 * @ClassName 		BrokerDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BrokerDao extends CommFuncDAO{
	
	public Broker loadBroker(@Param("brokerCode")String brokerCode);
	
	/**
	 * 根据席位号获取券商
	 * @param seatNumber
	 * @return
	 */
	public Broker loadBrokerBySeatNumber(@Param("seatNumber")String seatNumber);
	
	/**
	 * 获取所有券商
	 * @return
	 */
	public List<Broker> loadBrokerList(@Param("countryID")String countryID);
	
	public List<Broker> loadAllBrokerList();
	
	/**
	 * 获取投资组合关联的券商
	 * @param portnum
	 * @return
	 */
	public List<Broker> loadBrokerListByPortnum(@Param("portnum")String portnum);
	
	/**
	 * 检查是否有相同code或者名称的券商已经存在
	 * @param code
	 * @param name
	 * @return
	 */
	public boolean checkBrokerCodeOrNameExist(String brokerCode, String brokerName);
	
	public Broker loadDefaultBroker();
	
}
