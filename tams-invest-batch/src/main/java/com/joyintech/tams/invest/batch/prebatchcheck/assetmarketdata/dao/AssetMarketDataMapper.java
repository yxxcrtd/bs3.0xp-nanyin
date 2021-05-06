package com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dao;

import org.springframework.stereotype.Repository;
import com.joyintech.tams.invest.batch.prebatchcheck.assetmarketdata.dto.AssetMarketDataResultDto;
import java.util.List;
import java.util.Map;

@Repository
public interface AssetMarketDataMapper {
	
	List<AssetMarketDataResultDto> getAssetMarketDataResult(Map<String,Object> map);
}
