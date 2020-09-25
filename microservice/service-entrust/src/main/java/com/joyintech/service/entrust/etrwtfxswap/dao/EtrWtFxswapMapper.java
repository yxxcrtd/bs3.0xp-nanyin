/*
 * 文件名：EtrWtFxswapMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrwtfxswap.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.service.entrust.etrwtfxswap.dto.EtrWtFxswapDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapMapper
 * @since 1.0
 */
@Component
public interface EtrWtFxswapMapper {
    /**
     * 外汇掉期委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrWtFxswapDto> selectForPage(Map<String, Object> param);
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrWtFxswapDto selectByKey(@Param("entrustId") String entrustId);

}
