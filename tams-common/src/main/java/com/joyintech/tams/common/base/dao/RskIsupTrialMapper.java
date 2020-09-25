package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskIsupTrial;
import org.springframework.stereotype.Repository;

@Repository
public interface RskIsupTrialMapper {
    int insert(RskIsupTrial record);

    int insertSelective(RskIsupTrial record);

    int insertBasicByBasicInfo(RskIsupTrial record);
}