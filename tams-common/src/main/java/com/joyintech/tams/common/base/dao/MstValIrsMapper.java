package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstValIrs;
import com.joyintech.tams.common.base.entity.MstValIrsKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstValIrsMapper {
    int deleteByPrimaryKey(MstValIrsKey key);

    int insert(MstValIrs record);

    int insertSelective(MstValIrs record);

    int insertBasicByBasicInfo(MstValIrs record);

    MstValIrs selectByPrimaryKey(MstValIrsKey key);

    int updateByPrimaryKeySelective(MstValIrs record);

    int updateByPrimaryKey(MstValIrs record);
}