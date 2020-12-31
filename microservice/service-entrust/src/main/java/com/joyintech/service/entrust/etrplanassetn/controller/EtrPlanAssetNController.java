/*
 * 文件名：EtrPlanAssetNController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrplanassetn.controller;

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
import com.joyintech.service.entrust.etrplanassetn.dto.EtrPlanAssetNDto;
import com.joyintech.service.entrust.etrplanassetn.service.EtrPlanAssetNService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 计划类净值委托微服务controller
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月8日
 * @see EtrPlanAssetNController
 * @since 1.0
 */
@RestController("EtrPlanAssetNCtr_dtx")
@RequestMapping("/EtrPlanAssetN")
public class EtrPlanAssetNController  {

    @Autowired
    private EtrPlanAssetNService etrPlanAssetNService;
    /**
     * 
     * 计划类净值委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrPlanAssetNDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrPlanAssetNService.selectForPage(page,length,param);
    }
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustId")
    public List<SuggestDto> getentrustId(@RequestBody Map<String, Object> map)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetNService.getentrustId(map);
        return temp;
    }
    /**
     * 
     * 获取资产代码联想文本框信息
     * <功能详细描述>
     * @return 
     * @throws Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getfinprodId")
    public List<SuggestDto> getfinprodId(String finprodId)
        throws Exception {
        List<SuggestDto> temp = etrPlanAssetNService.getfinprodId(finprodId);
        return temp;
    }
    
    /**
     * 
     * 获取新建界面的资产代码联想下拉框框
     * <功能详细描述>
     * @param finprodId
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("getfinprodIdForAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, String> map, @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrPlanAssetNService.getfinprodIdForAdd(page,length,map);
        return temp;
    }
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getentrustIdAdd/{page}/{length}")
    @ResponseBody
    public Page<SelectOptionDto> getentrustIdAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length){
        Page<SelectOptionDto> temp = etrPlanAssetNService.getentrustIdAdd(page,length,map);
        return temp;
    }
    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrPlanAssetNDto dto){
        return etrPlanAssetNService.check(dto);
    }
    /**
     * 撤回委托 
     * <功能详细描述>
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
    public boolean recallDelegate(@RequestBody EtrPlanAssetNDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrPlanAssetNService.recallDelegate(dto);
    }
    /**
     * 关闭委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("close")
    public boolean closeDelegate(String entrustId,String closeWay) {
        return etrPlanAssetNService.closeDelegate(entrustId, closeWay);
    }
    /**
     * 
     * 工作流完成方法
     * <功能详细描述>
     * @param map
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrPlanAssetNDto dto) throws IllegalAccessException, InvocationTargetException{
        etrPlanAssetNService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrPlanAssetNDto dto){
        etrPlanAssetNService.doInsert(dto, dto.getOperateType());
    }
    /**
     * 
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrPlanAssetNService.deleteDelegate(entrustId);
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
    public EtrPlanAssetNDto selectByKey(String entrustId) {
        return etrPlanAssetNService.selectByKey(entrustId);
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
    public Map<String, String> searchType(String finprodId){
        return etrPlanAssetNService.searchType(finprodId);
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
    @ResponseBody
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrPlanAssetNService.getProAvailableCash(param);
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
        return etrPlanAssetNService.getAssetAvailablePosition(dto);
    }


    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    @GetMapping("getCounterName")
    public String getCounterName(String counterId){
        return etrPlanAssetNService.getCounterName(counterId);
    }
}
