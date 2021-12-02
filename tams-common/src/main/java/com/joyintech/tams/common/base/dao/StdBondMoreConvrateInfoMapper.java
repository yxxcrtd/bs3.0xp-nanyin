package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.StdBondMoreConvrateInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StdBondMoreConvrateInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(StdBondMoreConvrateInfo record);

    int insertSelective(StdBondMoreConvrateInfo record);

    int insertBasicByBasicInfo(StdBondMoreConvrateInfo record);

    StdBondMoreConvrateInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(StdBondMoreConvrateInfo record);

    int updateByPrimaryKey(StdBondMoreConvrateInfo record);
}