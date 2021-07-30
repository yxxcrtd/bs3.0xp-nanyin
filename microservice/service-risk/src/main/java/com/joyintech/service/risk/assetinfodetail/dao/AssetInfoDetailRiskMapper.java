package com.joyintech.service.risk.assetinfodetail.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.assetinfodetail.dto.AssetInfoDetailRiskDto;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodataWithBLOBs;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface AssetInfoDetailRiskMapper {
	/**
	 * 功能:批量更新资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int updateBatch(@Param("updateList") List<RskAssetInfo> updateList);
	int updateBatchPsbc(@Param("updateList") List<RskAssetInfo> updateList);
	/**
	 * 功能:批量插入资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insertBatch(@Param("insertList") List<RskAssetInfo> insertList);
	int insertBatchPsbc(@Param("insertList") List<RskAssetInfo> insertList);


	List<AssetInfoDetailRiskDto> selectChbInfoByParam (Map<String,Object> params);

	AssetInfoDetailRiskDto getAssetInfoDetail (@Param("Assetuuid") String Assetuuid);
	AssetInfoDetailRiskDto queryAssetInfo (@Param("assetCode") String assetCode);



	RskAssetInfo getChbAssetInfoByUUid (@Param("Assetuuid") String Assetuuid);

	int mergeInfoListEx(@Param("newDataList")List<RskAssetInfo> insertList);


	List<SysDatadictListLink> getDataListLinkData(Map<String,Object> params);

	List<ChbAssetInfoErrodataWithBLOBs> getChbAssetInfoErrorDataByUUid(@Param("assetUUid")String  assetUUid);


	List<SelectOptionDto> selectAssetCodeList(Map<String, Object> pageParam);

	BigDecimal  getTargetAmt(String  assetUUid);

	List<SelectOptionDto>  getAssetTye2(Map<String, Object> pageParam);

	int selectDataCount(@Param("keylist") List<AssetInfoDetailRiskDto> list);
	
	FinProductAdd selectByFinprodId(@Param("finprodId") String finprodId);

	FinProductType selectByFinprodIdType(@Param("finprodId") String finprodId);

	void deleteFinProductType(@Param("finprodId") String finprodId);

	int deleteError(String assetUUid);

	int updateChbAssetType(String assetUUid);


	int deleteErrorMsg(String assetCode);


}
