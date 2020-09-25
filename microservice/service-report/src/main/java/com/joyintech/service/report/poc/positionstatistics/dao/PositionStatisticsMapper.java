/*
 * 文件名：SecAssetStatisticsDao.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年10月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.positionstatistics.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.positionstatistics.dto.PositionStatisticsDto; 
/**
 * 头寸统计表微服务Mapper
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月6日
 * @see PositionStatisticsMapper
 * @since 1.0
 */
public interface PositionStatisticsMapper {
    
    /**
     * 查询查询日头寸
     *
     * @param map 参数map
     * @return 头寸数据
     * @see
     * @since 1.0
     */
    List<PositionStatisticsDto> selectForPage(Map<String, Object> map);
    
    /**
     * 查询未来头寸
     *
     * @param map 参数map
     * @return 头寸数据
     * @see
     * @since 1.0
     */
    List<PositionStatisticsDto> selectForPageFuture(Map<String, Object> map);
    
    /**
     * 理财经理联想下拉框
     *
     * @param param 参数map
     * @return 理财经理信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryManagerInfo(Map<String, Object> map);

}
