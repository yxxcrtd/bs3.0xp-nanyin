/*
 * 文件名：Holidaymarket001Mapper.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中债报送子系统
 * 修改人：zyf
 * 修改时间：2017年9月15日
 * 修改内容：新建
 * 系统名称：中债报送子系统
 */
 
package com.joyintech.service.disclosure.submitmaintenance.holidaymarket.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.submitmaintenance.holidaymarket.dto.DisclosureHolidaymarket001Dto;


/**
 * 节假日信息维护Mapper文件
 * 
 * @author zyf
 * @version 1.0 2017年9月15日
 * @see DisclosureHolidaymarket001Mapper
 * @since 1.0
 */
@Repository(value="serviceDisclosureHolidaymarket001Mapper")
public interface DisclosureHolidaymarket001Mapper {

    /**
     * 节假日信息维护
     * <p>根据指定条件查询列表数据</p>
     * @param param 查询参数
     * @return 查询结果列表
     */
    List<DisclosureHolidaymarket001Dto> queryForPage(Map<String, Object> param);
    
    
    /**
     * 插入导入数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param record
     * @return
     * @see
     * @since 1.0
     */
    int insertSelective(DisclosureHolidaymarket001Dto holidaymarket001Dto);
    
    /**
     * 设置节假日或工作日
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    int setDateByType(Map<String, Object> param);
    
    /**
     * 查询节假日或工作日数据库记录
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<DisclosureHolidaymarket001Dto> initHolid(Map<String,Object> param);
    /**
     * 
     * 查看当前日期是否为节假日
     * @param marketCode 市场机构
     * @param date       传入日期
     * @return           节假日
     * @see
     * @since 1.0
     */
    DisclosureHolidaymarket001Dto isNotHoliday(@Param("marketCode") String marketCode,@Param("holidayDate") Date date);
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
    DisclosureHolidaymarket001Dto getChbDeadline(@Param("marketCode") String marketCode,@Param("holidayDate") String date,
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
    DisclosureHolidaymarket001Dto getChbDeadlineZero(@Param("marketCode") String marketCode,@Param("holidayDate") Date date,
                                           @Param("type") String type);
}
