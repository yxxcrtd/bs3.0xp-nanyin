package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.tams.common.base.entity.ValInterflogKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterflogMapper {
    int deleteByPrimaryKey(ValInterflogKey key);

    int insert(ValInterflog record);

    int insertSelective(ValInterflog record);

    int insertBasicByBasicInfo(ValInterflog record);

    ValInterflog selectByPrimaryKey(ValInterflogKey key);

    int updateByPrimaryKeySelective(ValInterflog record);

    int updateByPrimaryKey(ValInterflog record);
}