/*
 * 文件名：TrdExchangeDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdexchangeduration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;

/**
 * 外汇远期掉期到期时待处理数据用微服务
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see TrdExchangeDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdExchangeDurationMapper {

    /**
     * 检索外汇远期掉期待处理数据
     * 
     * @param param 参数
     * @return 检索结果
     * @see
     * @since 1.0
     */
    List<TrdExchangeDurDbInertDto> findPage(Map<String, Object> param);
}
