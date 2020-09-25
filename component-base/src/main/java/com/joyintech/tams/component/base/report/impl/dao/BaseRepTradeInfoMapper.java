/*
 * 文件名：BaseRepTradeInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author 刘二通
 * @version 1.0 2018年11月13日
 * @see BaseRepTradeInfoMapper
 * @since 1.0
 */
@Repository
public interface BaseRepTradeInfoMapper {
    /**
     * 删除
     *
     * @param bankTradeCodeList List<行内交易编码>
     * @return
     * @see
     * @since 1.0
     */
    int delete(@Param("bankTradeCodeList") List<String> bankTradeCodeList);
    
    /**
     * 检索交易对手名称
     *
     * @param counterId 交易对手代码
     * @return
     * @see
     * @since 1.0
     */
    String selectTradeComponent(@Param("counterId") String counterId);

    void deleteRepAssetDeri(List<String> bankTradeCodeList);
}
