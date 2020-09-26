package com.joyintech.tams.svreport.interf.dis.prodcashyield.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;

@Repository
public interface DisRepCashYieldMapper {
    int insert(DisRepCashYield record);

    int insertSelective(DisRepCashYield record);

    int insertBasicByBasicInfo(DisRepCashYield record);

    /**
     * 根据启用日和现金流类型删除产品费率
     * @param disRepCashYield
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepCashYieldByStartDateAndCashType(DisRepCashYield disRepCashYield);

    /**
     * 批量存入产品费率
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int insertRepCashYieldList(List<DisRepCashYield> dtoList);
    
    /**
     * 
     * @param dtoList
     * @return
     */
    int mergeRepCashYield(List<DisRepCashYield> dtoList);
}