/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhao
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.yieldcurveinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Repository;

import com.joyintech.service.basicdata.marketdata.yieldcurveinfo.dto.ComYieldCurveInfoDto;



/**
 * 收益率曲线mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhao
 * @version 1.0 2018年8月8日
 * @see ComYieldCurveInfo001Mapper
 * @since 1.0
 */
@Repository 
public interface ComYieldCurveInfo001Mapper {
    
    /**
     * 查询收益率曲线信息
     * <一句话功能简述>
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<MstYieldCurveInfoDto>
     * @see
     * @since 1.0
     */
    List<ComYieldCurveInfoDto> select(Map<String, Object> param);
	List<ComYieldCurveInfoDto> selectByParams(Map<String, Object> param);

    // add by lishuping 20191008 start
    /**
     * 曲线名称的联想下拉框事件
     * @param
     * @return
     */
    List<SelectOptionDto> getCurveName(Map<String, Object> map);

    /**
     * N、K根据曲线名称联动
     * @param param
     * @return
     */
    List<ComYieldCurveInfoDto> selectNK(ComYieldCurveInfoDto param);
    // add by lishuping 20191008 end
}
