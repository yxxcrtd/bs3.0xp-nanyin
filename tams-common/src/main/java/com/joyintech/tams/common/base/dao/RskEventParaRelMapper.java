package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskEventParaRel;
import com.joyintech.tams.common.base.entity.RskEventParaRelKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskEventParaRelMapper {
    int deleteByPrimaryKey(RskEventParaRelKey key);

    int insert(RskEventParaRel record);

    int insertSelective(RskEventParaRel record);

    int insertBasicByBasicInfo(RskEventParaRel record);

    RskEventParaRel selectByPrimaryKey(RskEventParaRelKey key);

    int updateByPrimaryKeySelective(RskEventParaRel record);

    int updateByPrimaryKey(RskEventParaRel record);
}