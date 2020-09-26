/*
 * 文件名：ValFinPayInterestStdInfoMapper.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangnian 修改时间：2018年10月8日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.component.element.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.component.element.dto.ValFinPayInterestStdInfoDto;

/**
 * 付息信息 〈功能详细描述〉
 * 
 * @author zhangnian
 * @version 1.0 2018年10月8日
 * @see ValFinPayInterestStdInfoMapper
 * @since 1.0
 */
@Repository
public interface ValFinPayInterestStdInfoMapper {

    ValFinPayInterestStdInfoDto getFinPayInterestStdInfoByProductId(@Param("finprodId") String finprodId,
                                                                 @Param("vdate") String vdate, @Param("cashType") String cashType,
                                                                 @Param("finprodType") String finprodType);
    /**根据金融产品代码查询是否还本不带息
     **/
    String getRepayWithoutIntByProductId(@Param("finprodId") String finprodId,
                                          @Param("cashType") String cashType);
}
