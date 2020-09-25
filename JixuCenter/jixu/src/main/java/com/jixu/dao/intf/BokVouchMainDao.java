package com.jixu.dao.intf;

import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		BkInfoDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BokVouchMainDao extends CommFuncDAO{
	/**
	 * 获取下一个seq
	 * @return
	 */
	public long getNextSequence();
}
