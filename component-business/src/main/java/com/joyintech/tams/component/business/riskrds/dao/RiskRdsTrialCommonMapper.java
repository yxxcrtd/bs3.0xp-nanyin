/*
 * 文件名：RiskRdsTrialMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProductConvertible;

/**
 * 风控共通查询处理
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月23日
 * @see RiskRdsTrialCommonMapper
 * @since 1.0
 */
@Repository
public interface RiskRdsTrialCommonMapper {

    /**
     * 查询债转股维护信息
     * 
     * @param finprodIdList 资产代码
     * @return 债转股维护信息
     */
    List<FinProductConvertible> selectConvertible(List<String> finprodIdList);
}
