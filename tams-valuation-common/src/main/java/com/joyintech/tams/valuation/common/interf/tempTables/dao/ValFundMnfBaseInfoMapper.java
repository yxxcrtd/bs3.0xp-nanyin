package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfBaseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ValFundMnfBaseInfoMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValFundMnfBaseInfo record);

    int insertSelective(ValFundMnfBaseInfo record);

    int insertBasicByBasicInfo(ValFundMnfBaseInfo record);

    ValFundMnfBaseInfo selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValFundMnfBaseInfo record);

    int updateByPrimaryKey(ValFundMnfBaseInfo record);
}