/*
 * 文件名：EtrBoutRepoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrboutrepo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrboutrepo.dto.EtrBoutRepoDto;

/**
 * 买断式回购委托mapper
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月10日
 * @see EtrBoutRepoMapper
 * @since 1.0
 */
@Component
public interface EtrBoutRepoMapper {

    /**
     * 买断式回购委托分页查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrBoutRepoDto> selectForPage(Map<String, Object> param);
    /**
     * 
     * 获取组合代码联想文本框信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getentrustId(Map<String, Object> map);
    /**
     * 
     * 获取新建界面的组合代码联想下拉框框
     * <功能详细描述>
     * @param param 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getentrustIdAdd(Map<String, Object> map);
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrBoutRepoDto selectByKey(@Param("entrustId") String entrustId);
    /**
     * 
     * 判断是否内部交易对手
     * <功能详细描述>
     * @param counterId
     * @return
     * @see
     * @since 1.0
     */
    String judgeCounterType(@Param("counterId") String counterId);
    /**
     * 
     * 查询流程中已质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectWflAmt(Map<String, Object> map);
    /**
     * 
     * 查询交易中已质押额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal selectAmt(Map<String, Object> map);
    /**
     * 
     * 查询标准券额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> selectStandardAmt(Map<String, Object> map);
    /**
     * 
     * 查询托管证券账户
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getSecManageAcctId(Map<String, Object> param);
}
