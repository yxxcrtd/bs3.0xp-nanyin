package com.joyintech.tams.invest.common.prebatch.dao;

import com.joyintech.tams.invest.common.prebatch.dto.SysPrebatchResultDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPrebatchResultDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(SysPrebatchResultDetail record);

    int insertSelective(SysPrebatchResultDetail record);

    SysPrebatchResultDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SysPrebatchResultDetail record);

    int updateByPrimaryKey(SysPrebatchResultDetail record);
}