package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdFinProductBarterTotal;
import com.joyintech.tams.common.base.entity.TrdFinProductBarterTotalKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdFinProductBarterTotalMapper {
    int deleteByPrimaryKey(TrdFinProductBarterTotalKey key);

    int insert(TrdFinProductBarterTotal record);

    int insertSelective(TrdFinProductBarterTotal record);

    int insertBasicByBasicInfo(TrdFinProductBarterTotal record);

    TrdFinProductBarterTotal selectByPrimaryKey(TrdFinProductBarterTotalKey key);

    int updateByPrimaryKeySelective(TrdFinProductBarterTotal record);

    int updateByPrimaryKey(TrdFinProductBarterTotal record);
}