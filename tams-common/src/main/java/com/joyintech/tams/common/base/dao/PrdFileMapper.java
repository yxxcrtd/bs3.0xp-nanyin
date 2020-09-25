package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdFile;
import com.joyintech.tams.common.base.entity.PrdFileKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdFileMapper {
    int deleteByPrimaryKey(PrdFileKey key);

    int insert(PrdFile record);

    int insertSelective(PrdFile record);

    int insertBasicByBasicInfo(PrdFile record);

    PrdFile selectByPrimaryKey(PrdFileKey key);

    int updateByPrimaryKeySelective(PrdFile record);

    int updateByPrimaryKey(PrdFile record);
}