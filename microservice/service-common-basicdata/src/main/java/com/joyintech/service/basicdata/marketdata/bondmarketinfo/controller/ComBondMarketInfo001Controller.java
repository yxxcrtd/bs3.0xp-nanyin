/*
 * 文件名：ValProcessingBatch001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年3月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.bondmarketinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.dto.ComBondMarketInfo001Dto;
import com.joyintech.service.basicdata.marketdata.bondmarketinfo.service.ComBondMarketInfo001Service;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;

/**
 * 
 * 每日债券行情
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ComBondMarketInfo001Controller
 * @since 1.0
 */
@RestController(value="serviceComBondMarketInfo001Controller")
@RequestMapping("/ComBondMarketInfo001")
public class ComBondMarketInfo001Controller extends BaseController {

    /**
     *
     */
    @Autowired
    private ComBondMarketInfo001Service comBondMarketInfo001Service;

    /**
     * 债券代码下拉选择框
     *
     * @return list结果集
     * @throws Exception 异常
     */
    @GetMapping("getSecId")
    public List<SuggestDto> getSecIdOption(String secId)
        throws Exception {
        return comBondMarketInfo001Service.getSecIdOption(secId);
    }

    /**
     * 新建页面查询资产代码
     *
     * @return list
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @GetMapping("getSecIdToNew")
    public List<SuggestDto> getSecIdToNew(String secId)
        throws Exception {
        return comBondMarketInfo001Service.getSecIdToNew(secId);
    }

    /**
     * 债券简称的下拉框
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @GetMapping("getFinProdAbbr")
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr)
        throws Exception {
        return comBondMarketInfo001Service.getFinProdAbbr(finprodAbbr);
    }

    /**
     * 画面查询方法
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ComBondMarketInfo001Dto> selectAll(@RequestBody Map<String, Object> param, @PathVariable("page") int page,
                                                   @PathVariable("length") int length)
        throws Exception {
        return comBondMarketInfo001Service.selectForPage(page, length, param);
    }

    /**
     * 获取金融产品简称
     *
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @PostMapping("getFinprodAbbrOption/{page}/{length}")
    public Page<SelectOptionDto> getFinprodAbbrOption(@RequestBody Map<String, Object> map, @PathVariable("page") int page,@PathVariable("length") int length)
        throws Exception {
        return comBondMarketInfo001Service.getFinprodAbbrOption(map,page,length);
    }

    /**
     * 删除
     *
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondMarketInfo001Service.delete(dto);
    }

    /**
     * 插入
     *
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondMarketInfo001Service.insert(dto);
    }

    /**
     * 更新
     *
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody MstBondValuationInfo dto)
        throws Exception {
        return comBondMarketInfo001Service.update(dto);
    }

    /**
     * 根据债券代码查询
     *
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("getBySecId")
    public MstBondValuationInfo getBySecId(String secId)
        throws Exception {
        return comBondMarketInfo001Service.getBySecId(secId);
    }

    /**
     * 根据债券代码查询
     *
     * @return MstBondValuationInfo
     * @throws Exception 异常
     */
    @GetMapping("selectByKey")
    public MstBondValuationInfo selectByKey(String secId, String valueDate, String valueSource)
        throws Exception {
        return comBondMarketInfo001Service.selectByKey(secId, valueDate, valueSource);
    }


    /**
     * 查询所有符合条件的数据
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectAllList")
    public List<ComBondMarketInfo001Dto> selectAllList(@RequestBody Map<String, Object> param)
            throws Exception {
        return comBondMarketInfo001Service.selectAllList(param);
    }


   /**
    *
    * 插入数据
    * @param equityList 参数
    * @see
    * @since 1.0
    */
   @Transactional
   @DTXTransaction
   @PostMapping("/mergeInfoList")
   public void mergeInfoList(@RequestBody List<ComBondMarketInfo001Dto> equityList){
	   comBondMarketInfo001Service.mergeInfoList(equityList);
   }

}

