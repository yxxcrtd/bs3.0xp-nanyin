/*
 * 文件名：ValInterestRateQuotationInfo001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年8月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.valuation.basedata.marketdata.valinterestratequotationinfo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.basedata.marketdata.valinterestratequotationinfo.dto.ValInterestRateQuotationInfoDto;
import com.joyintech.service.valuation.basedata.marketdata.valinterestratequotationinfo.service.ValInterestRateQuotationInfoService;

/**
 * 利率行情信息查询
 * 
 * @author fanshuanghu
 * @version 1.0 2018年8月17日
 * @see ValInterestRateQuotationInfoController
 * @since 1.0
 */
@RestController("ValInterestRateQuotationInfoCtr_dtx")
@RequestMapping("/ValInterestRateQuotationInfo")
public class ValInterestRateQuotationInfoController  {

    /**
     * 注入service类
     */
    @Autowired
    private ValInterestRateQuotationInfoService valInterestRateQuotationInfoService;
    
    /**
     * 分页查询
     * 
     * @param pageParam 传入的查询条件
     * @return Page<ValInterestRateQuotationInfoDto> page对象
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ValInterestRateQuotationInfoDto> selectForPage(@RequestBody Map<String, Object> pageParam,@PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return valInterestRateQuotationInfoService.selectForPage(page,length,pageParam);
    }
    
    /**
     * 根据主键查询
     * @param map 查询条件
     * @return ValInterestRateQuotationInfoDto 实体类
     */
    @PostMapping("selectByPrimaryKey")
    public ValInterestRateQuotationInfoDto selectByPrimaryKey(@RequestBody Map<String, Object> map) {
        return valInterestRateQuotationInfoService.selectByPrimaryKey(map);
    }
    
    /**
     * 
     * 工作流完成
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody ValInterestRateQuotationInfoDto entity){
        String type = entity.getOperateType();
        valInterestRateQuotationInfoService.doComplete(entity,type);
    }
}
