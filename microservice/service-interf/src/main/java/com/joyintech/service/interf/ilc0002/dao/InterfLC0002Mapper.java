package com.joyintech.service.interf.ilc0002.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ilc0002.entity.InterfLC0002InEntity;
import com.joyintech.service.interf.ilc0002.entity.InterfLC0002OutEntity;

/**
 * 接口LC0002查询Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see InterfLC0002Mapper
 * @since 1.0
 */
@Repository
public interface InterfLC0002Mapper {

    /**
     * 产品信息查询
     * 
     * @param entity 产品信息
     * @return ILC0002OutEntity 接口LC0002输出
     * @see
     * @since 1.0
     */
    InterfLC0002OutEntity selectProdInfo(InterfLC0002InEntity entity);

    /**
     * 查询母产品的加权收益率
     * 
     * @param entity lc0002输入
     * @return 收益率
     * @see
     * @since 1.0
     */
    BigDecimal selectPyiled(InterfLC0002InEntity entity);

}
