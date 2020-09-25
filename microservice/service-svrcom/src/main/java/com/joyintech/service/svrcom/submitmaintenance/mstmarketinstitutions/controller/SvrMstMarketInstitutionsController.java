/*
 * 文件名：MstMarketInstitutions002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年7月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.svrcom.submitmaintenance.mstmarketinstitutions.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrMstMarketInstitutionsService;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrPaRskCustomerRatingService;

/**
 * 市场机构的基本信息管理
 * 显示市场机构信息的基本页面
 * @author Wangzehui
 * @version 1.0 2018年7月23日
 * @see SvrMstMarketInstitutionsController
 * @since 1.0
 * 
 */
@RestController
@RequestMapping("/SvrMstMarketInstitutions")
public class SvrMstMarketInstitutionsController {
    
    /**
     * 市场机构管理service注入
     */
    @Autowired
    private SvrMstMarketInstitutionsService mstMarketInstitutionsService;
    /**
     * 
     * {@inheritDoc}
     */
    @Autowired
    private SvrPaRskCustomerRatingService paRskCustomerRatingService;
    
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    @PostMapping("/getCustomerId")
    public List<SuggestDto> getCustomerId(@RequestBody Map<String, Object> map){
        return mstMarketInstitutionsService.getCustomerId(map);
    }
    /**
     * 客户名称联想文本框service层实现
      *
      * @param map Map<String, Object> 检索参数
      * @return SuggestDto
      */
    @PostMapping("/getCustomerName")
   public List<SuggestDto> getCustomerName(@RequestBody Map<String, Object> map) {
        return mstMarketInstitutionsService.getCustomerName(map);
    }
    /**
     * 使用给定的检索分页数据。返回类型是Service的泛型。
     *  市场机构代码
     *  市场机构名称
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<SvrMstMarketInstitutionsDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return mstMarketInstitutionsService.selectForPage(pageParam);
    }
    /**
     * selectPaRskCustomerPage
     * 根据客户代码查询市场机构详细信息
     * <功能详细描述>
     * @param map map
     * @return MstMarketInstitutionsDto
     * @see
     * @since 1.0
     */
    @PostMapping("/selectDetail")
    public SvrMstMarketInstitutionsDto selectDetail(@RequestBody Map<String, Object> map){
        return mstMarketInstitutionsService.selectDetail(map);
    }
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto MstMarketInstitutionsDto
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("/checkCodeIsUse")
    public int checkCodeIsUse(@RequestBody SvrMstMarketInstitutionsDto dto){
        return mstMarketInstitutionsService.checkCodeIsUse(dto);
    }
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<MstMarketInstitutionsDto> 
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("/checkAllCodeIsUse")
    public List<SvrMstMarketInstitutionsDto> checkAllCodeIsUse(@RequestBody List<SvrMstMarketInstitutionsDto> item){
        return mstMarketInstitutionsService.checkAllCodeIsUse(item);
    }
    /**
     * 
     * 通过客户代码删除客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return int
     * @see
     * @since 1.0
     */
    @PostMapping("/deletePaRskCustomerRatingByPrimaryKey")
    public int deletePaRskCustomerRatingByPrimaryKey(String customerId){
        return mstMarketInstitutionsService.deletePaRskCustomerRatingByPrimaryKey(customerId);
    }
    /**
     * 
     * 通过客户代码查询客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return List<PaRskCustomerRating>
     * @see
     * @since 1.0
     */
    @PostMapping("/selectPaRskCustomerRatingByPrimaryKey")
    public List<SvrPaRskCustomerRatingDto> selectPaRskCustomerRatingByPrimaryKey(String customerId){
        return mstMarketInstitutionsService.selectPaRskCustomerRatingByPrimaryKey(customerId);
    }
    /**
     * 
     * 编辑查询页面客户评级表查询分页
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page<PaRskCustomerRating>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectPaRskCustomerPage/{page}/{length}")
    public Page<SvrPaRskCustomerRatingDto> selectPaRskCustomerPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return paRskCustomerRatingService.selectPaRskCustomerPage(pageParam);
    }
    /**
     * 
     * 工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @PostMapping("doComplete")
    public void doComplete(@RequestBody SvrMstMarketInstitutionsDto entity){
        mstMarketInstitutionsService.doComplete(entity);
    }
    
    /**
     * 
     * 省市县联动
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("/getAddressSelectOption")
    public List<SelectOptionDto> getAddressSelectOption(@RequestBody Map<String, Object> param){
        return mstMarketInstitutionsService.getAddressSelectOption(param);
    }
    
    /**
     * 
     * 查询所有的市场机构代码、名称
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("/customerNameCheck")
    public List<SvrMstMarketInstitutionsDto> customerNameCheck(){
        return mstMarketInstitutionsService.customerNameCheck();
    }
    
    /**
     * 
     * 根据becif查询CMS_机构信息
     * <功能详细描述>
     * @param becif String
     * @return InterfCmsInstitution
     * @see
     * @since 1.0
     */
    @GetMapping("/selectInterfCmsInstitution")
    public InterfCmsInstitution selectInterfCmsInstitution(String becif){
        return mstMarketInstitutionsService.selectInterfCmsInstitution(becif);
    }
    
    @PostMapping("/selectForExport")
    public List<SvrMstMarketInstitutionsDto> selectForExport(@RequestBody Map<String, Object> param){
        return mstMarketInstitutionsService.selectForExport(param);
    }
}
