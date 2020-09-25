/*
 * 文件名：ProdDevelopManageMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.rddesgin.proddevelopmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.rddesgin.proddevelopmanage.dto.ProdDevelopManageDto;

/**
 * 微服务：产品研发方案管理Mapper
 * 
 * @author zhangJin
 * @version 1.0 2019年10月23日
 * @see ProdDevelopManageMapper
 * @since 1.0
 */
@Repository
public interface ProdDevelopManageMapper {

    /**
     * 画面查询方法
     * 
     * @param pageParam 请求参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<ProdDevelopManageDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 获取系列编号下拉框
     * 
     * @param pageParam 模糊查询
     * @return 下拉框
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getProdDesignSeries(Map<String, Object> pageParam);

    /**
     * 根据系列编号获取结果信息
     * 
     * @param pageParam 系列编号
     * @return 结果信息
     * @see
     * @since 1.0
     */
    ProdDevelopManageDto getProdResultInfos(Map<String, Object> pageParam);

}
