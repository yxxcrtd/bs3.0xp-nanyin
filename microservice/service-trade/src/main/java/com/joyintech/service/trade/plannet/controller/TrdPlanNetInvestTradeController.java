/*
 * 文件名：TrdPlanNetInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年12月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.plannet.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.plannet.dto.TrdPlanNetInvestTradeDto;
import com.joyintech.service.trade.plannet.service.TrdPlanNetInvestTradeService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;

/**
 * 计划类（净值）投资交易
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年12月2日
 * @see TrdPlanNetInvestTradeController
 * @since 1.0
 */
@RestController("TrdPlanNetInvestTradeCtr_dtx")
@RequestMapping("/TrdPlanNetInvestTrade")
public class TrdPlanNetInvestTradeController {

    @Autowired
    private TrdPlanNetInvestTradeService trdPlanNetInvestTradeService;
    
    /**
     * 
     * 分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdPlanNetInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdPlanNetInvestTradeService.selectForPage(page, length, param);
    }
    
    /**
     * 
     * 资产代码联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getFinprodId/{page}/{length}")
    public Page<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param,@PathVariable("page") int page, @PathVariable("length") int length) {
        return trdPlanNetInvestTradeService.getFinprodId(page, length,param);
    }
    
    /**
     * 
     * 提交进入流程时调用方法
     * <功能详细描述>
     * @param entity
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doInsert")
    public void doInsert(@RequestBody TrdPlanNetInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdPlanNetInvestTradeService.doInsert(entity);
    }
    /**
     * 
     * 流程处理结束时调用方法
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doComplete")
    public void doComplete(@RequestBody TrdPlanNetInvestTradeDto entity){
         trdPlanNetInvestTradeService.doComplete(entity);
    }
    /**
     * 
     * 经代办修改业务流程表方法
     * <功能详细描述>
     * @param entity
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doUpdate")
    public void doUpdate(@RequestBody TrdPlanNetInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdPlanNetInvestTradeService.doUpdate(entity);
    }
    /**
     * 
     * 流程作废时删除业务流程表方法
     * <功能详细描述>
     * @param workflowId
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("doDelete")
    public void doDelete(String workflowId){
        trdPlanNetInvestTradeService.doDelete(workflowId);
    }
    
    /**
     * 
     * 查询组合可用现金
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param){
        return trdPlanNetInvestTradeService.getProAvailableCash(param);
    }
    /**
     * 
     * 查询组合可用持仓
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getPtlSecPosition")
    public BigDecimal getPtlSecPosition(@RequestBody PtlSecPositionFlowDto dto){
        return trdPlanNetInvestTradeService.getPtlSecPosition(dto);
    }
    /**
     * 
     * 获取交易dto
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getFinDto")
    public List<BaseTradeProductDealDto> getFinDto(String tradId){
        return trdPlanNetInvestTradeService.getFinDto(tradId);
    }
    
    /**
     * 
     * 查询反显数据
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTrdFundDto")
    public TrdPlanNetInvestTradeDto getTrdFundDto(String tradId){
        return trdPlanNetInvestTradeService.getTrdFundDto(tradId);
    }
    
    /**
     * 
     * 查询单位净值
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getPrice")
    public BigDecimal getPrice(@RequestBody Map<String, Object> map){
        return trdPlanNetInvestTradeService.getPrice(map);
    }
    /**
     * 
     * 查询投资委托DTO
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getEtrustDto")
    public BaseEtrInvestInfoDto getEtrustDto(String entrustId){
        return trdPlanNetInvestTradeService.getEtrustDto(entrustId);
    }
    
    /**
     * 
     * 查询资产代码的交易方式
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getPurSpeed")
    public Map<String, Object> getPurSpeed(@RequestBody Map<String, Object> param){
        return trdPlanNetInvestTradeService.getPurSpeed(param);
    }
    /**
     * 
     * 插入交易到账信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doInsertRedemption")
    public void doInsertRedemption(@RequestBody TrdPlanNetInvestTradeDto entity){
        trdPlanNetInvestTradeService.doInsertRedemption(entity);
    }
    /**
     * 
     * 插入交易到账信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doDeleteRedemption")
    public void doDeleteRedemption(@RequestBody TrdPlanNetInvestTradeDto entity){
        trdPlanNetInvestTradeService.doDeleteRedemption(entity);
    }    
    /**
     * 
     * 查询到账交易是否托管匹配
     * <功能详细描述>
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selRedemption")
    public Integer selRedemption(String tradeId){
        return trdPlanNetInvestTradeService.selRedemption(tradeId);
    }
    /**
     * 
     * 查询撤销数据是否有后继交易
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("revokeCheck")
    public boolean revokeCheck(@RequestBody TrdPlanNetInvestTradeDto dto){
        return trdPlanNetInvestTradeService.revokeCheck(dto);
    }
    /**
     * 
     * 查询交易到账数据
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTrdFundDtoForAccount")
    public TrdPlanNetInvestTradeDto getTrdFundDtoForAccount(String tradeId){
        return trdPlanNetInvestTradeService.getTrdFundDtoForAccount(tradeId);
    }
    /**
     * 
     * 查询业务流程表中的划款指令数据
     * <功能详细描述>
     * @param bizCode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectOrdersByBizCode")
    public List<BussTrdTransferOrderBase> selectOrdersByBizCode(String bizCode) {
        return trdPlanNetInvestTradeService.selectOrdersByBizCode(bizCode);
    }
    /**
     * 
     * 根据赎回确认交易编号查询赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectAccountTrdId")
    public Map<String, Object> selectAccountTrdId(@RequestBody Map<String, Object> param){
        return trdPlanNetInvestTradeService.selectAccountTrdId(param);
    }
    /**
     * 
     * 根据赎回确认交易编号查询赎回到账交易编号
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAccountTrdIdinwfl")
    public Map<String, Object> selectAccountTrdIdinwfl(String tradId){
        return trdPlanNetInvestTradeService.selectAccountTrdIdinwfl(tradId);
    }
    /**
     * 
     * 插入交易到账撤销信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @PostMapping("revokeAccount")
    @Transactional
    @DTXTransaction
    public void revokeAccount(@RequestBody TrdPlanNetInvestTradeDto entity){
        trdPlanNetInvestTradeService.revokeAccount(entity);
    }
    /**
     * 
     * 取得反显费用明细
     * <功能详细描述>
     * @param tradId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getTradeRefrence")
    public Map<String, Object> getTradeRefrence(String tradId){
        return trdPlanNetInvestTradeService.getTradeRefrence(tradId);
    }
    /**
     * 
     * 查询委托可用金额
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectEntrustAmt")
    public Map<String, Object> selectEntrustAmt(@RequestBody Map<String, Object> param){
        return trdPlanNetInvestTradeService.selectEntrustAmt(param);
    }
    
    /**
	 * 确认页面组合分配列表查询
	 * @param param
	 * @return
	 */
    @RequestMapping("selectTrdTransfenpeiList")
    public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(@RequestBody Map<String, Object> param) {
    	return trdPlanNetInvestTradeService.selectTrdTransfenpeiList(param);
    }
    
    /**
	 * 根据资产代码查询持有金额
	 * @param param
	 * @return
	 */
    @GetMapping("queryHoldAmt")
	public BigDecimal queryHoldAmt(String finprodId) {
        return trdPlanNetInvestTradeService.queryHoldAmt(finprodId);
    }
	
	/**
	 * 根据资产代码查询发行金额
	 * @param param
	 * @return
	 */
    @GetMapping("queryIssueAmt")
	public BigDecimal queryIssueAmt(String finprodId) {
        return trdPlanNetInvestTradeService.queryIssueAmt(finprodId);
    }
}
