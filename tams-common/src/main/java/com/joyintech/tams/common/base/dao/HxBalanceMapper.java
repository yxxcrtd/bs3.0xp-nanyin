package com.joyintech.tams.common.base.dao;


import com.joyintech.tams.common.base.entity.HxBalance;

public interface HxBalanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HxBalance record);

    int insertSelective(HxBalance record);

    HxBalance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HxBalance record);

    int updateByPrimaryKey(HxBalance record);
}