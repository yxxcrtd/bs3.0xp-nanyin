package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.PortfolioInterfaceMapDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.PortfolioInterfaceMap;

/**
 * @ClassName 		PortfolioInterfaceMapDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("portfolioInterfaceMapDao")
public class PortfolioInterfaceMapDaoImpl extends CommFuncDAOImpl implements PortfolioInterfaceMapDao{

	@Override
	public List<PortfolioInterfaceMap> loadPortInterfaceMapListByPortNumImport(String portnum) {
		String hql="from PortfolioInterfaceMap where portfolio.id=:portnum and ineterfaceSource.sourceType>0";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public void createPortInterfaceMap(PortfolioInterfaceMap piMap, int sourceType) {
		this.saveEntity(piMap);
	}
	
	@Override
	public void deletePortInterfaceMap(PortfolioInterfaceMap piMap, int sourceType) {
		deleteEntity(piMap);
	}
	
	@Override
	public List<PortfolioInterfaceMap> loadAutoExportList(String portnum) {
		String hql="from PortfolioInterfaceMap where operationStep>0 and defaultImportType=2 and portfolio.id=:portnum";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeHQL(hql, params, values);
	}
	
	@Override
	public List<PortfolioInterfaceMap> loadPortInterfaceMapBySourceID(int sourceID) {
		String hql="from PortfolioInterfaceMap where ineterfaceSource.sourceID=:sourceID";
		return this.executeHQL(hql, "sourceID", sourceID);
	}
	
	@Override
	public List<PortfolioInterfaceMap> loadPortInterfaceMapListByPortNumExport(String portnum) {
		String hql="from PortfolioInterfaceMap where portfolio.id=:portnum and ineterfaceSource.sourceType=0";
		String[] params={"portnum"};
		Object[] values={portnum};
		return this.executeHQL(hql, params, values);
	}
	
}
