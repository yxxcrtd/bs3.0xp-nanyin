package com.joyintech.tams.svreport.chbsd.assetinfosub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;

@Repository
public interface SdAssetInfoSvrMapper {
	/**
	 * 查询跑批日未报送的数据
	 * @param batchDate
	 * @return
	 */
	public List<AssetInfoRegist001Dto> selcteUnRegData( @Param("batchDate")String batchDate);

}
