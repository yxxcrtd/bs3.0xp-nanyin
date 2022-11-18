package com.joyintech.tams.disclosure.information.config.investmentInfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo001Dto;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface InvestmentInfo001Mapper {
    List<InvestmentInfo001Dto> select(Map<String, Object> param);

    InvestmentInfo001Dto selectByPrimaryKey(Map<String,Object> map);

    List<SelectOptionDto> findProdId(Map<String, Object> map);

    int updateByKey(InvestmentInfo001Dto dto);

    int insert(InvestmentInfo001Dto investmentInfo001Dto);

    int insertHis(InvestmentInfo001Dto dto1);

    int deleteByKey(InvestmentInfo001Dto dto);

    List<SelectOptionDto> getProdId1(Map<Object, Object> map);

    List<InvestmentInfo001Dto> selectTopInfoList(List<InvestmentInfo001Dto> dataList);
}
