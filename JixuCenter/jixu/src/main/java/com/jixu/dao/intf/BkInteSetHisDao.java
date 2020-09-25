package com.jixu.dao.intf;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkInteSetHis;

/**
 * @ClassName 		BkInteSetHisDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkInteSetHisDao extends CommFuncDAO{
	
	public BkInteSetHis loadLatestInterestSet(@Param("portnum")String portnum, @Param("bankID") int cashacctid);
	
	public void deleteBkInteSetHisByTanum(String tanum);
}
