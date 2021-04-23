package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import org.springframework.stereotype.Repository;

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