package com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto.RskPortAutoRelatePoint002Dto;
import com.joyintech.service.riskrds.limitmanagement.rskportautorelatepoint.dto.RskPortAutoRelatePointDto;
import org.springframework.stereotype.Repository;


@Repository
public interface RskPortAutoRelatePointMapper {

	List<RskPortAutoRelatePointDto> getRuleName(Map<String, Object> param);

	List<RskPortAutoRelatePointDto> selectForPage(Map<String, Object> param);

	List<RskPortAutoRelatePoint002Dto> selectAllRule(Map<String, Object> param);

	int deletePoolConfigByRuleId(String ruleId);

	int deleteRuleIndicatorByRuleId(String ruleId);

	List<RskPortAutoRelatePointDto> findAllInt(Map<String, Object> param);

	Integer isExist(RskPortAutoRelatePoint002Dto dto);

	Integer isAreadyHaveInd(String ruleId);

}
