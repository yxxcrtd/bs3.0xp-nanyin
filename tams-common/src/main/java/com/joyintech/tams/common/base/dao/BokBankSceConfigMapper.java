package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBankSceConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBankSceConfigMapper {
    int deleteByPrimaryKey(String sqeNo);

    int insert(BokBankSceConfig record);

    int insertSelective(BokBankSceConfig record);

    int insertBasicByBasicInfo(BokBankSceConfig record);

    BokBankSceConfig selectByPrimaryKey(String sqeNo);

    int updateByPrimaryKeySelective(BokBankSceConfig record);

    int updateByPrimaryKey(BokBankSceConfig record);
}