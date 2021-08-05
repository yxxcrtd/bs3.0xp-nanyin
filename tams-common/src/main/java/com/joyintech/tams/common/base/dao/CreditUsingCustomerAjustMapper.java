package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditUsingCustomerAjust;

@Repository
public interface CreditUsingCustomerAjustMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditUsingCustomerAjust record);

    int insertSelective(CreditUsingCustomerAjust record);

    int insertBasicByBasicInfo(CreditUsingCustomerAjust record);

    CreditUsingCustomerAjust selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditUsingCustomerAjust record);

    int updateByPrimaryKey(CreditUsingCustomerAjust record);
}