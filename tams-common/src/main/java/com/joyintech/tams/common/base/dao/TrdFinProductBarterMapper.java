package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdFinProductBarter;
import com.joyintech.tams.common.base.entity.TrdFinProductBarterKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdFinProductBarterMapper {
    int deleteByPrimaryKey(TrdFinProductBarterKey key);

    int insert(TrdFinProductBarter record);

    int insertSelective(TrdFinProductBarter record);

    int insertBasicByBasicInfo(TrdFinProductBarter record);

    TrdFinProductBarter selectByPrimaryKey(TrdFinProductBarterKey key);

    int updateByPrimaryKeySelective(TrdFinProductBarter record);

    int updateByPrimaryKey(TrdFinProductBarter record);
}