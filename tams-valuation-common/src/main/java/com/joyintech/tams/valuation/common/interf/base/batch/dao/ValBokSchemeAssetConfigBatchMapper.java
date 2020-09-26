package com.joyintech.tams.valuation.common.interf.base.batch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSchemeAssetConfig;


@Repository
public interface ValBokSchemeAssetConfigBatchMapper {
	
	 int insertBatch(List<BokSchemeAssetConfig> list);
	 
	 int deleteByMyAssetId(List<BokSchemeAssetConfig> list);

}
