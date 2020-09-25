package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupPointVal;
import com.joyintech.tams.common.base.entity.RskIsupPointValKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupPointValMapper {
    int deleteByPrimaryKey(RskIsupPointValKey key);

    int insert(RskIsupPointVal record);

    int insertSelective(RskIsupPointVal record);

    int insertBasicByBasicInfo(RskIsupPointVal record);

    RskIsupPointVal selectByPrimaryKey(RskIsupPointValKey key);

    int updateByPrimaryKeySelective(RskIsupPointVal record);

    int updateByPrimaryKey(RskIsupPointVal record);
}