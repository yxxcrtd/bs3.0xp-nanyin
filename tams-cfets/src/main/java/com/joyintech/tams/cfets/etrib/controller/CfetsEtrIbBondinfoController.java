/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.cfets.etrib.controller;

import java.util.Map;

import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo001Dto;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 银行间债券指令
 * 
 * @author caiqun
 * @version 1.0 2021年10月14日
 * @see CfetsEtrIbBondinfoController
 * @since 1.0
 */
@RestController
@RequestMapping("/cfets/etrib/cfetsEtrIbBondinfo")
@Api(tags = "银行间债券指令")
public class CfetsEtrIbBondinfoController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsEtrIbBondinfoController.class);

    @Autowired
    private CfetsEtrIbBondinfoService cfetsEtrIbBondinfoService;

    /**
     * 检索产品持仓分页数据。 <功能详细描述>
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "查询产品持仓分页列表", notes = "查询产品持仓分页列表")
    @PostMapping("/findPositionsPage/{page}/{length}")
    public Message<Page<CfetsEtrIbBondinfo001Dto>> findPositionsPage(@RequestBody Map<String, Object> pageParam,
                                                                     @PathVariable("page") int page,
                                                                     @PathVariable("length") int length)
            throws Exception {
        try {
            Page<CfetsEtrIbBondinfo001Dto> list = cfetsEtrIbBondinfoService.findPositionsPage(page, length, pageParam);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询产品持仓分页列表异常",e);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().fail().setMessage("查询产品持仓分页列表异常");
        }
    }
    /**
     * 检索产品持仓分页数据。 <功能详细描述>
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "查询产品持仓分页列表New", notes = "查询产品持仓分页列表New")
    @PostMapping("/findPositionsPageNew/{page}/{length}")
    public Message<Page<CfetsEtrIbBondPosinfo001Dto>> findPositionsPageNew(@RequestBody Map<String, Object> pageParam,
                                                                        @PathVariable("page") int page,
                                                                        @PathVariable("length") int length)
            throws Exception {
        try {
            Page<CfetsEtrIbBondPosinfo001Dto> list = cfetsEtrIbBondinfoService.findPositionsPageNew(page, length, pageParam);
            return new Message<Page<CfetsEtrIbBondPosinfo001Dto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询产品持仓分页列表异常",e);
            return new Message<Page<CfetsEtrIbBondPosinfo001Dto>>().fail().setMessage("查询产品持仓分页列表异常");
        }
    }
}