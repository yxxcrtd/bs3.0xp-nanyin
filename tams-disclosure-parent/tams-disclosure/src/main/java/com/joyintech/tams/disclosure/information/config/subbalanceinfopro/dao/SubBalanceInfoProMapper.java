package com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface SubBalanceInfoProMapper {
    List<SubBalanceInfoPro001Dto> select(Map<String, Object> pageParam);

    SubBalanceInfoPro001Dto selectByPrimaryKey(Map map);

    int deleteByKey(SubBalanceInfoPro001Dto dto);

    int insert(SubBalanceInfoPro001Dto subBalanceInfoPro001Dto);

    int updateByKey(SubBalanceInfoPro001Dto dto);

    List<SelectOptionDto> getRegistNo(Map<String, Object> map);

    String selectByProdRegistNo(String prodRegistNo);

    List<SelectOptionDto> findProdId(Map<String, Object> map);

    List<SelectOptionDto> getRegistNoList(Map<String, String> map);

    List<SelectOptionDto> getProdIdlist(Map<String, String> map);


    List<SelectOptionDto> getRePord(Map<String, Object> pageParam);

    HashMap findProdName(String booksetId);

    List<SelectOptionDto> findSubjectNo(Map<String, Object> pageParam);


    List<SelectOptionDto> getProdId1(Map<String, Object> map);
}
