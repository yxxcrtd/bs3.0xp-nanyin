package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokProductColResult;
import com.joyintech.tams.common.base.entity.BokProductColResultKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokProductColResultMapper {
    int deleteByPrimaryKey(BokProductColResultKey key);

    int insert(BokProductColResult record);

    int insertSelective(BokProductColResult record);

    int insertBasicByBasicInfo(BokProductColResult record);

    BokProductColResult selectByPrimaryKey(BokProductColResultKey key);

    int updateByPrimaryKeySelective(BokProductColResult record);

    int updateByPrimaryKey(BokProductColResult record);
}