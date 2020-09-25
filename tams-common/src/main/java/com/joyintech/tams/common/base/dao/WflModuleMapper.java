package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflModule;
import org.springframework.stereotype.Repository;

@Repository
public interface WflModuleMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(WflModule record);

    int insertSelective(WflModule record);

    int insertBasicByBasicInfo(WflModule record);

    WflModule selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(WflModule record);

    int updateByPrimaryKey(WflModule record);
}