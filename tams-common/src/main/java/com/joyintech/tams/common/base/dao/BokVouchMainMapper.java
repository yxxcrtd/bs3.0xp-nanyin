package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokVouchMain;
import org.springframework.stereotype.Repository;

@Repository
public interface BokVouchMainMapper {
    int deleteByPrimaryKey(String vouchNum);

    int insert(BokVouchMain record);

    int insertSelective(BokVouchMain record);

    int insertBasicByBasicInfo(BokVouchMain record);

    BokVouchMain selectByPrimaryKey(String vouchNum);

    int updateByPrimaryKeySelective(BokVouchMain record);

    int updateByPrimaryKey(BokVouchMain record);
}