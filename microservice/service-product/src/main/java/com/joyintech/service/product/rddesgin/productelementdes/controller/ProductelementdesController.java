/*
 * 文件名：FinProductDurDealController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.productelementdes.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes001Dto;
import com.joyintech.service.product.rddesgin.productelementdes.dto.Productelementdes002Dto;
import com.joyintech.service.product.rddesgin.productelementdes.service.ProductelementdesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 产品存续期交易查询微服务
 *
 * @author jyl
 * @version 1.0 2019年9月25日
 * @see ProductelementdesController
 * @since 1.0
 */
@RestController("backProductelementdesController_dtx")
@RequestMapping("/Productelementdes")
public class ProductelementdesController {

    /**
     * ProductelementdesService
     */
    @Autowired
    private ProductelementdesService productelementdesService;

     /**
     * 分页查询
     *
     * @return Page<FinProductDurDealDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<Productelementdes002Dto> queryForPage(@RequestBody Map<String, Object> pageParam,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("length") int length)
        throws Exception {
        return productelementdesService.selectForPage(page, length, pageParam);
    }
    
    /**
     * 交易明细
     * @param pageParam
     * @param page
     * @param length
     * @return
     * @throws Exception 异常
     */
    @RequestMapping("/findDetailPage/{page}/{length}")
    public Page<Productelementdes001Dto> queryForDetailPage(@RequestBody Map<String, Object> pageParam,
                                                            @PathVariable("page") int page,
                                                            @PathVariable("length") int length)
    	throws Exception {

    	return productelementdesService.findDetailPage(page, length, pageParam);
    }
    @Transactional
    @DTXTransaction
    @RequestMapping("/insertProductelementdes")
    public void insertProductelementdes(@RequestBody Productelementdes002Dto productelementdes002Dto) throws Exception{
        productelementdesService.insertProductelementdes(productelementdes002Dto);
    }
    /**
     * 查看需求详细信息findprdDesignId
     */
    @RequestMapping("/findSinglePage")
    public Productelementdes001Dto findSinglePage(String pageParam) {


        return productelementdesService.findSinglePage(pageParam);
    }
    /**
     * 查看要修改产品要素设计信息
     */
    @RequestMapping("/findprdDesignId")
    public Productelementdes002Dto findprdDesignId(String pageParam){
        String prdDesignId=pageParam;
        return productelementdesService.findprdDesignId(prdDesignId);
    }
   //创建产品要素信息流程数据
    @Transactional
    @DTXTransaction
    @RequestMapping("/createProductelementdes002")
    public void createProductelementdes002(@RequestBody Productelementdes002Dto productelementdes002Dto){
        productelementdesService.createProductelementdes002(productelementdes002Dto);
    }
    //删除流程表
    @Transactional
    @DTXTransaction
    @RequestMapping("/deleteProductelementdes002")
    public void deleteProductelementdes002(String prdDesignId){
        productelementdesService.deleteProductelementdes002(prdDesignId);
    }
    //删除要素信息
    @Transactional
    @DTXTransaction
    @RequestMapping("/deletePro002Dto")
    public void deletePro002Dto(String prdDesignId){
        productelementdesService.deletePro002Dto(prdDesignId);
    }
    //修改要素信息updateProductelementdes
    @Transactional
    @DTXTransaction
    @RequestMapping("/updateProductelementdes")
    public void updateProductelementdes(@RequestBody Productelementdes002Dto productelementdes002Dto){
        productelementdesService.updateProductelementdes(productelementdes002Dto);
    }


}
