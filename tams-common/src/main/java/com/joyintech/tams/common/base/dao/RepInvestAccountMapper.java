package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepInvestAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface RepInvestAccountMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(RepInvestAccount record);

    int insertSelective(RepInvestAccount record);

    int insertBasicByBasicInfo(RepInvestAccount record);

    RepInvestAccount selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(RepInvestAccount record);

    int updateByPrimaryKey(RepInvestAccount record);
}
