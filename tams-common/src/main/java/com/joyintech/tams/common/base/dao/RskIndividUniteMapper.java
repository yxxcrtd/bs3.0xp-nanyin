package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndividUnite;
import com.joyintech.tams.common.base.entity.RskIndividUniteKey;

@Repository
public interface RskIndividUniteMapper {

    int deleteByPrimaryKey(RskIndividUniteKey key);

    int insert(RskIndividUnite record);

    int insertSelective(RskIndividUnite record);

    int insertBasicByBasicInfo(RskIndividUnite record);

    RskIndividUnite selectByPrimaryKey(RskIndividUniteKey key);

    int updateByPrimaryKeySelective(RskIndividUnite record);

    int updateByPrimaryKey(RskIndividUnite record);
}