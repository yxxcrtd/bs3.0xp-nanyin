package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfScheme;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuIntfSchemeMapper {
    int deleteByPrimaryKey(String schemeId);

    int insert(IbuIntfScheme record);

    int insertSelective(IbuIntfScheme record);

    int insertBasicByBasicInfo(IbuIntfScheme record);

    IbuIntfScheme selectByPrimaryKey(String schemeId);

    int updateByPrimaryKeySelective(IbuIntfScheme record);

    int updateByPrimaryKey(IbuIntfScheme record);
}