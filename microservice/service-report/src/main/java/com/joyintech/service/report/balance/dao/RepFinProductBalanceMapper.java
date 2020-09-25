/*
 * 文件名：FinBondTargetAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.balance.dto.RepFinProductBalanceDto;

/**
 * 债券标的信息
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAssetMapper
 * @since 1.0
 */
@Repository
public interface RepFinProductBalanceMapper {

    /**
     * 非分层
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepFinProductBalanceDto> queryForPage1(Map<String, Object> pageParam);
    
    /**
     * 分层
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<RepFinProductBalanceDto> queryForPage2(Map<String, Object> pageParam);
    
    List<SelectOptionDto> getFinprodIdByPortfolioId(Map<String, Object> map);
    
}
