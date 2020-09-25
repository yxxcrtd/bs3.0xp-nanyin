package com.joyintech.service.interf.ilc0001.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ilc0001.entity.InterfLC0001InEntity;
import com.joyintech.service.interf.ilc0001.entity.InterfLC0001OutEntity;

/**
 * 接口LC0001查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see InterfLC0001Mapper
 * @since 1.0
 */
@Repository
public interface InterfLC0001Mapper {

    /**
     * 产品信息查询
     * 
     * @param entity 产品信息
     * @return ILC0001OutEntity 接口LC0001输出
     * @see
     * @since 1.0
     */
    InterfLC0001OutEntity selectProdInfo(InterfLC0001InEntity entity);

    /**
     * 查询母产品的加权收益率
     * 
     * @param entity lc0001接口输入
     * @return 收益率
     * @see
     * @since 1.0
     */
    BigDecimal selectPyiled(InterfLC0001InEntity entity);

}
