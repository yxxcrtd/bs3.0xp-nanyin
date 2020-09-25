/*
 * 文件名：FinProductModuleMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productelement.module.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productelement.module.dto.FinProductModuleInfoDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月18日
 * @see FinProductModuleMapper
 * @since 1.0
 */
@Repository
public interface FinProductModuleMapper {

    /**
     * 模板名称的联想下拉框
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTempletNameList(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductModuleInfoDto> selectForPage(Map<String, Object> param);

    /**
     * 查询现金流代码
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    String getCashId(String finprodId);


    /**
     * 以金融产品代码检索模板信息
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinProductModuleInfoDto queryModuleInfoByFinprodId(Map<String, Object> param);


    /**
     * 以模板名称检索模板信息
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    FinProductModuleInfoDto queryModuleInfoByFinprodName(Map<String, Object> param);
    
    /**
     * 根据模板ID查询模板
     * @param templateId
     * @return
     */
    FinProductModuleInfoDto selectByTemplateId(String templateId);
}
