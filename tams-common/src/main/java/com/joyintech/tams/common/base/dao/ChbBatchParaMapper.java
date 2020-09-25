package com.joyintech.tams.common.base.dao;

import java.util.List;

import com.joyintech.tams.common.base.entity.ChbBatchPara;


public interface ChbBatchParaMapper {

    int deleteByPrimaryKey(String paraId);

    int insert(ChbBatchPara record);

    List<ChbBatchPara> selectAllData();

}