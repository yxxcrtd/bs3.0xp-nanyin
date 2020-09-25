package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfo;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecinfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcsecinfoMapper {
    int deleteByPrimaryKey(InterfPayhsrcsecinfoKey key);

    int insert(InterfPayhsrcsecinfo record);

    int insertSelective(InterfPayhsrcsecinfo record);

    int insertBasicByBasicInfo(InterfPayhsrcsecinfo record);

    InterfPayhsrcsecinfo selectByPrimaryKey(InterfPayhsrcsecinfoKey key);

    int updateByPrimaryKeySelective(InterfPayhsrcsecinfo record);

    int updateByPrimaryKey(InterfPayhsrcsecinfo record);
}