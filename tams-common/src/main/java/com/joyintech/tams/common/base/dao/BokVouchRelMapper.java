package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokVouchRel;
import com.joyintech.tams.common.base.entity.BokVouchRelKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokVouchRelMapper {
    int deleteByPrimaryKey(BokVouchRelKey key);

    int insert(BokVouchRel record);

    int insertSelective(BokVouchRel record);

    int insertBasicByBasicInfo(BokVouchRel record);

    BokVouchRel selectByPrimaryKey(BokVouchRelKey key);

    int updateByPrimaryKeySelective(BokVouchRel record);

    int updateByPrimaryKey(BokVouchRel record);
}