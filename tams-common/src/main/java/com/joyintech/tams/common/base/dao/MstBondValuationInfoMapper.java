package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstBondValuationInfoMapper {
    int deleteByPrimaryKey(MstBondValuationInfoKey key);

    int insert(MstBondValuationInfo record);

    int insertSelective(MstBondValuationInfo record);

    int insertBasicByBasicInfo(MstBondValuationInfo record);

    MstBondValuationInfo selectByPrimaryKey(MstBondValuationInfoKey key);

    int updateByPrimaryKeySelective(MstBondValuationInfo record);

    int updateByPrimaryKey(MstBondValuationInfo record);
}