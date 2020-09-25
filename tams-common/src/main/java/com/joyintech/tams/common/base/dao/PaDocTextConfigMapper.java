package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaDocTextConfig;
import com.joyintech.tams.common.base.entity.PaDocTextConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PaDocTextConfigMapper {
    int deleteByPrimaryKey(PaDocTextConfigKey key);

    int insert(PaDocTextConfig record);

    int insertSelective(PaDocTextConfig record);

    int insertBasicByBasicInfo(PaDocTextConfig record);

    PaDocTextConfig selectByPrimaryKey(PaDocTextConfigKey key);

    int updateByPrimaryKeySelective(PaDocTextConfig record);

    int updateByPrimaryKey(PaDocTextConfig record);
}