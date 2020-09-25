package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.MstValFx;
import com.joyintech.tams.common.base.entity.MstValFxKey;

@Repository
public interface MstValFxMapper {
    int deleteByPrimaryKey(MstValFxKey key);

    int insert(MstValFx record);

    int insertSelective(MstValFx record);

    int insertBasicByBasicInfo(MstValFx record);

    MstValFx selectByPrimaryKey(MstValFxKey key);

    int updateByPrimaryKeySelective(MstValFx record);

    int updateByPrimaryKey(MstValFx record);
}