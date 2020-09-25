/*
 * 文件名：InterfLC0010Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0010.dao;

import org.springframework.stereotype.Repository;

/**
 * Lc0010cms机构评级信息mapper
 * 
 * @author changwenbin
 * @version 1.0 2019年1月4日
 * @see InterfLC0010Mapper
 * @since 1.0
 */
@Repository
public interface InterfLC0010Mapper {

    /**
     * 通过客户id删除cms机构信息数据
     * 
     * @param customerId 客户id
     * @return 数量
     * @see
     * @since 1.0
     */
    int deleteByCusId(String customerId);

    /**
     * 通过客户信息删除cms机构评级数据
     * 
     * @param gradeOrgId 客户信息
     * @return 数量
     * @see
     * @since 1.0
     */
    int deleteByGradeOrgId(String gradeOrgId);
}
