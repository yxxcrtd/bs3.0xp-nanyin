package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductTypeConfigMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(FinProductTypeConfig record);

    int insertSelective(FinProductTypeConfig record);

    int insertBasicByBasicInfo(FinProductTypeConfig record);

    FinProductTypeConfig selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FinProductTypeConfig record);

    int updateByPrimaryKey(FinProductTypeConfig record);
}