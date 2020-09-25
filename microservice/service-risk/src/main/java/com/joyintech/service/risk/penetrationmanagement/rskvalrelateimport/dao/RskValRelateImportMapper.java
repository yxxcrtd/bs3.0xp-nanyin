/*
 * 文件名：RskValRelateImportMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImportDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImportMapper
 * @since 1.0
 */
@Repository
public interface RskValRelateImportMapper {

    /**
     * <一句话功能简述> 页面查询
     * 
     * @param param
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<RskValRelateImportDto> selectForPage(Map<String, Object> param);

    /**
     * <一句话功能简述> 指标结构映射联想下拉框
     * 
     * @return 指标结构映射信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryStructMapId();

    /**
     * <一句话功能简述> 汇总科目映射联想下拉框 
     * 
     * @return 汇总科目映射信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> querySumsubMapId();

    /**
     * <一句话功能简述> 资产科目映射联想下拉框
     * 
     * @return 资产科目映射信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryAssetSubMapId();

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param ptlId
     * @see
     * @since 1.0
     */
    String selectGroupName(String ptlId);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param deleteList
     * @see
     * @since 1.0
     */
    int deleteList(List<String> deleteList);

    /**
     * <一句话功能简述>
     * 组合名称重复校验
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int checkGroupName(RskValRelateImportDto dto);

}
