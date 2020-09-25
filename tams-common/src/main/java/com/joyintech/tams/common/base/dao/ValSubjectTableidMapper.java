package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValSubjectTableid;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSubjectTableidMapper {
    int deleteByPrimaryKey(String subjectHead);

    int insert(ValSubjectTableid record);

    int insertSelective(ValSubjectTableid record);

    int insertBasicByBasicInfo(ValSubjectTableid record);

    ValSubjectTableid selectByPrimaryKey(String subjectHead);

    int updateByPrimaryKeySelective(ValSubjectTableid record);

    int updateByPrimaryKey(ValSubjectTableid record);
}