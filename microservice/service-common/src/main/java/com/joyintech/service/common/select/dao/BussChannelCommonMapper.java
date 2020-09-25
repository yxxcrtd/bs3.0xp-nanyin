/*
 * 文件名：TrdRepoTrade003Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年01月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.common.select.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

/**
 * 通道管理共通组件-微服务调用Mapper
 *
 * @author Johnnie
 * @version 1.0 2019年03月26日
 * @see BussChannelCommonMapper
 * @since 1.0
 */
@Repository
public interface BussChannelCommonMapper {

    List<SelectOptionDto> getChannelId(Map<String, Object> params);

    List<SelectOptionDto> getYcxhkChannelId(Map<String, Object> params);

	List<SelectOptionDto> getChannelIdAndCollectType(Map<String, Object> params);
}
