/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.ValTableBas;
import com.joyintech.tams.common.base.entity.ValTableIns;
import com.joyintech.tams.common.base.entity.ValTableScoe;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.AssetLiabilityStatementDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.OwnerEquityChangeDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ProfitCalculationDto;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ThreeTableLandingDelResult;
import com.joyintech.tams.valuation.batch.threetableslanding.dto.ValTaskTodoDto;


/**
 * 估值表生成Mapper
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ThreeTableLandingMapper
 * @since 1.0
 */
@Repository
public interface ThreeTableLandingMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param params taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
    List<ValTaskTodoDto> selectValTaskTodo(Map<String, Object> params);
    

    /**
     * 删除当天资产负债表
     * 
     * @param result 删除Dto
     * @see
     * @since 1.0
     */
    void deleteTdyValTableBas(ThreeTableLandingDelResult result);

    /**
     * 删除当天利润表
     * 
     * @param result 删除Dto
     * @see
     * @since 1.0
     */
    void deleteTdyValTableIns(ThreeTableLandingDelResult result);

    /**
     * 删除当天所有者权益表
     * 
     * @param result 删除Dto
     * @see
     * @since 1.0
     */
    void deleteTdyValTableScoe(ThreeTableLandingDelResult result);
    
    /**
     * 资产负债表查询科目余额
     * @param dto 参数
     * @return
     */
    List<AssetLiabilityStatementDto> selectBokBalance(Map<String, Object> params);
    
    /**
     * 查询利润表表样
     * 
     * @return
     */
    List<ProfitCalculationDto> selectProfitCalculationDtoList();
    
    /**
     * 利润表查询科目余额
     * @param pageParam 参数
     * @return
     * @see
     * @since 1.0
     */
    List<BokBalance> selectAllBalance(Map<String, Object> pageParam);
    
    /**
     * 查询所有者权益表余额数据
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<OwnerEquityChangeDto> selectAll(Map<String, Object> pageParam);
    
    /**
     * 批量插入资产负债表
     * @param list 参数
     * @see
     * @since 1.0
     */
    void insertValTableBasList(List<ValTableBas> list);
    
    /**
     * 批量插入利润表
     * @param list 参数
     * @see
     * @since 1.0
     */
    void insertValTableInsList(List<ValTableIns> list);
    
    /**
     * 批量插入所有者权益表
     * @param list 参数
     * @see
     * @since 1.0
     */
    void insertValTableScoeList(List<ValTableScoe> list);
    
//    /**
//     * 删除资负不平主动提醒
//     * 
//     * @param result 删除Dto
//     * @see
//     * @since 1.0
//     */
//    void deleteRemindMessage(ThreeTableLandingDelResult result);
    
//    /**
//     * 批量插入主动提醒信息
//     * @param list 参数
//     * @see
//     * @since 1.0
//     */
//    void insertActiveReminderMessage(List<ActiveReminderMessage> list);
    
}
