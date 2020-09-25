package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfSendMurex;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfSendMurexMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(InterfSendMurex record);

    int insertSelective(InterfSendMurex record);

    int insertBasicByBasicInfo(InterfSendMurex record);

    InterfSendMurex selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(InterfSendMurex record);

    int updateByPrimaryKeyWithBLOBs(InterfSendMurex record);

    int updateByPrimaryKey(InterfSendMurex record);
}