/*
 * 文件名：RepRaiseSumRegistMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repraisesumregist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import com.joyintech.framework.select.SelectOptionDto;

/**
 * 募集期总量登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepRaiseSumRegistMapper
 * @since 1.0
 */
@Repository
public interface RepRaiseSumRegistMapper {
    /**
     * 分页查询
     * @param map 分页查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<RepRaiseSum> findPage(Map<String,Object> map);
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getProductCode(Map<String,Object> map);
}
