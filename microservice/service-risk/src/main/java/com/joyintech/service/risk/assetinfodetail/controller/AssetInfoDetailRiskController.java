/*
 * 文件名：AssetInfoDetailRisk001Controller.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.service.risk.assetinfodetail.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.assetinfodetail.dto.AssetInfoDetailRiskDto;
import com.joyintech.service.risk.assetinfodetail.service.AssetInfoDetailRiskService;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 资产要素登记
 *
 * @author weiyuqiang
 * @version 1.6.0 2018年5月8日
 * @see AssetInfoDetailRiskController
 * @since 1.0
 */
@RestController
@RequestMapping("/AssetInfoDetailRiskController")
public class AssetInfoDetailRiskController extends BaseController {

	/**
	 * 资产要素service
	 */
	@Autowired
	private AssetInfoDetailRiskService assetInfoDetailRiskService;
   

	/**
	 *
	 * 业务画面查询事件
	 * <功能详细描述>
	 * @return
	 * @see
	 * @since 1.0
	 */	 
    @PostMapping("findPage/{page}/{length}")
    public Page<AssetInfoDetailRiskDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) {
        Page<AssetInfoDetailRiskDto> pageList = assetInfoDetailRiskService.selectForPage(page, length, param);
        return pageList;
    }


    /**
     * 查询对应的资产要素实体类     
     * @param assetUUid
     * @return
     */
    @GetMapping("/BackRskAssetInfoMethod")
    public RskAssetInfo BackRskAssetInfoMethod(String assetuuid) {
        return assetInfoDetailRiskService.BackRskAssetInfoMethod(assetuuid);
    }

    /**
	 * 根据uuid获取资产信息
	 * @param UUid
	 * @return
	 */
    @GetMapping("/getChbAssetInfoByUUid")
    public RskAssetInfo getChbAssetInfoByUUid(String uuid) {
        return assetInfoDetailRiskService.getChbAssetInfoByUUid(uuid);
    }

     
    /**
     * 资产代码下拉框
     * @param param
     * @return
     */
    @PostMapping("/selectAssetCodeList")
    public List<SelectOptionDto> selectAssetCodeList(@RequestBody Map<String, Object> param) {
        return assetInfoDetailRiskService.selectAssetCodeList(param);
    }

	/**
	 *
	 * 工作流完成操作
	 * <功能详细描述>
	 * @param map 业务实体
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/doComplete")
	public Integer doComplete(@RequestBody AssetInfoDetailRiskDto dto) {
		return assetInfoDetailRiskService.doComplete(dto);
	}
	
	/**
	 * 获取资产及二级类型的关系
	 * @return
	 */
    @PostMapping("/getDataListLinkData")
    public List<SysDatadictListLink> getDataListLinkData(@RequestBody Map<String,Object> map) {
        return assetInfoDetailRiskService.getDataListLinkData(map);
    }
    
    /**
     * 查询回购交易的金额
     * <功能详细描述>
     * @param param String assetuuid
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @GetMapping("/getTargetAmt")
    public BigDecimal getTargetAmt(String assetuuid) {
        return assetInfoDetailRiskService.getTargetAmt(assetuuid);
    }
    
    /**
	 * 根据界面传入数据获取二级资产类型
	 * @param resultMap
	 * @return
	 */
    @PostMapping("/getAssetTye2")
    public List<SelectOptionDto> getAssetTye2(@RequestBody Map<String, Object> param) {
        return assetInfoDetailRiskService.getAssetTye2(param);
    }
    
    /**
	 * 查询资产信息
	 * @return
	 */
    @PostMapping("/selectChbInfoByParam")
    public List<AssetInfoDetailRiskDto> selectChbInfoByParam(@RequestBody Map<String,Object> map) {
        return assetInfoDetailRiskService.selectChbInfoByParam(map);
    }
    
    /**
	 * 检查是否存在对应资产
	 * @param UUid
	 * @return
	 */
    @GetMapping("/queryAssetInfo")
    public RskAssetInfo queryAssetInfo(String assetCode) {
        return assetInfoDetailRiskService.queryAssetInfo(assetCode);
    }
    
    /**
  	 * 数据维护
  	 * @return
  	 */
  	@PostMapping("selectDataCount")
  	public Integer selectDataCount(@RequestBody List<AssetInfoDetailRiskDto> dto) {
  		return assetInfoDetailRiskService.selectDataCount(dto);
  	}

	@PostMapping("importData")
	public List<ExcelValidDto> importData(@RequestBody List<AssetInfoDetailRiskDto> dataList ){
		return assetInfoDetailRiskService.importData(dataList);
	}
}
