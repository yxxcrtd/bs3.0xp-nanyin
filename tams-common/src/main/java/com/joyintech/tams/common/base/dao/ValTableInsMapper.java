package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValTableIns;
import com.joyintech.tams.common.base.entity.ValTableInsKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValTableInsMapper {
    int deleteByPrimaryKey(ValTableInsKey key);

    int insert(ValTableIns record);

    int insertSelective(ValTableIns record);

    int insertBasicByBasicInfo(ValTableIns record);

    ValTableIns selectByPrimaryKey(ValTableInsKey key);

    int updateByPrimaryKeySelective(ValTableIns record);

    int updateByPrimaryKey(ValTableIns record);
}