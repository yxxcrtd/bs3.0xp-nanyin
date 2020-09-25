/*
 * 文件名：FinProductDurDealController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDetailDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealDto;
import com.joyintech.service.product.durdeal.dto.FinProductDurDealProdDto;
import com.joyintech.service.product.durdeal.dto.InterfProdInfoDto;
import com.joyintech.service.product.durdeal.service.FinProductDurDealService;

/**
 * 产品存续期交易查询微服务
 *
 * @author etliu
 * @version 1.0 2018年12月18日
 * @see FinProductDurDealController
 * @since 1.0
 */
@RestController("backFinProductDurDealController")
@RequestMapping("/FinProductDurDeal")
public class FinProductDurDealController {

    /**
     * FinProductDurDealService
     */
    @Autowired
    private FinProductDurDealService finProductDurDealService;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("queryProdIdInfo")
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> paramMap) {
        return finProductDurDealService.queryProdIdInfo(paramMap);
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductDurDealDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<FinProductDurDealDto> queryForPage(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return finProductDurDealService.selectForPage(page, length, pageParam);
    }
    
    /**
     * 交易明细
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception 异常
     */
    @RequestMapping("/findDetailPage/{page}/{length}")
    public Page<FinProductDurDealDto> queryForDetailPage(@RequestBody Map<String, Object> pageParam,
    												@PathVariable("page") int page,
    												@PathVariable("length") int length)
    	throws Exception {
    	return finProductDurDealService.findDetailPage(page, length, pageParam);
    }
    
    /**
     * 分页查询明细数据
     *
     * @return Page<FinProductDurDealDetailDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findDetailData/{page}/{length}")
    public Page<FinProductDurDealDetailDto> findDetailData(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return finProductDurDealService.findDetailData(page, length, pageParam);
    }

    /**
     * 查询出开放类型为‘开放式’且处理模式为'日添利','智能日添利','货币型','净值型'
     * 且到期日大于平台日的产品代码、金融产品代码、销售部门、处理模式
     *
     * @return 产品信息
     * @see
     * @since 1.0
     */
    @RequestMapping("selectTargetProdData")
    public List<FinProductDurDealProdDto> selectTargetProdData() {
        return finProductDurDealService.selectTargetProdData();
    }

    /**
     * 查询产品申赎信息接口表数据
     *
     * @return 产品申赎信息接口表数据
     * @see
     * @since 1.0
     */
    @RequestMapping("selectInterfProdInfoData")
    public List<InterfProdInfoDto> selectInterfProdInfoData() {
        return finProductDurDealService.selectInterfProdInfoData();
    }

    /**
     * 查询已同步交易数据
     *
     * @return 已同步交易数据
     * @see
     * @since 1.0
     */
    @RequestMapping("selectSynchroedTradeData")
    public List<TrdFinProductDealTotal> selectSynchroedTradeData() {
        return finProductDurDealService.selectSynchroedTradeData();
    }
}
