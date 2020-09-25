package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSubHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestholdInfoSubHisMapper {
    int deleteByPrimaryKey(Integer hisNo);

    int insert(ChbInvestholdInfoSubHis record);

    int insertSelective(ChbInvestholdInfoSubHis record);

    int insertHisByBasicInfo(ChbInvestholdInfoSubHis record);

    int insertHisByWorkflowId(ChbInvestholdInfoSubHis record);

    ChbInvestholdInfoSubHis selectByPrimaryKey(Integer hisNo);

    int updateByPrimaryKeySelective(ChbInvestholdInfoSubHis record);

    int updateByPrimaryKey(ChbInvestholdInfoSubHis record);
}