/*
 * 文件名：TrdPlanCcyInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.planccy.controller;

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
import com.joyintech.service.trade.planccy.dto.TrdPlanCcyInvestTradeDto;
import com.joyintech.service.trade.planccy.service.TrdPlanCcyInvestTradeService;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月7日
 * @see TrdPlanCcyInvestTradeController
 * @since 1.0
 */
@RestController("TrdPlanCcyInvestTradeCtr_dtx")
@RequestMapping("/TrdPlanCcyInvestTrade")
public class TrdPlanCcyInvestTradeController {

    /**
     * 
     */
    @Autowired
    private TrdPlanCcyInvestTradeService trdPlanCcyInvestTradeService;
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
    public Page<TrdPlanCcyInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdPlanCcyInvestTradeService.selectForPage(page, length, param);
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
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param) {
        return trdPlanCcyInvestTradeService.getFinprodId(param);
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
    public void doInsert(@RequestBody TrdPlanCcyInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdPlanCcyInvestTradeService.doInsert(entity);
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
    public void doComplete(@RequestBody TrdPlanCcyInvestTradeDto entity){
         trdPlanCcyInvestTradeService.doComplete(entity);
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
    public void doUpdate(@RequestBody TrdPlanCcyInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdPlanCcyInvestTradeService.doUpdate(entity);
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
        trdPlanCcyInvestTradeService.doDelete(workflowId);
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
        return trdPlanCcyInvestTradeService.getProAvailableCash(param);
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
        return trdPlanCcyInvestTradeService.getPtlSecPosition(dto);
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
        return trdPlanCcyInvestTradeService.getFinDto(tradId);
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
    public TrdPlanCcyInvestTradeDto getTrdFundDto(String tradId,String feeType){
        //YUCHUBS-647 交易查询时费用为空 guyunfei 20190910 start
        return trdPlanCcyInvestTradeService.getTrdFundDto(tradId,feeType);
        //YUCHUBS-647 交易查询时费用为空 guyunfei 20190910 end
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
        return trdPlanCcyInvestTradeService.getPrice(map);
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
        return trdPlanCcyInvestTradeService.getEtrustDto(entrustId);
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
        return trdPlanCcyInvestTradeService.getPurSpeed(param);
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
    public void doInsertRedemption(@RequestBody TrdPlanCcyInvestTradeDto entity){
        trdPlanCcyInvestTradeService.doInsertRedemption(entity);
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
        return trdPlanCcyInvestTradeService.selRedemption(tradeId);
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
    public boolean revokeCheck(@RequestBody TrdPlanCcyInvestTradeDto dto){
        return trdPlanCcyInvestTradeService.revokeCheck(dto);
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
    public TrdPlanCcyInvestTradeDto getTrdFundDtoForAccount(String tradeId){
        return trdPlanCcyInvestTradeService.getTrdFundDtoForAccount(tradeId);
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
        return trdPlanCcyInvestTradeService.selectOrdersByBizCode(bizCode);
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
        return trdPlanCcyInvestTradeService.selectAccountTrdId(param);
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
        return trdPlanCcyInvestTradeService.selectAccountTrdIdinwfl(tradId);
    }
    /**
     * 
     * 插入交易到账撤销信息
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("revokeAccount")
    public void revokeAccount(@RequestBody TrdPlanCcyInvestTradeDto entity){
        trdPlanCcyInvestTradeService.revokeAccount(entity);
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
        return trdPlanCcyInvestTradeService.selectEntrustAmt(param);
    }
    
    /**
     * 多组合交易查询
     * @param param
     * @return
     */
    @RequestMapping("selectAllTrade")
    public List<TrdPlanCcyInvestTradeDto> selectAllTrade(@RequestBody Map<String, Object> param) {
    	return trdPlanCcyInvestTradeService.selectAllTrade(param);
    }
    
    /**
     * 多组合交易点击确认按钮的校验
     * @param param
     * @return
     */
    @RequestMapping("isCheckConfirm")
    public boolean isCheckConfirm(@RequestBody Map<String, Object> param) {
    	return trdPlanCcyInvestTradeService.isCheckConfirm(param);
    }
    
    /**
	 * 确认页面组合分配列表查询
	 * @param param
	 * @return
	 */
    @RequestMapping("selectTrdTransfenpeiList")
    public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(@RequestBody Map<String, Object> param) {
    	return trdPlanCcyInvestTradeService.selectTrdTransfenpeiList(param);
    }
    
    @PostMapping("getDoEntrustMoneyByEntrustId")
    public BigDecimal getDoEntrustMoneyByEntrustId(@RequestBody Map<String, Object> param) {
        return trdPlanCcyInvestTradeService.getDoEntrustMoneyByEntrustId(param);
    }
}
