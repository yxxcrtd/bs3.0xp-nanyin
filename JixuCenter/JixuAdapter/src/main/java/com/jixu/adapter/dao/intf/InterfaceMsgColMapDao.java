package com.jixu.adapter.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.AdapterMsgColMap;

/**
 * @ClassName 		InterfaceMsgColMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface InterfaceMsgColMapDao extends CommFuncDAO{
	
	public List<InterfaceMsgColMap> loadInterfaceMsgColMap(@Param("sourceID")int sourceID);
	
	public List<AdapterMsgColMap> loadAdapterMsgColMap(@Param("adapterID")int adapterID);

	/**
	 * 判断基础列、增强列id是否已被InterfaceMsgColMap使用
	 * @param cfgID
	 * @return
	 */
	public boolean checkCfgColExistInMap(int cfgID);
}
