package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdZsmx;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdZsmxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdZsmx record);

    int insertSelective(SzdZsmx record);

    int insertBasicByBasicInfo(SzdZsmx record);

    SzdZsmx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdZsmx record);

    int updateByPrimaryKey(SzdZsmx record);
}