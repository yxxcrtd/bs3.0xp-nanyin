package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.TranSubRed;

/**
 * @ClassName 		TranSubRedDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranSubRedDao extends CommFuncDAO{
	public List<TranSubRed> loadTranSubRedInClass(@Param("portList")List<String> portList, @Param("tradeDate") Date tradeDate);
	
	public List<TranSubRed> loadTranSubRed(@Param("portfolio")String portfolio, @Param("tradedate") Date tradedate);

	public void deleteTranSubRedAfterLedgerDate(String portnum, Date ledgerDate);
}
