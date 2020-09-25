package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdPbRelation;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdPbRelationMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrdPbRelation record);

    int insertSelective(TrdPbRelation record);

    int insertBasicByBasicInfo(TrdPbRelation record);

    TrdPbRelation selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrdPbRelation record);

    int updateByPrimaryKey(TrdPbRelation record);
}