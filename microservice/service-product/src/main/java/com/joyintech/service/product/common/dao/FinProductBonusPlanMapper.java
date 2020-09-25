/*
 * 文件名：FinProductBonusPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.common.dto.BussFinProductBonusPlanDto;

/**
 * 分红信息组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月25日
 * @see FinProductBonusPlanMapper
 * @since 1.0
 */
@Repository
public interface FinProductBonusPlanMapper {

    
    /**
     * 查询分红计划相关信息
     * @param map 参数
     * @return 浮动管理费相关信息
     */
    BussFinProductBonusPlanDto queryBonusPlanInfoByFinprodId(Map<String, Object> map);
    
}
