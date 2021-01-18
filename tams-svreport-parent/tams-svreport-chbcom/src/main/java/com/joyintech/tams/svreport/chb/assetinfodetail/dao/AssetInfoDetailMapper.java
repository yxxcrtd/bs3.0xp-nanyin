package com.joyintech.tams.svreport.chb.assetinfodetail.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.base.dao.SysDatadictListLinkMapper;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodataWithBLOBs;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
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


	List<AssetInfoDetailDto> selectChbInfoByParam (Map<String,Object> params);

	AssetInfoDetailDto getAssetInfoDetail (@Param("Assetuuid") String Assetuuid);
	ChbAssetInfo getChbAssetInfoByUUid (@Param("Assetuuid") String Assetuuid);

	int mergeInfoListEx(@Param("newDataList")List<ChbAssetInfo> insertList);


	List<SysDatadictListLink> getDataListLinkData(Map<String,Object> params);

	List<ChbAssetInfoErrodataWithBLOBs> getChbAssetInfoErrorDataByUUid(@Param("assetUUid")String  assetUUid);


	List<SelectOptionDto> selectAssetCodeList(Map<String, Object> pageParam);

}
