/*
 * 文件名：AssetLiabilityStatementQuery001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.controller;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001Dto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.dto.AssetLiabilityStatementNewQuery001SearchDto;
import com.joyintech.service.valuation.financialstatement.assetliailitystatementnew.service.AssetLiabilityStatementNewQuery001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author huanghao
 *
 */
@RestController(value="serviceAssetLiabilityStatementNewQuery001Controller")
@RequestMapping("/AssetLiabilityStatementNewQuery001")
public class AssetLiabilityStatementNewQuery001Controller extends BaseController {

    @Autowired
    private AssetLiabilityStatementNewQuery001Service assetLiabilityStatementNewQuery001Service;

    @PostMapping("/selectBokBalanceAll")
    public List<AssetLiabilityStatementNewQuery001Dto> selectBokBalanceAll(@RequestBody AssetLiabilityStatementNewQuery001SearchDto dto) {
        return assetLiabilityStatementNewQuery001Service.selectBokBalanceAll(dto);
    }

    @PostMapping("/selectBokBalance")
    public List<AssetLiabilityStatementNewQuery001Dto> selectBokBalance(@RequestBody AssetLiabilityStatementNewQuery001SearchDto dto) {
        return assetLiabilityStatementNewQuery001Service.selectBokBalance(dto);
    }

    @PostMapping("/getLinkSelectOption")
    public List<SelectOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param) {
        return assetLiabilityStatementNewQuery001Service.getLinkSelectOption(param);
    }
    @PostMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId(@RequestBody Map<String, Object> param) {
        return assetLiabilityStatementNewQuery001Service.getBooksetId(param);
    }

}
