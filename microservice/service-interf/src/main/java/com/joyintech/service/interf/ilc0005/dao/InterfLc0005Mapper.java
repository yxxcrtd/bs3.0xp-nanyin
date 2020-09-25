/*
 * 文件名：InterfLc0005Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0005.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.ilc0005.entity.InterfLc0005InEntity;
import com.joyintech.service.interf.ilc0005.entity.InterfLc0005OutEntity;
import com.joyintech.service.interf.ilc0005.entity.InterfLc0005OutLvlEntity;

/**
 * 理财产品报价报价查询接口Lc0005mapper
 * 
 * @author changwenbin
 * @version 1.0 2018年11月8日
 * @see InterfLc0005Mapper
 * @since 1.0
 */
@Repository
public interface InterfLc0005Mapper {

    /**
     * 是否存在该产品
     * 
     * @param entity 理财产品报价报价查询Lc0005输入
     * @return 金融产品代码
     * @see
     * @since 1.0
     */
    String selectFinprodidCount(InterfLc0005InEntity entity);

    /**
     * 查询现金流计算规则最大生效日期
     * 
     * @param entity 理财产品报价报价查询Lc0005输入
     * @return 生效日期
     * @see
     * @since 1.0
     */
    String selectEffDate(InterfLc0005InEntity entity);

    /**
     * 接口输出主体信息
     * 
     * @param entity 理财产品报价报价查询Lc0005输出
     * @return 理财产品报价报价查询Lc0005输出
     * @see
     * @since 1.0
     */
    InterfLc0005OutEntity selectFinprodid(InterfLc0005InEntity entity);

    /**
     * 接口输出信息（分层）
     * 
     * @param entity 理财产品报价报价查询Lc0005输入
     * @return 理财产品报价报价查询Lc0005输出list（分层）
     * @see
     * @since 1.0
     */
    List<InterfLc0005OutLvlEntity> selectIslayInfo(InterfLc0005InEntity entity);

    /**
     * 接口输出信息（非分层）
     * 
     * @param entity 理财产品报价报价查询Lc0005输入
     * @return 理财产品报价报价查询Lc0005输出list（非分层）
     * @see
     * @since 1.0
     */
    List<InterfLc0005OutLvlEntity> selectNotlayInfo(InterfLc0005InEntity entity);
}
