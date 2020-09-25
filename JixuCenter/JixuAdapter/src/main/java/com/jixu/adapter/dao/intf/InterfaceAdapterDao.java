package com.jixu.adapter.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		InterfaceSourceDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface InterfaceAdapterDao extends CommFuncDAO{

	/**
	 * 获取数据源
	 * @param sourceid
	 * @return
	 */
	public InterfaceSource loadSource(@Param("sourceid")int sourceid);
	
	public List<InterfaceSource> loadInterfaceSourceList(@Param("ports")int[] ports, @Param("sourceType") int sourceType);
	
	public List<InterfaceSource> loadInterfaceSourceByPortnum(@Param("portnum")int portnum);
	
	public List<InterfaceSource> loadInterfaceSource(@Param("ports")int[] ports, @Param("sourceType") int sourceType);
	
	public List<InterfaceSource> loadAutoExecuteJobs();
	
	public List<InterfaceSource> loadAllInterfaceSourceList();
	
	public List<InterfaceSource> loadInterfaceByType(@Param("sourceType")int sourceType, String category, String sourcename);
	
	public List<InterfaceSource> loadExpInterfaceSourceList(@Param("sourcename")String sourcename, @Param("className") String className);
	
	public List<InterfaceSource> loadChildInterfaces(@Param("sourceID")int sourceID);
	
	/**
	 * 获取导出类型的适配器配置
	 * @return
	 */
	public List<InterfaceSource> loadExpInterfaceSourceList();
	
	
	// TODO add by wangqidong
	public List<Object> getFGuid();
	// TODO add by wangqidong
	public List<String> getFileSeq();
	// TODO add by wangqidong
	public List<Date> getCurDate();
	
	/**
	 * 获取下一个序列号
	 * @param seqname
	 * @return
	 */
	public long genDataKeySeq(String seqname);

	public List<String> getSeq(String seqName);
}
