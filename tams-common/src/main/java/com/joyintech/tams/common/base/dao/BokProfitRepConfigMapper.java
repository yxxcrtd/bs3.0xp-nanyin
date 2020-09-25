package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokProfitRepConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface BokProfitRepConfigMapper {
    int deleteByPrimaryKey(String projCode);

    int insert(BokProfitRepConfig record);

    int insertSelective(BokProfitRepConfig record);

    int insertBasicByBasicInfo(BokProfitRepConfig record);

    BokProfitRepConfig selectByPrimaryKey(String projCode);

    int updateByPrimaryKeySelective(BokProfitRepConfig record);

    int updateByPrimaryKey(BokProfitRepConfig record);
}