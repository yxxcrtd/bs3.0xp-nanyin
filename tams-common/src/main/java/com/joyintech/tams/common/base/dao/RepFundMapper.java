package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepFund;
import org.springframework.stereotype.Repository;

@Repository
public interface RepFundMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepFund record);

    int insertSelective(RepFund record);

    int insertBasicByBasicInfo(RepFund record);

    RepFund selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepFund record);

    int updateByPrimaryKey(RepFund record);
}