/*
 * 文件名：RepAssetDeptRegistMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repassetdeptregist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repassetdeptregist.dto.RepAssetDeptRegistDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2019年1月19日
 * @see RepAssetDeptRegistMapper
 * @since 1.0
 */
@Repository
public interface RepAssetDeptRegistMapper {
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage1(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage2(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage3(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage4(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage5(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage6(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage7(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage8(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage9(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage10(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage11(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage12(Map<String, Object> pageParam);
    
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage13(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage14(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage15(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage16(Map<String, Object> pageParam);
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage17(Map<String, Object> pageParam);
    
    /**
     * 页面查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepAssetDeptRegistDto> selectForPage18(Map<String, Object> pageParam);
    /**
     * 获取资产代码集合
     * @param map 请求参数
     * @return 资产代码集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepAssetCode(Map<String,Object> map);
    
}
