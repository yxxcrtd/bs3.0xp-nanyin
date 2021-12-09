package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StlSecReconDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface StlSecReconDealMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(StlSecReconDeal record);

    int insertSelective(StlSecReconDeal record);

    int insertBasicByBasicInfo(StlSecReconDeal record);

    StlSecReconDeal selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(StlSecReconDeal record);

    int updateByPrimaryKey(StlSecReconDeal record);
}