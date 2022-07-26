package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import org.springframework.stereotype.Repository;

@Repository
public interface DisNostdCreditorMapper {
    int deleteByPrimaryKey(String nostdAssetId);

    int insert(DisNostdCreditor record);

    int insertSelective(DisNostdCreditor record);

    int insertBasicByBasicInfo(DisNostdCreditor record);

    DisNostdCreditor selectByPrimaryKey(String nostdAssetId);

    int updateByPrimaryKeySelective(DisNostdCreditor record);

    int updateByPrimaryKey(DisNostdCreditor record);
}

