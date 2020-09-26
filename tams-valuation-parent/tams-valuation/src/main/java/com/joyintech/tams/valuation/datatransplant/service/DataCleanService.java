/*
 * 文件名：DataCleanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.datatransplant.dao.DataCleanMapper;
import com.joyintech.tams.valuation.datatransplant.dto.DataCleanParameterDto;

/**
 * 数据移植 - 数据清空
 * 
 * @author yangjian
 * @version 1.0 2018年11月12日
 * @see DataCleanService
 * @since 1.0
 */
@Service
public class DataCleanService {

    /**
     * 数据清空mapper
     */
    @Autowired
    private DataCleanMapper dataCleanMapper;

    /**
     * 【增值税模版】对应表删除
     * 
     * @param dataCleanParameterDto
     * @see
     * @since 1.0
     */
    public void delteForValueAddedTax(DataCleanParameterDto dataCleanParameterDto) {
        // 【增值税账务明细余额(TAX_BOK_BALANCE)】数据删除
        dataCleanMapper.deleteTaxBokBlance(dataCleanParameterDto);

        // 【增值税汇总余额(TAX_BOK_BALANCE_SUM)】数据删除
        dataCleanMapper.deleteTaxBokBlanceSum(dataCleanParameterDto);

        // 【增值税账务明细流水(TAX_BOK_DETAIL)】数据删除
        dataCleanMapper.deleteTaxBokDetail(dataCleanParameterDto);
    }

    /**
     * 【账务余额模版】对应表删除
     * 
     * @param dataCleanParameterDto
     * @see
     * @since 1.0
     */
    public void deleteForAccountsBlance(DataCleanParameterDto dataCleanParameterDto) {
        // 【账务明细代码表（BOK_DETAIL_ID）】数据删除
        dataCleanMapper.deleteBokDetailId(dataCleanParameterDto);

        // 【业务明细科目（BOK_BUSS_DETSUB）】数据删除
        dataCleanMapper.deleteBokBussDetsub(dataCleanParameterDto);

        // 【账务明细(当前表)(BOK_BOOKDETAIL_CURRENT)】数据删除
        dataCleanMapper.deleteBokdetailCurrent(dataCleanParameterDto);

        // 【账务明细(历史表)（BOK_BOOKDETAIL_ALL）】数据删除
        dataCleanMapper.deleteBookDetailAll(dataCleanParameterDto);

        // 【科目余额（BOK_BALANCE）】数据删除
        dataCleanMapper.deleteBokBlance(dataCleanParameterDto);
    }

    /**
     * 【头寸模板】对应表删除
     * 
     * @param dataCleanParameterDto
     * @see
     * @since 1.0
     */
    public void deleteForPosition(DataCleanParameterDto dataCleanParameterDto) {
        // 【统计分析账套汇总表（BOK_STAT_BOK_POSITION）】数据删除
        dataCleanMapper.deleteBokStatBokPosition(dataCleanParameterDto);
    }
    
    /**
     * 【增值税90号文模版】对应表删除
     * 
     * @param dataCleanParameterDto
     * @see
     * @since 1.0
     */
    public void delteForValueAddedTax90(DataCleanParameterDto dataCleanParameterDto) {
        // 【增值税账务明细余额(TAX_BOK_BALANCE)】数据删除
        dataCleanMapper.deleteTaxBokBlance(dataCleanParameterDto);

        // 【增值税待抵扣增值额(TAX_TURNED_BALANCE)】数据删除
        dataCleanMapper.deleteTaxTurnedBlance(dataCleanParameterDto);
    }
  
}
