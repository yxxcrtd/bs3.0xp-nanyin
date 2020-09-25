package com.joyintech.service.trade.option.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.option.dto.TrdOptionTradeDto;
import com.joyintech.service.trade.option.service.TrdOptionTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 期权交易微服务
 */
@RestController
@RequestMapping("/TrdOptionTrade")
public class TrdOptionTradeController {

    @Autowired
    private TrdOptionTradeService trdOptionTradeService;

    /**
     * 分页查询
     *
     * @return Page<TrdOptionTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdOptionTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length)
            throws Exception {
        return trdOptionTradeService.selectForPage(page, length, param);
    }

    /**
     * 合约标的查询
     * @param param
     * @return 合约标的下拉框
     */
    @RequestMapping("selectFinprodIdAbbr")
    public List<SelectOptionDto> selectFinprodIdAbbr(@RequestBody Map<String, Object> param){
        return trdOptionTradeService.selectFinprodIdAbbr(param);
    }

    /**
     * 根据工作流代码查询交易编号
     * @param workflowId 工作流代码
     * @return 汇总交易编号
     */
    @RequestMapping("selectTradeIdByWorkflowId/{workflowId}")
    public String selectTradeIdByWorkflowId(@PathVariable("workflowId") String workflowId){
        return trdOptionTradeService.selectTradeIdByWorkflowId(workflowId);
    }

    /**
     * 根据工作流代码查询交易编号
     * @param tradeId 汇总交易编号
     * @return 数据信息
     */
    @RequestMapping("getTrdOptionTradeDto/{tradeId}/{BRANCH_0}")
    public TrdOptionTradeDto getTrdOptionTradeDto(@PathVariable("tradeId") String tradeId,
                                                  @PathVariable("BRANCH_0") String BRANCH_0){
        return trdOptionTradeService.getTrdOptionTradeDto(tradeId, BRANCH_0);
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
        return trdOptionTradeService.isInFlowDataExisted(tradeId);
    }

    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    @RequestMapping("selectDurationDataCount/{tradeId}")
    public Boolean selectDurationDataCount(@PathVariable("tradeId") String tradeId) {
        return trdOptionTradeService.selectDurationDataCount(tradeId);
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
        return trdOptionTradeService.isAlreadyRevoked(tradeId);
    }

    /**
     * 根据交易编号查询该交易是否已做流出资金核对
     *
     * @param tradeId 交易编号
     * @return 该交易是否已做流出资金核对
     * @see
     * @since 1.0
     */
    @RequestMapping("isAlreadyLqdPipedTrade/{tradeId}")
    public Boolean isAlreadyLqdPipedTrade(@PathVariable("tradeId") String tradeId) {
        return trdOptionTradeService.isAlreadyLqdPipedTrade(tradeId);
    }

    /**
     * 根据标的代码查询标的类型
     *
     * @param targetId 标的代码
     * @return 标的类型
     * @see
     * @since 1.0
     */
    @RequestMapping("selectTargetType/{targetId}")
    public String selectTargetType(@PathVariable("targetId") String targetId) {
        return trdOptionTradeService.selectTargetType(targetId);
    }

    /**
     * 根据委托编号查询信息
     *
     * @param entrustId 委托编号
     * @return 数据
     * @see
     * @since 1.0
     */
    @RequestMapping("findTradeInfoByEntrustId/{entrustId}")
    public TrdOptionTradeDto findTradeInfoByEntrustId(@PathVariable("entrustId") String entrustId) {
        return trdOptionTradeService.findTradeInfoByEntrustId(entrustId);
    }

    /**
     * 查询委托所做交易的期权费总额
     *
     * @param entrustId 委托编号
     * @return 数量
     * @see
     * @since 1.0
     */
    @RequestMapping("getPrinAmt/{entrustId}")
    public BigDecimal getPrinAmt(@PathVariable("entrustId") String entrustId) {
        return trdOptionTradeService.getPrinAmt(entrustId);
    }
}
