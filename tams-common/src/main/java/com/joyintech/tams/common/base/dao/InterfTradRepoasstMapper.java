package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfTradRepoasst;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfTradRepoasstMapper {
    int insert(InterfTradRepoasst record);

    int insertSelective(InterfTradRepoasst record);

    int insertBasicByBasicInfo(InterfTradRepoasst record);
}