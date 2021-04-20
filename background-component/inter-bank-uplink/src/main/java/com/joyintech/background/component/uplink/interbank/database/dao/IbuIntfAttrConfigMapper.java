package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfAttrConfig;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfAttrConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuIntfAttrConfigMapper {
    int deleteByPrimaryKey(IbuIntfAttrConfigKey key);

    int insert(IbuIntfAttrConfig record);

    int insertSelective(IbuIntfAttrConfig record);

    int insertBasicByBasicInfo(IbuIntfAttrConfig record);

    IbuIntfAttrConfig selectByPrimaryKey(IbuIntfAttrConfigKey key);

    int updateByPrimaryKeySelective(IbuIntfAttrConfig record);

    int updateByPrimaryKey(IbuIntfAttrConfig record);
}