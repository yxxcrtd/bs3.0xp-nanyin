package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupIndicator;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupIndicatorMapper {
    int deleteByPrimaryKey(String indId);

    int insert(RskIsupIndicator record);

    int insertSelective(RskIsupIndicator record);

    int insertBasicByBasicInfo(RskIsupIndicator record);

    RskIsupIndicator selectByPrimaryKey(String indId);

    int updateByPrimaryKeySelective(RskIsupIndicator record);

    int updateByPrimaryKey(RskIsupIndicator record);
}