package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfPayhsrcholiday;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfPayhsrcholidayMapper {
    int deleteByPrimaryKey(String objectId);

    int insert(InterfPayhsrcholiday record);

    int insertSelective(InterfPayhsrcholiday record);

    int insertBasicByBasicInfo(InterfPayhsrcholiday record);

    InterfPayhsrcholiday selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(InterfPayhsrcholiday record);

    int updateByPrimaryKey(InterfPayhsrcholiday record);
}