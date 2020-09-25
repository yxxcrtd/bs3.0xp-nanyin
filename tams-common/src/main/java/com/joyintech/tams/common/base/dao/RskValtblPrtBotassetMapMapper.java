package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMap;
import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMapKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblPrtBotassetMapMapper {
    int deleteByPrimaryKey(RskValtblPrtBotassetMapKey key);

    int insert(RskValtblPrtBotassetMap record);

    int insertSelective(RskValtblPrtBotassetMap record);

    int insertBasicByBasicInfo(RskValtblPrtBotassetMap record);

    RskValtblPrtBotassetMap selectByPrimaryKey(RskValtblPrtBotassetMapKey key);

    int updateByPrimaryKeySelective(RskValtblPrtBotassetMap record);

    int updateByPrimaryKey(RskValtblPrtBotassetMap record);
}