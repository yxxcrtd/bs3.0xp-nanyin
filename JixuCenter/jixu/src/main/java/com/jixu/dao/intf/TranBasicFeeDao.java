package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.TranBasicFee;

/**
 * @ClassName 		TranBasicFeeDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranBasicFeeDao extends CommFuncDAO{
	
	public List<TranBasicFee> loadTranBasicFee(@Param("tanum")String tanum);
	
	/**
	 * 获取正常的交易费用，当天和证券清算款一起结转的。还有一种是以后每天计费的，按照比率或者固定费用计费
	 * @param portnum
	 * @param ledgerDate
	 * @return
	 */
	public List<TranBasicFee> loadTranFeeList(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate);
	
	public List<TranBasicFee> loadSubRedTranFeeList(@Param("portnum")String portnum, @Param("ledgerDate") Date ledgerDate);
}
