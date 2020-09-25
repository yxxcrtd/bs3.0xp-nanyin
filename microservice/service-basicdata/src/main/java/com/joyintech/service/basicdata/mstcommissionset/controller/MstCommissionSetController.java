/*
 * 文件名：MstCommissionSetController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：裴家俊 修改时间：2019年3月22日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstcommissionset.controller;

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
import com.joyintech.service.basicdata.mstcommissionset.dto.MstCommissionSetDto;
import com.joyintech.service.basicdata.mstcommissionset.service.MstCommissionSetService;

/**
 * 佣金设置
 * 
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSetController
 * @since 1.0
 */
@RestController("MstCommissionSetCtr_dtx")
@RequestMapping("/MstCommissionSet")
public class MstCommissionSetController {

    /**
     * 
     */
    @Autowired
    private MstCommissionSetService mstCommissionSetService;

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<MstCommissionSetDto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                               @PathVariable("length") int length)
        throws Exception {
        return mstCommissionSetService.selectForPage(page, length, param);
    }

    /**
     * 根据主键获取佣金设置信息
     * 
     * @param
     * @return PrdValParam
     */
    @PostMapping("getBussinessEntityBySeqNo")
    public MstCommissionSetDto getBussinessEntityBySeqNo(@RequestBody Map<String, Object> map) {
        return mstCommissionSetService.getBussinessEntityBySeqNo(map);
    }

    /**
     * 证券代码联想
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("/getSecAcctName")
    public List<SelectOptionDto> getSecAcctName(@RequestBody Map<String, Object> map) {
        return mstCommissionSetService.getSecAcctName(map);
    }

    /**
     * 新建编辑场合时查询是否重复
     * 
     * @param batchSeq secAcctId ps tradeType
     */
    @RequestMapping("/searchRepeatBySecPs")
    public int searchRepeatBySecPs(@RequestBody Map<String, Object> map) {
        return mstCommissionSetService.searchRepeatBySecPs(map);
    }
    
    @RequestMapping("/searchRepeatBySecPs1")
    public int searchRepeatBySecPs1(@RequestBody Map<String, Object> map) {
        return mstCommissionSetService.searchRepeatBySecPs1(map);
    }

    /**
     * 插入
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody MstCommissionSetDto dto)
        throws Exception {
        mstCommissionSetService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody MstCommissionSetDto dto)
        throws Exception {
        mstCommissionSetService.update(dto);
    }

    /**
     * 删除
     * 
     * @param
     * @return 结果
     * @throws Exception
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstCommissionSetDto dto)
        throws Exception {
        return mstCommissionSetService.delete(dto);
    }
}
