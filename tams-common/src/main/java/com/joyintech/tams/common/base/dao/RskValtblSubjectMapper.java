package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.tams.common.base.entity.RskValtblSubjectKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblSubjectMapper {
    int deleteByPrimaryKey(RskValtblSubjectKey key);

    int insert(RskValtblSubject record);

    int insertSelective(RskValtblSubject record);

    int insertBasicByBasicInfo(RskValtblSubject record);

    RskValtblSubject selectByPrimaryKey(RskValtblSubjectKey key);

    int updateByPrimaryKeySelective(RskValtblSubject record);

    int updateByPrimaryKey(RskValtblSubject record);
}