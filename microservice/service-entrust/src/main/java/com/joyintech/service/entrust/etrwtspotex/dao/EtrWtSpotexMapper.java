/*
 * 文件名：EtrWtSpotexMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年6月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrwtspotex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etrwtspotex.dto.EtrWtSpotexDto;

/**
 * 外汇即期委托微服务用Mapper
 * 
 * @author wangguoxian
 * @version 1.0 2019年6月30日
 * @see EtrWtSpotexMapper
 * @since 1.0
 */
@Repository
public interface EtrWtSpotexMapper {

    /**
     * 外汇即期委托分页查询 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrWtSpotexDto> selectForPage(Map<String, Object> param);

    /**
     * 修改/查看信息查询 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrWtSpotexDto selectByKey(@Param("entrustId") String entrustId);
}
