/*
 * 文件名：FinFloatMgtExpQuotationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.controller;

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
import com.joyintech.service.product.messageadjust.bmquotation.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.service.FinFloatMgtExpQuotationService;

/**
 * 微服务端浮动管理费报价管理controller
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2018年10月19日
 * @see FinFloatMgtExpQuotationController
 * @since 1.0
 */
@RestController("backFinFloatMgtExpQuotationController_dtx")
@RequestMapping("/FinFloatMgtExpQuotation")
public class FinFloatMgtExpQuotationController {
    /**
     * 微服务端浮动管理费报价查询service
     */
    @Autowired
    private FinFloatMgtExpQuotationService finFloatMgtExpQuotationService;
    /**
     * 产品代码联想框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFinprodId")
    public List<SelectOptionDto> getFinprodId(@RequestBody Map<String, Object> param) {
        return finFloatMgtExpQuotationService.getFinprodId(param);
    }
    /**
     * 浮动管理费报价 分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<FinFloatMgtExpQuotationDto> selectForPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
            throws Exception{
        return finFloatMgtExpQuotationService.selectForPage(page, length, param);
    }
    
    /**
     * 浮动管理费报价 分页查询
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("selectForList")
    public List<FinFloatMgtExpQuotationDto> selectForList(@RequestBody Map<String, Object> param)
            throws Exception{
        return finFloatMgtExpQuotationService.selectForList(param);
    }
    /**
     * 工作流完成后的db操作
     * <功能详细描述>
     * @param dto
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("doComplete")
    public void doComplete(@RequestBody FinFloatMgtExpQuotationDto dto) throws Exception{
        finFloatMgtExpQuotationService.doComplete(dto);
    }
    /**
     * 产品代码时联动费用名称等
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFeeinfos")
    public FinFloatMgtExpQuotationDto getFeeinfos(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.getFeeinfos(param);
    }
    /**
     * 费用名称联想框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFeeInfo")
    public List<SelectOptionDto> getFeeInfo(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.getFeeInfo(param);
    }
    /**
     * 产品代码，生效日联动利差等
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getRuleDetail")
    public List<FinFloatMgtExpQuotationDto> getRuleDetail(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.getRuleDetail(param);
    }
    /**
     * 根据生效日判断浮动管理费计提数量
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("getFloatFeeCount")
    public int getFloatFeeCount(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.getFloatFeeCount(param);
    }
    /**
     * 查看，修改时的数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectDetail")
    public FinFloatMgtExpQuotationDto selectDetail(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.selectDetail(param);
    }
    /**
     * 根据产品代码得到起息日和到期日
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getVdateAndMdate")
    public FinFloatMgtExpQuotationDto getVdateAndMdate(String finprodId){
        return finFloatMgtExpQuotationService.getVdateAndMdate(finprodId);
    }
    
    /**
     * 
     * 根据产品代码和生效日得到基准编号并得到基准利率
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<FinFloatMgtExpQuotationDto>
     * @see
     * @since 1.0
     */
    @PostMapping("getIntRateById")
    public List<FinFloatMgtExpQuotationDto> getIntRateById(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.getIntRateById(param);
    }
    
    /**
     * 查询是否是存续期产品
     * @param finprodId
     * @return
     */
    @GetMapping("queryProductByProdId")
    public FinFloatMgtExpQuotationDto queryProductByProdId(String finprodId){
    	return finFloatMgtExpQuotationService.queryProductByProdId(finprodId);
    }
    
    /**
     * 判断现金流信息是否存在
     * @param finprodId
     * @return
     */
    @GetMapping("selectAlreadHave")
    public FinFloatMgtExpQuotationDto selectAlreadHave(String finprodId){
    	return finFloatMgtExpQuotationService.selectAlreadHave(finprodId);
    }
    
    /**
     * 产品信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryProductByProdIdAndProdStyle")
    public FinFloatMgtExpQuotationDto queryProductByProdIdAndProdStyle(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.queryProductByProdIdAndProdStyle(param);
    }
    /**
     * 查看，修改时的数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectBussBenchMarkInfo")
    public BussBenchMarkInfoDto selectBussBenchMarkInfo(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.selectBussBenchMarkInfo(param);
    }
    
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
    @PostMapping("checkeffDate")
    public boolean checkeffDate(@RequestBody Map<String,Object> param){
        return finFloatMgtExpQuotationService.checkeffDate(param);
    }
    //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
}