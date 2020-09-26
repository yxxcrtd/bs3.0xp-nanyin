/*
 * 文件名：ValBokClawbackInfoQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.bokclawbackInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.valuation.bokclawbackInfo.dto.ValBokClawbackInfoQuery001Dto;

/**
 * 回补信息查询
 * @author zhangwei
 * @version 1.0 2019年2月27日
 * @see ValBokClawbackInfoQuery001Mapper
 * @since 1.0
 */
public interface ValBokClawbackInfoQuery001Mapper {

    /**
     * 检索账套代码
     * 
     * @return 账套代码列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBooksetId(Map<String, Object> map);
    
    List<ValBokClawbackInfoQuery001Dto> selectBokClawbackInfo(Map<String, Object> map);

}
