package com.joyintech.tams.svreport.chb.monthreport.dao;

import java.util.List;

import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthFeePosition;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReport;

public interface ChbMonthFeePositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthFeePosition record);

    int insertSelective(ChbMonthFeePosition record);

    ChbMonthFeePosition selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthFeePosition record);

    int updateByPrimaryKey(ChbMonthFeePosition record);

    /**
     * 
     * 费用头寸查询
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    List<ChbMonthFeePosition> select(ChbMonthFeePosition pos);
    
    /**
     * 
     * 费用头寸生成
     * <功能详细描述>
     * @param pos
     * @return
     * @see
     * @since 1.0
     */
    int genPosition(ChbMonthReport report);
    
    int deleteByModel(ChbMonthReport report);
    
}