package com.joyintech.service.risk.creditmanagement.creditrulemanage.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.risk.creditmanagement.creditrulemanage.dto.CreditRuleInfoDto;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

@Repository
public interface CreditRuleMapper {

    List<CreditRuleInfoDto> selectForPage(Map<String, Object> param);

   	List<SelectOptionDto> getLimitLabel(Map<String, Object> map);
   	
    List<CreditRuleInfoDto> getCreditLabel(Map<String, Object> pageParam);

	List<SelectOptionDto> getCreditTagInfoList(Map<String, Object> param);
	
    int checkTag(Map<String, Object> param);

    List<CreditRuleInfoDto> checkRule(CreditRuleInfoDto param);
}