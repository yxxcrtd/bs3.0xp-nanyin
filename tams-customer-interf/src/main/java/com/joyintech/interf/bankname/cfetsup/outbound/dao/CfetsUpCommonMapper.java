package com.joyintech.interf.bankname.cfetsup.outbound.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CfetsUpCommonMapper {
    Integer getSuccessfulSendedCount(String seqno);

    Integer getSendCountWithinInterval(String seqno);

    void lock(Map<String, Object> paras);

    void updateSendStatus(Map<String, Object> paras);
}