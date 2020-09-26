/*
 * 文件名：ChbDeadLineMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：Administrator
 * 修改时间：2017年12月16日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.valuation.report.common.base.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbMktHoliday;
import com.joyintech.tams.valuation.report.common.base.entity.ChbDeadLine001Dto;
import com.joyintech.tams.valuation.report.common.base.entity.ChbDeadLineDto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年12月16日
 * @see ChbDeadLineMapper
 * @since 1.0
 */
@Repository("valuationChbDeadLineMapper")
public interface ChbDeadLineMapper {
    /**
     * 报表名称+细分类型 明细
     *
     * @param  reportCode
     * @param  typeDetail
     * @return 报表名称+细分类型 数目
     */
    ChbDeadLineDto findByReportCode(@Param("reportCode") String reportCode, @Param("typeDetail")String typeDetail);

    /**
     *
     * 获取最近的工作日
     * @param marketCode 市场机构
     * @param date       传入日期
     * @param type       判断标志
     * @param days       报送时点配置天数
     * @return           节假日
     * @see
     * @since 1.0
     */
    ChbDeadLine001Dto getChbDeadline(@Param("marketCode") String marketCode,@Param("holidayDate") String date,
                                       @Param("type") String type,@Param("days")  int days);

    /**
     *
     * 获取最近的工作日
     * @param marketCode 市场机构
     * @param date       传入日期
     * @return           节假日
     * @see
     * @since 1.0
     */
    ChbDeadLine001Dto getChbDeadlineZero(@Param("marketCode") String marketCode,@Param("holidayDate") String date,
                                           @Param("type") String type);
    /**
     * 通过日期类型与当前日期查询节假日表中数据
     * @param marketCode 市场类型
     * @param holidayDate 传入内容
     * @return 返回
     */
    ChbMktHoliday selectChbMktHolidayByKey(@Param("marketCode") String marketCode,@Param("busiDate") Date busiDate);
}
