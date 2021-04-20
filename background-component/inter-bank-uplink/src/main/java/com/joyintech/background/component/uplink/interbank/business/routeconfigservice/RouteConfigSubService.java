/*
 * 文件名：RouteConfigSubService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.routeconfigservice;

import java.util.Map;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfScheme;

/**
 * 路由配置子组件服务 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月8日
 * @see RouteConfigSubService
 * @since 1.0
 */
public interface RouteConfigSubService {

    /**
     * 获取默认路由配置方案
     * 
     * @return 默认路由配置方案
     * @see
     * @since 1.0
     */
    public IbuIntfScheme getDefaultScheme();

    /**
     * 根据配置属性获取路由配置方案
     * 
     * @param attrMap 属性信息
     * @return 路由配置方案
     * @see
     * @since 1.0
     */
    public IbuIntfScheme getSchemeForAttr(Map<String, String> attrMap);

    /**
     * 路由配置方案新增 新增路由配置方案
     * 
     * @param ibuIntfScheme
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public void insertScheme(IbuIntfScheme ibuIntfScheme);

    /**
     * 路由配置方案修改
     * 
     * @param schemeId 方案代码，主键
     * @param ibuIntfScheme 待更新路由配置方案信息，空属性自动跳过
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public void updateScheme(String schemeId, IbuIntfScheme ibuIntfScheme);

    /**
     * 路由配置方案删除 根据主键删除配置方案
     * 
     * @param schemeId 方案代码，主键
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public void deleleScheme(String schemeId);

    /**
     * 路由配置方案默认方案指定 指定传入的方案代码为默认方案
     * 
     * @param schemeId
     * @return 处理结果
     * @see
     * @since 1.0
     */
    public void setDefaultScheme(String schemeId);

    /**
     * 报价路由方案新增 新增报价路由方案
     * 
     * @param SchemeId 方案代码
     * @param attrMap 属性列表
     * @return
     * @see
     * @since 1.0
     */
    public void insertQuoteScheme(String schemeId, Map<String, String> attrMap);

    /**
     * 报价路由方案修改 根据属性组代码，旧方案代码删除报价路由方案，重新根据属性列表和新方案代码重新生成报价路由方案
     * 
     * @param oldSchemeId 旧方案代码
     * @param newSchemeId 新方案代码
     * @param arrtGrpId 属性组代码
     * @param attrMap 属性列表
     * @return
     * @see
     * @since 1.0
     */
    public void updateQuoteScheme(String oldSchemeId, String newSchemeId, String arrtGrpId,
                                  Map<String, String> attrMap);

    /**
     * 报价路由方案删除 删除报价路由方案
     * 
     * @param schemeId 方案代码
     * @param arrtGrpId 属性组代码
     * @return
     * @see
     * @since 1.0
     */
    public void deleteQuoteScheme(String schemeId, String arrtGrpId);
}
