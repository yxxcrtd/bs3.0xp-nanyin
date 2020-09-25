package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIndividAssetDel;

@Repository
public interface RskIndividAssetDelMapper {

    int deleteByPrimaryKey(String delId);

    int insert(RskIndividAssetDel record);

    int insertSelective(RskIndividAssetDel record);

    int insertBasicByBasicInfo(RskIndividAssetDel record);

    RskIndividAssetDel selectByPrimaryKey(String delId);

    int updateByPrimaryKeySelective(RskIndividAssetDel record);

    int updateByPrimaryKey(RskIndividAssetDel record);
}