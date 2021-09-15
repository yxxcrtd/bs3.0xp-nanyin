package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoErrordata;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfoErrordataWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdBaseinfoErrordataMapper {
    int deleteByPrimaryKey(String datauuid);

    int insert(ChbPrdBaseinfoErrordataWithBLOBs record);

    int insertSelective(ChbPrdBaseinfoErrordataWithBLOBs record);

    int insertBasicByBasicInfo(ChbPrdBaseinfoErrordataWithBLOBs record);

    ChbPrdBaseinfoErrordataWithBLOBs selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(ChbPrdBaseinfoErrordataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbPrdBaseinfoErrordataWithBLOBs record);

    int updateByPrimaryKey(ChbPrdBaseinfoErrordata record);
}