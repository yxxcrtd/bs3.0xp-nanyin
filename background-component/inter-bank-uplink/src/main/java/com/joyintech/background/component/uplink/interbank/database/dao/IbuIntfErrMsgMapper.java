package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfErrMsg;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfErrMsgKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuIntfErrMsgMapper {
    int deleteByPrimaryKey(IbuIntfErrMsgKey key);

    int insert(IbuIntfErrMsg record);

    int insertSelective(IbuIntfErrMsg record);

    int insertBasicByBasicInfo(IbuIntfErrMsg record);

    IbuIntfErrMsg selectByPrimaryKey(IbuIntfErrMsgKey key);

    int updateByPrimaryKeySelective(IbuIntfErrMsg record);

    int updateByPrimaryKey(IbuIntfErrMsg record);
}