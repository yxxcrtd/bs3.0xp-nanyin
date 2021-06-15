/*
 * 文件名：EtrBoutRepoMapper.java
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etribboutrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etribboutrepo.dto.EtrIbBoutRepo002Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购委托mapper
 * 〈功能详细描述〉
 * @since 1.0
 */
@Component
public interface EtrIbBoutRepo002Mapper {
    /**
     * 
     * 通过主键查询修改信息
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    EtrIbBoutRepo002Dto selectByKey(@Param("entrustId") String entrustId);
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
}
