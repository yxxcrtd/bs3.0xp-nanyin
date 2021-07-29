package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditUsingCustomer;

@Repository
public interface CreditUsingCustomerMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditUsingCustomer record);

    int insertSelective(CreditUsingCustomer record);

    int insertBasicByBasicInfo(CreditUsingCustomer record);

    CreditUsingCustomer selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditUsingCustomer record);

    int updateByPrimaryKey(CreditUsingCustomer record);
}