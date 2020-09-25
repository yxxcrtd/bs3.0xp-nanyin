package com.jixu.dao.intf;


import java.util.List;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BokSubject;

/**
 * @ClassName 		BkInfoDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BokSubjectDao extends CommFuncDAO{
	
	/**
	 * 获取账套模板的科目
	 * @param bookSetID
	 * @return
	 */
	public List<BokSubject> loadBokSubjectBySetID(String bookSetID);
	
	/**
	 * 获取账套模板的科目
	 * @param bookSetID
	 * @return
	 */
	public List<BokSubject> loadBokSubjectLevel3BySetID(String bookSetID);
	
	public List<BokSubject> loadBokSubjectsByPortnum(String portnum);
}
