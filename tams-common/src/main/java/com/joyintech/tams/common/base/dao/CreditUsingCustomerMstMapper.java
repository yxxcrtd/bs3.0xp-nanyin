package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditUsingCustomerMst;

@Repository
public interface CreditUsingCustomerMstMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditUsingCustomerMst record);

    int insertSelective(CreditUsingCustomerMst record);

    int insertBasicByBasicInfo(CreditUsingCustomerMst record);

    CreditUsingCustomerMst selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditUsingCustomerMst record);

    int updateByPrimaryKey(CreditUsingCustomerMst record);
}