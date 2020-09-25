package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTradSecposition;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOpicsTradSecpositionMapper {
    int deleteByPrimaryKey(String br);

    int insert(InterfOpicsTradSecposition record);

    int insertSelective(InterfOpicsTradSecposition record);

    int insertBasicByBasicInfo(InterfOpicsTradSecposition record);

    InterfOpicsTradSecposition selectByPrimaryKey(String br);

    int updateByPrimaryKeySelective(InterfOpicsTradSecposition record);

    int updateByPrimaryKey(InterfOpicsTradSecposition record);
}