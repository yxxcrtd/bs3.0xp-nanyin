package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;

@Repository
public interface MstCustomerInfoMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(MstCustomerInfo record);

    int insertSelective(MstCustomerInfo record);

    int insertBasicByBasicInfo(MstCustomerInfo record);

    MstCustomerInfo selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(MstCustomerInfo record);

    int updateByPrimaryKey(MstCustomerInfo record);
}