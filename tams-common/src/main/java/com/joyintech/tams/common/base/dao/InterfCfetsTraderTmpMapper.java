package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCfetsTraderTmp;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCfetsTraderTmpMapper {
    int deleteByPrimaryKey(String traderId);

    int insert(InterfCfetsTraderTmp record);

    int insertSelective(InterfCfetsTraderTmp record);

    int insertBasicByBasicInfo(InterfCfetsTraderTmp record);

    InterfCfetsTraderTmp selectByPrimaryKey(String traderId);

    int updateByPrimaryKeySelective(InterfCfetsTraderTmp record);

    int updateByPrimaryKey(InterfCfetsTraderTmp record);
}