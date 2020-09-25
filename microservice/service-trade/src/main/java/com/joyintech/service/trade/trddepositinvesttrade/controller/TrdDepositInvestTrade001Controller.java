/*
 * 文件名：TrdDepositInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trddepositinvesttrade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.service.trade.trddepositinvesttrade.service.TrdDepositInvestTrade001Service;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.trade.BaseTradeProductDealService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;

/**
 * 存款类投资交易查询
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositInvestTrade001Controller
 * @since 1.0
 */
@RestController("TrdDepositInvestTrade001Ctr_dtx")
@RequestMapping("/TrdDepositInvestTrade001")
public class TrdDepositInvestTrade001Controller {
    /**
     * 注入存款类投资交易Service
     */
    @Autowired
    private TrdDepositInvestTrade001Service trdDepositInvestTrade001Service;
    
    @Autowired
    private BaseTradeProductDealService baseTradeProductDealService;
    
    
    /**
     * 查询 <功能详细描述>
     * @return page分页参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdDepositInvestTrade001Dto> selectAllCommonChar(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        map.put("validStatus_02", DictCodeListExt1.交易流程状态_有效.getValue());
        map.put("validStatus_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        map.put("validStatus_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_存款.getValue());
        map.put("pingTypeBuy", DictCodeListExt1.平安业务类型_存款类资产买入.getValue());
        map.put("pingTypeSale", DictCodeListExt1.平安业务类型_存款类资产卖出.getValue());
        map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
        return trdDepositInvestTrade001Service.selectForPage(page, length, map);
    }
    
    /**
     * 获取委托代码联想框集合
     * @param entrustId 委托代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getEntrustId")
    public List<SuggestDto> getEntrustId(String entrustId){
        return trdDepositInvestTrade001Service.getEntrustId(entrustId);
    }
    /**
     * 获取委组合代码联想框集合
     * @param portfolidId 组合代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        return trdDepositInvestTrade001Service.getPortfolioId(page, length, map);
    }
    /**
     * 获取联想下拉框实体类
     * @param map 请求参数
     * @return 联想下拉框实体类
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodMarketId2")
    public List<SelectOptionDto> getFinprodMarketId2(@RequestBody Map<String,Object> map){
        return trdDepositInvestTrade001Service.getFinprodMarketId2(map);
    }
    /**
     * 获取资产代码联想框集合
     * @param finprodMarketId 资产代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(String finprodMarketId){
        return trdDepositInvestTrade001Service.getFinprodMarketId(finprodMarketId);
    }
    /**
     * 获取币种下拉框集合
     * @return 币种下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetProvider")
    public List<SelectOptionDto> getAssetProvider(@RequestBody Map<String,Object> map){
        return trdDepositInvestTrade001Service.getAssetProvider(map);
    }
    /**
     * 资产代码带出的信息
     * @param finprodId 资产代码
     * @return 资产类投资交易实体类
     * @see
     * @since 1.0
     */
    @GetMapping("getFinInfo")
    public TrdDepositInvestTrade001Dto getFinInfo(String finprodId){
        return trdDepositInvestTrade001Service.getFinInfo(finprodId);
    }
    /**
     * 是否保本
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    @GetMapping("getIsGuarantee")
    public String getIsGuarantee(String portfolioId){
        return trdDepositInvestTrade001Service.getIsGuarantee(portfolioId);
    }
    /**
     * 组合可用持仓、组合可用现金等
     * @param map 查询参数
     * @return 余额列表
     * @see
     * @since 1.0
     */
    @PostMapping("available")
    public List<SuggestDto> available(@RequestBody Map<String,Object> map){
        List<SuggestDto> list = trdDepositInvestTrade001Service.available(map);
        return list;
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
        return trdDepositInvestTrade001Service.getTrader(map);
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
    public int doComplete(@RequestBody TrdDepositInvestTrade001Dto dto){
        if("NEW".equals(dto.getOperateType())){
            trdDepositInvestTrade001Service.insert(dto);
        }else{
            trdDepositInvestTrade001Service.undo(dto);
        }
        return 1;
    }
    /**
     * 清空业务流程表
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doDelete")
    public void doDelete(String workflowId){
        trdDepositInvestTrade001Service.doDelete(workflowId);
    }
    /**
     * 获取划款指令信息
     * @param tradeId 交易编号
     * @return 划款指令实体类
     * @see
     * @since 1.0
     */
    @GetMapping("stlInfo")
    public StlPayOrders stlInfo(String tradeId){
        if(trdDepositInvestTrade001Service.stlInfo(tradeId) != null && trdDepositInvestTrade001Service.stlInfo(tradeId).size()>0){
            return trdDepositInvestTrade001Service.stlInfo(tradeId).get(0);
        }
        return null;
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
        return baseTradeProductDealService.select(list);
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
        return trdDepositInvestTrade001Service.getProdRegistCode(map);
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
        return trdDepositInvestTrade001Service.getClosePrice(map);
    }
    /**
     * 获取opics数据
     * @param tradeId 交易代码
     * @return opics实体类
     * @see
     * @since 1.0
     */
    @RequestMapping("getOpics")
    public List<InterfSendOpics> getOpics(String tradeId){
        return trdDepositInvestTrade001Service.getOpics(tradeId);
    }
    
    /**
     * 获取组合代码
     * @return 联想文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getPortfolioIdSug")
    public List<SuggestDto> getPortfolioIdSug(@RequestBody Map<String,Object> map){
        return trdDepositInvestTrade001Service.getPortfolioIdSug(map);
    }
    
    /**
     * 获取opics下拉框
     * @param map 请求类型
     * @return 下拉框
     * @see
     * @since 1.0
     */
    @PostMapping("getSelectOptions")
    public List<SelectOptionDto> getSelectOptions(@RequestBody Map<String,Object> map){
        return trdDepositInvestTrade001Service.getSelectOptions(map);
    }

    /**
     * 获取当日应记利息
     * @param map 请求参数
     * @return 当日应记利息
     */
    @PostMapping("getTdyIntincexp")
    public BigDecimal getTdyIntincexp(@RequestBody Map<String,Object> map){
        return trdDepositInvestTrade001Service.getTdyIntincexp(map);
    }

    /**
     * 验证同一组合同一标的的交易
     * @param dto 请求参数
     * @return 个数
     */
    @PostMapping("checkTradeById")
    public Integer checkTradeById(@RequestBody TrdDepositInvestTrade001Dto dto){
        return trdDepositInvestTrade001Service.checkTradeById(dto);
    }
    
    /**
     * 验证标的是否做过终止协议交易
     * @param dto 请求参数
     * @return 个数
     */
    @PostMapping("checkIsCleanById")
    public Integer checkIsCleanById(@RequestBody TrdDepositInvestTrade001Dto dto){
        return trdDepositInvestTrade001Service.checkIsCleanById(dto);
    }

}
