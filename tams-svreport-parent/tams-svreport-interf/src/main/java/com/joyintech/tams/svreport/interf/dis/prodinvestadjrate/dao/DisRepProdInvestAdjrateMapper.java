package com.joyintech.tams.svreport.interf.dis.prodinvestadjrate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.prodinvestadjrate.dto.DisRepProdInvestAdjrate;

@Repository
public interface DisRepProdInvestAdjrateMapper {
    int insert(DisRepProdInvestAdjrate record);

    int insertSelective(DisRepProdInvestAdjrate record);

    int insertBasicByBasicInfo(DisRepProdInvestAdjrate record);

    /**
     * 根据调整日期删除记录
     * 
     * @param disRepProdInvestAdjrate
     * @return
     * @see
     * @since 1.0
     */
    int deleteRepProdInvestAdjrateByInvestAdjustDate(DisRepProdInvestAdjrate disRepProdInvestAdjrate);

    /**
     * 批量存入产品投资种类比例调整
     * 
     * @param dtoList
     * @see
     * @since 1.0
     */
    int insertRepProdInvestAdjrateList(List<DisRepProdInvestAdjrate> dtoList);
}