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

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondinfo002Dto;
import com.joyintech.tams.cfets.etrib.service.CfetsEtrIbBondinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 银行间债券指令
 * 
 * @author caiqun
 * @version 1.0 2021年10月14日
 * @see CfetsEtrIbBondinfoController
 * @since 1.0
 */
@Controller
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
    @ResponseBody
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
     * 检索指令管理分页数据。
     *
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrExchangeTrade002Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "查询指令管理分页数据", notes = "查询指令管理分页数据")
    @PostMapping("/findEtrinfoPage/{page}/{length}")
    @ResponseBody
    public Message<Page<CfetsEtrIbBondinfo001Dto>> findEtrinfoPage(@RequestBody Map<String, Object> pageParam,
                                                           @PathVariable("page") int page,
                                                           @PathVariable("length") int length)
            throws Exception {
        try {
            Page<CfetsEtrIbBondinfo001Dto> list = cfetsEtrIbBondinfoService.findEtrinfoPage(page, length, pageParam);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().success().setData(list);
        }catch (Exception e) {
            logger.error("查询指令分页列表异常",e);
            return new Message<Page<CfetsEtrIbBondinfo001Dto>>().fail().setMessage("查询指令分页列表异常");
        }

    }

    @PostMapping("save")
    @ApiOperation(value = "多组合指令新建保存", notes = "多组合指令新建保存")
    @ResponseBody
    public Message<CfetsEtrIbBondinfo002Dto> save(@RequestBody CfetsEtrIbBondinfo002Dto dto) {
        try {
            cfetsEtrIbBondinfoService.saveBaseEtrInvestInfoDto(dto);
            return new Message<CfetsEtrIbBondinfo002Dto>().fail().setMessage("新建成功");
        } catch (Exception e) {
            logger.error("多组合指令新建异常", e);
            return new Message<CfetsEtrIbBondinfo002Dto>().fail().setMessage("多组合指令新建异常");
        }
    }
}