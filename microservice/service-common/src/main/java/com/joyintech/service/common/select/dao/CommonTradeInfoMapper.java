/*
 * 文件名：BussBlendSecMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年06月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.dao;

import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 融入劵管理共通组件-微服务调用Mapper
 *
 * @author 裴家俊
 * @version 1.0 2019年06月27日
 * @since 1.0
 */
@Repository
public interface CommonTradeInfoMapper {

    List<FinCashPlanDto> getFinCashPlanByProductId(@Param("finprodId") String finprodId, @Param("cashType") String cashType,
                                                   @Param("finprodType") String finprodType);

    List<FinCashPlanDto> getFinCashPlanByProductIdOther(Map<String, Object> params);
}
