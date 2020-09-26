package com.joyintech.tams.svreport.interf.dis.prodinvestadjust.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.prodinvestadjust.dto.DisRepProdInvestAdjust;

@Repository
public interface DisRepProdInvestAdjustMapper {

    int insert(DisRepProdInvestAdjust record);

    int insertSelective(DisRepProdInvestAdjust record);

    int insertBasicByBasicInfo(DisRepProdInvestAdjust record);

    /**
     * 批量存入数据
     * 
     * @param dtoList
     * @see
     * @since 1.0
     */
    int insertRepProdInvestAdjustList(List<DisRepProdInvestAdjust> dtoList);

    /**
     * 根据调整日期删除数据
     * 
     * @param disRepProdInvestAdjust
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepProdInvestAdjustByAdjustDate(DisRepProdInvestAdjust disRepProdInvestAdjust);
}