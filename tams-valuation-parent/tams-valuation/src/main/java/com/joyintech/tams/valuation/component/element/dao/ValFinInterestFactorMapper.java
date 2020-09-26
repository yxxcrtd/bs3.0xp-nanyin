/*
 * 文件名：ValFinInterestFactorMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.component.element.dto.ValFinInterestFactorDto;



/**
 * 计息要素
 * @author zhangnian
 * @version 1.0 2018年9月26日
 * @see ValFinInterestFactorMapper
 * @since 1.0
 */
@Repository
public interface ValFinInterestFactorMapper {
    
    ValFinInterestFactorDto getFinInterestFactorByProductId(@Param("finprodId")String finprodId,@Param("vdate") String vdate,@Param("cashType") String cashType);
}
