package com.jixu.dao.intf;

import java.util.List;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkInfo;

/**
 * @ClassName 		BkInfoDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkInfoDao extends CommFuncDAO{
	/**
	 * 获取银行列表
	 * @return
	 */
	public List<BkInfo> loadBankList();
	
	public BkInfo loadloadBkInfoIDByName(String name);
	
	public boolean checkBkInfoNameExist(String name);
}
