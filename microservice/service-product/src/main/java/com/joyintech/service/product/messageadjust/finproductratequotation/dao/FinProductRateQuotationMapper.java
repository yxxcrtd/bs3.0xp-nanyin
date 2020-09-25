/*
 * 文件名：FinProductRateQuotationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finproductratequotation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;

/**
 * 信息调整管理_产品费率报价管理 Mapper                                                     
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotationMapper
 * @since 1.0
 */
@Repository
public interface FinProductRateQuotationMapper {
    
    /**
     * 产品代码联想下拉框
     * @param pageParam Map<String, Object>
     * @return List<SelectOptionDto>
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> pageParam);
    
    /**
     * 产品费率报价管理查询
     * @param pageParam Map<String, Object>
     * @return List<FinProductRateQuotationDto>
     */
    List<FinProductRateQuotationDto> selectForPage(Map<String, Object> pageParam);
    
    /**
     * 
     * 新建页面产品代码下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectFinprodId(Map<String, Object> param);
    /**
     * 
     * 新建页面产品代码下联动适用款型拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectProdStyleByFinprodId(Map<String, Object> param);
    
    /**
     * 
     * 新建页面画面联动
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinProductRateQuotationDto>
     * @see
     * @since 1.0
     */
    List<FinProductRateQuotationDto> selectOther(Map<String, Object> param);
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    FinProductRateQuotationDto selectDetail(Map<String, Object> param);
    
    /**
     * 
     * 新建check
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinProductRateQuotationDto>
     * @see
     * @since 1.0
     */
    List<FinProductRateQuotationDto> selectAlreadyHava(Map<String, Object> param);
    
    /**
     * 根据金融产品代码拿到起息日、到期日
     * <一句话功能简述>
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    FinProductRateQuotationDto getVdateAndMdate(Map<String, Object> param);

}
