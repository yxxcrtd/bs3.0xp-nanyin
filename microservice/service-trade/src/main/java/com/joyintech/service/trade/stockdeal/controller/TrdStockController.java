/*
 * 文件名：TrdStockController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.stockdeal.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.service.trade.bond.dto.TrdBondInvestTradeCSTPDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.stockdeal.dto.TrdStockDto;
import com.joyintech.service.trade.stockdeal.service.TrdStockService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

@RestController
@RequestMapping("/TrdStock")
public class TrdStockController {
	 /**
     * 注入service类
     */
    @Autowired
    private TrdStockService trdStockService;

    /**
     * 分页查询
     *
     * @return Page<TrdProductDeal>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdStockDto> queryForPage(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdStockService.selectForPage(page, length, pageParam);
    }

    /**
     * 查询股票交易信息
     *
     * @param tradeId 交易编号
     * @return 股票交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdProductDealDto/{tradeId}")
    public TrdStockDto getTrdProductDealDto(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdStockService.getTrdProductDealDto(tradeId);
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
    public TrdStockDto getEntrustInfo(@PathVariable("entrustId") String entrustId) {
        return trdStockService.getEntrustInfo(entrustId);
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
        return trdStockService.getSecManageAcctId(param);
    }
    
    /**
     * 通道：联想框文本框
     *
     * @param param 画面参数
     * @return 通道：联想框文本框
     * @see
     * @since 1.0
     */
   /* @RequestMapping("getChannel")
    public List<SuggestDto> getChannel(@RequestBody Map<String, Object> param) {
        return trdStockService.getChannel(param);
    }

    @RequestMapping("getChannelInfo")
    public List<String> getChannelInfo(@RequestBody Map<String, Object> param) {
        return trdStockService.getChannelInfo(param);
    }*/


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
        return trdStockService.getFinprodId(page, length, param);
    }
    
    /**
     * 取得证券托管户的登记托管机构
     * 
     * @param param Map<String, Object>
     * @return 证券托管户的登记托管机构
     * @see
     * @since 1.0
     */
    @GetMapping("getRegistOrg")
    public String getRegistOrg(String secAcctId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("secAcctId", secAcctId);
        return trdStockService.getRegistOrg(map);
    }
    
    
    
    /**
     *@title: getProAvailableCash
     *@description: 组合可用现金查询
     *@author: junYin
     *@date: 2019/06/04 11:09
     *@param: * @param param 1
     *@return: java.util.Map<java.lang.String,java.lang.Object>
     *@throws:
     */
    @PostMapping("getProAvailableCash")
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return trdStockService.getProAvailableCash(param);
    }
    /**
     *@title: getAssetAvailablePosition
     *@description: 组合可用持仓查询
     *@author: junYin
     *@date: 2019/06/04 11:09
     *@param: * @param dto 1
     *@return: java.math.BigDecimal
     *@throws:
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return trdStockService.getAssetAvailablePosition(dto);
    }
    
    
    
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
   /* @PostMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String, Object> map){
        BigDecimal big = trdStockService.getTongDaoCash(map);
        if(big!=null){
            return big;
        }
        return BigDecimal.ZERO;
    }*/
    /**
     * 查询债券cstp交易下拉框
     * @param param 输入参数
     * @return
     */
    @RequestMapping("queryRepoCstpInfoList")
    public List<SuggestDto> queryRepoCstpInfoList(@RequestBody Map<String, Object> param){
        return trdStockService.queryRepoCstpInfoList(param);
    }
    /**
     * 根据成交编号查询cstp交易信息
     * @param
     * @param loginUserName 登录用户
     * @return cstp交易信息
     */

    @GetMapping("querySecCstpInfoByNo")
    public TrdStockDto querySecCstpInfoByNo(String fedealno, String loginUserName){
        return trdStockService.querySecCstpInfoByNo(fedealno, loginUserName);
    }

}
