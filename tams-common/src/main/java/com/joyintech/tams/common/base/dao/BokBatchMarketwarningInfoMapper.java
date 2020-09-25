package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBatchMarketwarningInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBatchMarketwarningInfoMapper {
    int insert(BokBatchMarketwarningInfo record);

    int insertSelective(BokBatchMarketwarningInfo record);

    int insertBasicByBasicInfo(BokBatchMarketwarningInfo record);
}