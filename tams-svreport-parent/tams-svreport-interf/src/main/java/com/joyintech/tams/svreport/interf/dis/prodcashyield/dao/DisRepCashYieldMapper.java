package com.joyintech.tams.svreport.interf.dis.prodcashyield.dao;

import com.joyintech.tams.common.base.entity.RepCashYield;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisRepCashYieldMapper {
    int insert(RepCashYield record);

    int insertSelective(RepCashYield record);

    int insertBasicByBasicInfo(RepCashYield record);

    /**
     * 根据启用日和现金流类型删除产品费率
     * @param disRepCashYield
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepCashYieldByStartDateAndCashType(RepCashYield disRepCashYield);

    /**
     * 批量存入产品费率
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int insertRepCashYieldList(List<RepCashYield> dtoList);
    
    /**
     * 
     * @param dtoList
     * @return
     */
    int mergeRepCashYield(List<RepCashYield> dtoList);
}