package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.HkTz;
import org.springframework.stereotype.Repository;

@Repository
public interface HkTzMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(HkTz record);

    int insertSelective(HkTz record);

    int insertBasicByBasicInfo(HkTz record);

    HkTz selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(HkTz record);

    int updateByPrimaryKey(HkTz record);
}