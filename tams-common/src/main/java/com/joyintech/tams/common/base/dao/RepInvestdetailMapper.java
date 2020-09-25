package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInvestdetail;

@Repository
public interface RepInvestdetailMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(RepInvestdetail record);

    int insertSelective(RepInvestdetail record);

    int insertBasicByBasicInfo(RepInvestdetail record);

    RepInvestdetail selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(RepInvestdetail record);

    int updateByPrimaryKey(RepInvestdetail record);
}