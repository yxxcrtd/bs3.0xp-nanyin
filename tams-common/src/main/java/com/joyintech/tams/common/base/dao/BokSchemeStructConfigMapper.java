package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeStructConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeStructConfigMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(BokSchemeStructConfig record);

    int insertSelective(BokSchemeStructConfig record);

    int insertBasicByBasicInfo(BokSchemeStructConfig record);

    BokSchemeStructConfig selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(BokSchemeStructConfig record);

    int updateByPrimaryKey(BokSchemeStructConfig record);
}