package com.joyintech.tams.svreport.interf.dis.boksubject.dao;

import com.joyintech.tams.svreport.interf.dis.boksubject.dto.BokSubject;
import com.joyintech.tams.svreport.interf.dis.boksubject.dto.BokSubjectKey;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DisBokSubjectMapper {
    int deleteByPrimaryKey(BokSubjectKey key);

    int insert(List<BokSubject> list);

    int insertSelective(BokSubject record);

    int insertBasicByBasicInfo(BokSubject record);

    BokSubject selectByPrimaryKey(BokSubjectKey key);

    int updateByPrimaryKeySelective(BokSubject record);

    int updateByPrimaryKey(BokSubject record);

    int delete();
}