package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransTgMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransTgMappingMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(SettleTransTgMapping record);

    int insertSelective(SettleTransTgMapping record);

    int insertBasicByBasicInfo(SettleTransTgMapping record);

    SettleTransTgMapping selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(SettleTransTgMapping record);

    int updateByPrimaryKey(SettleTransTgMapping record);
}