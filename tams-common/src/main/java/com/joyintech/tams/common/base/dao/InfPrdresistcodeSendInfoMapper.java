package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfPrdresistcodeSendInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InfPrdresistcodeSendInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(InfPrdresistcodeSendInfo record);

    int insertSelective(InfPrdresistcodeSendInfo record);

    int insertBasicByBasicInfo(InfPrdresistcodeSendInfo record);

    InfPrdresistcodeSendInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(InfPrdresistcodeSendInfo record);

    int updateByPrimaryKey(InfPrdresistcodeSendInfo record);
}