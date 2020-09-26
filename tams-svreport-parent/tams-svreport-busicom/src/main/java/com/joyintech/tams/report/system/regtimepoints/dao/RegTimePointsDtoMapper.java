/*
 * 文件名：RegTimePointsDtoMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：tangliang
 * 修改时间：2017年9月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.regtimepoints.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.system.regtimepoints.dto.RegTimePoints001Dto;

/**
 * 〈一句话功能简述〉
 * 报送时点管理查询
 * @author tangliang
 * @version 1.0 2017年9月12日
 * @see RegTimePointsDtoMapper
 * @since 1.0
 */
@Repository
public interface  RegTimePointsDtoMapper {
    
    /**
     * 报送时点管理信息
     * 
     * @param map 参数
     * @return 报送时点管理信息
     * @see
     * @since 1.0
     */
    List<RegTimePoints001Dto> searchPage(Map<String, Object> map);
    
//    /**
//     * 报表名称
//     * @param reportCode 参数
//     * @return 报表名称
//     * @see
//     * @since 1.0
//     */
//    List<SelectOptionDto> searchReportCode(String reportCode);
    /**
     * 说明：根据主键更新一条报送时点信息<br>
     * 备注：<br>
     * @param dto 更新内容
     * @see
     * @since 1.0
     */
    void updateRegTimePoints(RegTimePoints001Dto dto);
    /**
     * 说明：更新所有报送时点信息<br>
     * 备注：<br>
     * @param map 更新内容
     * @see
     * @since 1.0
     */
    void updateAllRegTimePoints(Map<String , Object> map);
    /**
     * 报送时点数目
     * 
     * @param paramMap 查询参数Map
     * @return 报送时点数目
     */
    int findRegTimePointsCount(Map<String, Object> paramMap);
    /**
     * 报表名称+细分类型 数目
     * 
     * @param  reportCode 
     * @param  typeDetail 
     * @return 报表名称+细分类型 数目
     */
    int findCount(@Param("reportCode") String reportCode, @Param("typeDetail")String typeDetail);
    /**
     * 报表名称+细分类型 明细
     * 
     * @param  reportCode 
     * @param  typeDetail 
     * @return 报表名称+细分类型 数目
     */
    RegTimePoints001Dto findByReportCode(@Param("reportCode") String reportCode, @Param("typeDetail")String typeDetail);

    /**
     * 查询数据
     * @return 数据dto
     * @see
     * @since 1.0
     */
    List<RegTimePoints001Dto> selectPoint();

}
