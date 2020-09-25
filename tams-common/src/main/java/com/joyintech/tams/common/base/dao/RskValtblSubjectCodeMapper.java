package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblSubjectCode;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblSubjectCodeMapper {
    int deleteByPrimaryKey(String subjectCode);

    int insert(RskValtblSubjectCode record);

    int insertSelective(RskValtblSubjectCode record);

    int insertBasicByBasicInfo(RskValtblSubjectCode record);

    RskValtblSubjectCode selectByPrimaryKey(String subjectCode);

    int updateByPrimaryKeySelective(RskValtblSubjectCode record);

    int updateByPrimaryKey(RskValtblSubjectCode record);
}