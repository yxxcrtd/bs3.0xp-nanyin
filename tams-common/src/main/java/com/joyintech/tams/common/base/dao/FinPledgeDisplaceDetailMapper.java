package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPledgeDisplaceDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(FinPledgeDisplaceDetail record);

    int insertSelective(FinPledgeDisplaceDetail record);

    int insertBasicByBasicInfo(FinPledgeDisplaceDetail record);

    FinPledgeDisplaceDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(FinPledgeDisplaceDetail record);

    int updateByPrimaryKey(FinPledgeDisplaceDetail record);
}