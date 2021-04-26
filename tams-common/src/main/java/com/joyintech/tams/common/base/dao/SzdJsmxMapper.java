package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdJsmx;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdJsmxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdJsmx record);

    int insertSelective(SzdJsmx record);

    int insertBasicByBasicInfo(SzdJsmx record);

    SzdJsmx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdJsmx record);

    int updateByPrimaryKey(SzdJsmx record);
}