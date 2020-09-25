/*
 * 文件名：ValFinprodTradeQuery001Contoller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.trademanage.finprodtradequery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.trademanage.finprodtradequery.dto.ValFinprodTradeQuery001Dto;
import com.joyintech.service.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 金融产品交易查询
 * 
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Contoller
 * @since 1.0
 */
//@RestController(value="serviceValFinprodTradeQuery001Contoller")
@RestController("valFinprodTradeQuery001Ctl_dtx")
@RequestMapping("/ValFinprodTradeQuery001")
public class ValFinprodTradeQuery001Contoller extends BaseController {

    /**
     * ValFinprodTradeQuery001Service
     */
    @Autowired
    private ValFinprodTradeQuery001Service valFinprodTradeQuery001Service;

    /**
     * 分页数据查询
     * 
     * @return page
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<ValFinprodTradeQuery001Dto> findPage(@RequestBody Map<String, Object> param,
                                                     @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return valFinprodTradeQuery001Service.findPage(page, length, param);
    }

    /**
     * 不分页数据查询
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/queryPageList")
    public List<ValFinprodTradeQuery001Dto> queryPageList(@RequestBody Map<String, Object> param)
        throws Exception {
        return valFinprodTradeQuery001Service.queryPageList(param);
    }

    /**
     * 流程完成后的操作
     * 
     * @param dto 业务实体
     * @return 画面地址
     * @throws Exception
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping(value = "/doComplete/{action}")
    public void doComplete(@RequestBody ValFinprodTradeQuery001Dto dto, @PathVariable("action") String action) {
        valFinprodTradeQuery001Service.doComplete(dto, OperationType.valueOf(action));
    }

}
