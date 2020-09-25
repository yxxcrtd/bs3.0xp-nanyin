/*
 * 文件名：TrdBondInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.pretrdbondtrade.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.bond.service.TrdBondInvestTradeService;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade001Dto;
import com.joyintech.service.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade002Dto;
import com.joyintech.service.trade.pretrdbondtrade.service.PreTrdBondInvestTradeService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;

/**
 * 债券交易预录入微服务
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see PreTrdBondInvestTradeController
 * @since 1.0
 */
@RestController
@RequestMapping("/PreTrdBondInvestTrade")
public class PreTrdBondInvestTradeController {
    /**
     * 注入service类
     */
    @Autowired
    private PreTrdBondInvestTradeService preBondInvestTradeService;

    @Autowired
    private TrdBondInvestTradeService trdBondInvestTradeService;





    /**
     * 分页查询
     *
     * @return Page<TrdBondInvestTradeDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<PreTrdBondInvestTrade001Dto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return preBondInvestTradeService.selectForPage(page, length, param);
    }


    @RequestMapping("getPreTrdBondInvestTradeDto")
    public PreTrdBondInvestTrade002Dto getPreTrdRepoTradeDto(@RequestBody Map<String,Object> params){
        return preBondInvestTradeService.getPreTrdBondInvestTradeDto(params);
    }



    /**
     * 查询委托信息
     *
     * @param entrustId 委托编号
     * @return 委托信息
     * @see
     * @since 1.0
     */
    @RequestMapping("getEntrustInfo/{entrustId}")
    public BaseEtrInvestInfoDto getEntrustInfo(@PathVariable("entrustId") String entrustId) {
        return trdBondInvestTradeService.getEntrustInfo(entrustId);
    }
    @RequestMapping("getEntrustInfos/{entrustId}")
    public List<BaseEtrInvestInfoDto> getEntrustInfos(@PathVariable("entrustId") String entrustId) {
        return trdBondInvestTradeService.getEntrustInfos(entrustId);
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
        return trdBondInvestTradeService.getSecManageAcctId(param);
    }
    
    

    /**
     * 资产代码：下拉框
     *
     * @param params 画面参数
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return preBondInvestTradeService.getFinprodId(page, length, param);
    }
    /**
     * 资金账户下拉框
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("getacctNo/{page}/{length}")
    public Page<SelectOptionDto> getacctNo(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return preBondInvestTradeService.getacctNo(page, length, param);
    }
    /**
     * 资金账户下拉框
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @RequestMapping("getsecAcctId/{page}/{length}")
    public Page<SelectOptionDto> getsecAcctId(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return preBondInvestTradeService.getsecAcctId(page, length, param);
    }
    
    @RequestMapping("getdeliveryType")
    public List<SelectOptionDto> getdeliveryType(@RequestBody Map<String, Object> param) {
        return preBondInvestTradeService.getdeliveryType(param);
    }
    
    @RequestMapping("getclearType")
    public List<SelectOptionDto> getclearType(@RequestBody Map<String, Object> param) {
        return preBondInvestTradeService.getclearType(param);
    }
    

    /**
     * 查询债券cstp交易下拉框
     * @param param 输入参数
     * @return
     */
    @RequestMapping("querySecCstpInfoList/{page}/{length}")
    public Page<SelectOptionDto> querySecCstpInfoList(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                 @PathVariable("length") int length){
        Page<SelectOptionDto> suggestDtos = trdBondInvestTradeService.querySecCstpInfoList(page,length,param);
        return suggestDtos;
    }
    /**
     * 根据成交编号查询cstp交易信息
     * @param tradeId 成交编号
     * @param loginUserName 登录用户
     * @return cstp交易信息
     */

//    @GetMapping("querySecCstpInfoByNo")
//    public TrdBondInvestTradeCSTPDto querySecCstpInfoByNo(String fedealno, String loginUserName){
//        return trdBondInvestTradeService.querySecCstpInfoByNo(fedealno, loginUserName);
//    }





}
