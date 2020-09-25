/*
 * 文件名：PtlActivityPeriod001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlactivityperiod.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlactivityperiod.dto.PtlActivityPeriod001Dto;
import com.joyintech.service.portfolio.ptlactivityperiod.service.PtlActivityPeriod001Service;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;

/**
 * 活期结息
 * @author fanshuanghu
 * @version 1.0 2018年9月17日
 * @see PtlActivityPeriod001Controller
 * @since 1.0
 */
@RestController("ptlActivityPeriod001Controller_dtx")
@RequestMapping("/ptlactivityperiod")
public class PtlActivityPeriod001Controller {
    /**
     * 注入service
     */
    @Autowired
    private PtlActivityPeriod001Service ptlActivityPeriod001Service;
    /**
     * 画面查询方法
     * @param param 请求参数
     * @param page 页码
     * @param length 每页条数
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<PtlActivityPeriod001Dto> selectAll(@RequestBody Map<String, Object> param,
                                                   @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        if(param.get("portfolioId") != null && !param.get("portfolioId").equals("")){
            List<String> portfolioId = new ArrayList<String>();
            String[] port = param.get("portfolioId").toString().split(",");
            for(int i = 0; i<port.length; i++){
                portfolioId.add(port[i]);
            }
            param.replace("portfolioId", portfolioId);
        }
        else{
            param.replace("portfolioId", null);
        } 
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(param.get("prodManager") != null && !param.get("prodManager").equals("")){
            List<String> prodManager = new ArrayList<String>();
            String[] port = param.get("prodManager").toString().split(",");
            for(int i = 0; i<port.length; i++){
                prodManager.add(port[i]);
            }
            param.replace("prodManager", prodManager);
        }
        else{
            param.replace("prodManager", null);
        } 
        // add by wangyang1 【吉林银行】 增加 产品经理  end 
        return ptlActivityPeriod001Service.selectForPage(page,length,param);
    }
    /**
     * 获取组合代码
     * @param map 请求参数
     * @return 组合代码集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getPortfolioId(@RequestBody Map<String,Object> map,
                                                @PathVariable int page,@PathVariable int length){
        return ptlActivityPeriod001Service.getPortfolioId(map,page,length);
    }
    /**
     * 获取组合代码
     * @param map 请求参数
     * @return 联想下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getPortfolioIdSug")
    public List<SuggestDto> getPortfolioIdSug(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getPortfolioIdSug(map);
    }
    /**
     * 获取账号下拉框集合
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getBusiAcctNo/{page}/{length}")
    public Page<SelectOptionDto> getBusiAcctNo(@RequestBody Map<String,Object> map,
                                               @PathVariable int page,@PathVariable int length){
        return ptlActivityPeriod001Service.getBusiAcctNo(map,page,length);
    }
    
    /**
     * 获取账号下拉框集合
     * @param map 请求参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getBankDep/{page}/{length}")
    public Page<SelectOptionDto> getBankDep(@RequestBody Map<String,Object> map,
                                            @PathVariable int page,@PathVariable int length){
        return ptlActivityPeriod001Service.getBankDep(map,page,length);
    }
    
    /**
     * 获取账号
     * @param map 请求参数
     * @return 联想下拉框集合
     * @see
     * @since 1.0
     */
    @PostMapping("getBusiNo")
    public List<SuggestDto> getBusiNo(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getBusiNo(map);
    }
    /**
     * 获取组合名称
     * @param map 请求参数
     * @return 组合名称
     * @see
     * @since 1.0
     */
    @PostMapping("getportFolioName")
    public String getportFolioName(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getportFolioName(map);
    }
    /**
     * 获取账号
     * @param map 请求参数集合
     * @return 账号
     * @see
     * @since 1.0
     */
    @PostMapping("getAcctNo")
    public String getAcctNo(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getAcctNo(map);
    }
    /**
     * 获取账号名称
     * @param map 请求参数集合
     * @return 账号名称
     * @see
     * @since 1.0
     */
    @PostMapping("getAcctName")
    public String getAcctName(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getAcctName(map);
    }
    /**
     * 获取上次收息日
     * @param map 账号,组合代码
     * @return 日期字符串
     * @see
     * @since 1.0
     */
    @PostMapping("getVDate")
    public String getVDate(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.getVDate(map);
    }
    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    @PostMapping("getCounterAcct/{page/{length}}")
    public Page<SelectOptionDto> getCounterAcct(@RequestBody Map<String,Object> map,
                                                @PathVariable int page,@PathVariable int length){
        return ptlActivityPeriod001Service.getCounterAcct(map,page,length);
    }
    /**
     * 获取划款信息
     * @param tradeId 交易编号
     * @return 划款实体类
     * @see
     * @since 1.0
     */
    @PostMapping("selectBussTrdTransferOrder")
    public List<BussTrdTransferOrderDto> selectBussTrdTransferOrder(@RequestBody Map<String,Object> map){
        return ptlActivityPeriod001Service.selectBussTrdTransferOrder(map);
    }
    
    /**
     * 划款金额默认为理论计提金额
     * @param paramMap 请求参数集合
     * @return 理论计提金额
     * @see
     * @since 1.0
     */
    @PostMapping("initTradeAmt")
    public BigDecimal initTradeAmt(@RequestBody Map<String,Object> paramMap) {
        return ptlActivityPeriod001Service.initTradeAmt(paramMap);
    }
    
    /**
     * 查询明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findDetailsPage")
    public List<PtlActivityPeriod001Dto> findDetailsPage(@RequestBody Map<String,Object> map) {
        return ptlActivityPeriod001Service.findDetailsPage(map);
        
    }
    
    /**
     * 查询子组合明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findChildrenPortfolioDetails")
    public List<PtlActivityPeriod001Dto> findChildrenPortfolioDetails(@RequestBody Map<String,Object> map) {
        return ptlActivityPeriod001Service.findChildrenPortfolioDetails(map);
    }
    
    
    /**
     * 通过交易编号查询组合明细信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findDetailsByBusiNo")
    public List<PtlActivityPeriod001Dto> findDetailsByBusiNo(@RequestBody Map<String,Object> map) {
        return ptlActivityPeriod001Service.findDetailsByBusiNo(map);
        
    }
    
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号
     * @see
     * @since 1.0
     */
    @PostMapping("getRepAcctNo")
    public String getRepAcctNo(@RequestBody Map<String,Object> map) {
        return ptlActivityPeriod001Service.getAcctNo(map);
    }
    
    
    /**
     * 获取报送登记编号
     * @param map 组合代码（市场代码）
     * @return 登记编号
     * @see
     * @since 1.0
     */
    @PostMapping("getProdRegistCode")
    public List<String> getProdRegistCode(@RequestBody Map<String,Object> map) {
        return ptlActivityPeriod001Service.getProdRegistCode(map);
    }
    
}
