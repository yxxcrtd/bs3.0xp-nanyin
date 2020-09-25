/*
 * 文件名：TrdCommandsCollectionController.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.trade.investtrade.commandscollection.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection002Dto;
import com.joyintech.service.trade.investtrade.commandscollection.dto.TrdCommandsCollection003Dto;
import com.joyintech.service.trade.investtrade.commandscollection.service.TrdCommandsCollectionService;

/**
 *  指令汇总页面
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollectionController
 * @since 1.0
 */
@RestController
@RequestMapping("/TrdCommandsCollection")
public class TrdCommandsCollectionController {
    
    /**
     * 微服务指令汇总service注入
     */
    @Autowired
    private TrdCommandsCollectionService trdCommandsCollectionService;
    
    /**
     * 资产代码联想查询各类资产对应的资产代码
     * @param param 包括资产大类和输入的资产名称
     * @return List<SuggestDto> 联想框下拉选项
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodMarketIds")
    public List<SuggestDto> selectFinprodMarketIds(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdCommandsCollectionService.getFinprodMarketIds(param);
    }

    /**
     * 查询汇总指令
     * @param param 包括资产大类和输入的查询条件
     * @param page page
     * @param length length
     * @return Page<TrdCommandsCollection001Dto> 汇总指令列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdCommandsCollection001Dto> findPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
    	return trdCommandsCollectionService.selectForPage(page, length, param);
    }

    /**
     * 查询委托详情列表
     * @param param 查询参数
     * @return List<TrdCommandsCollection002Dto> 委托详情列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectEntrustList")
    public List<TrdCommandsCollection002Dto> selectEntrustList(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdCommandsCollectionService.selectEntrustList(param);
    }

    /**
     * 委托质押券详情查询
     * @param param 包括委托代码
     * @return List<TrdCommandsCollection003Dto> 委托质押券详情
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPledgeList")
    public List<TrdCommandsCollection003Dto> selectPledgeList(@RequestBody Map<String, Object> param)
        throws Exception {
        return trdCommandsCollectionService.selectPledgeList(param);
    }
    
}
