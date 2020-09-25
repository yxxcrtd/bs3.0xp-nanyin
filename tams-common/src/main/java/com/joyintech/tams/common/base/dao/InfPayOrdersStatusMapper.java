package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfPayOrdersStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface InfPayOrdersStatusMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(InfPayOrdersStatus record);

    int insertSelective(InfPayOrdersStatus record);

    int insertBasicByBasicInfo(InfPayOrdersStatus record);

    InfPayOrdersStatus selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(InfPayOrdersStatus record);

    int updateByPrimaryKey(InfPayOrdersStatus record);
}