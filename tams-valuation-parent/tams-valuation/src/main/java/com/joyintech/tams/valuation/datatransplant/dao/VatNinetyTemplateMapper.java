/*
 * 文件名：VatNinetyTemplateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.datatransplant.dto.BooksetTaxConfigDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月15日
 * @see VatNinetyTemplateMapper
 * @since 1.0
 */
public interface VatNinetyTemplateMapper {

    /**
     * 读取账套税务配置
     * 
     * @param booksetId 账套代码
     * @param schemeType 方案配置方案类型
     * @param configId 配置项代码
     * @return
     * @see
     * @since 1.0
     */
    List<BooksetTaxConfigDto> queryBooksetTaxConfig(@Param("booksetId") String booksetId,
                                                    @Param("schemeType") String schemeType,
                                                    @Param("configId") String configId);

}
