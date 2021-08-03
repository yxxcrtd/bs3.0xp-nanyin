package com.joyintech.service.basicdata.futuresdepositpercent.dao;
import com.joyintech.service.basicdata.futuresdepositpercent.dto.FuturesDepositPercentDto;

import java.util.List;
import java.util.Map;

public interface FuturesDepositPercentMapper {
    List<FuturesDepositPercentDto> selectForPage(Map<String, Object> param);

    void deleteByPrimaryKey(FuturesDepositPercentDto entity);

    void insert(FuturesDepositPercentDto entity);

    void updateByPrimaryKeySelective(FuturesDepositPercentDto entity);

    FuturesDepositPercentDto selectByPrimaryKey(Map<String, Object> param);
}
