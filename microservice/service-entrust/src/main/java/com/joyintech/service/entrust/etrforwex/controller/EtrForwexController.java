/*
 * 文件名：EtrForwexController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrforwex.controller;

import java.lang.reflect.InvocationTargetException;
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
import com.joyintech.service.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.service.entrust.etrforwex.service.EtrForwexService;
@RestController("EtrForwexCtr_dtx")
@RequestMapping("/EtrForwex")
public class EtrForwexController {

	/**
     * 自定义service
     */
    @Autowired
    private EtrForwexService etrForwexService;
	/**
     * 
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     * <功能详细描述>
     * @param pageParam int
     * @param page int
     * @param length int
     * @return Page<EtrForwexDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/findPage/{page}/{length}")
    public Page<EtrForwexDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page,
                                          @PathVariable("length") int length)throws Exception {
        return etrForwexService.selectForPage(page,length,pageParam);
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
        return etrForwexService.getPortfolioId(param);
    }
    
    
    /**
     * 
     * 操作前校验
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    @PostMapping("check")
    public List<ValidMessage> check(@RequestBody EtrForwexDto dto){
        return etrForwexService.check(dto);
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
        return etrForwexService.deleteDelegate(entrustId);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doInsert")
    public void doInsert(@RequestBody EtrForwexDto dto) throws IllegalAccessException, InvocationTargetException {
    	etrForwexService.doInsert(dto);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto EtrForwexDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doDelete")
    public void doDelete(@RequestBody EtrForwexDto dto) throws IllegalAccessException, InvocationTargetException {
    	etrForwexService.doDelete(dto);
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
        return etrForwexService.recallDelegate(entrustId);
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
        return etrForwexService.closeDelegate(entrustId, closeWay);
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
        return etrForwexService.getSelectPortfolioId(page,length,param);
    }
    
    /**
     * 
     * 
     * 工作流完成操作
     * @param dto EtrForwexDto
     * @throws IllegalAccessException IllegalAccessException
     * @throws InvocationTargetException InvocationTargetException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/doComplete")
    public void doComplete(@RequestBody EtrForwexDto dto) throws IllegalAccessException, InvocationTargetException {
    	etrForwexService.doComplete(dto);
    }
    
    /**
     * 
     * 编辑、双击页面查看详情
     * 根据主键查询
     * @param entrustId String
     * @return EtrForwexDto
     * @see
     * @since 1.0
     */
    @GetMapping("/selectByKey")
    public EtrForwexDto selectByKey(String entrustId){
        return etrForwexService.selectByKey(entrustId);
    }
    
    
}
