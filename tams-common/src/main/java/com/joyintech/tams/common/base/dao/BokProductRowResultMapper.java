package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokProductRowResult;
import org.springframework.stereotype.Repository;

@Repository
public interface BokProductRowResultMapper {
    int deleteByPrimaryKey(String compareId);

    int insert(BokProductRowResult record);

    int insertSelective(BokProductRowResult record);

    int insertBasicByBasicInfo(BokProductRowResult record);

    BokProductRowResult selectByPrimaryKey(String compareId);

    int updateByPrimaryKeySelective(BokProductRowResult record);

    int updateByPrimaryKey(BokProductRowResult record);
}