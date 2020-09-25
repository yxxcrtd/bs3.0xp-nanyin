package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.GlCoaaMapModel;
import com.orm.obj.CoaaGroup;
import com.orm.obj.GlCoaaMap;

/**
 * @ClassName 		GlCoaaMapDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface GlCoaaMapDao extends CommFuncDAO{
	
	/**
	 * 获取总账栏映射
	 * @param chartnum
	 * @param groupnum
	 * @return
	 */
	public List<GlCoaaMapModel> loadGlCoaaMap(@Param("chartnum")String chartnum, @Param("groupnum") int groupnum); 
	
	@CacheEvict(value="coaDataCache", key="'GlColMap_'+#coamap.getChartNum()+'_'+#coamap.getGroupNum()+'_'+#coamap.getColumnNum()")
	public void deleteGlColMap(@Param("coamap")GlCoaaMap coamap);
	
	@CacheEvict(value="coaDataCache", key="'GlColMap_'+#coamap.getChartNum()+'_'+#coamap.getGroupNum()+'_'+#coamap.getColumnNum()")
	public void updateGlColMap(@Param("coamap")GlCoaaMap coamap);
	
	@Cacheable(value="coaDataCache", key="'GlColMap_'+#chartnum+'_'+#groupnum+'_'+#glnum")
	public String loadGLAcctnum(@Param("chartnum")String chartnum, @Param("groupnum") int groupnum, @Param("glnum") int glnum);

	/**
	 * 获取总账栏映射
	 * @param chartnum
	 * @param groupnum
	 * @return
	 */
	public List<GlCoaaMapModel> loadGlBookSubjectMap(String chartnum, int groupnum);
	
	/**
	 * 自动匹配总账栏
	 * @param assetType	资产类型
	 * @param currency1	即期换汇(空方货币), 外汇掉期(远端收入货币)
	 * @param currency2 即期换汇(多方货币), 外汇掉期(远端支出货币)
	 * @return
	 */
	public CoaaGroup getMapedGlGroup(String assetType, String currency1, String currency2);
	
	/**
	 * 根据科目表，总账组获取科目的所有配置
	 * @param chartnum
	 * @param groupnum
	 * @return
	 */
	public List<GlCoaaMap> loadGlColAcctMap(String chartnum, int groupnum);
}
