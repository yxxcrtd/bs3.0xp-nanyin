/*
 * 文件名：EtrPlanAssetR001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrplanassetr.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrplanassetr.dto.EtrPlanAssetRDto;
import com.joyintech.service.entrust.etrplanassetr.service.EtrPlanAssetRService;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 计划类利率委托 〈功能详细描述〉
 * 
 * @author zhangjun
 * @version 1.0 2018年9月25日
 * @see EtrPlanAssetRController
 * @since 1.0
 */
@RestController("EtrPlanAssetRCtr_dtx")
@RequestMapping("/EtrPlanAssetR")
public class EtrPlanAssetRController {

    /**
     * 计划类利率委托微服务service
     */
    @Autowired
    private EtrPlanAssetRService etrPlanAssetService;

    /**
     * 分页查询 <功能详细描述>
     * 
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrPlanAssetRDto> selectForPage(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page,
                                               @PathVariable("length") int length)
        throws Exception {
        return etrPlanAssetService.selectForPage(page, length, param);
    }

    /**
     * 获取组合代码联想文本框信息 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetService.getentrustId(map);
        return temp;
    }

    /**
     * 获取资产代码联想文本框信息 <功能详细描述>
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getfinprodId")
    public List<SuggestDto> getfinprodId(String finprodId)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetService.getfinprodId(finprodId);
        return temp;
    }

    /**
     * 操作前校验 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrPlanAssetRDto dto) {
        return etrPlanAssetService.check(dto);
    }

    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("recall")
    public boolean recallDelegate(@RequestBody EtrPlanAssetRDto dto)
        throws IllegalAccessException,
        InvocationTargetException {
        return etrPlanAssetService.recallDelegate(dto);
    }

    /**
     * 关闭委托
     * 
     * @param entrustId
     * @param closeWay
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId, String closeWay) {
        return etrPlanAssetService.closeDelegate(entrustId, closeWay);
    }

    /**
     * 获取新建界面的资产代码联想下拉框框 <功能详细描述>
     * 
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrPlanAssetService.getfinprodIdForAdd(page,length,map);
        return temp;
    }

    /**
     * 获取新建界面的组合代码联想下拉框框 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length) {
        Page<SelectOptionDto> temp = etrPlanAssetService.getentrustIdAdd(page,length,map);
        return temp;
    }

    /**
     * 工作流完成方法 <功能详细描述>
     * 
     * @param dto EtrPlanAssetDto
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrPlanAssetRDto dto)
        throws IllegalAccessException,
        InvocationTargetException {
        etrPlanAssetService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrPlanAssetRDto dto){
        etrPlanAssetService.doInsert(dto, dto.getOperateType());
    }
    /**
     * 删除投资委托 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrPlanAssetService.deleteDelegate(entrustId);
    }
    /**
     * 
     * 通过主键查询数据
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrPlanAssetRDto selectByKey(String entrustId) {
        return etrPlanAssetService.selectByKey(entrustId);
    }
    /**
     * 
     * 
     * 根据资产代码查询资产类型
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("searchType")
    public String searchType(String finprodId){
        return etrPlanAssetService.searchType(finprodId);
    }
    /**
     * 
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrPlanAssetService.getProAvailableCash(param);
    }
    /**
     * 
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetAvailablePosition")
    public BigDecimal getAssetAvailablePosition(@RequestBody PtlSecPositionFlowDto dto) {
        return etrPlanAssetService.getAssetAvailablePosition(dto);
    }
    
    /**
     * 查询金融产品信息
     * @param finprodId
     * @return
     */
    @GetMapping("getFinProductInfo")
    public FinProduct getFinProductInfo(String finprodId){
        return etrPlanAssetService.getFinProductInfo(finprodId);
    }
}
