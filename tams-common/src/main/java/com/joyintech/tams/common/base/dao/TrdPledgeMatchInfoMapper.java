package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdPledgeMatchInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdPledgeMatchInfoMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(TrdPledgeMatchInfo record);

    int insertSelective(TrdPledgeMatchInfo record);

    int insertBasicByBasicInfo(TrdPledgeMatchInfo record);

    TrdPledgeMatchInfo selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(TrdPledgeMatchInfo record);

    int updateByPrimaryKey(TrdPledgeMatchInfo record);
}