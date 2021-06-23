package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SzhkTz;
import org.springframework.stereotype.Repository;

@Repository
public interface SzhkTzMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(SzhkTz record);

    int insertSelective(SzhkTz record);

    int insertBasicByBasicInfo(SzhkTz record);

    SzhkTz selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(SzhkTz record);

    int updateByPrimaryKey(SzhkTz record);
}