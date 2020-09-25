package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductRecomment;
import com.joyintech.tams.common.base.entity.FinProductRecommentKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductRecommentMapper {
    int deleteByPrimaryKey(FinProductRecommentKey key);

    int insert(FinProductRecomment record);

    int insertSelective(FinProductRecomment record);

    int insertBasicByBasicInfo(FinProductRecomment record);

    FinProductRecomment selectByPrimaryKey(FinProductRecommentKey key);

    int updateByPrimaryKeySelective(FinProductRecomment record);

    int updateByPrimaryKey(FinProductRecomment record);
}