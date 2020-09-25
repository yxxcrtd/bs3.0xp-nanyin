/*
 * 文件名：RepFinProductPerDeclareMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperdeclare.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PrdDeclareSchemeDoc;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repfinproductperdeclare.dto.RepFinProductPerDeclareDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年11月21日
 * @see RepFinProductPerDeclareMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductPerDeclareMapper {
    
    /**
     * 
     * 产品名称下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<RepFinProductPerDeclareDto> 
     * @see
     * @since 1.0
     */
    List<RepFinProductPerDeclareDto> selectForPage(Map<String, Object> param);
    
    /**
     * 
     * 页面导出
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<RepFinProductPerDeclareDto> 
     * @see
     * @since 1.0
     */
    List<RepFinProductPerDeclareDto> selectForExport(Map<String, Object> param);
    
    /**
     * 
     * 获取申报附件
     * <功能详细描述>
     * @param schemeId String
     * @return PrdDeclareSchemeDoc
     * @see
     * @since 1.0
     */
    List<PrdDeclareSchemeDoc> getSeqno(String schemeId);

}
