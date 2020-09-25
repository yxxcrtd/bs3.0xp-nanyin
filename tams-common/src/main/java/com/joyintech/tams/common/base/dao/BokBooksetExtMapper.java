package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBooksetExt;
import com.joyintech.tams.common.base.entity.BokBooksetExtKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBooksetExtMapper {
    int deleteByPrimaryKey(BokBooksetExtKey key);

    int insert(BokBooksetExt record);

    int insertSelective(BokBooksetExt record);

    int insertBasicByBasicInfo(BokBooksetExt record);

    BokBooksetExt selectByPrimaryKey(BokBooksetExtKey key);

    int updateByPrimaryKeySelective(BokBooksetExt record);

    int updateByPrimaryKey(BokBooksetExt record);
}