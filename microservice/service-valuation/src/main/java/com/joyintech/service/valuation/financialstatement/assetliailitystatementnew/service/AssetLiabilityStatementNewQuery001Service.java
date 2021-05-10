/*
 * 文件名：AssetLiabilityStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dao.AssetLiabilityStatementNewQuery001Mapper;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;

/**
 * 资产负债表
 * 
 * @author huanghao
 * @version 1.0 2020年5月31日
 * @see AssetLiabilityStatementNewQuery001Service
 * @since 1.0
 */
@Service(value="serviceAssetLiabilityStatementNewQuery001Service")
public class AssetLiabilityStatementNewQuery001Service
		extends CommonExportService<AssetLiabilityStatementNewQuery001Dto> {

	@Autowired
	AssetLiabilityStatementNewQuery001Mapper assetLiabilityStatementNewQuery001Mapper;

    public List<AssetLiabilityStatementNewQuery001Dto> selectBokBalanceAll(AssetLiabilityStatementNewQuery001SearchDto dto) {
    	return assetLiabilityStatementNewQuery001Mapper.selectBokBalanceAll(dto);
    }

    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return assetLiabilityStatementNewQuery001Mapper.getLinkSelectOption(param);
    }

    public List<AssetLiabilityStatementNewQuery001Dto> selectBokBalance(AssetLiabilityStatementNewQuery001SearchDto dto) {
        return assetLiabilityStatementNewQuery001Mapper.selectBokBalance(dto);
    }

    public List<SuggestDto> getBooksetId(Map<String, Object> param) {
        String profitType = param.get("profitType") == null ? null : (String) param.get("profitType");
        String booksetType = param.get("booksetType") == null ? null : (String) param.get("booksetType");
        return assetLiabilityStatementNewQuery001Mapper.getBooksetId(booksetType,profitType);
    }
}
