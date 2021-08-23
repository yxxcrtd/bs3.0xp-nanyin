package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSubSum;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestholdInfoSubSumMapper {
    int insert(ChbInvestholdInfoSubSum record);

    int insertSelective(ChbInvestholdInfoSubSum record);
}