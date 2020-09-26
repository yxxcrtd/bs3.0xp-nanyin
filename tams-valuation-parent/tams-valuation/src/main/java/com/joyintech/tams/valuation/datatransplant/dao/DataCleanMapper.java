/*
 * 文件名：DataCleanMapping.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.datatransplant.dto.DataCleanParameterDto;

/**
 * 数据移植 - 数据删除
 * 
 * @author yangjian
 * @version 1.0 2018年11月12日
 * @see DataCleanMapper
 * @since 1.0
 */
@Repository
public interface DataCleanMapper {

    /**
     * 【增值税模版】【增值税账务明细余额(TAX_BOK_BALANCE)】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteTaxBokBlance(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【增值税模版】【增值税汇总余额(TAX_BOK_BALANCE_SUM)】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteTaxBokBlanceSum(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【增值税模版】【增值税账务明细流水(TAX_BOK_DETAIL)】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteTaxBokDetail(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【账务余额模版】【账务明细代码表（BOK_DETAIL_ID）】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokDetailId(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【账务余额模版】【业务明细科目（BOK_BUSS_DETSUB）】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokBussDetsub(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【账务余额模版】【账务明细(当前表)(BOK_BOOKDETAIL_CURRENT)】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokdetailCurrent(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【账务余额模版】【账务明细(历史表)（BOK_BOOKDETAIL_ALL）】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBookDetailAll(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【账务余额模版】【科目余额（BOK_BALANCE）】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokBlance(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【头寸模版】【统计分析账套汇总表（BOK_STAT_BOK_POSITION）】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteBokStatBokPosition(DataCleanParameterDto dataCleanParameterDto);

    /**
     * 【增值税90号文模版】【增值税待抵扣增值额(TAX_TURNED_BALANCE)】数据删除
     * 
     * @param dataCleanParameterDto
     * @return
     * @see
     * @since 1.0
     */
    int deleteTaxTurnedBlance(DataCleanParameterDto dataCleanParameterDto);
}
