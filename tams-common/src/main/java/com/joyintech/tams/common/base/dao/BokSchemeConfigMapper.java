package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeConfigMapper {
    int deleteByPrimaryKey(String configId);

    int insert(BokSchemeConfig record);

    int insertSelective(BokSchemeConfig record);

    int insertBasicByBasicInfo(BokSchemeConfig record);

    BokSchemeConfig selectByPrimaryKey(String configId);

    int updateByPrimaryKeySelective(BokSchemeConfig record);

    int updateByPrimaryKey(BokSchemeConfig record);
}