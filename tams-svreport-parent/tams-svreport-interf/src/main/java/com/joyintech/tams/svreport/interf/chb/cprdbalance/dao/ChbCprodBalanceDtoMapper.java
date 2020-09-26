package com.joyintech.tams.svreport.interf.chb.cprdbalance.dao;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.ChbCprodBalanceDto;

public interface ChbCprodBalanceDtoMapper {
    int deleteByPrimaryKey(String balId);
    
    int deleteByPosdate(Date batchTime);

    int insert(ChbCprodBalanceDto record);

    int insertSelective(ChbCprodBalanceDto record);

    ChbCprodBalanceDto selectByPrimaryKey(String balId);

    int updateByPrimaryKeySelective(ChbCprodBalanceDto record);

    int updateByPrimaryKey(ChbCprodBalanceDto record);
    
    List<Date> isUpdated(Date batchTime);
    
    void selectByPosdate(Date batchTime);
    
    List<ChbCprodBalanceDto> selectByValuedate(Date valueDate);
    
//    void mergeData(@Param("newDataList")List<ChbCprodBalanceDto> newDataList);
}