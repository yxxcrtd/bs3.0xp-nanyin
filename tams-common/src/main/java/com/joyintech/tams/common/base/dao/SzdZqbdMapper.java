package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdZqbd;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdZqbdMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdZqbd record);

    int insertSelective(SzdZqbd record);

    int insertBasicByBasicInfo(SzdZqbd record);

    SzdZqbd selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdZqbd record);

    int updateByPrimaryKey(SzdZqbd record);
}