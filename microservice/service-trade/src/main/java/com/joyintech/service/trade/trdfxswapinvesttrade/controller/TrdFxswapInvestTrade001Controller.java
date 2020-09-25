/*
 * 文件名：TrdFxswapInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.trdfxswapinvesttrade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.service.trade.trdfxswapinvesttrade.service.TrdFxswapInvestTrade001Service;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;

/**
 * 外汇掉期投资交易查询
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Controller
 * @since 1.0
 */
@RestController("TrdFxswapInvestTrade001Ctr_dtx")
@RequestMapping("/TrdFxswapInvestTrade001")
public class TrdFxswapInvestTrade001Controller {

    /**
     * 注入外汇掉期投资交易Service
     */
    @Autowired
    private TrdFxswapInvestTrade001Service trdFxswapInvestTrade001Service;

    @Autowired
    private BaseTradeProductDealService baseTradeProductDealService;

    /**
     * 查询
     * 
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdFxswapInvestTrade001Dto> selectAllCommonChar(@RequestBody Map<String, Object> map,
                                                                @PathVariable("page") int page,
                                                                @PathVariable("length") int length)
        throws Exception {
        return trdFxswapInvestTrade001Service.selectForPage(page, length, map);
    }

    /**
     * 交易编号带出交易信息
     * 
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("getDealInfo")
    public TrdFxswapInvestTrade001Dto getDealInfo(String tradeId, String tradeStatus) {
        return trdFxswapInvestTrade001Service.getDealInfo(tradeId, tradeStatus);
    }
    
    /**
     * 委托代码带出交易必要信息
     * 
     * @param entrustId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("getDealInfoByEntrustId")
    public TrdFxswapInvestTrade001Dto getDealInfoByEntrustId(String entrustId) {
        return trdFxswapInvestTrade001Service.getDealInfoByEntrustId(entrustId);
    }

    /**
     * 组合可用持仓、组合可用现金等
     * 
     * @param map 查询参数
     * @return 余额列表
     * @see
     * @since 1.0
     */
    @PostMapping("available")
    public List<SuggestDto> available(@RequestBody Map<String, Object> map) {
        List<SuggestDto> list = trdFxswapInvestTrade001Service.available(map);
        return list;
    }

    /**
     * 查询交易员
     * 
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    @PostMapping("getTrader")
    public String getTrader(@RequestBody Map<String, Object> map) {
        return trdFxswapInvestTrade001Service.getTrader(map);
    }

    /**
     * 流程处理
     * 
     * @param dto 外汇掉期投资交易实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public int doComplete(@RequestBody TrdFxswapInvestTrade001Dto dto) {
        if ("NEW".equals(dto.getOperateType())) {
            trdFxswapInvestTrade001Service.insert(dto);
        }
        else {
            trdFxswapInvestTrade001Service.undo(dto);
        }
        return 1;
    }

    /**
     * 清空业务流程表
     * 
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doDelete")
    public void doDelete(String workflowId) {
        trdFxswapInvestTrade001Service.doDelete(workflowId);
    }

    /**
     * 获取划款指令信息
     * 
     * @param tradeId 交易编号
     * @return 划款指令实体类
     * @see
     * @since 1.0
     */
    @GetMapping("stlInfo")
    public List<StlPayOrders> stlInfo(String tradeId) {
        return trdFxswapInvestTrade001Service.stlInfo(tradeId);
    }

    /**
     * 获取金融产品实体类
     * 
     * @param tradeId 交易编号
     * @return 金融产品交易实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getFinDto")
    public List<BaseTradeProductDealDto> getFinDto(String tradeId) {
        List<String> list = new ArrayList<String>();
        list.add(tradeId);
        // 到期交易交易编号查询
        String tradeIdDur = trdFxswapInvestTrade001Service.getTradeIdDur(tradeId);
        if(StringUtil.isNotEmpty(tradeIdDur)){
            list.add(tradeIdDur);
        }
        return baseTradeProductDealService.select(list);
    }
}
