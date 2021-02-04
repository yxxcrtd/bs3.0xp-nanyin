package com.joyintech.service.trade.transfer.trdassetsproject.controller;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.trdassetsproject.service.TrdAssetsProjectService;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 项目类资产转仓
 */
@RestController("backTrdAssetsProjectController_dtx")
@RequestMapping("/service/trade/transfer/trdassetsproject/TrdAssetsProject")
public class TrdAssetsProjectController {
    /**
     * trdassetsProjectService
     */
    @Autowired
    TrdAssetsProjectService trdassetsProjectService;
    /**
     * 查询已拆分信息
     * @return 已拆分信息
     */
    @RequestMapping("selectSplitInfo")
    public com.joyintech.service.trade.transfer.trdassetsproject.dto.ProjSplitInfoDto selectSplitInfo(@RequestBody Map<String,Object>param){
        return trdassetsProjectService.selectSplitInfo(param);
    }
    /**
     * 查询组合
     */
    @RequestMapping("selectPortfolioId")
    public List<SelectOptionDto> selectPortfolioId(@RequestBody Map<String,Object>param){
        return trdassetsProjectService.selectPortfolioId(param);
    }

    /**
     * 查询划款指令
     */
    @RequestMapping("selectBussTrdTraOrderByTradeId")
    public List<BussTrdTransferOrderDto> selectBussTrdTraOrderByTradeId(@RequestBody Map<String,Object>param){
        return trdassetsProjectService.selectBussTrdTraOrderByTradeId(param);
    }
}
