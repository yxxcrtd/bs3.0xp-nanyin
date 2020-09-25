package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskAccessListLog;
import com.joyintech.tams.common.base.entity.RskAccessListLogKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskAccessListLogMapper {
    int deleteByPrimaryKey(RskAccessListLogKey key);

    int insert(RskAccessListLog record);

    int insertSelective(RskAccessListLog record);

    int insertBasicByBasicInfo(RskAccessListLog record);

    RskAccessListLog selectByPrimaryKey(RskAccessListLogKey key);

    int updateByPrimaryKeySelective(RskAccessListLog record);

    int updateByPrimaryKey(RskAccessListLog record);
}