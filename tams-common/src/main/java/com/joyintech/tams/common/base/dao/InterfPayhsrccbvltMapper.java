package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrccbvlt;
import com.joyintech.tams.common.base.entity.InterfPayhsrccbvltKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrccbvltMapper {
    int deleteByPrimaryKey(InterfPayhsrccbvltKey key);

    int insert(InterfPayhsrccbvlt record);

    int insertSelective(InterfPayhsrccbvlt record);

    int insertBasicByBasicInfo(InterfPayhsrccbvlt record);

    InterfPayhsrccbvlt selectByPrimaryKey(InterfPayhsrccbvltKey key);

    int updateByPrimaryKeySelective(InterfPayhsrccbvlt record);

    int updateByPrimaryKey(InterfPayhsrccbvlt record);
}