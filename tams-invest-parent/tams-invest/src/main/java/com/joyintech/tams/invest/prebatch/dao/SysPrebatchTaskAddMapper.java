package com.joyintech.tams.invest.prebatch.dao;


import com.joyintech.tams.invest.prebatch.dto.SysPrebatchTaskAdd;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchTaskAddKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPrebatchTaskAddMapper {
    int deleteByPrimaryKey(SysPrebatchTaskAddKey key);

    int insert(SysPrebatchTaskAdd record);

    int insertSelective(SysPrebatchTaskAdd record);

    SysPrebatchTaskAdd selectByPrimaryKey(SysPrebatchTaskAddKey key);

    int updateByPrimaryKeySelective(SysPrebatchTaskAdd record);

    int updateByPrimaryKey(SysPrebatchTaskAdd record);

    List<SysPrebatchTaskAdd> selectByGroupId(String groupId);

}