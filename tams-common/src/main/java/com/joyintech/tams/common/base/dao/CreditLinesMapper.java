package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditLines;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLinesMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CreditLines record);

    int insertSelective(CreditLines record);

    int insertBasicByBasicInfo(CreditLines record);

    CreditLines selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CreditLines record);

    int updateByPrimaryKey(CreditLines record);
}