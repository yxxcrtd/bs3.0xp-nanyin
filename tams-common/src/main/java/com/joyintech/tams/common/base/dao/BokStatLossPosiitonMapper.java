package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokStatLossPosiiton;
import org.springframework.stereotype.Repository;

@Repository
public interface BokStatLossPosiitonMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(BokStatLossPosiiton record);

    int insertSelective(BokStatLossPosiiton record);

    int insertBasicByBasicInfo(BokStatLossPosiiton record);

    BokStatLossPosiiton selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(BokStatLossPosiiton record);

    int updateByPrimaryKey(BokStatLossPosiiton record);
}