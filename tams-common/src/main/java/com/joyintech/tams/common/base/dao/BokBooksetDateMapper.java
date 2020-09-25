package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokBooksetDate;
import org.springframework.stereotype.Repository;

@Repository
public interface BokBooksetDateMapper {
    int deleteByPrimaryKey(String booksetId);

    int insert(BokBooksetDate record);

    int insertSelective(BokBooksetDate record);

    int insertBasicByBasicInfo(BokBooksetDate record);

    BokBooksetDate selectByPrimaryKey(String booksetId);

    int updateByPrimaryKeySelective(BokBooksetDate record);

    int updateByPrimaryKey(BokBooksetDate record);
}