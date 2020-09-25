/*
 * 文件名：RepFinProductRenewalRegistMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductrenewalregist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepDuration;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年12月23日
 * @see RepFinProductRenewalRegistMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductRenewalRegistMapper {
    List<RepDuration> selectForPage(Map<String,Object> map);
    /**
     * 获取金融产品代码
     * @return 金融产品代码集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepFinprodName(Map<String,Object> map);
    /**
     * 获取登记编号
     * @return 登记编号实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepFinprodRegId(Map<String,Object> map);
}
