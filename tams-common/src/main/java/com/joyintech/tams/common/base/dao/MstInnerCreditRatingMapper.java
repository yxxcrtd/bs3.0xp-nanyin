package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstInnerCreditRating;
import com.joyintech.tams.common.base.entity.MstInnerCreditRatingKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstInnerCreditRatingMapper {
    int deleteByPrimaryKey(MstInnerCreditRatingKey key);

    int insert(MstInnerCreditRating record);

    int insertSelective(MstInnerCreditRating record);

    int insertBasicByBasicInfo(MstInnerCreditRating record);

    MstInnerCreditRating selectByPrimaryKey(MstInnerCreditRatingKey key);

    int updateByPrimaryKeySelective(MstInnerCreditRating record);

    int updateByPrimaryKey(MstInnerCreditRating record);
}