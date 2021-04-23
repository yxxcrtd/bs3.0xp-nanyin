package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCfetsErrMsgTmp;
import com.joyintech.tams.common.base.entity.InterfCfetsErrMsgTmpKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCfetsErrMsgTmpMapper {
    int deleteByPrimaryKey(InterfCfetsErrMsgTmpKey key);

    int insert(InterfCfetsErrMsgTmp record);

    int insertSelective(InterfCfetsErrMsgTmp record);

    int insertBasicByBasicInfo(InterfCfetsErrMsgTmp record);

    InterfCfetsErrMsgTmp selectByPrimaryKey(InterfCfetsErrMsgTmpKey key);

    int updateByPrimaryKeySelective(InterfCfetsErrMsgTmp record);

    int updateByPrimaryKey(InterfCfetsErrMsgTmp record);
}