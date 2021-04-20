package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteCrepHisMapper {
    int deleteByPrimaryKey(IbuQuoteCrepHisKey key);

    int insert(IbuQuoteCrepHis record);

    int insertSelective(IbuQuoteCrepHis record);

    int insertHisByBasicInfo(IbuQuoteCrepHis record);

    IbuQuoteCrepHis selectByPrimaryKey(IbuQuoteCrepHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteCrepHis record);

    int updateByPrimaryKey(IbuQuoteCrepHis record);
}