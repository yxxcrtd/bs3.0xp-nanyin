package com.joyintech.service.period.trdbondlendtargetduration.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdbondlendtargetduration.service.TrdBondLendTargetDurationService;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondlendtargetduration.InvestTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdbondlendtargetduration.TrdBondLendTargetDurationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 产品存续期-强增强减管理
 *
 * @author : 强成西
 * @version V1.0
 * @Project: microservice
 * @Package com.joyintech.service.product.productduration.addreduce.controller
 * @date Date : 2018年09月19日 10:02
 */
@RestController("TrdBondLendTargetDurationCtr_dtx")
@RequestMapping("/TrdBondLendTargetDuration")
public class TrdBondLendTargetDurationController {

    @Autowired
    private TrdBondLendTargetDurationService trdBondLendTargetDurationService;

    /**
     * 画面查询方法-交易编号
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getBusiCode/{page}/{length}")
    public Page<SelectOptionDto> getBusiCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                             @PathVariable("length") int length) {
        return trdBondLendTargetDurationService.getBusiCode(page, length, param);
    }

    /**
     * 画面查询方法-证券账户
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("getSecManageAcctId/{page}/{length}")
    public Page<SelectOptionDto> getSecManageAcctId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return trdBondLendTargetDurationService.getSecManageAcctId(page, length, param);
    }

    /**
     * 画面查询方法-市场代码
     *
     * @return 查询结果集
     */
    @PostMapping("getFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodMarketId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                    @PathVariable("length") int length) {
        return trdBondLendTargetDurationService.getFinprodMarketId(page, length, param);
    }

    /**
     * 查询有效、撤销或待处理的存续期交易数据
     *
     * @return 查询结果集
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<TrdBondLendTargetDurationDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                       @PathVariable("length") int length) {
        return trdBondLendTargetDurationService.findPage(page, length, param);
    }

    /**
     * 检索融出券投资目的持仓
     * 
     * @param tradeId 参数
     * @return
     */
    @GetMapping("findAmountGroupByInv/{tradeId}/{portfolioId}")
    public List<PtlSecPositionFlow> findAmountGroupByInv(@PathVariable("tradeId") String tradeId, @PathVariable("portfolioId") String portfolioId) {
        return trdBondLendTargetDurationService.findAmountGroupByInv(tradeId, portfolioId);
    }

    /**
     * 所有交易数据
     * 
     * @param totalTradeId 汇总交易编号
     * @return
     */
    @GetMapping("findFinProductTradeDto/{totalTradeId}")
    public InvestTradeInfoDto findFinProductTradeDto(@PathVariable("totalTradeId") String totalTradeId) {
        return trdBondLendTargetDurationService.findFinProductTradeDto(totalTradeId);
    }

    /**
     * 查询存续期交易数据
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findEffectiveData")
    public TrdBondLendTargetDurationDto findEffectiveData(@RequestBody Map<String, Object> params) {
        return trdBondLendTargetDurationService.findEffectiveData(params);
    }

    /**
     * 查询利息明细信息
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findAssetIntInfo")
    public List<FinAssetIntInfo> findAssetIntInfo(@RequestBody Map<String, Object> params) {
        return trdBondLendTargetDurationService.findAssetIntInfo(params);
    }
}
