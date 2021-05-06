package com.joyintech.tams.invest.common.prebatch.dao;


import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResult;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPrebatchResultMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(SysPrebatchResult record);

    int insertSelective(SysPrebatchResult record);

    SysPrebatchResult selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SysPrebatchResult record);

    int updateByPrimaryKey(SysPrebatchResult record);
}