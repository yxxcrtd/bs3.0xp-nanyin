package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepPledgeHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepPledgeHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteCrepPledgeHisMapper {
    int deleteByPrimaryKey(IbuQuoteCrepPledgeHisKey key);

    int insert(IbuQuoteCrepPledgeHis record);

    int insertSelective(IbuQuoteCrepPledgeHis record);

    int insertHisByBasicInfo(IbuQuoteCrepPledgeHis record);

    IbuQuoteCrepPledgeHis selectByPrimaryKey(IbuQuoteCrepPledgeHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteCrepPledgeHis record);

    int updateByPrimaryKey(IbuQuoteCrepPledgeHis record);
}