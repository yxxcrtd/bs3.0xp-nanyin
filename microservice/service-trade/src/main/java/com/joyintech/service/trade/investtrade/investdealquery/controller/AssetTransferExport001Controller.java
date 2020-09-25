package com.joyintech.service.trade.investtrade.investdealquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;
import com.joyintech.service.trade.investtrade.investdealquery.service.AssetTransferExport001Service;

/**
 * 微服务端交易查询controller
 * 
 * @author wj
 */
@RestController
@RequestMapping("/AssetTransferExport001")
public class AssetTransferExport001Controller {
	
	/**
     * 微服务流程中交易查询service
     */
    @Autowired
    private AssetTransferExport001Service assetTransferExport001Service;

    
	/**
     * 查询导出转移定价导出数据时的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("selectTransExportPage/{page}/{length}")
    public Page<AssetTransferExport001Dto> selectTransExportPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length)
        throws Exception {
        Page<AssetTransferExport001Dto> pageList = assetTransferExport001Service.selectForPage(page, length, param);
        
        return pageList;

    }
    
}
