/*
 * 文件名：EtrBrowDepoInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrbrowdepoinfo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月19日
 * @see EtrBrowDepoInfoMapper
 * @since 1.0
 */
@Repository
public interface EtrBrowDepoInfoMapper {
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param map 查询参数
     * @return List<EtrBrowDepoInfoDto>
     * @see
     * @since 1.0
     */
    List<EtrBrowDepoInfoDto> select(Map<String,Object> map);
    
    /**
     * 
     * 组合代码联想文本框
     * <功能详细描述>
     * @param map 联想参数
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getPortfolioId(Map<String,Object> map);
    
    /**
     * 
     * 资产代码联想文本框
     * <功能详细描述>
     * @param map 联想参数
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketId(Map<String,Object> map);
    
    /**
     * 
     * 新建页面资产代码联想
     * <功能详细描述>
     * @param map 用户输入值
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectFinprodMarketId(Map<String,Object> map);
    
    /**
     * 
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param map 用户输入值
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectPortfolioId(Map<String,Object> map);

    /**
     *
     * 获取通道
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getChannelId(Map<String,Object> map);

    /**
     *
     * 获取通道可用现金
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getChannelAvailableCash(Map<String,Object> map);
    
    /**
     * 
     * 编辑、双击打开页面详情
     * <功能详细描述>
     * @param entrustId String
     * @return List<EtrBrowDepoInfoDto>
     * @see
     * @since 1.0
     */
    List<EtrBrowDepoInfoDto> selectByKey(@Param("entrustId") String entrustId);
    
    /**
     * 
     * 查询委托金额
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectEntrustAmt(Map<String,Object> map);
    
    /**
     * 
     * 查询发行规模
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectIssueAmt(Map<String,Object> map);

}
