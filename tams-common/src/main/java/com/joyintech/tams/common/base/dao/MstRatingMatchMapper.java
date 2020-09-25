package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstRatingMatch;
import com.joyintech.tams.common.base.entity.MstRatingMatchKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstRatingMatchMapper {
    int deleteByPrimaryKey(MstRatingMatchKey key);

    int insert(MstRatingMatch record);

    int insertSelective(MstRatingMatch record);

    int insertBasicByBasicInfo(MstRatingMatch record);

    MstRatingMatch selectByPrimaryKey(MstRatingMatchKey key);

    int updateByPrimaryKeySelective(MstRatingMatch record);

    int updateByPrimaryKey(MstRatingMatch record);
}