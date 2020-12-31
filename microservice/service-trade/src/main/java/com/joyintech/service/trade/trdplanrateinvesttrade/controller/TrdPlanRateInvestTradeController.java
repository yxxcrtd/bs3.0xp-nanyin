/*
 * 文件名：TrdPlanRateInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trdplanrateinvesttrade.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.trdplanrateinvesttrade.dto.TrdPlanRateInvestTradeDto;
import com.joyintech.service.trade.trdplanrateinvesttrade.service.TrdPlanRateInvestTradeService;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.asset.dto.FinCashCalcRuleDto;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 计划类（利率型）投资交易
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTradeController
 * @since 1.0
 */
@RestController("TrdPlanRateInvestTradeCtr_dtx")
@RequestMapping("/TrdPlanRateInvestTrade")
public class TrdPlanRateInvestTradeController {
    /**
     * 计划类（利率型）投资交易Service
     */
    @Autowired
    private TrdPlanRateInvestTradeService trdPlanRateInvestTradeService;
    /**
     * 金融产品交易基础组件
     */
    @Autowired
    private BaseTradeProductDealService baseTradeProductDealService;
    /**
     * 分页查询
     * @param map 请求参数
     * @return 分页查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdPlanRateInvestTradeDto> findPage(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        map.put("validStatus_02", DictCodeListExt1.交易流程状态_有效.getValue());
        map.put("validStatus_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        map.put("validStatus_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        map.put("pingTypePurchase", DictCodeListExt1.平安业务类型_计划类资产_利率型_申购.getValue());
        map.put("pingTypeRedemption", DictCodeListExt1.平安业务类型_计划类资产_利率型_赎回.getValue());
        map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
        return trdPlanRateInvestTradeService.findPage(page,length,map);
    }
    /**
     * 获取资产代码下拉框
     * @param map 请求参数
     * @return 下拉框集合类
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getFinprodMarketId(map);
    }
    /**
     * 获取资产类型
     * @param map 请求参数（金融产品代码）
     * @return 资产类型
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodType")
    public Map<String,Object> getFinprodType(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getFinprodType(map);
    }
    /**
     * 获取登记编号
     * @param map 查询参数
     * @return 登记编号
     * @see
     * @since 1.0
     */
    @PostMapping("getProdRegistCode")
    public String getProdRegistCode(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getProdRegistCode(map);
    }
    /**
     * 获取收盘价
     * @param map 日期、币种
     * @return 汇率（收盘价）
     * @see
     * @since 1.0
     */
    @PostMapping("getClosePrice")
    public BigDecimal getClosePrice(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getClosePrice(map);
    }
    /**
     * 获取金融产品实体类
     * @param tradeId 交易编号
     * @return 金融产品交易实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getFinDto")
    public List<BaseTradeProductDealDto> getFinDto(String tradeId){
        List<String> list = new ArrayList<String>();
        list.add(tradeId);
        return baseTradeProductDealService.selectTotalDeal(list);
    }
    /**
     * 查询交易员
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    @PostMapping("getTrader")
    public String getTrader(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getTrader(map);
    }
    /**
     * 获取计息基础
     * @param finprodId 金融产品代码
     * @return 计息基础
     * @see
     * @since 1.0
     */
    @GetMapping("getBasis")
    public String getBasis(String finprodId){
        return trdPlanRateInvestTradeService.getBasis(finprodId);
    }
    /**
     * 流程处理
     * @param dto 存款类投资交易实体类
     * @return 是否
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public int doComplete(@RequestBody TrdPlanRateInvestTradeDto dto){
        if("NEW".equals(dto.getOperateType())){
            trdPlanRateInvestTradeService.insert(dto);
        }else{
//            trdPlanRateInvestTradeService.undo(dto);
        }
        return 1;
    }
    /**
     * 获取上次付息日
     * @param map 请求参数
     * @return 上次付息日
     * @see
     * @since 1.0
     */
    @PostMapping("getLastVDate")
    public FinCashPlan getLastVDate(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getLastVDate(map);
    }
    /**
     * 获取计算规则
     * @param map 请求参数
     * @return 计算规则集合
     * @see
     * @since 1.0
     */
    @PostMapping("getFinCashCalcRule")
    public List<FinCashCalcRuleDto> getFinCashCalcRule(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getFinCashCalcRule(map);
    }

    @PostMapping("getFinCashPayRule")
    public FinCashPayRule getFinCashPayRule(@RequestBody Map<String,Object> map){
        return trdPlanRateInvestTradeService.getFinCashPayRule(map);
    }

    /**
     * 获取已完成委托额度
     * @param entrustId 请求参数
     * @return 已完成委托额度
     * @see
     * @since 1.0
     */
    @GetMapping("queryActiveMandate")
    public BigDecimal queryActiveMandate(String entrustId) {
    	return trdPlanRateInvestTradeService.queryActiveMandate(entrustId);
    }
    
    /**
     * 查询金融产品信息
     * @param finprodId
     * @return
     */
    @GetMapping("getFinProductInfo")
    public FinProduct getFinProductInfo(String finprodId){
        return trdPlanRateInvestTradeService.getFinProductInfo(finprodId);
    }
}
