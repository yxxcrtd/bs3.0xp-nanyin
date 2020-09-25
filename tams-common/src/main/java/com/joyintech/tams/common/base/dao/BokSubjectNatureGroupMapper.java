package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSubjectNatureGroup;
import com.joyintech.tams.common.base.entity.BokSubjectNatureGroupKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSubjectNatureGroupMapper {
    int deleteByPrimaryKey(BokSubjectNatureGroupKey key);

    int insert(BokSubjectNatureGroup record);

    int insertSelective(BokSubjectNatureGroup record);

    int insertBasicByBasicInfo(BokSubjectNatureGroup record);

    BokSubjectNatureGroup selectByPrimaryKey(BokSubjectNatureGroupKey key);

    int updateByPrimaryKeySelective(BokSubjectNatureGroup record);

    int updateByPrimaryKey(BokSubjectNatureGroup record);
}