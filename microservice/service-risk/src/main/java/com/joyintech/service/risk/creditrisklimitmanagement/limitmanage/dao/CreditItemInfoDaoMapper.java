package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;




@Repository
public interface CreditItemInfoDaoMapper {
    int insert(CreditItemInfoDto record);

    int insertSelective(CreditItemInfoDto record);

    int insertBasicByBasicInfo(CreditItemInfoDto record);
    
    List<CreditItemInfoDto> getCreditConfig(Map<String, Object> pageParam);
    
    CreditItemInfoDto queryCreditItemInfoBySeqno(Map<String, Object> param);

    int deleteByPrimaryKey(String creditMstId);
    
    int updateByPrimaryKey(CreditItemInfoDto record);



}