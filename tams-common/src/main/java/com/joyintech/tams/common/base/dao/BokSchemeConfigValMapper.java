package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeConfigVal;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeConfigValMapper {
    int deleteByPrimaryKey(String seqId);

    int insert(BokSchemeConfigVal record);

    int insertSelective(BokSchemeConfigVal record);

    int insertBasicByBasicInfo(BokSchemeConfigVal record);

    BokSchemeConfigVal selectByPrimaryKey(String seqId);

    int updateByPrimaryKeySelective(BokSchemeConfigVal record);

    int updateByPrimaryKey(BokSchemeConfigVal record);
}