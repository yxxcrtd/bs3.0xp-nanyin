package com.joyintech.service.trade.investtrade.investdealquery.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;

@Repository
public interface AssetTransferExport001Mapper {

	List<AssetTransferExport001Dto> selectForPage(Map<String, Object> pageParam);

}
