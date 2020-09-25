package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PaRskCustomerRating;
import com.joyintech.tams.common.base.entity.PaRskCustomerRatingKey;

@Repository
public interface PaRskCustomerRatingMapper {
    int deleteByPrimaryKey(PaRskCustomerRatingKey key);

    int insert(PaRskCustomerRating record);

    int insertSelective(PaRskCustomerRating record);

    int insertBasicByBasicInfo(PaRskCustomerRating record);

    PaRskCustomerRating selectByPrimaryKey(PaRskCustomerRatingKey key);

    int updateByPrimaryKeySelective(PaRskCustomerRating record);

    int updateByPrimaryKey(PaRskCustomerRating record);
}