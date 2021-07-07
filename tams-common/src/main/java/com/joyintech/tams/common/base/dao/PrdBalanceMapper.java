package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PrdBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdBalanceMapper {
    int insert(PrdBalance record);

    int insertSelective(PrdBalance record);
}