package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIsupFixRecode;
import com.joyintech.tams.common.base.entity.RskIsupFixRecodeKey;

@Repository
public interface RskIsupFixRecodeMapper {

    int deleteByPrimaryKey(RskIsupFixRecodeKey key);

    int insert(RskIsupFixRecode record);

    int insertSelective(RskIsupFixRecode record);

    int insertBasicByBasicInfo(RskIsupFixRecode record);

    RskIsupFixRecode selectByPrimaryKey(RskIsupFixRecodeKey key);

    int updateByPrimaryKeySelective(RskIsupFixRecode record);

    int updateByPrimaryKeyWithBLOBs(RskIsupFixRecode record);

    int updateByPrimaryKey(RskIsupFixRecode record);
}