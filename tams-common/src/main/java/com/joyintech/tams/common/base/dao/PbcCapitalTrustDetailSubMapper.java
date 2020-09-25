package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcCapitalTrustDetailSub;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PbcCapitalTrustDetailSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcCapitalTrustDetailSub record);

    int insertSelective(PbcCapitalTrustDetailSub record);

    int insertBasicByBasicInfo(PbcCapitalTrustDetailSub record);

    PbcCapitalTrustDetailSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcCapitalTrustDetailSub record);

    int updateByPrimaryKey(PbcCapitalTrustDetailSub record);
    
    int deleteByModel(Map<String, Object> paraMap);
}