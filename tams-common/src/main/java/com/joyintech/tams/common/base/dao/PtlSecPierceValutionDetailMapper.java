package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PtlSecPierceValutionDetail;
import com.joyintech.tams.common.base.entity.PtlSecPierceValutionDetailKey;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PtlSecPierceValutionDetailMapper {
    int deleteByPrimaryKey(PtlSecPierceValutionDetailKey key);

    int insert(PtlSecPierceValutionDetail record);

    int insertSelective(PtlSecPierceValutionDetail record);

    int insertBasicByBasicInfo(PtlSecPierceValutionDetail record);

    PtlSecPierceValutionDetail selectByPrimaryKey(PtlSecPierceValutionDetailKey key);

    int updateByPrimaryKeySelective(PtlSecPierceValutionDetail record);

    int updateByPrimaryKey(PtlSecPierceValutionDetail record);
    
    int insertBatch(List<PtlSecPierceValutionDetail> list);
}