/*
 * 文件名：AssetCommonController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basicdata.asset.common.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.common.service.ValAssetCommonService;
import com.joyintech.tams.common.base.entity.FinProductClass;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonController
 * @since 1.0
 */
@RestController(value = "serviceValAssetCommonController")
@RequestMapping("/ValAssetCommon")
public class ValAssetCommonController {

    @Autowired
    private ValAssetCommonService assetCommonService;

    @GetMapping("getAssetId")
    public List<SuggestDto> getAssetId(String search,String finprodType,String profitType, String child) {
        Map<String, Object> param = new HashMap<>();
        param.put("search",search);
        param.put("finprodType",finprodType);
        param.put("profitType",profitType);
        param.put("child",child);
         return assetCommonService.getAssetId(param);
    }

    @PostMapping("/getAssetIdOption/{page}/{length}")
    public Page<SelectOptionDto> getAssetIdOption(@RequestBody Map<String, Object> param,
    @PathVariable("page") int page, @PathVariable("length") int length) {
        return assetCommonService.getAssetIdOption(page,length,param);
    }


    /**
     * 发行人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getIssuer/{page}/{length}")
    public Page<SelectOptionDto> getIssuer(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        param.put("IS_ISSUER", DictCodeList.是否_是.getValue());
        return assetCommonService.getCustomer(page, length, param);
    }

    /**
     * 发行人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("selectCalendarIdForPge/{page}/{length}")
    public Page<SelectOptionDto> selectCalendarIdForPge(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return assetCommonService.selectCalendarIdForPge(page, length, param);
    }

    /**
     * 核算分类
     * @param param finProductClass
     * @throws Exception
     */
    @RequestMapping("getFinanceProductClass")
    public String getFinanceProductClass(@RequestBody FinProductClass finProductClass) {
        return assetCommonService.getFinanceProductClass(finProductClass);
    }

    /**
     * 查询税务分类
     * @param param finProductClass
     * @throws Exception
     */
    @RequestMapping("getFinanceProductTaxClass")
    public String getFinanceProductTaxClass(@RequestBody FinProductClass finProductClass) {
        return assetCommonService.getFinanceProductTaxClass(finProductClass);
    }

    /**
     * 管理人
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getManager/{page}/{length}")
    public Page<SelectOptionDto> getManager(@RequestBody Map<String, Object> param,
                                            @PathVariable("page") int page,
                                            @PathVariable("length") int length) {
        param.put("IS_ASSTE_MANAGER", DictCodeList.是否_是.getValue());
        return assetCommonService.getCustomer(page, length, param);
    }

    /**
     * 市场机构
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("getCustomerName/{page}/{length}")
    public Page<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param,
                                           @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return assetCommonService.getCustomerName(page, length, param);
    }
}
