package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSubjectInfo;

@Repository
public interface ValSubjectInfoMapper {
    int deleteByPrimaryKey(String subjectId);

    int insert(ValSubjectInfo record);

    int insertSelective(ValSubjectInfo record);

    int insertBasicByBasicInfo(ValSubjectInfo record);

    ValSubjectInfo selectByPrimaryKey(String subjectId);

    int updateByPrimaryKeySelective(ValSubjectInfo record);

    int updateByPrimaryKey(ValSubjectInfo record);
}