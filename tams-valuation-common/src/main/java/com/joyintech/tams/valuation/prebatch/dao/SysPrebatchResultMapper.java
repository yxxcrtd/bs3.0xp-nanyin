package com.joyintech.tams.valuation.prebatch.dao;


import com.joyintech.tams.valuation.prebatch.dto.SysPrebatchResult;
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