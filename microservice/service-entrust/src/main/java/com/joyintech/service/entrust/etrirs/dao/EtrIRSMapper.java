/*
 * 文件名：EtIRSMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.entrust.etrirs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etrirs.dto.EtrIRSDto;

/**
 * 利率互换委托微服务用Mapper
 * 
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtIRSMapper
 * @since 1.0
 */
@Repository
public interface EtrIRSMapper {

    /**
     * 利率互换委托分页查询 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrIRSDto> selectForPage(Map<String, Object> param);

    /**
     * 修改/查看信息查询 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrIRSDto selectByKey(@Param("entrustId") String entrustId);

    /**
     * 
     * 判断交易对手类型
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    String judgeCounterType(@Param("counterId") String counterId);
}
