package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdSjsjg;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdSjsjgMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdSjsjg record);

    int insertSelective(SzdSjsjg record);

    int insertBasicByBasicInfo(SzdSjsjg record);

    SzdSjsjg selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdSjsjg record);

    int updateByPrimaryKey(SzdSjsjg record);
}