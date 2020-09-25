package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokValbalTableStatus;
import com.joyintech.tams.common.base.entity.BokValbalTableStatusKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokValbalTableStatusMapper {
    int deleteByPrimaryKey(BokValbalTableStatusKey key);

    int insert(BokValbalTableStatus record);

    int insertSelective(BokValbalTableStatus record);

    int insertBasicByBasicInfo(BokValbalTableStatus record);

    BokValbalTableStatus selectByPrimaryKey(BokValbalTableStatusKey key);

    int updateByPrimaryKeySelective(BokValbalTableStatus record);

    int updateByPrimaryKey(BokValbalTableStatus record);
}