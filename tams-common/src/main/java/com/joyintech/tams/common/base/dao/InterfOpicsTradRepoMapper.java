package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOpicsTradRepoMapper {
    int deleteByPrimaryKey(String tradeid);

    int insert(InterfOpicsTradRepo record);

    int insertSelective(InterfOpicsTradRepo record);

    int insertBasicByBasicInfo(InterfOpicsTradRepo record);

    InterfOpicsTradRepo selectByPrimaryKey(String tradeid);

    int updateByPrimaryKeySelective(InterfOpicsTradRepo record);

    int updateByPrimaryKey(InterfOpicsTradRepo record);
}