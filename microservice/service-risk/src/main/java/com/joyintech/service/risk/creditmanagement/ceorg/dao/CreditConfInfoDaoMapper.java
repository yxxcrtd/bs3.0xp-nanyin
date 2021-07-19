package com.joyintech.service.risk.creditmanagement.ceorg.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.CreditConfInfo;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;



@Repository
public interface CreditConfInfoDaoMapper {
    int insert(CreditConfInfoDto record);

    int insertSelective(CreditConfInfoDto record);

    int insertBasicByBasicInfo(CreditConfInfoDto record);
    
    List<CreditConfInfoDto> getCreditConfig(Map<String, Object> pageParam);

    int updateByPrimaryKeySelective(CreditConfInfoDto record);

    int updateByPrimaryKey(CreditConfInfoDto record);
    
    int deleteByPrimaryKey(String creditMstId);

    List<CreditTagInfo> findCreditTagInfo(String creditType);

    List<CreditConfInfo> findCreditConfInfo(String creditTagId);

    CreditLines findCreditLines(@Param(value = "customerId") String customerId, @Param(value = "pflsh") String pflsh);
}