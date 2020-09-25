package com.jixu.dao.intf;

import java.util.List;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Coaahdr;

/**
 * @ClassName 		CoaahdrDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CoaahdrDao extends CommFuncDAO{
	
	/**
	 * 获取所有的会计科目表
	 * @return
	 */
	public List<Coaahdr> loadCoaahdr();

}
