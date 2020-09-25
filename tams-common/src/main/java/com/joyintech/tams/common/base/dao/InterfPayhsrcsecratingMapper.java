package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcsecrating;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecratingKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcsecratingMapper {
    int deleteByPrimaryKey(InterfPayhsrcsecratingKey key);

    int insert(InterfPayhsrcsecrating record);

    int insertSelective(InterfPayhsrcsecrating record);

    int insertBasicByBasicInfo(InterfPayhsrcsecrating record);

    InterfPayhsrcsecrating selectByPrimaryKey(InterfPayhsrcsecratingKey key);

    int updateByPrimaryKeySelective(InterfPayhsrcsecrating record);

    int updateByPrimaryKey(InterfPayhsrcsecrating record);
}