package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSchemeInfoDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSchemeInfoDetailMapper {
    int deleteByPrimaryKey(String schId);

    int insert(BokSchemeInfoDetail record);

    int insertSelective(BokSchemeInfoDetail record);

    int insertBasicByBasicInfo(BokSchemeInfoDetail record);

    BokSchemeInfoDetail selectByPrimaryKey(String schId);

    int updateByPrimaryKeySelective(BokSchemeInfoDetail record);

    int updateByPrimaryKey(BokSchemeInfoDetail record);
}