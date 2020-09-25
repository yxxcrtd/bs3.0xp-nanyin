/*
 * 文件名：TrdOutRightRepoTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.buyoutrepo.controller;

import java.math.BigDecimal;
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
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDetailDto;
import com.joyintech.service.trade.buyoutrepo.dto.TrdOutRightRepoTradeDto;
import com.joyintech.service.trade.buyoutrepo.service.TrdOutRightRepoTradeService;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 买断式回购
 *
 * @author zhangliang
 * @version 1.0 2019年1月22日
 * @see TrdOutRightRepoTradeController
 * @since 1.0
 */
@RestController("TrdOutRightRepoTradeCtr_dtx")
@RequestMapping("/TrdOutRightRepoTrade")
public class TrdOutRightRepoTradeController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired
	private TrdOutRightRepoTradeService trdOutRightRepoTradeService;
	
	/**
     * 分页查询
     *
     * @return Page<TrdOutRightRepoTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("queryForPage/{page}/{length}")
    public Page<TrdOutRightRepoTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdOutRightRepoTradeService.selectForPage(page, length, param);
    }
    
    /**
	 * 查询债券代码下拉框
	 * @param map 查询参数
	 * @return 债券代码下拉框
	 */
    @RequestMapping("querySecFinprodIdList")
    public List<SelectOptionDto> querySecFinprodIdList(@RequestBody Map<String, Object> map){
    	return trdOutRightRepoTradeService.querySecFinprodIdList(map);
    }
    
    /** 根据交易编号查询买断式回购信息
	 * @param tradeId 交易编号
	 * @return 买断式回购信息
	 */
    @GetMapping("queryBuyOutRepoByTradeId")
    public TrdOutRightRepoTradeDetailDto queryBuyOutRepoByTradeId(String tradeId){
    	return trdOutRightRepoTradeService.queryBuyOutRepoByTradeId(tradeId);
    }

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPtlSecPositionFlow/{bizCode}")
    public List<PtlSecPositionFlow> selectPtlSecPositionFlow(@PathVariable("bizCode") String bizCode) {
        return trdOutRightRepoTradeService.selectPtlSecPositionFlow(bizCode, null);
    }

    /**
     * 根据交易编号删除组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deletePtlSecPositionFlow/{bizCode}")
    public long deletePtlSecPositionFlow(@PathVariable("bizCode") String bizCode, @RequestBody String holdingType) {
        return trdOutRightRepoTradeService.deletePtlSecPositionFlow(bizCode, holdingType);
    }

    /**
     * 根据交易编号删除组合持仓流水（持仓类型为融入融出）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("updatePledgeBondForRevoke/{bizCode}")
    public long updatePledgeBondForRevoke(@PathVariable("bizCode") String bizCode, @RequestBody String holdingType) {
        return trdOutRightRepoTradeService.updatePledgeBondForRevoke(bizCode, holdingType, false);
    }

    /**
     * 查询投资委托信息
     *
     * @param entrustId 委托代码
     * @return 投资委托信息
     * @see
     * @since 1.0
     */
    @RequestMapping("getEntrustInfo/{entrustId}")
    public BaseEtrInvestInfoDto getEntrustInfo(@PathVariable("entrustId") String entrustId) {
        return trdOutRightRepoTradeService.getEntrustInfo(entrustId);
    }
    
    /** 检查日期区间是否有 付息计划
     * @param param 参数
     * @return 是否有 付息计划
     */
    @PostMapping("checkDateInPayPlan")
    public int checkDateInPayPlan(@RequestBody Map<String, Object> param){
    	return trdOutRightRepoTradeService.checkDateInPayPlan(param);
    }
    
    /** 检查日期区间是否有 付息计划
     * @param param 参数
     * @return 是否有 付息计划
     */
    @PostMapping("selectDoEntrustMoney")
    public BigDecimal selectDoEntrustMoney(@RequestBody Map<String, Object> map){
        return trdOutRightRepoTradeService.selectDoEntrustMoney(map);
    }
    /** 根据交易编号删除质押品信息
     * @param tradeId 交易编号
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinDealPledgeByTradeId")
    public int deleteFinDealPledgeByTradeId(String tradeId){
 	   return trdOutRightRepoTradeService.deleteFinDealPledgeByTradeId(tradeId);
    }
    
    /** 根据交易编号删除交易类金融产品
     * @param tradeId 交易编号
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinTradeProductByTradeId")
    public int deleteFinTradeProductByTradeId(String tradeId){
 	   return trdOutRightRepoTradeService.deleteFinTradeProductByTradeId(tradeId);
    }

    /**
     * 根据画面条件检索可融出券信息
     *
     * @param param 画面参数
     * @return List<PledgeBondInfoDto>
     */
    @PostMapping("selectAvaPledgeBondDetailList")
    public List<PledgeBondInfoDto> selectAvaPledgeBondDetailList(@RequestBody Map<String, Object> param) {
        return trdOutRightRepoTradeService.selectAvaPledgeBondDetailList(param);
    }

    /**
     * 根据委托代码查询首次净价区间
     * @param entrustId
     * @return
     */
    @GetMapping("getfCpriceBtw")
    public TrdOutRightRepoTradeDto getfCpriceBtw(String entrustId){
        return trdOutRightRepoTradeService.getfCpriceBtw(entrustId);
    }

    /**
     * 根据参数删除组合持仓流水
     *
     * @param map 参数列表
     * @return Boolean
     * @see
     * @since 1.0
     */
    @RequestMapping("deletePtlSecPositionFlowByParm")
    public Boolean deletePtlSecPositionFlowByParm( @RequestBody Map<String, Object> param) {
        return trdOutRightRepoTradeService.deletePtlSecPositionFlowByParm(param);
    }

    @RequestMapping("getFinCashPlanForRepoRate")
    public List<FinCashPlan> getFinCashPlanForRepoRate(@RequestBody Map<String, Object> param) {
        return trdOutRightRepoTradeService.getFinCashPlanForRepoRate(param);
    }
    
    /**
     * 撤销时查询原交易编号
     */
    @PostMapping("findCeTradeIdByTradeId/{tradeId}")
    public String findCeTradeIdByTradeId(@PathVariable("tradeId") String tradeId) {
        return trdOutRightRepoTradeService.findCeTradeIdByTradeId(tradeId);
    }
}
