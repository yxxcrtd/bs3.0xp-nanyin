package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.BokSubjectKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokSubjectMapper {
    int deleteByPrimaryKey(BokSubjectKey key);

    int insert(BokSubject record);

    int insertSelective(BokSubject record);

    int insertBasicByBasicInfo(BokSubject record);

    BokSubject selectByPrimaryKey(BokSubjectKey key);

    int updateByPrimaryKeySelective(BokSubject record);

    int updateByPrimaryKey(BokSubject record);
}