package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AutoDispenseRight;
import com.joyintech.tams.common.base.entity.AutoDispenseRightKey;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoDispenseRightMapper {
    int deleteByPrimaryKey(AutoDispenseRightKey key);

    int insert(AutoDispenseRight record);

    int insertSelective(AutoDispenseRight record);

    int insertBasicByBasicInfo(AutoDispenseRight record);

    AutoDispenseRight selectByPrimaryKey(AutoDispenseRightKey key);

    int updateByPrimaryKeySelective(AutoDispenseRight record);

    int updateByPrimaryKey(AutoDispenseRight record);
}