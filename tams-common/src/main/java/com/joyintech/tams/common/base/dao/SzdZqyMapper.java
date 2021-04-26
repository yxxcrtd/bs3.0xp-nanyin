package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdZqy;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdZqyMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdZqy record);

    int insertSelective(SzdZqy record);

    int insertBasicByBasicInfo(SzdZqy record);

    SzdZqy selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdZqy record);

    int updateByPrimaryKey(SzdZqy record);
}