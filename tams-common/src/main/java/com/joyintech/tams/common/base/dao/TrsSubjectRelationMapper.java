package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrsSubjectRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface TrsSubjectRelationMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrsSubjectRelation record);

    int insertSelective(TrsSubjectRelation record);

    int insertBasicByBasicInfo(TrsSubjectRelation record);

    TrsSubjectRelation selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrsSubjectRelation record);

    int updateByPrimaryKey(TrsSubjectRelation record);
}