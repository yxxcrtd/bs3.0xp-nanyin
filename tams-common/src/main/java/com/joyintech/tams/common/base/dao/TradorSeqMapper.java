package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TradorSeq;
import org.springframework.stereotype.Repository;

@Repository
public interface TradorSeqMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TradorSeq record);

    int insertSelective(TradorSeq record);

    int insertBasicByBasicInfo(TradorSeq record);

    TradorSeq selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TradorSeq record);

    int updateByPrimaryKey(TradorSeq record);
}