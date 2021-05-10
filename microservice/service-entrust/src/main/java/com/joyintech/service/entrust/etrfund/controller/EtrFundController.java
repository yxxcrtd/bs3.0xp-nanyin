/*
 * 文件名：EtrFundController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrfund.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrfund.dto.EtrFundDto;
import com.joyintech.service.entrust.etrfund.service.EtrFundService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月8日
 * @see EtrFundController
 * @since 1.0
 */
@RestController("EtrFundCtr_dtx")
@RequestMapping("/EtrFund")
public class EtrFundController {
    
    /**
     * 自定义service
     */
    @Autowired
    private EtrFundService etrFundService;
    
    /**
     * 
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * <功能详细描述>
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrFundDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrFundDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page,
                                          @PathVariable("length") int length)throws Exception {
        return etrFundService.selectForPage(page,length,pageParam);
    }
    
    /**
     * 
     * 组合代码联想文本框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getPortfolioId")
    public List<SuggestDto> getPortfolioId(@RequestBody Map<String, Object> param){
        
        return etrFundService.getPortfolioId(param);
    }
    
    /**
     * 
     * 资产代码联想文本框
     * <功能详细描述>
     * @param finprodId String
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    @GetMapping("/getFinprodMarketId")
    public List<SuggestDto> getFinprodMarketId(String finprodId, String pageId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("pageId", pageId);

        return etrFundService.getFinprodMarketId(map);
    }
    
    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto EtrFundDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrFundDto dto){
        return etrFundService.check(dto);
    }
    
    /**
     * 
     * 删除委托
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/delete")
    public boolean deleteDelegate(String entrustId) {
        return etrFundService.deleteDelegate(entrustId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody EtrFundDto dto) throws IllegalAccessException, InvocationTargetException {
        etrFundService.doInsert(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doDelete")
    public void doDelete(@RequestBody EtrFundDto dto) throws IllegalAccessException, InvocationTargetException {
        etrFundService.doDelete(dto);
    }
    
    /**
     * 
     * 撤销委托
     * <功能详细描述>
     * @param entrustId String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/recall")
    public boolean recallDelegate(String entrustId){
        return etrFundService.recallDelegate(entrustId);
    }
    
    /**
     * 
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("/close")
    public boolean closeDelegate(String entrustId,String closeWay){
        return etrFundService.closeDelegate(entrustId, closeWay);
    }
    
    /**
     * 
     * 新建页面资产代码联想下拉框
     * <功能详细描述>
     * @param param 输入参数
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getSelectFinprodMarketId/{page}/{length}")
    public Page<SelectOptionDto> getSelectFinprodMarketId(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                          @PathVariable("length") int length){
        return etrFundService.getSelectFinprodMarketId(page,length,param);
    }
    
    /**
     * 
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @PostMapping("/getSelectPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> getSelectPortfolioId(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                      @PathVariable("length") int length){
        return etrFundService.getSelectPortfolioId(page,length,param);
    }
    
    /**
     * 
     * 
     * 工作流完成操作
     * @param dto EtrFundDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody EtrFundDto dto) throws IllegalAccessException, InvocationTargetException {
        etrFundService.doComplete(dto);
    }
    
    /**
     * 
     * 编辑、双击页面查看详情
     * 根据主键查询
     * @param entrustId String
     * @param finprodId String
     * @param portfolioId String
     * @return EtrFundDto
     * @see
     * @since 1.0
     */
    @GetMapping("/selectByKey")
    public EtrFundDto selectByKey(String entrustId,String finprodId, String portfolioId){
        
        return etrFundService.selectByKey(entrustId, finprodId, portfolioId);
    }
    
    /**
     * 
     * 根据金融产品代码获取委托金额
     * <功能详细描述>
     * @param finprodId String
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @GetMapping("/selectEntrustAmt")
    public BigDecimal selectEntrustAmt(String finprodId){
        return etrFundService.selectEntrustAmt(finprodId);
    }
    /**
     * 
     * 根据金融产品代码获取发行规模
     * <功能详细描述>
     * @param finprodId String
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @GetMapping("/selectIssueAmt")
    public BigDecimal selectIssueAmt(String finprodId){
        return etrFundService.selectIssueAmt(finprodId); 
    }
    
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto PtlCashPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrFundService.getProAvailableCash(param);
    }
    /**
     * 
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto PtlSecPositionFlowDto
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return etrFundService.getAssetAvailablePosition(dto);
    }
    
    /**
     * 
     * 查询单位净值
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    @PostMapping("getPrice")
    public BigDecimal getPrice(@RequestBody Map<String,Object> map){
        
        return etrFundService.getPrice(map);
    }
    
    /**
     * 新建页面通道下拉框
     * @param param
     * @param page
     * @param length
     * @return
     */
    @PostMapping("/getSelectTongDao/{page}/{length}")
    public Page<SelectOptionDto> getSelectTongDao(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                          @PathVariable("length") int length){
        return etrFundService.getSelectTongDao(page,length,param);
    }
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    @PostMapping("/getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String, Object> map){
    	BigDecimal big = etrFundService.getTongDaoCash(map);
    	if(big!=null){
    		return big;
    	}
    	return BigDecimal.ZERO;
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
    @ResponseBody
    public String selectManeger(String issuer){
        return etrFundService.selectManeger(issuer);
    }

    @PostMapping("/findSameNameCounterId")
    public List<String> findSameNameCounterId(@RequestBody Map<String, Object> map) {
        return etrFundService.findSameNameCounterId(map);
    }
}
