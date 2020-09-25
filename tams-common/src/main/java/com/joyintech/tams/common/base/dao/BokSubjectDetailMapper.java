package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSubjectDetail;
import com.joyintech.tams.common.base.entity.BokSubjectDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSubjectDetailMapper {
    int deleteByPrimaryKey(BokSubjectDetailKey key);

    int insert(BokSubjectDetail record);

    int insertSelective(BokSubjectDetail record);

    int insertBasicByBasicInfo(BokSubjectDetail record);

    BokSubjectDetail selectByPrimaryKey(BokSubjectDetailKey key);

    int updateByPrimaryKeySelective(BokSubjectDetail record);

    int updateByPrimaryKey(BokSubjectDetail record);
}