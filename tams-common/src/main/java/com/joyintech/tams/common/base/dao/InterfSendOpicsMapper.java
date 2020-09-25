package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfSendOpics;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfSendOpicsMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(InterfSendOpics record);

    int insertSelective(InterfSendOpics record);

    int insertBasicByBasicInfo(InterfSendOpics record);

    InterfSendOpics selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(InterfSendOpics record);

    int updateByPrimaryKey(InterfSendOpics record);
}