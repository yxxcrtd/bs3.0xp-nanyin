/*
 * 文件名：EtrIRSController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrirs.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.service.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.service.entrust.etrirs.service.EtrIRSService;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;

/**
 * 利率互换委托微服务controller 〈功能详细描述〉
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtrIRSController
 * @since 1.0
 */
@RestController("EtrIRSCtr_dtx")
@RequestMapping("/EtrIRS")
public class EtrIRSController {

    /**
     * 利率互换委托微服务service
     */
    @Autowired
    private EtrIRSService etrIRSService;

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
    public Page<EtrIRSDto> selectForPage(@RequestBody Map<String, Object> param,
                                              @PathVariable("page") int page,
                                              @PathVariable("length") int length)
        throws Exception {
        return etrIRSService.selectForPage(page, length, param);
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
    public List<ValidMessage> check(@RequestBody EtrIRSDto dto) {
        return etrIRSService.check(dto);
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
    public boolean recallDelegate(@RequestBody EtrIRSDto dto)
        throws IllegalAccessException,
        InvocationTargetException {
        return etrIRSService.recallDelegate(dto);
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
    public boolean closeDelegate(String entrustId, String closeWay) {
        return etrIRSService.closeDelegate(entrustId, closeWay);
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
        return etrIRSService.deleteDelegate(entrustId);
    }

    /**
     * 修改/查看信息查询 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectByKey")
    public EtrIRSDto selectByKey(String entrustId) {
        return etrIRSService.selectByKey(entrustId);
    }

    /**
     * 工作流完成方法 <功能详细描述>
     * 
     * @param map
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody EtrIRSDto dto)
        throws IllegalAccessException,
        InvocationTargetException {
    	etrIRSService.doComplete(dto, dto.getOperateType());
    }

    @Transactional
    @DTXTransaction
    @PostMapping("doInsert")
    public void doInsert(@RequestBody EtrIRSDto dto) {
    	etrIRSService.doInsert(dto, dto.getOperateType());
    }

    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getProAvailableCash")
    public Map<String, Object> getProAvailableCash(@RequestBody Map<String, Object> param) {
        return etrIRSService.getProAvailableCash(param);
    }

    /**
     * 查询上传文件 <功能详细描述>
     * 
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    @GetMapping("/selectFileByBusinessId")
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId) {
        return etrIRSService.selectFileByBusinessId(businessId);
    }
}
