package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthPrdPosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;

public interface ChbMonthPrdPositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthPrdPosition record);

    int insertSelective(ChbMonthPrdPosition record);

    ChbMonthPrdPosition selectByPrimaryKey(String posuuid);
    
    int updateByPrimaryKeySelective(ChbMonthPrdPosition record);

    int updateByPrimaryKey(ChbMonthPrdPosition record);

    /**
     * 
     * 查询产品头寸
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbMonthPrdPosition> select(ChbMonthPrdPosition pos);
    
    /**
     * 生成月报产品头寸
     * <一句话功能简述>
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    int genPosition(ChbMonthReport report);
    
    int deleteByModel(ChbMonthReport report);
    /**
     * 根据产品代码和日期查询最新净值报价
     * @param report
     * @return
     */
    List<ChbPrdPeriodYield> selectPrciceByProdAndDate(Date enddate);
}