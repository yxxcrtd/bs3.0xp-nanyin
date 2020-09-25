package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.common.base.entity.MstBondCreditRatingKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstBondCreditRatingMapper {
    int deleteByPrimaryKey(MstBondCreditRatingKey key);

    int insert(MstBondCreditRating record);

    int insertSelective(MstBondCreditRating record);

    int insertBasicByBasicInfo(MstBondCreditRating record);

    MstBondCreditRating selectByPrimaryKey(MstBondCreditRatingKey key);

    int updateByPrimaryKeySelective(MstBondCreditRating record);

    int updateByPrimaryKey(MstBondCreditRating record);
}