package com.joyintech.tams.invest.common.risktrialrds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrialrds.dto.SupervisionParameterDto;
import com.joyintech.tools.collectors.JoyinCollectors;  

@Service("RiskTrialCommService")
public class RiskTrialCommService {

	@Autowired
	private RiskTrialCommMapper riskTrialCommMapper;

	public List<SupervisionParameterDto> getIndicatorParamAll(String valDate,String userName){ 
	    List<SupervisionParameterDto> indicatorParams = new ArrayList<>();
	    List<SupervisionParameterDto> clearParamAll = new ArrayList<>();
	    List<SupervisionParameterDto> clearParamAllTwo = new ArrayList<>();
	    LoginUserUtil.getInstCode();
	    
 		String institution =  MultilevelDataAuthorityUtil.getOrganizationCode(userName); 
		List<SupervisionParameterDto> IndicatorParamAll = riskTrialCommMapper.getIndicatorParamAll(valDate); 
		for (SupervisionParameterDto surDto : IndicatorParamAll) {
            if (StringUtil.equals(surDto.getParaGranularity(), BussinessDictCodeListRds.指标参数实现类型_固定值.getValue())) {
                if (StringUtil.equals(surDto.getInstitutionCode(), institution)) {
                    indicatorParams.add(surDto);
                    clearParamAllTwo.add(surDto);
                }else {
                    clearParamAll.add(surDto);
                }
            }else {
                indicatorParams.add(surDto);
            }
        }
		if (!CollectionUtils.isEmpty(clearParamAll)) { 
    		Map<String, List<SupervisionParameterDto>> clearParamAllMap =clearParamAllTwo.stream().collect(JoyinCollectors.groupingBy(item->item.getParaCode()));
    		for (SupervisionParameterDto surDto : clearParamAll) {
    		    if (!StringUtil.isNotEmpty(clearParamAllMap.get(surDto.getParaCode()))
    		        && StringUtil.equals(surDto.getInstitutionCode(), BussinessDictCodeListRds.指标参数实现类型_固定值.getValue())) {
                    indicatorParams.add(surDto);
                }
            }
		}
		return indicatorParams;
	}
}
