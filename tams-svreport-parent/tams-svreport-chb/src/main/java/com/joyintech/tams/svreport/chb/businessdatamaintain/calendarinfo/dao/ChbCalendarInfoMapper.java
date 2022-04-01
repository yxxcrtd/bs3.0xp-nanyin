/*
 * 文件名：MarketCalendarInfo001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dao;


import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dto.ChbCalendarInfo001Dto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.calendarinfo.dto.ChbCalendarInfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 市场日历信息mapper接口
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2022年3月30日
 * @see ChbCalendarInfoMapper
 * @since 1.0
 */
@Repository
public interface ChbCalendarInfoMapper {

    /**
     * 
     * 查询
     * <功能详细描述>
     * @param param 查询参数
     * @return 查询结果的实体类集合
     * @see
     * @since 1.0
     */
    List<ChbCalendarInfo001Dto> selectInfo(Map<String, Object> param);
    /**
     * 
     * 日历类型表信息查询
     * <功能详细描述>
     * @param parm 导入数据
     * @return 查询结果集合
     * @see
     * @since 1.0
     */
    List<ChbCalendarInfoDto> selectCalendarInfo(Map<String,Object> parm);
    /**
     * 
     * 设置为节假日
     * <功能详细描述>
     * @param dto 需要修改节假日信息的数据
     * @see
     * @since 1.0
     */
    void sethday(ChbCalendarInfo001Dto dto);
    /**
     * 
     * 设置为工作日
     * <功能详细描述>
     * @param dto 需要修改节假日信息的数据
     * @see
     * @since 1.0
     */
    void setwday(ChbCalendarInfo001Dto dto);
    /**
     * 
     * 检验导入表中的数据是否存在
     * <功能详细描述>
     * @param equityInfo001Dto 导入数据
     * @return 查询结果
     * @see
     * @since 1.0
     */
    ChbCalendarInfo001Dto getMstMarketCalendarInfo001DtoByKey(ChbCalendarInfo001Dto equityInfo001Dto);
    /**
     * 
     * 导入（新建）
     * <功能详细描述>
     * @param listN 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int insertBtachEquityInfo(@Param("listN")List<ChbCalendarInfo001Dto> listN);
    /**
     * 
     * 导入（修改）
     * <功能详细描述>
     * @param listA 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int updateBtachEquityInfo(@Param("listA")List<ChbCalendarInfo001Dto> listA);

    /**
     * 日历信息--用于wind 自动加券
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<MstCalendarInfo> getMstCalendarInfo();
}
