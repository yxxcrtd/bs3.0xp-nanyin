package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.StandardVoucherDetails;

/**
 * @ClassName 		StandardVoucherDetailsDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface StandardVoucherDetailsDao extends CommFuncDAO{
	
	//查询标准凭证  并将查询结果存入到HashMap中
	public List<StandardVoucherDetails>  testCaseDailyVoucherDetails(@Param("portID")String[] portID, @Param("ledgerDate")Date ledgerDate);

}
