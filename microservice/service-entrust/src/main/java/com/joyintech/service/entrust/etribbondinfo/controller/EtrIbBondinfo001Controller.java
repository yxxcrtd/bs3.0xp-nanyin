/*
 * 文件名：EtrBondFirstBiddingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etribbondinfo.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrFinProductDto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrIbBondinfo001Dto;
import com.joyintech.service.entrust.etribbondinfo.dto.EtrIbBondinfo002Dto;
import com.joyintech.service.entrust.etribbondinfo.service.EtrIbBondinfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 场内交易委托
 * 
 * @author zhangnian
 * @version 1.0 2019年10月24日
 * @see EtrIbBondinfo001Controller
 * @since 1.0
 */
@RestController("EtrIbBondinfo001Ctr_dtx")
@RequestMapping("EtrIbBondinfo001")
public class EtrIbBondinfo001Controller {
    @Autowired
    private EtrIbBondinfo001Service etrIbBondinfo001Service;

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
    @PostMapping("/findPositionsPage/{page}/{length}")
    public Page<EtrIbBondinfo001Dto> findPositionsPage(@RequestBody Map<String, Object> pageParam,
                                                       @PathVariable("page") int page,
                                                       @PathVariable("length") int length)
        throws Exception {
        return etrIbBondinfo001Service.findPositionsPage(page, length, pageParam);
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
    @PostMapping("/findZqzyPositionsPage/{page}/{length}")
    public Page<EtrIbBondinfo001Dto> findZqzyPositionsPage(@RequestBody Map<String, Object> pageParam,
            @PathVariable("page") int page,
            @PathVariable("length") int length)
                    throws Exception {
        return etrIbBondinfo001Service.findZqzyPositionsPage(page, length, pageParam);
    }

    /**
     * 获得债券信息
     * @param pageParam
     * @return
     */
    @PostMapping("getEtrFinProductInfo")
    public EtrFinProductDto getEtrFinProductInfo(@RequestBody Map<String, Object> pageParam) {
        return etrIbBondinfo001Service.getEtrFinProductInfo(pageParam);
    }

    /**
     * 查询债券持仓成本价
     * @param pageParam
     * @return
     */
    @PostMapping("getCostPrice")
    public BigDecimal getCostPrice(@RequestBody Map<String, Object> pageParam) {
        return etrIbBondinfo001Service.getCostPrice(pageParam);
    }
}