/*
 * 文件名：TrdConBondController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.conbond.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.conbond.dto.TrdConBondDto;
import com.joyintech.service.trade.conbond.service.TrdConBondService;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 可转债微服务
 *
 * @author wangpeng
 * @version 1.0 2019年11月14日
 * @see TrdConBondController
 * @since 1.0
 */
@RestController
@RequestMapping("/TrdConBond")
public class TrdConBondController {
    /**
     * 注入service类
     */
    @Autowired
    private TrdConBondService trdConBondService;

    /**
     * 分页查询
     *
     * @return Page<TrdConBondDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<TrdConBondDto> queryForPage(@RequestBody Map<String, Object> param,
                                                    @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        return trdConBondService.selectForPage(page, length, param);
    }

    /**
     * 查询债券投资交易信息
     *
     * @param tradeId 交易编号
     * @return 债券投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdConBondDto/{tradeId}")
    public TrdConBondDto getTrdConBondDto(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdConBondService.getTrdConBondDto(tradeId);
    }
    
    /**
     * 查询退款到账数据
     *
     * @param tradeId 交易编号
     * @return 债券投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdConBondDtoForAccount/{tradeId}")
    public TrdConBondDto getTrdConBondDtoForAccount(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdConBondService.getTrdConBondDtoForAccount(tradeId);
    }
    
    /**
     * 查询退款到账数据
     *
     * @param tradeId 交易编号
     * @return 债券投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getTrdConBondDtoForTradeId/{tradeId}")
    public TrdConBondDto getTrdConBondDtoForTradeId(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdConBondService.getTrdConBondDtoForTradeId(tradeId);
    }
    
    /**
     * 查询债券投资交易信息
     *
     * @param tradeId 交易编号
     * @return 债券投资交易信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("getBusicode/{tradeId}")
    public Map<String, Object> getBusicode(@PathVariable("tradeId") String tradeId)
            throws Exception {
        return trdConBondService.getBusicode(tradeId);
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
        return trdConBondService.getSecManageAcctId(param);
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
    public void doInsert(@RequestBody TrdConBondDto entity) throws IllegalAccessException, InvocationTargetException{
    	trdConBondService.doInsert(entity);
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
    public void doComplete(@RequestBody TrdConBondDto entity){
    	trdConBondService.doComplete(entity);
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
    public void doUpdate(@RequestBody TrdConBondDto entity) throws IllegalAccessException, InvocationTargetException{
    	trdConBondService.doUpdate(entity);
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
    	trdConBondService.doDelete(workflowId);
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
        return trdConBondService.getFinprodId(page, length, param);
    }

    /**
     * 查询清算指令
     *
     * @param finprodId 金融产品代码
     * @see
     * @since 1.0
     */
    @RequestMapping("getStlPayOrders")
    public List<StlPayOrders> getStlPayOrders(@RequestBody Map<String, Object> param) {
        return trdConBondService.getStlPayOrders(param);
    }
    
    @RequestMapping("getTrdProductDeals")
    public List<StlPayOrders> getTrdProductDeals(@RequestBody Map<String, Object> param) {
        return trdConBondService.getTrdProductDeals(param);
    }


    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    @PostMapping("getTongDaoCash")
    public BigDecimal getTongDaoCash(@RequestBody Map<String, Object> map){
        BigDecimal big = trdConBondService.getTongDaoCash(map);
        if(big!=null){
            return big;
        }
        return BigDecimal.ZERO;
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
    public boolean revokeCheck(@RequestBody TrdConBondDto dto){
        return trdConBondService.revokeCheck(dto);
    }
    
    /**
     * 
     * 查询撤销数据是否有撤销流程中数据
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("revokeCheckWfl")
    public boolean revokeCheckWfl(@RequestBody TrdConBondDto dto){
        return trdConBondService.revokeCheckWfl(dto);
    }
    
    /**
     * 
     * 获取生效退款到账交易编号
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getTradeId/{busiCode}")
    public String getTradeId(@PathVariable("busiCode") String busiCode){
        return trdConBondService.getTradeId(busiCode);
    }
    
    /**
     * 
     * 获取退款到账交易编号(包括流程中）
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("getRefundTradeId/{busiCode}")
    public String getRefundTradeId(@PathVariable("busiCode") String busiCode){
        return trdConBondService.getRefundTradeId(busiCode);
    }

}
