package com.jixu.dao.intf;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.type.PortTranMapType;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.SecAccountModel;
import com.orm.obj.SecAcct;

/**
 * @ClassName 		SecAcctDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface SecAcctDao extends CommFuncDAO{

	//根据证券机构代码获取证券账户
	public SecAcct loadSecAcctBySeatNum(@Param("seatNumber")String seatNumber);
	
	/**
	 * 更具证券账户ID获取证券账户
	 * @param secacctNo	证券账户ID
	 * @return	证券账户实例
	 */
	@Cacheable(value="portDataCache", key="'SecAcct'+#secacctNo")
	public SecAcct loadSecAcct(@Param("secacctNo")String secacctNo);
	
	/**
	 * 从joyin表获取证券账户对应的现金账户
	 * @param secacct
	 * @return
	 */
	public int loadDefaultCashAcctNameBySecAcctFromJoyin(String portnum, String secacct);

	public SecAcct loadSecAcctByAcctCode(@Param("secacct")String secacct);
	
	public List<PortTranMapType> getPortTranMapTypeList(@Param("portnum")String portnum);
	
	public List<SecAccountModel> loadSecAcctList(@Param("brokerCode")String brokerCode);
	
	@CacheEvict(value="portDataCache", key="'SecAcct'+#secacct.getAcctID()")
	public void updateSecacct(@Param("secacct")SecAcct secacct);
	
	public List<SecAccountModel> loadSecAcctByPortnum(@Param("portNum")String portNum);
	
	/**
	 * 从joyin表获取证券账户
	 * @param portnum
	 * @return
	 */
	public HashMap<String, String> loadSecAcctByPortnumFromJoyin(String portnum);
	
	public boolean checkSecAcctNameExist(@Param("secAcctName")String secAcctName, @Param("portnum") String portnum);

	/**
	 * 根据投资组合代码查询对应的证券账户
	 * @param portnum
	 * @return
	 */
	public List<SecAcct> loadSecAcctsByPortnum(String portnum);
}
