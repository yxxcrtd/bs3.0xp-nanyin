/*
 * 文件名：Interf150010Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i150010.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.service.interf.i150010.entity.Interf150010InArrEntity;

/**
 * 资管费用信息接口（150010）-查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see Interf150010Mapper
 * @since 1.0
 */
public interface Interf150010Mapper {

    /**
     * 查询产品费用信息
     * 
     * @param finProdId 金融产品代码
     * @param cashType1 现金流一级类型
     * @return 产品费用信息
     */
    List<Interf150010InArrEntity> selectFeeInfo(@Param("finProdId") String finProdId,
                                                @Param("cashType1") String cashType1);
}
