package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.entity.CfgReadonlyFormShow;

public interface CfgReadonlyFormShowMapper {
    int deleteByPrimaryKey(CfgReadonlyFormShow cfgReadonlyFormShow);

    int insert(CfgReadonlyFormShow record);

    int insertSelective(CfgReadonlyFormShow record);

    CfgReadonlyFormShow selectByPrimaryKey(CfgReadonlyFormShow cfgReadonlyFormShow);

    int updateByPrimaryKeySelective(CfgReadonlyFormShow record);

    int updateByPrimaryKey(CfgReadonlyFormShow record);
}