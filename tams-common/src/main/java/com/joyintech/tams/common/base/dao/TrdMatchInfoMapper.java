package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdMatchInfoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrdMatchInfo record);

    int insertSelective(TrdMatchInfo record);

    int insertBasicByBasicInfo(TrdMatchInfo record);

    TrdMatchInfo selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrdMatchInfo record);

    int updateByPrimaryKey(TrdMatchInfo record);
}