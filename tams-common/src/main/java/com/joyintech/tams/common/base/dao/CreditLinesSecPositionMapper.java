package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CreditLinesSecPosition;
import com.joyintech.tams.common.base.entity.CreditLinesSecPositionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLinesSecPositionMapper {
    int deleteByPrimaryKey(CreditLinesSecPositionKey key);

    int insert(CreditLinesSecPosition record);

    int insertSelective(CreditLinesSecPosition record);

    int insertBasicByBasicInfo(CreditLinesSecPosition record);

    CreditLinesSecPosition selectByPrimaryKey(CreditLinesSecPositionKey key);

    int updateByPrimaryKeySelective(CreditLinesSecPosition record);

    int updateByPrimaryKey(CreditLinesSecPosition record);
}