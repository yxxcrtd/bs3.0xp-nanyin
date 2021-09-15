package com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao;

import org.springframework.stereotype.Repository;
import com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dto.AssetMarketDataResultDto;
import java.util.List;
import java.util.Map;

/**
 * 资产行情信息 mapper
 * @author yantianyuan
 * 创建日期：2021-05-13
 */
@Repository
public interface AssetMarketDataMapper {
	
	/**
	 * 获取持有的资产信息
	 * @param map
	 * @return
	 */
	List<AssetMarketDataResultDto> getAssetMarketDataResult(Map<String,Object> map);
}
