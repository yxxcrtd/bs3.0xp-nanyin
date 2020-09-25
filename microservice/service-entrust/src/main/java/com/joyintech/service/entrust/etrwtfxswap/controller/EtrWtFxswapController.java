/*
 * 文件名：EtrWtFxswapController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrwtfxswap.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.service.entrust.etrwtfxswap.service.EtrWtFxswapService;

/**
 * 外汇掉期委托微服务Controller
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapController
 * @since 1.0
 */
@RestController("EtrWtFxswapCtr_dtx")
@RequestMapping("/EtrWtFxswap")
public class EtrWtFxswapController {
    
    @Autowired
    private EtrWtFxswapService etrWtFxswapService;
    /**
     * 
     * 外汇掉期委托分页查询
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
    public Page<EtrWtFxswapDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return etrWtFxswapService.selectForPage(page,length,param);
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
    public List<ValidMessage> check(@RequestBody EtrWtFxswapDto dto){
        return etrWtFxswapService.check(dto);
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
        return etrWtFxswapService.closeDelegate(entrustId, closeWay);
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
    public boolean recallDelegate(@RequestBody EtrWtFxswapDto dto) throws IllegalAccessException, InvocationTargetException{
        return etrWtFxswapService.recallDelegate(dto);
    }
    /**
     * 删除委托 
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @GetMapping("delete")
    public boolean deleteDelegate(String entrustId) {
        return etrWtFxswapService.deleteDelegate(entrustId);
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
    public EtrWtFxswapDto selectByKey(String entrustId) {
        return etrWtFxswapService.selectByKey(entrustId);
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
    @PostMapping("getProAvailableCash")
    public BigDecimal getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrWtFxswapService.getProAvailableCash(param);
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
    public void doComplete(@RequestBody EtrWtFxswapDto dto) throws IllegalAccessException, InvocationTargetException{
        etrWtFxswapService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrWtFxswapDto dto){
        etrWtFxswapService.doInsert(dto, dto.getOperateType());
    }
}
