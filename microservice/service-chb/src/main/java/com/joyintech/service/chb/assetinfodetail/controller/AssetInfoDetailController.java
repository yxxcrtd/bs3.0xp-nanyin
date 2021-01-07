package com.joyintech.service.chb.assetinfodetail.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetail001Service;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.service.AssetInfoRegist001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 资产要素登记服务
 *
 * @author zhangzhongliang
 * @version 1.6.0 2019年04月24日
 * @see AssetInfoDetailController
 * @since 1.0
 */
@RestController
@RequestMapping("/AssetInfoDetail")
public class AssetInfoDetailController {

	/**
	 * 资产要素service
	 */
    @Autowired
    private AssetInfoDetail001Service assetInfoDetail001Service;
    
    @SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(getClass());
    
	
    /**
    *
    * 业务画面查询事件
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
    @PostMapping("findPage/{page}/{length}")
	public Page<AssetInfoDetailDto> searchForPage(@RequestBody Map<String, Object> param) {
		return assetInfoDetail001Service.searchForPage(param);
	}


}
