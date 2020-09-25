package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfEmailmessage;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfEmailmessageMapper {
    int insert(InterfEmailmessage record);

    int insertSelective(InterfEmailmessage record);

    int insertBasicByBasicInfo(InterfEmailmessage record);
}