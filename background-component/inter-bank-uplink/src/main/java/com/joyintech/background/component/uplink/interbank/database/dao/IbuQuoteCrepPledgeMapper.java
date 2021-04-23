package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepPledge;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrepPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteCrepPledgeMapper {
    int deleteByPrimaryKey(IbuQuoteCrepPledgeKey key);

    int insert(IbuQuoteCrepPledge record);

    int insertSelective(IbuQuoteCrepPledge record);

    int insertBasicByBasicInfo(IbuQuoteCrepPledge record);

    IbuQuoteCrepPledge selectByPrimaryKey(IbuQuoteCrepPledgeKey key);

    int updateByPrimaryKeySelective(IbuQuoteCrepPledge record);

    int updateByPrimaryKey(IbuQuoteCrepPledge record);
}