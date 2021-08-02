package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditUsingSeries;

@Repository
public interface CreditUsingSeriesMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditUsingSeries record);

    int insertSelective(CreditUsingSeries record);

    int insertBasicByBasicInfo(CreditUsingSeries record);

    CreditUsingSeries selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditUsingSeries record);

    int updateByPrimaryKey(CreditUsingSeries record);
}