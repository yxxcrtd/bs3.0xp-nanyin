package com.joyintech.tams.svreport.chbhz.inAssetInfoSub.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetInfoTmp;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSub001Dto;

@Repository
public interface InAssetInfoSvrMapper {
	
	/**
	 * 查询申报接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbAssetInfoTmp>  genChbAssetInfoTmpList(@Param("batchdate")String  batchdate);
    /**
     * 根据接口信息查询申报信息表
     * @return
     */
    List<ChbAssetInfoSub001Dto>   genChbAssetInfoSubList(@Param("keylist")List<String> keylist);
}