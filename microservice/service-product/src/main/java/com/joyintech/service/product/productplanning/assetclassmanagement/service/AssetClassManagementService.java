/*
 * 文件名：AssetClassManagement001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.assetclassmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.productplanning.assetclassmanagement.dao.AssetClassManagementMapper;
import com.joyintech.service.product.productplanning.assetclassmanagement.dto.AssetClassManagementDto;

/**
 * 资产分类管理
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagementService
 * @since 1.0
 */
@Service("backAssetClassManagementService")
public class AssetClassManagementService extends BaseService<AssetClassManagementDto> {

	@Autowired
	private AssetClassManagementMapper assetClassManagementMapper;

	/**
	 * 界面主查询
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<AssetClassManagementDto> selectForPage(int page, int length, Map<String, Object> parameters) {
		Page<AssetClassManagementDto> pages = super.queryForPage(page, length,
				() -> assetClassManagementMapper.selectForPage(parameters));
		return pages;
	}

	/**
	 * 插入
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void insertSelective(AssetClassManagementDto assetClassManagementDto) {
		assetClassManagementMapper.insertSelective(assetClassManagementDto);
		return;
	}

	/**
	 * 更新
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public int doupdate(AssetClassManagementDto dto) {
		return assetClassManagementMapper.doupdate(dto);

	}

	/**
	 * 根据主键查询
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public AssetClassManagementDto selectByPrimaryKey(Map<String, Object> param) {
		List<AssetClassManagementDto> resultList = assetClassManagementMapper.selectByPrimaryKey(param);
		AssetClassManagementDto resultDto = resultList.get(0);
		return resultDto;

	}

	/**
	 * 删除
	 * 
	 * @param parameters
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void doComplete(AssetClassManagementDto entity) {
		String type = entity.getOperateType();
		if (StringUtil.equals(type, "DELETE")) {
			AssetClassManagementDto keyDto = new AssetClassManagementDto();
			keyDto.setCustomAssetTypeTwo(entity.getCustomAssetTypeTwo());
			keyDto.setCustomAssetTypeThree(entity.getCustomAssetTypeThree());
			keyDto.setCustomAssetTypeFour(entity.getCustomAssetTypeFour());
			keyDto.setCustomAssetTypeFive(entity.getCustomAssetTypeFive());
			assetClassManagementMapper.deleteByPrimaryKey(keyDto);
		}

	}
}
