package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdVouchDealDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdVouchDealDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdVouchDealDetail record);

    int insertSelective(TrdVouchDealDetail record);

    int insertBasicByBasicInfo(TrdVouchDealDetail record);

    TrdVouchDealDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdVouchDealDetail record);

    int updateByPrimaryKey(TrdVouchDealDetail record);
}