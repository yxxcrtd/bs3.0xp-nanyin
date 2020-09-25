package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcLoanDetailInfoSub;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface PbcLoanDetailInfoSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcLoanDetailInfoSub record);

    int insertSelective(PbcLoanDetailInfoSub record);

    int insertBasicByBasicInfo(PbcLoanDetailInfoSub record);

    PbcLoanDetailInfoSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcLoanDetailInfoSub record);

    int updateByPrimaryKey(PbcLoanDetailInfoSub record);
    
    int deleteByModel(Map<String, Object> paraMap);
}