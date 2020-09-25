package com.joyintech.service.trade.investtrade.investdealquery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.investtrade.investdealquery.dao.AssetTransferExport001Mapper;
import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;

@Service
@Transactional(rollbackFor=Exception.class)
public class AssetTransferExport001Service  extends BaseService<AssetTransferExport001Dto>{

	/**
	 * 手动书写mapper
	 */
	@Autowired
	private AssetTransferExport001Mapper assetTransferExport001Mapper;
	
	 /**
     * 项目类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<AssetTransferExport001Dto> selectForPage(int page, int length,Map<String, Object> pageParam)
			throws Exception {
//		setParams(pageParam);
		Page<AssetTransferExport001Dto>  pageLimit = super.queryForPage(page, length, () -> assetTransferExport001Mapper.selectForPage(pageParam));
		//finprodType2PinAn(pageLimit.getRoot());
		return pageLimit;
    }
    
}
