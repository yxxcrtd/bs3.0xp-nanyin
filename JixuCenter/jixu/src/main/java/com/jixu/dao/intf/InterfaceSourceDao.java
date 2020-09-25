package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		InterfaceSourceDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface InterfaceSourceDao extends CommFuncDAO{

	/**
	 * 获取数据源
	 * @param sourceid
	 * @return
	 */
	public InterfaceSource loadSource(@Param("sourceid")int sourceid);
	
	public List<InterfaceSource> loadInterfaceSourceList(@Param("ports")String[] ports, @Param("sourceType") int sourceType);
	
	public List<InterfaceSource> loadInterfaceSourceByPortnum(@Param("portnum")String portnum);
	
	public List<InterfaceSource> loadInterfaceSource(@Param("ports")String[] ports, @Param("sourceType") int sourceType);
	
	public List<InterfaceSource> loadAutoExecuteJobs();
	
	public List<InterfaceSource> loadInterfaceByType(@Param("sourceType")int sourceType);

	/**
	 * 根据投资组合里配置的接口映射,获取价格/汇率/smf等对应的数据源
	 * @param portnum		投资组合ID
	 * @param sourceType	数据源类型,价格,汇率等
	 * @param rpSecType		资产类型
	 * @return
	 */
	@Cacheable(value="portDataCache", key="'PortInterfaceMap_'+#portnum+#sourceType+#rpSecType")
	public int getInterfaceSourceID(@Param("portnum")String portnum, @Param("sourceType") int sourceType, @Param("rpSecType") int rpSecType);
	
}
