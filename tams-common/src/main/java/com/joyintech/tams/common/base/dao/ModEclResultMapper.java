package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModEclResult;
import org.springframework.stereotype.Repository;

@Repository
public interface ModEclResultMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModEclResult record);

    int insertSelective(ModEclResult record);

    int insertBasicByBasicInfo(ModEclResult record);

    ModEclResult selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModEclResult record);

    int updateByPrimaryKey(ModEclResult record);
}