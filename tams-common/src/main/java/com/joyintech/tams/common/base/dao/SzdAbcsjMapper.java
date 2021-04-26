package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzdAbcsj;
import org.springframework.stereotype.Repository;

@Repository
public interface SzdAbcsjMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzdAbcsj record);

    int insertSelective(SzdAbcsj record);

    int insertBasicByBasicInfo(SzdAbcsj record);

    SzdAbcsj selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzdAbcsj record);

    int updateByPrimaryKey(SzdAbcsj record);
}