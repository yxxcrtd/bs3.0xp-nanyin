package com.joyintech.tams.svreport.chb.common.util.asset.dto;


import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodataWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RechbAassetInfoMapper {


	List<ChbAssetInfoErrodataWithBLOBs> getChbAssetInfoErrorDataByUUid(@Param("assetUUid")String  assetUUid);

}
