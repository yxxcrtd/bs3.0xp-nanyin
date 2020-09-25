/*
 * 文件名：CommonSelectController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：杨智 修改时间：2018年09月25日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.channel.feepay.controller;

import java.util.Map;

import com.joyintech.service.trade.channel.feepay.service.ChannelFeePayService;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeePayDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductTradeInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 通道资产查询
 * 
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see ChannelFeePayController
 * @since 1.0
 */
@RestController
@RequestMapping("/ChannelFeePay")
public class ChannelFeePayController {

    /**
     * 通道资产查询
     */
    @Autowired
    private ChannelFeePayService channelFeePayService;

    /**
     * 画面查询方法
     * 
     * @param param 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChannelFeePayDto> findPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return channelFeePayService.findPage(page, length, param);
    }

    @PostMapping("findChannelId/{page}/{length}")
    public Page<SelectOptionDto> findChannelId(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                           @PathVariable("length") int length) {
        return channelFeePayService.findChannelId(page, length, param);
    }

    @PostMapping("searchTheoryAmt")
    public ChannelFeePayDto searchTheoryAmt(@RequestBody Map<String, Object> param) {
        return channelFeePayService.searchTheoryAmt(param);
    }

    @PostMapping("getEffectiveData")
    public ChannelFeePayDto getEffectiveData(@RequestBody String tradeId) {
        return channelFeePayService.getEffectiveData(tradeId);
    }

    @PostMapping("findAllTrade")
    public ProductTradeInfoDto findAllTrade(@RequestBody String tradeId) {
        return channelFeePayService.findAllTrade(tradeId);
    }

    @PostMapping("findChannelFeePaied")
    public String findChannelFeePaied(@RequestBody Map<String, Object> param) {
        return channelFeePayService.findChannelFeePaied(param);
    }

}
