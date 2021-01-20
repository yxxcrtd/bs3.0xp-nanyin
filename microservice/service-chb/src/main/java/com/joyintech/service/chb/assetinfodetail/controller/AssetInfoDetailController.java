package com.joyintech.service.chb.assetinfodetail.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetail001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * 数据维护
	 * @return
	 */
	@PostMapping("doSave")
	public Integer doSave(@RequestBody AssetInfoDetailDto dto) {
		return assetInfoDetail001Service.doSave(dto);
	}

	/**
	 * 数据维护
	 * @return
	 */
	@PostMapping("doDelete")
	public Integer doDelete(@RequestBody AssetInfoDetailDto dto) {
		return assetInfoDetail001Service.doDelete(dto);
	}

}
