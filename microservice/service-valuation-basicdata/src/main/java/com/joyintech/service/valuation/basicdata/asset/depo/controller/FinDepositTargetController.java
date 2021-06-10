/*
 * 文件名：FinDepositTargetController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.asset.depo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basicdata.asset.depo.dto.FinDepositTargetDto;
import com.joyintech.service.valuation.basicdata.asset.depo.service.FinDepositTargetService;
import com.joyintech.tams.common.base.entity.FinProductClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 存款类资产标的信息
 * @author zhangnian
 * @version 1.0 2018年9月14日
 * @see FinDepositTargetController
 * @since 1.0
 */
@RestController(value="serviceFinDepositTargetController")
@RequestMapping("/FinDepositTarget")
public class FinDepositTargetController {

    
    @Autowired
    private FinDepositTargetService finDepositTargetService;

    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<FinDepositTargetDto> selectAll(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return finDepositTargetService.selectForPage(page, length, param);
    }
    
    /**
     * 插入
     * @param dto
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody FinDepositTargetDto dto) throws Exception {
         finDepositTargetService.insert(dto);
    }

    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody FinDepositTargetDto dto) throws Exception{
        finDepositTargetService.update(dto);
    }

    /**
     * 删除
     * @param dto
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody FinDepositTargetDto dto) throws Exception{
        finDepositTargetService.delete(dto);
    }

    /**
     * 获取基本信息--修改/详细
     * @param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getBasicInfo")
    public FinDepositTargetDto getBasicInfo(String finprodId,String finprodType,String cashType,String finprodMarketId,String assetType) throws Exception {
        return finDepositTargetService.getBasicInfo(finprodId,finprodType,cashType,finprodMarketId,assetType);
    }


    /**
     * 存款机构
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 start*/
    @PostMapping("getCustomerName")
    public List<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param) {
        return finDepositTargetService.getCustomerName(param);
    }
    /*存借款机构匹配存放行和融资人属性 modified by lishuping 20191018 end*/


    /**
     *
     * 根据条件查询全部信息
     * @param pageParam 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    @PostMapping("/selectAll")
    public List<FinDepositTargetDto> selectAll(@RequestBody Map<String, Object> pageParam) {
        return finDepositTargetService.selectAll(pageParam);
    }

    /**
     * 金融产品代码是否重复
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectCountByfinprodId")
    public int selectCountByfinprodId(String finprodId) {
        return finDepositTargetService.selectCountByfinprodId(finprodId);
    }

    @GetMapping("getAssetId")
    public List<SuggestDto> getAssetId(String search, String finprodType, String profitType, String child) {
        Map<String, Object> param = new HashMap<>();
        param.put("search",search);
        param.put("finprodType",finprodType);
        param.put("profitType",profitType);
        param.put("child",child);
        return finDepositTargetService.getAssetId(param);
    }

    /**
     * 核算分类
     * @param  finProductClass
     * @throws Exception
     */
    @RequestMapping("getFinanceProductClass")
    public FinProductClass getFinanceProductClass(@RequestBody FinProductClass finProductClass) {
        return finDepositTargetService.getFinanceProductClass(finProductClass);
    }
}
