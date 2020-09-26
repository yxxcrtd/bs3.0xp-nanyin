package com.joyintech.tams.svreport.interf.chb.baseinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;

public interface SvrMstCustomerInfoImpMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(MstCustomerInfo record);

    int insertSelective(MstCustomerInfo record);

    MstCustomerInfo selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(MstCustomerInfo record);

    int updateByPrimaryKey(MstCustomerInfo record);
    
    int deleteCustomerInfoBatch(@Param("list")List<MstCustomerInfo> record);
    
    int insertCustomerInfo(MstCustomerInfo record);
    
    int mergeMstCustomerInfoData(@Param("list")List<MstCustomerInfo> record);
    
    int deletAllData();
    
    int deleteTodayData();
}