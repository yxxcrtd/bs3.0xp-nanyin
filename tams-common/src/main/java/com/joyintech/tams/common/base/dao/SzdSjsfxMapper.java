package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdSjsfx;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdSjsfxMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdSjsfx record);

    int insertSelective(SzdSjsfx record);

    int insertBasicByBasicInfo(SzdSjsfx record);

    SzdSjsfx selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdSjsfx record);

    int updateByPrimaryKey(SzdSjsfx record);
}