package com.joyintech.service.trade.investtrade.investdealquery.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;

public interface AssetTransferExport001Mapper {

	List<AssetTransferExport001Dto> selectForPage(Map<String, Object> pageParam);

}
