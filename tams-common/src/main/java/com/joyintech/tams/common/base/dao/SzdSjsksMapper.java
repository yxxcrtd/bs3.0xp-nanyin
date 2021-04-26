package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdSjsks;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdSjsksMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdSjsks record);

    int insertSelective(SzdSjsks record);

    int insertBasicByBasicInfo(SzdSjsks record);

    SzdSjsks selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdSjsks record);

    int updateByPrimaryKey(SzdSjsks record);
}