/*
 * 文件名：EtrBondFirstBiddingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yangjm
 * 修改时间：2019年5月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribboutrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo001Dto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  场内交易委托
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年11月14日
 * @see EtrIbBoutRepo001Mapper
 * @since 1.0
 */
@Component
public interface EtrIbBoutRepo001Mapper {
    /**
     * 指令管理分页查询
     * <功能详细描述>
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbBoutRepo001Dto> findZlglPage(Map<String, Object> pageParam);
    /**
     * 指令管理查询分页查询
     *
     * @param pageParam 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
    List<EtrIbBoutRepo001Dto> findZlglPositionPage(Map<String, Object> pageParam);

}
