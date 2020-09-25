/*
 * 文件名：PtlFutureCashPosiMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlfuturecashposi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.portfolio.ptlfuturecashposi.dto.PtlFutureCashPosiDto;

/**
 * 组合未来头寸分析
 * 
 * @author renping
 * @version 1.0 2019年11月9日
 * @see PtlFutureCashPosiMapper
 * @since 1.0
 */
@Repository
public interface PtlFutureCashPosiMapper {

    /**
     * 查询报表所需数据
     * 
     * @param pageParam 参数
     * @return 报表所需数据
     * @see
     * @since 1.0
     */
    List<PtlFutureCashPosiDto> selectData(Map<String, Object> pageParam);
}
