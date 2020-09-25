package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.PortfolioInterfaceMap;

/**
 * @ClassName 		PortfolioInterfaceMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface PortfolioInterfaceMapDao extends CommFuncDAO{
	
	public List<PortfolioInterfaceMap> loadPortInterfaceMapListByPortNumImport(@Param("portnum")String portnum);
	
	@Caching(evict={@CacheEvict(value="portDataCache", key="'PortInterfaceMap_'+#piMap.getPortfolio().getId()+#sourceType+#piMap.getRpSecType()"),
	@CacheEvict(value="portDataCache", key="'ExportPortNum_'+#piMap.getPortfolio().getId()")})
	public void deletePortInterfaceMap(@Param("piMap")PortfolioInterfaceMap piMap, @Param("sourceType") int sourceType);
	
	@Caching(evict={@CacheEvict(value="portDataCache", key="'PortInterfaceMap_'+#piMap.getPortfolio().getId()+#sourceType+#piMap.getRpSecType()"),
	@CacheEvict(value="portDataCache", key="'ExportPortNum_'+#piMap.getPortfolio().getId()")})
	public void createPortInterfaceMap(@Param("piMap")PortfolioInterfaceMap piMap, @Param("sourceType") int sourceType);
	
	public List<PortfolioInterfaceMap> loadAutoExportList(@Param("portnum")String portnum);
	
	public List<PortfolioInterfaceMap> loadPortInterfaceMapBySourceID(@Param("sourceID")int sourceID);
	
	public List<PortfolioInterfaceMap> loadPortInterfaceMapListByPortNumExport(@Param("portnum")String portnum);
}
