package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdTempTradeFeeDetail;
import com.joyintech.tams.common.base.entity.TrdTempTradeFeeDetailKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdTempTradeFeeDetailMapper {
    int deleteByPrimaryKey(TrdTempTradeFeeDetailKey key);

    int insert(TrdTempTradeFeeDetail record);

    int insertSelective(TrdTempTradeFeeDetail record);

    int insertBasicByBasicInfo(TrdTempTradeFeeDetail record);

    TrdTempTradeFeeDetail selectByPrimaryKey(TrdTempTradeFeeDetailKey key);

    int updateByPrimaryKeySelective(TrdTempTradeFeeDetail record);

    int updateByPrimaryKey(TrdTempTradeFeeDetail record);
}