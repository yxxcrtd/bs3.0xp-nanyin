package com.jixu.adapter.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Adapter;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.BasicDataSet;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.DataSource;
import com.orm.obj.UnionDataSet;

/**
 * @ClassName 		AdapterDao
 * @author  		Wang Zhongyuan
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface AdapterDao extends CommFuncDAO{
	
	public List<DataSource> loadDataSourceList(String connName,int connType);
	
	public List<DataSource> loadDataSourceList();
	
	public List<BasicDataSet> loadBasicDataSetList(String dsName);
	
	public List<BasicDataSet> loadBasicDataSetList();
	
	public List<UnionDataSet> loadUnionDataSetList(String dsName);
	
	public List<UnionDataSet> loadUnionDataSetList();
	
	public List<Adapter> loadAdapterList(String name);
	
	public List<AdapterColConfig> loadAdapterColConfig(@Param("dataSetID")int dataSetID, @Param("colTypes") int[] colTypes);
	
	public List<DataSetColTemp> loadDataSetColTemp(@Param("dataSetID")int dataSetID, @Param("colTypes") int[] colTypes);
	
	public List<DataSetColTemp> loadDataSetColTempByDataSetID(@Param("dataSetID")int dataSetID);
	
	public List<AdapterColConfig> loadAdapterColConfig(@Param("dataSetID")int dataSetID);
}
