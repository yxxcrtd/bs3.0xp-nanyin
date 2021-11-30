package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustDetailMapper {
    int insert(EtrEntrustDetail record);

    int insertSelective(EtrEntrustDetail record);

    int insertBasicByBasicInfo(EtrEntrustDetail record);
}