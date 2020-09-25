package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PaBranchSaleDetail;
import com.joyintech.tams.common.base.entity.PaBranchSaleDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface PaBranchSaleDetailMapper {
    int deleteByPrimaryKey(PaBranchSaleDetailKey key);

    int insert(PaBranchSaleDetail record);

    int insertSelective(PaBranchSaleDetail record);

    int insertBasicByBasicInfo(PaBranchSaleDetail record);

    PaBranchSaleDetail selectByPrimaryKey(PaBranchSaleDetailKey key);

    int updateByPrimaryKeySelective(PaBranchSaleDetail record);

    int updateByPrimaryKey(PaBranchSaleDetail record);
}