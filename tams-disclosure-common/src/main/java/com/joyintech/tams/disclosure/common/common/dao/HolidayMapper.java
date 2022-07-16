/*
 * 文件名：HolidayMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.common.common.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

/**
 * 节假日Mapper
 * 
 * @author chenggaoling
 * @version 1.0 2022年7月15日
 * @see HolidayMapper
 * @since 1.0
 */
@Repository("disHolidayMapper")
public interface HolidayMapper {

    /**
     * 获取上一工作日
     * @param params 参数
     * @return 上一工作日
     */
    Date getLastWorkDay(Map<String, Object> params);

    /**
     * 获取下一工作日
     * @param params 参数
     * @return 下一工作日
     */
    Date getNextWorkDay(Map<String, Object> params);
}
