package com.joyintech.service.trade.transfer.buyoutrepo.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer003Dto;
import com.joyintech.service.trade.transfer.buyoutrepo.service.BuyOutRepoTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购转仓
 * @author liuwei
 * @version 1.0 2020年1月6日
 * @see BuyOutRepoTransferController
 * @since 1.0
 */
@RestController("BuyOutRepoTransferCtr_dtx")
@RequestMapping("/service/BuyOutRepoTransfer")
public class BuyOutRepoTransferController {

    /**
     *  买断式回购转仓Service
     */
    @Autowired
    private BuyOutRepoTransferService buyOutRepoTransferService;

    /**
     * 获取交易界面
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    @GetMapping("getTradeInfo")
    public BuyOutRepoTransfer003Dto getTradeInfo(String tradeId){
        return buyOutRepoTransferService.getTradeInfo(tradeId);
    }

    /**
     * 获取转仓信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map){
        return buyOutRepoTransferService.getPortfolioId(map);
    }

    /**
     * 获取债券数量
     * @return 债券数量
     * @see
     * @since 1.0
     */
    @PostMapping("selectPosition")
    public BigDecimal selectPosition(@RequestBody Map<String,Object> map){
        return buyOutRepoTransferService.selectPosition(map);
    }

    /**
     * 获取待转仓产品组合持有债券总持仓（针对债券和交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    @PostMapping("getBondAmount")
    public BigDecimal getBondAmount(@RequestBody Map<String,Object> map){
        return buyOutRepoTransferService.getBondAmount(map);
    }

    /**
     * 获取待转仓产品组合总持仓（针对交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    @PostMapping("getTradeAmount")
    public BigDecimal getTradeAmount(@RequestBody Map<String,Object> map){
        return buyOutRepoTransferService.getTradeAmount(map);
    }

    /**
     * 根据交易编号删除金融产品分类表
     * @param tradeId 交易编号
     * @return Db
     */
    @Transactional
    @DTXTransaction
    @GetMapping("deleteFinProductTypeByTradeId")
    public int deleteFinProductTypeByTradeId(String tradeId){
        return buyOutRepoTransferService.deleteFinProductTypeByTradeId(tradeId);
    }
}
