package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.CreditUsingAst;

@Repository
public interface CreditUsingAstMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditUsingAst record);

    int insertSelective(CreditUsingAst record);

    int insertBasicByBasicInfo(CreditUsingAst record);

    CreditUsingAst selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditUsingAst record);

    int updateByPrimaryKey(CreditUsingAst record);
}