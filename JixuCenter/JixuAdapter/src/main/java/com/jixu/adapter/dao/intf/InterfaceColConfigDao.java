package com.jixu.adapter.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.framework.dao.intf.CommFuncDAO;

/**
 * @ClassName 		InterfaceColConfigDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface InterfaceColConfigDao extends CommFuncDAO{
	
	public List<InterfaceColConfig> loadInterfaceColConfig(@Param("sourceID")int sourceID);
	
	public List<InterfaceColConfig> loadExpTemplateBasicAdvanceCfg(int sourceID);

	/**
	 * 获取某个接口的基础列/增强列的所有配置，按照执行先后顺序排序。系统拿到这个先后配置顺序去计算对应的增强列值
	 * @param sourceID	接口ID
	 * @param colTypes	基础列，增强列
	 * @return	所有按照ordernum顺序排列的接口配置
	 */
	public List<InterfaceColConfig> loadCfgOrderByTypeAndID(@Param("sourceID")int sourceID, @Param("colTypes") int[] colTypes);

	/**
	 * 获取循环最大参数值的配置
	 * @param sourceID
	 * @return
	 */
	public List<InterfaceColConfig> loadCycleMaxParam(int sourceID);
	
	/**
	 * 根据导出列的列号排序获取导出列
	 * @param sourceID
	 * @param colTypes
	 * @return
	 */
	public List<InterfaceColConfig> loadCfgOrderByTypeAndColno(int sourceID, int[] colTypes);
}
