package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeSubjectConfig;
import com.joyintech.tams.common.base.entity.BokSchemeSubjectConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeSubjectConfigMapper {
    int deleteByPrimaryKey(BokSchemeSubjectConfigKey key);

    int insert(BokSchemeSubjectConfig record);

    int insertSelective(BokSchemeSubjectConfig record);

    int insertBasicByBasicInfo(BokSchemeSubjectConfig record);

    BokSchemeSubjectConfig selectByPrimaryKey(BokSchemeSubjectConfigKey key);

    int updateByPrimaryKeySelective(BokSchemeSubjectConfig record);

    int updateByPrimaryKey(BokSchemeSubjectConfig record);
}