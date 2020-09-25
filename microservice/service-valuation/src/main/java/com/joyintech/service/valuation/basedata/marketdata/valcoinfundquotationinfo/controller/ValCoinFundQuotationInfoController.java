/*
 * 文件名：ValCoinFundQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年7月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.dto.ValCoinFundQuotationInfo001Dto;
import com.joyintech.service.valuation.basedata.marketdata.valcoinfundquotationinfo.service.ValCoinFundQuotationInfoService;

/**
 * 货币型基金行情信息
 * 
 * @author fanshuanghu
 * @version 1.0 2018年7月16日
 * @see ValCoinFundQuotationInfoController
 * @since 1.0
 */
@RestController("ValCoinFundQuotationInfoCtr_dtx")
@RequestMapping("/ValCoinFundQuotationInfo")
public class ValCoinFundQuotationInfoController  {

    /**
     * service注入
     */
    @Autowired
    private ValCoinFundQuotationInfoService valCoinFundQuotationInfoService;
    
    /**
     * 分页查询
     * 
     * @param pageParam 查询参数
     * @return page对象
     * @throws Exception 错误
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ValCoinFundQuotationInfo001Dto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        Page<ValCoinFundQuotationInfo001Dto> pages = valCoinFundQuotationInfoService.selectForPage(page,length,pageParam);
        return pages;
    }
    /**
     * 获取页面信息（修改）
     * @param pageParam 主键
     * @return 基金类行情信息列表
     * @see
     * @since 1.0
     */
    @PostMapping("selectPageInfo")
    public List<ValCoinFundQuotationInfo001Dto> selectPageInfo(@RequestBody Map<String, Object> pageParam){
        return valCoinFundQuotationInfoService.selectPageInfo(pageParam);
    }
    /**
     * 联想文本框service层实现
     * 
     * @param param 检索参数
     * @return SuggestDto
     */
    @PostMapping("/selectForCode")
    public List<SuggestDto> selectForCode(@RequestBody Map<String, Object> param) {
        return valCoinFundQuotationInfoService.selectForCode(param);
    }
    /**
     * data-url 方式的下拉框
     * 
     * @param map 检索参数
     * @return SuggestDto
     */
    @PostMapping("/getManagement")
    public List<SuggestDto> getManagement(@RequestBody Map<String, Object> map) {
        return valCoinFundQuotationInfoService.getManagement(map);
    }
    
    /**
     * 联想下拉框基金代码
     * @param map 查询参数
     * @return List<FinprodSeleOptionDto>金融产品实体类集合
     * @see
     * @since 1.0
     */
    @PostMapping("/queryFinprod")
    public List<SuggestDto> queryFinprod(@RequestBody Map<String, Object> map) {
        return valCoinFundQuotationInfoService.queryFinprod(map);
    }
    /**
     * 以基金代码获取基金类型和基金管理人名称
     * 
     * @param map 界面参数
     * @return ValCoinFundQuotationInfo001Dto金融产品实体类
     * @see
     * @since 1.0
     */
    @PostMapping("/getManageAndType")
    public ValCoinFundQuotationInfo001Dto getManageAndType(@RequestBody Map<String,Object> map) {
        return valCoinFundQuotationInfoService.getManageAndType((String)map.get("finprodId"));
    }
    /**
     * 
     * 工作流完成操作
     * <功能详细描述>
     * @param map 业务实体
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("/doComplete")
    public void doComplete(@RequestBody ValCoinFundQuotationInfo001Dto map) {
       valCoinFundQuotationInfoService.doComplete(map);
    }
    /**
     * 通过估值日与ID查询
     * <功能详细描述>
     * @param finprodId id
     * @param valDate 估值日期
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("/selectByIdAndValDate")
    public String selectByIdAndValDate(@RequestBody Map<String,Object>map) {
        return valCoinFundQuotationInfoService.selectByIdAndValDate(map);
    }
    
    @GetMapping("/getIplatDate")
    public String getIplatDate(){
        return valCoinFundQuotationInfoService.getIplatDate();
    }
}
