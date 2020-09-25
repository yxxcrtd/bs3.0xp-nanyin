package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.TranCollateral;

/**
 * @ClassName 		TranCollateralDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranCollateralDao extends CommFuncDAO{
	
	public List<TranCollateral> loadTranCollateralList(@Param("tanum")String tanum);

}
