package com.jixu.dao.intf;

import java.util.List;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Province;

/**
 * @ClassName 		ProvinceDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface ProvinceDao extends CommFuncDAO{
	
	/**
	 * 获取省列表
	 * @return
	 */
	public List<Province> getProvinceist();

}
