package com.jixu.dao.intf;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.TranFeeDef;

/**
 * @ClassName 		TranFeeDefDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface TranFeeDefDao extends CommFuncDAO{
	
	public List<TranFeeDef> loadTranFeeDefListByTranType(String assettype);
	
	public List<TranFeeDef> loadTranFeeTypeList();
	
	public List<TranFeeDef> subExpenseTypes(@Param("portID")String portID, @Param("secid")String secid, @Param("lotnum") int lotnum);
	
	/**
	 * 判断该资产类型是否存在按照证券头寸每天计提费用的配置存在
	 * @param assetType	资产类型
	 * @return 是否存在按照证券头寸每天计提费用的配置存在
	 */
	@Cacheable(value="secDataCache", key="'secFeeFollow'")
	public List<Integer> getAssetFeeFollowList();
	
	@Cacheable(value="secFeeCache", key="'TranComFee_'+#tranFeeTypeID")
	public TranFeeDef loadTranFeeDef(@Param("tranFeeTypeID")int tranFeeTypeID);

}
