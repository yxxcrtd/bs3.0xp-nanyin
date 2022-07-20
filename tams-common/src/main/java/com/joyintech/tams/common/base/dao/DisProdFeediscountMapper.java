package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisProdFeediscount;
import org.springframework.stereotype.Repository;

@Repository
public interface DisProdFeediscountMapper {
    int deleteByPrimaryKey(String prodDiscountId);

    int insert(DisProdFeediscount record);

    int insertSelective(DisProdFeediscount record);

    int insertBasicByBasicInfo(DisProdFeediscount record);

    DisProdFeediscount selectByPrimaryKey(String prodDiscountId);

    int updateByPrimaryKeySelective(DisProdFeediscount record);

    int updateByPrimaryKey(DisProdFeediscount record);
}