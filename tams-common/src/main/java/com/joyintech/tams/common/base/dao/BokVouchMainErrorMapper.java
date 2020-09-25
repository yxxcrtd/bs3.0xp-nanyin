package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokVouchMainError;
import com.joyintech.tams.common.base.entity.BokVouchMainErrorKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokVouchMainErrorMapper {
    int deleteByPrimaryKey(BokVouchMainErrorKey key);

    int insert(BokVouchMainError record);

    int insertSelective(BokVouchMainError record);

    int insertBasicByBasicInfo(BokVouchMainError record);

    BokVouchMainError selectByPrimaryKey(BokVouchMainErrorKey key);

    int updateByPrimaryKeySelective(BokVouchMainError record);

    int updateByPrimaryKey(BokVouchMainError record);
}