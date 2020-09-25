package com.jixu.dao.intf;

import java.util.List;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BokBookSet;

/**
 * @ClassName 		BkInfoDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BokBookSetDao extends CommFuncDAO{
	
	/**
	 * 获取账套类型为01的所有账套
	 * @return
	 */
	public List<BokBookSet> loadBokBookSetTemplateList();
}
