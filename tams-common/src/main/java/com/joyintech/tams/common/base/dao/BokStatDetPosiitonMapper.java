package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokStatDetPosiiton;
import com.joyintech.tams.common.base.entity.BokStatDetPosiitonKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokStatDetPosiitonMapper {
    int deleteByPrimaryKey(BokStatDetPosiitonKey key);

    int insert(BokStatDetPosiiton record);

    int insertSelective(BokStatDetPosiiton record);

    int insertBasicByBasicInfo(BokStatDetPosiiton record);

    BokStatDetPosiiton selectByPrimaryKey(BokStatDetPosiitonKey key);

    int updateByPrimaryKeySelective(BokStatDetPosiiton record);

    int updateByPrimaryKey(BokStatDetPosiiton record);
}