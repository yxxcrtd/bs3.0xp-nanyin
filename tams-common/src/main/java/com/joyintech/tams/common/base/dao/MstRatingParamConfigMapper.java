package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstRatingParamConfig;
import com.joyintech.tams.common.base.entity.MstRatingParamConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstRatingParamConfigMapper {
    int deleteByPrimaryKey(MstRatingParamConfigKey key);

    int insert(MstRatingParamConfig record);

    int insertSelective(MstRatingParamConfig record);

    int insertBasicByBasicInfo(MstRatingParamConfig record);

    MstRatingParamConfig selectByPrimaryKey(MstRatingParamConfigKey key);

    int updateByPrimaryKeySelective(MstRatingParamConfig record);

    int updateByPrimaryKey(MstRatingParamConfig record);
}