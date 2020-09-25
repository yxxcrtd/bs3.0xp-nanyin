/*
 * 文件名：TrdRepoTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.trdrepotrade.controller;

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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeCSTPDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeExportMailDto;
import com.joyintech.service.trade.trdrepotrade.dto.TrdRepoTradeReferenceDto;
import com.joyintech.service.trade.trdrepotrade.service.TrdRepoTradeService;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 回购交易微服务
 */
@RestController
@RequestMapping("/TrdRepoTrade")
public class TrdRepoTradeController {

    @Autowired
    private TrdRepoTradeService trdRepoTradeService;

    /**
     * 回购资产交易查询画面，查询回购资产交易信息列表
     *
     * @return Page<TrdRepoTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectForPage/{page}/{length}")
    public Page<TrdRepoTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length)
            throws Exception {
        return trdRepoTradeService.selectForPage(page, length, param);
    }

    /**
     * 回购资产交易新建画面，查看模式：查询回购资产交易信息
     *
     * @param param 交易编号
     * @return 回购资产交易信息
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdRepoTradeDto")
    public TrdRepoTradeReferenceDto getTrdRepoTradeDto(@RequestBody Map<String, Object> param)
            throws Exception {
        return trdRepoTradeService.getTrdRepoTradeDto(param);
    }

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为实际）
     *
     * @param bizCode 交易编号
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPtlSecPositionFlow/{bizCode}")
    public List<PtlSecPositionFlow> selectPtlSecPositionFlow(@PathVariable("bizCode") String bizCode) {
        return trdRepoTradeService.selectPtlSecPositionFlow(bizCode);
    }

    /**
     * 根据工作流代码查询交易编号
     *
     * @param workflowId 工作流代码
     * @return 交易编号
     * @see
     * @since 1.0
     */
    @RequestMapping("selectTradeIdByWorkflowId/{workflowId}")
    public String selectTradeIdByWorkflowId(@PathVariable("workflowId") String workflowId) {
        return trdRepoTradeService.selectTradeIdByWorkflowId(workflowId);
    }

    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    @RequestMapping("isInFlowDataExisted/{tradeId}")
    public Boolean isInFlowDataExisted(@PathVariable("tradeId") String tradeId) {
        return trdRepoTradeService.isInFlowDataExisted(tradeId);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    @RequestMapping("isAlreadyRevoked/{tradeId}")
    public Boolean isAlreadyRevoked(@PathVariable("tradeId") String tradeId) {
        return trdRepoTradeService.isAlreadyRevoked(tradeId);
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
        return trdRepoTradeService.getEntrustInfo(entrustId);
    }

    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    public List<SuggestDto> getSecManageAcctId(@RequestBody Map<String, Object> param) {
        return trdRepoTradeService.getSecManageAcctId(param);
    }

    /**
     * 组合代码、证券托管户、交易日、到期日改变时，查询可使用标准券额度
     *
     * @param params params
     * @return 可使用标准券额度
     * @see
     * @since 1.0
     */
    @RequestMapping("getAvaStandardStockPosition")
    public BigDecimal getAvaStandardStockPosition(@RequestBody Map<String, Object> params) {
        return this.trdRepoTradeService.getAvaStandardStockPosition(params);
    }

    /**
     * 查询副总裁/总裁助理授权额度
     *
     * @param params params
     * @return 可使用标准券额度
     * @see
     * @since 1.0
     */
    @RequestMapping("getVPOrPAuthAmount")
    public BigDecimal getVPOrPAuthAmount(@RequestBody Map<String, Object> params) {
        return this.trdRepoTradeService.getVPOrPAuthAmount(params);
    }

    /**
     * 查询正回购交易质押券信息
     *
     * @param selectParam selectParam
     * @return 正回购交易质押券信息
     * @see
     * @since 1.0
     */
    @RequestMapping("selectFinDealPledge")
    public List<TrdRepoTradeExportMailDto> selectFinDealPledge(@RequestBody Map<String, Object> selectParam) {
        return this.trdRepoTradeService.selectFinDealPledge(selectParam);
    }

    /**
     * 查询债券cstp交易下拉框
     * @param param 输入参数
     * @return
     */
    @RequestMapping("queryRepoCstpInfoList/{page}/{length}")
    public Page<SelectOptionDto> queryRepoCstpInfoList(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                       @PathVariable("length") int length){
        return trdRepoTradeService.queryRepoCstpInfoList(page,length,param);
    }

    /**
     * 根据成交编号查询cstp交易信息
     * @param fedealno 成交编号
     * @param loginUserName 登录用户
     * @return cstp交易信息
     */
    @GetMapping("queryRepoCstpInfoByNo")
    public TrdRepoTradeCSTPDto queryRepoCstpInfoByNo(String fedealno,String entrustId,String preTradeId, String loginUserName,String prodType,String isCstpSure){
        return trdRepoTradeService.queryRepoCstpInfoByNo(fedealno,entrustId,preTradeId,loginUserName,prodType,isCstpSure);
    }

    /**
     * 根据成交单号查询回购抵押品关联
     *
     * @param fedealno 成交单号
     * @return 回购抵押品关联
     * @see
     * @since 1.0
     */
    @GetMapping("queryRepoAssetByNo")
    public List<InterfTradRepoasst> queryRepoAssetByNo(String fedealno){
        return trdRepoTradeService.queryRepoAssetByNo(fedealno);
    }
    
    /**
     * 查询债券信息
     *
     * @param param 成交单号
     * @return 回购抵押品关联
     * @see
     * @since 1.0
     */
    @RequestMapping("querySecinfo")
    public FinProduct querySecinfo(@RequestBody Map<String, Object> param){
        return trdRepoTradeService.querySecinfo(param);
    }
    /**
     * 根据成交编号查询劵面总额的值是否和接口的值一致
     * @param
     * @return
     */
    @PostMapping("isAllTradeAmtByFTradeId")
    public Boolean isAllTradeAmtByFTradeId(@RequestBody Map<String, Object> map){
        Boolean allTradeAmtByFTradeId = trdRepoTradeService.isAllTradeAmtByFTradeId(map);
        return allTradeAmtByFTradeId;
    }
    /**
     * 获取质押券其他信息
     * @param othInputMap 参数
     * @return 质押券信息
     */
    @RequestMapping("getPledgeBondInfoDto")
    public PledgeBondInfoDto getPledgeBondInfoDto(@RequestBody Map<String, Object> othInputMap) {
        return trdRepoTradeService.getPledgeBondInfoDto(othInputMap);
    }

    /**
     * 获取换出质押券信息
     * @param parm 参数
     * @return 质押券信息
     */
    @RequestMapping("getOutPledgeInfo/{page}/{length}")
    public Page<PledgeBondInfoDto> getOutPledgeInfo(@RequestBody Map<String, Object> parm,@PathVariable("page") int page,
                                                    @PathVariable("length") int length) {

        return  trdRepoTradeService.getOutPledgeInfo(page,length,parm);
    }

    /**
     * 根据交易编号查询该交易是否是到期续做产生的数据
     *
     * @param tradeId 交易编号
     * @return 该交易是否由到期续做产品的数据
     * @see
     * @since 1.0
     */
    @RequestMapping("isDqTrdProductInfo/{tradeId}")
    public Boolean isDqTrdProductInfo(@PathVariable("tradeId")String tradeId) {
        return trdRepoTradeService.isDqTrdProductInfo(tradeId);
    }

    /**
     * 获取金融产品信息
     * @param finprodMarketId 参数
     * @return 金融产品信息
     */
    @RequestMapping("getFinProduct")
    public FinProduct getFinProduct(@RequestBody String finprodMarketId) {
        return trdRepoTradeService.getFinProduct(finprodMarketId);
    }

    /**
     * 获取债券融入的全面总额
     * @param params 参数,finprodId,tradeDate,mdate
     * @return 金融产品信息
     */
    @RequestMapping("queryLendinQty")
    public BigDecimal queryLendinQty(@RequestBody Map<String, Object> params) {
        return trdRepoTradeService.queryLendinQty(params);
    }
    
    /** 检查日期区间是否有 付息计划
     * @param param 参数
     * @return 是否有 付息计划
     */
    @PostMapping("selectDoEntrustMoney")
    public BigDecimal selectDoEntrustMoney(@RequestBody Map<String, Object> map){
        return trdRepoTradeService.selectDoEntrustMoney(map);
    }
    
    /**
     * 获取预交易编号
     * @param params
     * @return 金融产品信息
     */
    @RequestMapping("selectPreTradeId")
    public List<TrdMatchInfo> selectPreTradeId(@RequestBody Map<String, Object> paramPre) {
        return trdRepoTradeService.selectPreTradeId(paramPre);
    }
}
