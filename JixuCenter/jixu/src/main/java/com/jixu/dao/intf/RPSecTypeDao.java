package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.jixu.mvc.control.model.RPSecTypeLevelModel;
import com.orm.obj.RPSecType;

/**
 * @ClassName 		RPSecTypeDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface RPSecTypeDao extends CommFuncDAO{

	public RPSecType loadIssuerType(@Param("issuerTypeCode")int issuerTypeCode);
	
	public RPSecType loadStockType(@Param("stockTypeCode")int stockTypeCode);
	
	public RPSecType loadStockType2(@Param("stockTypeCode")int stockTypeCode);
	
	/**
	 * 
	 * @param tableCode		1.证券类型	2.行业类型
	 * @param groupLevel	1.等级，目前支持三级
	 * @return
	 */
	public List<RPSecType> loadRPSecTypeS(@Param("tableCode")int tableCode, @Param("groupLevel") int groupLevel);
	
	//public RPSecTypeLevelModel loadAssetTypeModel(@Param("level3ID")int level3ID);
	
	//public RPSecTypeLevelModel loadIndustryTypeModel(@Param("level3ID")int level3ID);
	
	//public List<RPSecTypeLevelModel> loadIndustryTypeModel2();
	
	public List<RPSecTypeLevelModel> loadRPSecTypeLevelModel(@Param("level3id")int level3id, @Param("tablecode")int tablecode);
	
	public List<RPSecType> getRPSecTypename(@Param("name")String name, @Param("tablecode")int tablecode);
	
	public List<RPSecType> loadAssetFatherType();
	
	public List<RPSecType> loadIndustryFatherType();
	
	//public List<RPSecType> loadAssetTypeList(@Param("typeCode")int typeCode);
	
	//public List<RPSecType> loadIndustryTypeList(@Param("typeCode")int typeCode);
	
	public List<RPSecType> loadRPSecTypeList(@Param("tableCode")int tableCode,@Param("typeCode")int typeCode);
	
	public List<RPSecType> loadIndustryAssetTypeList(@Param("typeCode")int typeCode);
}
