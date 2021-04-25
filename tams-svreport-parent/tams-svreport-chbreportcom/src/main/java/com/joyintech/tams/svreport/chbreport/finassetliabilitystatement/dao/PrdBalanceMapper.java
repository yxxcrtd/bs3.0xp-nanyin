package com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dao;

import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.PrdBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdBalanceMapper {
    int insert(PrdBalance record);

    int insertSelective(PrdBalance record);
}