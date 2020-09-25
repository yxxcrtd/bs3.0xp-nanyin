package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupPointDetVal;
import com.joyintech.tams.common.base.entity.RskIsupPointDetValKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupPointDetValMapper {
    int deleteByPrimaryKey(RskIsupPointDetValKey key);

    int insert(RskIsupPointDetVal record);

    int insertSelective(RskIsupPointDetVal record);

    int insertBasicByBasicInfo(RskIsupPointDetVal record);

    RskIsupPointDetVal selectByPrimaryKey(RskIsupPointDetValKey key);

    int updateByPrimaryKeySelective(RskIsupPointDetVal record);

    int updateByPrimaryKey(RskIsupPointDetVal record);
}