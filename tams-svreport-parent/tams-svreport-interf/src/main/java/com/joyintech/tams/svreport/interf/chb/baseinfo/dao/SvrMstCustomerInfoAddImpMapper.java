package com.joyintech.tams.svreport.interf.chb.baseinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;

public interface SvrMstCustomerInfoAddImpMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(MstCustomerInfoAdd record);

    int insertSelective(MstCustomerInfoAdd record);

    MstCustomerInfoAdd selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(MstCustomerInfoAdd record);

    int updateByPrimaryKey(MstCustomerInfoAdd record);
    
    int deleteCustomerAddBatch(@Param("list")List<MstCustomerInfoAdd> record);
    
    int deleteAllData();
    
    int mergeMstCustomerInfoData(@Param("list")List<MstCustomerInfoAdd> record);
    
    int deleteTodayData();
}