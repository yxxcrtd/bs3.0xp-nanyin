/*
 * 文件名：TrdCommandDispatcher001Mapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：wangkang 修改时间：2018年9月12日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.investtrade.investdealquery.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.investtrade.investdealquery.dto.AssetTransferExport001Dto;
import com.joyintech.service.trade.investtrade.investdealquery.dto.TrdInvestDealQuery001Dto;
import com.joyintech.service.trade.investtrade.investdealquery.service.TrdInvestDealQuery001Service;

/**
 * 微服务端交易查询controller
 * 
 * @author wangkang
 */
@RestController("backTrdInvestDealQuery001Controller")
@RequestMapping("/TrdInvestDealQuery001")
public class TrdInvestDealQuery001Controller {

    /**
     * 微服务流程中交易查询service
     */
    @Autowired
    private TrdInvestDealQuery001Service trdInvestDealQuery001Service;

    /**
     * 流程中的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdInvestDealQuery001Dto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        Page<TrdInvestDealQuery001Dto> pageList = trdInvestDealQuery001Service.selectForPage(page, length, param);
        /*List<TrdInvestDealQuery001Dto> list = pageList.getRoot();
        if (!list.isEmpty()) {
            for (TrdInvestDealQuery001Dto dto : list) {
                if (StringUtil.isNotEmpty(dto.getBackLendDirection())) {
                    // 金融产品交易扩展取出回购/拆借方向
                    if (StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), dto.getFinprodType2())) {
                        dto.setPs(dto.getBackLendDirection());
                    }
                }
            }
        }*/
        return pageList;
    }

    /**
     * 有效时 的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("validFindPage/{page}/{length}")
    public Page<TrdInvestDealQuery001Dto> validSelectForPage(@RequestBody Map<String, Object> param,
                                                             @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<TrdInvestDealQuery001Dto> pageList = trdInvestDealQuery001Service.validSelectForPage(page, length, param);
        /*List<TrdInvestDealQuery001Dto> list = pageList.getRoot();
        if (!list.isEmpty()) {
            for (TrdInvestDealQuery001Dto dto : list) {
                if (StringUtil.isNotEmpty(dto.getBackLendDirection())) {
                    // 金融产品交易扩展取出回购/拆借方向
                    if (StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), dto.getFinprodType2())) {
                        dto.setPs(dto.getBackLendDirection());
                    }
                }
            }
        }*/
        return pageList;
    }

    /**
     * 撤销时的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("cancelFindPage/{page}/{length}")
    public Page<TrdInvestDealQuery001Dto> cancelSelectForPage(@RequestBody Map<String, Object> param,
                                                              @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<TrdInvestDealQuery001Dto> pageList = trdInvestDealQuery001Service.cancelSelectForPage(page, length, param);
        /*List<TrdInvestDealQuery001Dto> list = pageList.getRoot();
        if (!list.isEmpty()) {
            for (TrdInvestDealQuery001Dto dto : list) {
                if (StringUtil.isNotEmpty(dto.getBackLendDirection())) {
                    // 金融产品交易扩展取出回购/拆借方向
                    if (StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), dto.getFinprodType2())) {
                        dto.setPs(dto.getBackLendDirection());
                    }
                }
            }
        }*/
        return pageList;
    }

    /**
     * 状态为空时的搜索
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("allFindPage/{page}/{length}")
    public Page<TrdInvestDealQuery001Dto> allSelectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                           @PathVariable("length") int length)
        throws Exception {
        Page<TrdInvestDealQuery001Dto> pageList = trdInvestDealQuery001Service.alllSelectForPage(page, length, param);
        /*List<TrdInvestDealQuery001Dto> list = pageList.getRoot();
        if (!list.isEmpty()) {
            for (TrdInvestDealQuery001Dto dto : list) {
                if (StringUtil.isNotEmpty(dto.getBackLendDirection())) {
                    // 金融产品交易扩展取出回购/拆借方向
                    if (StringUtil.equals(DictCodeList.平安资产类型_质押式回购.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_拆借.getValue(), dto.getFinprodType2())
                        || StringUtil.equals(DictCodeList.平安资产类型_买断式回购.getValue(), dto.getFinprodType2())) {
                        dto.setPs(dto.getBackLendDirection());
                    }
                }
            }
        }*/
        return pageList;

    }

    /**
     * 委托代码联想文本框
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @GetMapping("/getEntrustIds")
    public List<SuggestDto> getEntrustId(String entrustId)
        throws Exception {
        return trdInvestDealQuery001Service.getEntrustId(entrustId);
    }

    /**
     * 组合代码联想文本框
     * 
     * @param portfolioId
     * @return
     * @throws Exception
     */
    @GetMapping("/getPortfolioIds")
    public List<SuggestDto> getPortfolioIds(String portfolioId)
        throws Exception {
        return trdInvestDealQuery001Service.getPortfolioIds(portfolioId);
    }

    /**
     * 市场代码联想文本框
     * 
     * @param finprodMarketId
     * @return
     * @throws Exception
     */
    @GetMapping("/getFinprodMarketIds")
    public List<SuggestDto> getFinprodMarketIds(String finprodMarketId)
        throws Exception {
        return trdInvestDealQuery001Service.getFinprodMarketIds(finprodMarketId);
    }
     

    /**
     * check是否为转仓交易
     * 
     * @param teadIds
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectCheck")
    public int selectCheck(@RequestBody List<String> teadIds) {
        return trdInvestDealQuery001Service.selectCheck(teadIds);
    }
    
    /**
     * 债券类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByBond")
    public List<AssetTransferExport001Dto> selectDataByBond(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByBond(teadIds);
    }
     
    /**
     * 存款类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByDeposit")
    public List<AssetTransferExport001Dto> selectDataByDeposit(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByDeposit(teadIds);
    }
    
    /**
     * 计划类利率型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByPlanLl")
    public List<AssetTransferExport001Dto> selectDataByPlanLl(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByPlanLl(teadIds);
    }
     
    /**
     * 计划类净值型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */ 
    @PostMapping("/selectDataByPlanJz")
    public List<AssetTransferExport001Dto> selectDataByPlanJz(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByPlanJz(teadIds);
    }

    /**
     * 计划类货币型交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByPlanHb")
    public List<AssetTransferExport001Dto> selectDataByPlanHb(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByPlanHb(teadIds);
    }

    /**
     * 基金类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByFund")
    public List<AssetTransferExport001Dto> selectDataByFund(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByFund(teadIds);
    }
    
    /**
     * 项目类交易
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDataByProject")
    public List<AssetTransferExport001Dto> selectDataByProject(@RequestBody List<String> teadIds){
        return trdInvestDealQuery001Service.selectDataByProject(teadIds);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectInvestRateByPlan")
    public BigDecimal selectInvestRateByPlan(@RequestBody AssetTransferExport001Dto dto) {
        return trdInvestDealQuery001Service.selectInvestRateByPlan(dto);
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/selectInvestRateByProject")
    public BigDecimal selectInvestRateByProject(@RequestBody AssetTransferExport001Dto dto) {
        return trdInvestDealQuery001Service.selectInvestRateByProject(dto);
    } 
}
