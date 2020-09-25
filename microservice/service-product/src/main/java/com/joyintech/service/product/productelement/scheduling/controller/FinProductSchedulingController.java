/*
 * 文件名：FinProductSchedulingController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productelement.scheduling.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.product.productelement.scheduling.dto.PrdIssueScheduleDto;
import com.joyintech.service.product.productelement.scheduling.service.FinProductSchedulingService;

/**
 * 理财产品排期信息管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月13日
 * @see FinProductSchedulingController
 * @since 1.0
 */
@RestController("backFinProductSchedulingController_dtx")
@RequestMapping("/FinProductScheduling")
public class FinProductSchedulingController {

    /**
     * {@link FinProductSchedulingService}
     */
    @Autowired
    private FinProductSchedulingService finProductSchedulingService;

    /**
     * 画面查询方法
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<PaPrdIssueSchedule> selectForPage(@RequestBody Map<String, Object> param,
                                                  @PathVariable("page") int page,
                                                  @PathVariable("length") int length)
        throws Exception {
        return finProductSchedulingService.selectForPage(page, length, param);
    }

    /**
     * 产品代码的下拉框
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("querySchedulingProdInfo")
    public List<SuggestDto> querySchedulingProdInfo(String param) {
        return finProductSchedulingService.querySchedulingProdInfo(param);
    }

    /**
     * 产品代码的下拉框
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("checkRegistCodeExists")
    public int checkRegistCodeExists(String finprodId) {
        return finProductSchedulingService.checkRegistCodeExists(finprodId);
    }

    /**
     * 产品代码的下拉框
     * 
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody PrdIssueScheduleDto entity) {
        return finProductSchedulingService.delete(entity.getFinprodId());
    }

    /**
     * 修改产品排期信息
     * 
     * @param entity 产品排期信息对象
     * @return 影响行数
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody PrdIssueScheduleDto entity)
        throws IllegalAccessException, InvocationTargetException {
        return finProductSchedulingService.update(entity);
    }

    /**
     * 插入产品排期信息
     * 
     * @param entity 产品排期信息对象
     * @return 影响行数
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody PrdIssueScheduleDto entity) throws Exception {
        return finProductSchedulingService.insert(entity);
    }

    /**
     * 查询产品模板下拉框
     * 
     * @param param 参数map
     * @return 产品模板
     * @see
     * @since 1.0
     */
    @PostMapping("queryProdTempleteInfo/{page}/{length}")
    public Page<SelectOptionDto> queryProdTempleteInfo(@RequestBody Map<String, Object> param,
    		@PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductSchedulingService.queryProdTempleteInfo(param,page,length);
    }

    /**
     * 查询产品申报方案下拉框
     * 
     * @param param 参数map
     * @return 产品申报方案
     * @see
     * @since 1.0
     */
    @PostMapping("queryDeclareSchemeInfo")
    public List<SelectOptionDto> queryDeclareSchemeInfo(@RequestBody Map<String, Object> param) {
        return finProductSchedulingService.queryDeclareSchemeInfo(param);
    }

    /**
     * 根据产品金融代码查询排期信息
     * 
     * @param FinprodId 产品金融代码
     * @return 排期信息
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    @GetMapping("queryIssueInfoByFinprodId")
    public PrdIssueScheduleDto queryIssueInfoByFinprodId(String finprodId)
        throws IllegalAccessException, InvocationTargetException {
        return finProductSchedulingService.queryIssueInfoByFinprodId(finprodId);
    }

    /**
     * 查询产品基本信息是否存在
     * 
     * @param finprodMarketId 产品代码
     * @param finprodAbbr 产品简称
     * @param finprodName 产品名称
     * @param finprodId 金融产品代码
     * @return 是否存在
     * @see
     * @since 1.0
     */
    @GetMapping("checkProdInfoExist")
    public int checkProdInfoExist(String finprodMarketId, String finprodAbbr, String finprodName,
                                  String finprodId) {
        return finProductSchedulingService.checkProdInfoExist(finprodMarketId, finprodAbbr,
            finprodName, finprodId);
    }
}
