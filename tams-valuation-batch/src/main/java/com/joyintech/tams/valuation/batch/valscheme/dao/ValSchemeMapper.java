/*
 * 文件名：SubjectBalanceCollectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.batch.valscheme.dto.ValLayeringInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.TaxBokBalanceSum;
import com.joyintech.tams.valuation.batch.valscheme.dto.InvGruopDto;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeBatchDto;
import com.joyintech.tams.valuation.batch.valscheme.dto.ValSchemeTodoDto;

/**
 * 估值表生成Mapper
 * 
 * @author yangzhi
 * @version 1.0 2018年5月28日
 * @see ValSchemeMapper
 * @since 1.0
 */
@Repository
public interface ValSchemeMapper {

    /**
     * 查询所有待处理的账套代码
     * 
     * @param params taskId 任务代码
     * @return 所有待处理的账套代码结果集
     * @see
     * @since 1.0
     */
    List<ValSchemeTodoDto> selectValSchemeTaskTodo(Map<String, Object> params);

    /**
     * 检索账套科目
     * 
     * @param params 账套代码
     * @return 账套科目
     * @see
     * @since 1.0
     */
    List<BokSubject> selectSubjectInfo(Map<String, Object> params);

    /**
     * 资产、负债、共同类科目的数据处理查询
     * 
     * @param params booksetId 任务代码
     * @param params bookDate 入账日
     * @return 估值表处理结果集
     * @see
     * @since 1.0
     */
    List<ValSchemeBatchDto> selectValSchemeDataDeal(Map<String, Object> params);

    /**
     * 实收资本查询
     * 
     * @param params booksetId 任务代码
     * @param params bookDate 入账日
     * @return 估值表处理结果集
     * @see
     * @since 1.0
     */
    List<ValSchemeBatchDto> selectValSchemeDataRealCapital(Map<String, Object> params);

    /**
     * 损益平淮金查询
     * 
     * @param params booksetId 任务代码
     * @param params bookDate 入账日
     * @return 估值表处理结果集
     * @see
     * @since 1.0
     */
    List<ValSchemeBatchDto> selectValSchemeDataProfitAndLoss(Map<String, Object> params);
    
    /**
     * 待抵扣投资收益税,待抵扣暂估投资收益税
     * 
     * @param params booksetId 任务代码
     * @param params bookDate 入账日
     * @return 估值表处理结果集
     * @see
     * @since 1.0
     */
    TaxBokBalanceSum selectInvIncomeTax(Map<String, Object> params);

    /**
     * 根据估值日期和账套删除估值表数据
     * @param valDate 估值日期
     * @param booksetId 账套代码
     * @see
     * @since 1.0
     */
    void deleteBokValTableData(@Param("valDate") Date valDate,
                               @Param("booksetId") String booksetId);

    /**
     * 
     * 获取从成立以来累计万份收益
     * @param booksetId 账套代码
     * @param bookDate 入账日
     * @param queryDate 查询开始日期
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getPYieldSumSince(@Param("booksetId") String booksetId,
                            @Param("bookDate") Date bookDate,
                            @Param("queryDate") Date queryDate);
    
    /**
     * 
     * 获取从成立以来累计万份收益(分层)
     * @param booksetId 账套代码
     * @param bookDate 入账日
     * @param queryDate 查询开始日期
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getLayerPYieldSumSince(@Param("booksetId") String booksetId,
                            @Param("bookDate") Date bookDate,
                            @Param("queryDate") Date queryDate,
                            @Param("layeringId") String layeringId);
    
    /**
     * 查询投资组合总存续期、总到期日
     * @param params 参数
     * @return 投资组合
     * @see
     * @since 1.0
     */
    InvGruopDto queryInvGruop(Map<String, Object> params);
    
    /**
     * 获取债券其他应收款余额
     * @param params 参数
     * @return 债券其他应收款余额
     * @see
     * @since 1.0
     */
    BigDecimal getOtherRecvBalance(Map<String, Object> params);
    
    /**
     * 获取四级科目号的实收资本
     * @param params 参数
     * @return 四级科目号的科目余额明细
     * @see
     * @since 1.0
     */
    List<ValSchemeBatchDto> selectDetailRealCapital(Map<String, Object> params);


    /**
     * 当日浮动管理费计提金额
     *
     * @param bookDate 入账日
     * @param booksetId 账套代码
     * @param acctSubjectId 核算主体代码
     * @return
     */
    BigDecimal getTdyFloatAcctAmt(@Param("bookDate") Date bookDate,
                                  @Param("booksetId") String booksetId,
                                  @Param("acctSubjectId") String acctSubjectId);

    /**
     * 查询分层信息
     *
     * @param bookDate 入账日
     * @param booksetId 账套代码
     * @param acctSubjectId 核算主体代码
     * @return
     */
    ValLayeringInfo queryBokAcctLayeringInfo(@Param("bookDate") Date bookDate,
                                             @Param("booksetId") String booksetId,
                                             @Param("acctSubjectId") String acctSubjectId,
                                             @Param("layeringId") String layeringId);
}
