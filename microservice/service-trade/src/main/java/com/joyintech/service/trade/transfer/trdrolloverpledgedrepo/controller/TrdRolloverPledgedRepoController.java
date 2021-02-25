package com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.trdassetsproject.dto.FinDealPledgeDto;
import com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.service.TrdRolloverPledgedRepoService;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 质押式回购拆分
 */
@RestController("backTrdRolloverPledgedRepoController_dtx")
@RequestMapping("/trade/transfer/trdrolloverpledgedrepo/TrdRolloverPledgedRepo")
public class TrdRolloverPledgedRepoController {
    /**
     * trdRolloverPledgedRepoService
     */
    @Autowired
    private TrdRolloverPledgedRepoService trdRolloverPledgedRepoService;
//    /**
//     * 查询已拆分信息
//     * @return 已拆分信息
//     */
//    @RequestMapping("selectSplitInfo")
//    public SplitInfoDto selectSplitInfo(@RequestBody Map<String,Object>param){
//        return trdSplitPledgedRepoService.selectSplitInfo(param);
//    }
    /**
     * 查询组合
     */
    @RequestMapping("selectPortfolioId")
    public List<SelectOptionDto> selectPortfolioId(@RequestBody Map<String,Object>param){
        return trdRolloverPledgedRepoService.selectPortfolioId(param);
    }

    /**
     * 查询质押券拆分列表
     */
    @RequestMapping("selectPledgedRepoRolloverList")
    public List<FinDealPledgeDto> selectPledgedRepoRolloverList(@RequestBody Map<String,Object>param){
        return trdRolloverPledgedRepoService.selectFinDealPledgeList(param);
    }
    /**
     * 查询已拆分质押券信息
     */
    @RequestMapping("selectAlFinDealPledgeList")
    public List<FinDealPledgeDto> selectAlFinDealPledgeList(@RequestBody Map<String,Object>param){
        return trdRolloverPledgedRepoService.selectAlFinDealPledgeList(param);
    }
    /**
     * 查询交易信息
     */
    @RequestMapping("selectDealBySplitTradeId")
    public List<TrdProductDeal> selectDealBySplitTradeId(@RequestBody String splitTradeId){
        return trdRolloverPledgedRepoService.selectDealBySplitTradeId(splitTradeId);
    }
}
