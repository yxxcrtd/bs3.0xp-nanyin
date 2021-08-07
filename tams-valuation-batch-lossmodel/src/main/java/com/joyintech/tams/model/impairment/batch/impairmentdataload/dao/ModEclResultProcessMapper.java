package com.joyintech.tams.model.impairment.batch.impairmentdataload.dao;

import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import org.springframework.stereotype.Repository;

@Repository
public interface ModEclResultProcessMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModEclResultProcess record);

    int insertSelective(ModEclResultProcess record);

    int insertBasicByBasicInfo(ModEclResultProcess record);

    ModEclResultProcess selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModEclResultProcess record);

    int updateByPrimaryKey(ModEclResultProcess record);
}