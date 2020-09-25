package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.common.base.entity.MstIntRateInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstIntRateInfoMapper {
    int deleteByPrimaryKey(MstIntRateInfoKey key);

    int insert(MstIntRateInfo record);

    int insertSelective(MstIntRateInfo record);

    int insertBasicByBasicInfo(MstIntRateInfo record);

    MstIntRateInfo selectByPrimaryKey(MstIntRateInfoKey key);

    int updateByPrimaryKeySelective(MstIntRateInfo record);

    int updateByPrimaryKey(MstIntRateInfo record);
}