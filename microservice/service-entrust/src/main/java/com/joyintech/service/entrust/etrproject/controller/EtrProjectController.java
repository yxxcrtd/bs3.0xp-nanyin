/*
 * 文件名：EtrProjectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrproject.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrproject.dto.EtrProjectDto;
import com.joyintech.service.entrust.etrproject.service.EtrProjectService;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * 项目类委托
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年10月11日
 * @see EtrProjectController
 * @since 1.0
 */
@RestController("EtrProjectCtr_dtx")
@RequestMapping("/EtrProject")
public class EtrProjectController {

    /**
     * 微服务端service
     */
    @Autowired
    private EtrProjectService etrProjectService;
    
    /**
     * 
     * 项目类委托分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<EtrProjectDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        String assetType1 = (String)param.get("assetType1");
        if(StringUtil.isNotEmpty(assetType1)){
            param.put("assetType1List", StringUtil.split(assetType1, GlobalConstant.COMMA));
        }
        return etrProjectService.selectForPage(page,length,param);
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
        List<SuggestDto> temp = etrProjectService.getentrustId(map);
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
        List<SuggestDto> temp = etrProjectService.getfinprodId(finprodId);
        return temp;
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
    public boolean recallDelegate(@RequestBody EtrProjectDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrProjectService.recallDelegate(dto);
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
        return etrProjectService.closeDelegate(entrustId, closeWay);
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
        return etrProjectService.deleteDelegate(entrustId);
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
    public Page<SelectOptionDto> getfinprodIdForAdd(@RequestBody Map<String, Object> map, @PathVariable("page") int page, @PathVariable("length") int length)
        throws Exception {
        Page<SelectOptionDto> temp = etrProjectService.getfinprodIdForAdd(page,length,map);
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
        Page<SelectOptionDto> temp = etrProjectService.getentrustIdAdd(page,length,map);
        return temp;
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
    public void doComplete(@RequestBody EtrProjectDto dto) throws IllegalAccessException, InvocationTargetException{
        etrProjectService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrProjectDto dto){
        etrProjectService.doInsert(dto, dto.getOperateType());
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
    public EtrProjectDto selectByKey(String entrustId) {
        return etrProjectService.selectByKey(entrustId);
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
        return etrProjectService.searchType(finprodId);
    }
    /**
     * 
     * 
     * 根据资产代码查询融资金额
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("searchIssueAmt")
    public BigDecimal searchIssueAmt(String finprodId){
        return etrProjectService.searchIssueAmt(finprodId);
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
        return etrProjectService.getProAvailableCash(param);
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
        return etrProjectService.getAssetAvailablePosition(dto);
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
    public List<ValidMessage> check(@RequestBody EtrProjectDto dto) {
        return etrProjectService.check(dto);
    }
    /**
     * 获取交易对手名称
     * @param counterId
     * @return
     */
    @GetMapping("getCounterName")
    public String getCounterName(String counterId){
        return etrProjectService.getCounterName(counterId);
    }
}
