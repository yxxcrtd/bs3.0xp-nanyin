/*
 * 文件名：Interf448103Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.i448103.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.interf.i448103.entity.Interf448103InEntity;

/**
 * 将货币产品/净值型产品的,万份收益/单位净值发送给统一TA
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月13日
 * @see Interf448103Mapper
 * @since 1.0
 */
@Repository
public interface Interf448103Mapper {

    /**
     * 查询产品净值和万分收益信息
     * 
     * @param map 金融产品代码， 平台时间，金融产品类型F16-理财产品，分红方案_按单位分红-01-净值
     * @return 448103输入实体类中产品净值和万分收益信息
     * @see
     * @since 1.0
     */
    Interf448103InEntity selectPrdValInfo(Map<String, Object> map);

}
