package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdMinpriceConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdMinpriceConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdMinpriceConfig record);

    int insertSelective(TrdMinpriceConfig record);

    int insertBasicByBasicInfo(TrdMinpriceConfig record);

    TrdMinpriceConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdMinpriceConfig record);

    int updateByPrimaryKey(TrdMinpriceConfig record);
}