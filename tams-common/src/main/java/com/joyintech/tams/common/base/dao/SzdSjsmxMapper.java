package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdSjsmx;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdSjsmxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdSjsmx record);

    int insertSelective(SzdSjsmx record);

    int insertBasicByBasicInfo(SzdSjsmx record);

    SzdSjsmx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdSjsmx record);

    int updateByPrimaryKey(SzdSjsmx record);
}