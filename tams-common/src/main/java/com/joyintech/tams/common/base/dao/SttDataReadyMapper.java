package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttDataReady;
import org.springframework.stereotype.Repository;

@Repository
public interface SttDataReadyMapper {
    int insert(SttDataReady record);

    int insertSelective(SttDataReady record);

    int insertBasicByBasicInfo(SttDataReady record);
}