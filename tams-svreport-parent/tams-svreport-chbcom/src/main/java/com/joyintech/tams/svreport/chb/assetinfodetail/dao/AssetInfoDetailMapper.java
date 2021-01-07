package com.joyintech.tams.svreport.chb.assetinfodetail.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AssetInfoDetailMapper {
	/**
	 * 功能:批量更新资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int updateBatch(@Param("updateList") List<ChbAssetInfo> updateList);
	int updateBatchPsbc(@Param("updateList") List<ChbAssetInfo> updateList);
	/**
	 * 功能:批量插入资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insertBatch(@Param("insertList") List<ChbAssetInfo> insertList);
	int insertBatchPsbc(@Param("insertList") List<ChbAssetInfo> insertList);


	List<ChbAssetInfo> selectChbInfoByParam (Map<String,Object> params);
    
}
