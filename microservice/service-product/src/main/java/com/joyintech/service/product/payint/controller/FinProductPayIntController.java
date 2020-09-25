/*
 * 文件名：FinProductPayIntController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.payint.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntIntDetailDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntProdBaseInfoDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntShowDto;
import com.joyintech.service.product.payint.service.FinProductPayIntService;

/**
 * 产品中间付息查询微服务
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayIntController
 * @since 1.0
 */
@RestController("backFinProductPayIntController")
@RequestMapping("/FinProductPayInt")
public class FinProductPayIntController {

    /**
     * FinProductPayIntService
     */
    @Autowired
    private FinProductPayIntService finProductPayIntService;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("queryProdIdInfo/{page}/{length}")
    public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> paramMap,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductPayIntService.queryProdIdInfo(page, length,paramMap);
    }

    /**
     * 组合代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    @RequestMapping("selectPortfolioId/{page}/{length}")
    public Page<SelectOptionDto> selectPortfolioId(@RequestBody Map<String, Object> paramMap,
            @PathVariable("page") int page,
            @PathVariable("length") int length) {
        return finProductPayIntService.selectPortfolioId(page, length,paramMap);
    }

    /**
     * 分页查询
     *
     * @return Page<FinProductPayIntDto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<FinProductPayIntDto> queryForPage(@RequestBody Map<String, Object> pageParam,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("length") int length)
        throws Exception {
        return finProductPayIntService.selectForPage(page, length, pageParam);
    }

    /**
     * 1.6确认数据取得
     * 产品基本信息数据查询
     * @param paramDto 参数
     * @return 产品基本信息数据
     * @throws Exception
     */
    @RequestMapping("selectConfirmProdBaseInfo")
    public FinProductPayIntProdBaseInfoDto selectConfirmProdBaseInfo(@RequestBody FinProductPayIntDto paramDto) {
        return finProductPayIntService.selectConfirmProdBaseInfo(paramDto);
    }

    /**
     * 查询利息明细信息
     *
     * @param paramDto 参数
     * @return 利息明细信息
     * @throws Exception
     */
    @RequestMapping("findIntDetailInfo")
    public List<FinProductPayIntIntDetailDto> findIntDetailInfo(@RequestBody FinProductPayIntProdBaseInfoDto paramDto) {
       List<FinProductPayIntIntDetailDto> list=finProductPayIntService.findIntDetailInfo(paramDto);
    	return list;
    }

//    /**
//     * 查询获取存续期报送信息所需金额信息
//     *
//     * @param paramDto 参数
//     * @return 存续期报送信息所需金额信息
//     * @throws Exception
//     */
//    @RequestMapping("genRepDurationAmountInfo")
//    public RepDuration genRepDurationAmountInfo(@RequestBody FinProductPayIntProdBaseInfoDto paramDto) {
//        return finProductPayIntService.genRepDurationAmountInfo(paramDto);
//    }
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    @PostMapping("queryProdInfoByprodId")
    public FinProductPayIntProdBaseInfoDto queryProdInfoByFinprodId(@RequestBody Map<String, Object> map){
        return finProductPayIntService.queryProdInfoByprodId(map);
    }
    /**
     * 产品代码的下拉框
     * 
     * @param param 参数
     * @return 查询结果集
     * @throws Exception
     *             异常
     */
    @PostMapping("queryProdIdInfoIssueSuc")
    public List<SelectOptionDto> queryProdIdInfoIssueSuc(@RequestBody Map<String, Object> param) {
        return finProductPayIntService.queryProdIdInfoIssueSuc(param);
    }
    /**
     * 中间付息双击查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectProdPayInfoByFinprodId")
    public FinProductPayIntShowDto selectProdPayInfoByFinprodId(@RequestBody Map<String, Object> param){
        return finProductPayIntService.selectProdPayInfoByFinprodId(param);
    }
    /**
     * 中间付息双击查询分层信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectLayIntDetailDataList")
    public List<FinProductPayIntIntDetailDto> selectLayIntDetailDataList(@RequestBody Map<String, Object> param){
        return finProductPayIntService.selectLayIntDetailDataList(param);
    }
}
