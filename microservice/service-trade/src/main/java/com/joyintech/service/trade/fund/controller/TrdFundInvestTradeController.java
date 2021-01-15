/*
 * 文件名：TrdFundInvestTradeController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.fund.controller;

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
import com.joyintech.service.trade.fund.dto.TrdFundInvestTradeDto;
import com.joyintech.service.trade.fund.dto.WorkFlowDto;
import com.joyintech.service.trade.fund.service.TrdFundInvestTradeService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;

/**
 * 基金投资交易微服务端controller
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月19日
 * @see TrdFundInvestTradeController
 * @since 1.0
 */
@RestController("TrdFundInvestTradeCtr_dtx")
@RequestMapping("/TrdFundInvestTrade")
public class TrdFundInvestTradeController {

    @Autowired
    private TrdFundInvestTradeService trdFundInvestTradeService;
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
    public Page<TrdFundInvestTradeDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdFundInvestTradeService.selectForPage(page, length, param);
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
        return trdFundInvestTradeService.getFinprodId(page, length,param);
    }

    //    资产推荐方 新增 zhuyongkang 20191023 start
    /**
     * 构建推荐方下拉框
     *
     * @param param 参数
     * @return 下拉框查询结果
     * @see
     * @since 1.0
     */
    @RequestMapping("getAssetRecommandOrg/{page}/{length}")
    public Page<SelectOptionDto> getAssetRecommandOrg(@RequestBody Map<String, Object> param,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length) {
        return trdFundInvestTradeService.getAssetRecommandOrg(page, length, param);
    }
    //    资产推荐方 新增 zhuyongkang 20191023 end

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
    public void doInsert(@RequestBody TrdFundInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdFundInvestTradeService.doInsert(entity);
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
    public void doComplete(@RequestBody TrdFundInvestTradeDto entity){
         trdFundInvestTradeService.doComplete(entity);
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
    public void doUpdate(@RequestBody TrdFundInvestTradeDto entity) throws IllegalAccessException, InvocationTargetException{
        trdFundInvestTradeService.doUpdate(entity);
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
        trdFundInvestTradeService.doDelete(workflowId);
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
        return trdFundInvestTradeService.getProAvailableCash(param);
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
        return trdFundInvestTradeService.getPtlSecPosition(dto);
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
        return trdFundInvestTradeService.getFinDto(tradId);
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
    @PostMapping("getTrdFundDto")
    public TrdFundInvestTradeDto getTrdFundDto(@RequestBody Map<String, Object> params){
        return trdFundInvestTradeService.getTrdFundDto(params);
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
        return trdFundInvestTradeService.getPrice(map);
    }

    /**
     * 查询待结转份额
     * @param map
     * @return
     */
    @RequestMapping("getcarryAmt")
    public BigDecimal getcarryAmt(@RequestBody Map<String,Object> map){
        return trdFundInvestTradeService.getcarryAmt(map);
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
        return trdFundInvestTradeService.getEtrustDto(entrustId);
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
        return trdFundInvestTradeService.getPurSpeed(param);
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
    public void doInsertRedemption(@RequestBody TrdFundInvestTradeDto entity){
        trdFundInvestTradeService.doInsertRedemption(entity);
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
        return trdFundInvestTradeService.selRedemption(tradeId);
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
    public boolean revokeCheck(@RequestBody TrdFundInvestTradeDto dto){
        return trdFundInvestTradeService.revokeCheck(dto);
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
    public TrdFundInvestTradeDto getTrdFundDtoForAccount(String tradeId){
        return trdFundInvestTradeService.getTrdFundDtoForAccount(tradeId);
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
        return trdFundInvestTradeService.selectOrdersByBizCode(bizCode);
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
        return trdFundInvestTradeService.selectAccountTrdId(param);
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
        return trdFundInvestTradeService.selectAccountTrdIdinwfl(tradId);
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
    public void revokeAccount(@RequestBody TrdFundInvestTradeDto entity){
        trdFundInvestTradeService.revokeAccount(entity);
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
        return trdFundInvestTradeService.selectEntrustAmt(param);
    }
    
    /**
     * 
     * 查询资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectManeger")
    public String selectManeger(String issuer){
        return trdFundInvestTradeService.selectManeger(issuer);
    }
    
    /**
     * 
     * 通过资产代码查找资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectFManeger")
    public String selectFManeger(String finprodId){
    	return trdFundInvestTradeService.selectFManeger(finprodId);
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
    @PostMapping("selectTradeAfter")
    public List<WorkFlowDto> selectTradeAfter(@RequestBody Map<String, Object> param){
        return trdFundInvestTradeService.selectTradeAfter(param);
    }

    /**
     * 投管接口（到账确认信息输入接口）
     * 
     * @param entity 参数
     * @return 是否成功
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/insertPortfolioInfo")
    public String insertPortfolioInfo(@RequestBody TrdFundInvestTradeDto entity) {
        return trdFundInvestTradeService.do赎回确认到账生成(entity);
    }

    /**
     * 确认页面组合分配列表查询
     * @param param
     * @return
     */
    @RequestMapping("selectTrdTransfenpeiList")
    public List<BussTrdTransfenpeiDto> selectTrdTransfenpeiList(@RequestBody Map<String, Object> param) {
        return trdFundInvestTradeService.selectTrdTransfenpeiList(param);
    }
}
