package com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskFactroRManageMapper;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.*;


/**
 * 流动性风险-情景管理
 * @author wangkang
 * @version 1.0 2019年8月21日
 * @see FlowRiskFactroRManageService
 * @since 1.0
 */
@Service
public class FlowRiskFactroRManageService extends BaseService<FlowRiskFactroRManageDto> {
	
	@Autowired
	private FlowRiskFactroRManageMapper FlowRiskFactroRManageMapper;
	 @Transactional
	 @DataSource(DataSourceType.STRESSTEST)
	 public List<FlowRiskFactroRManageDto> querycheck(Map<String, Object> pageParam)
	        throws Exception {
	        return FlowRiskFactroRManageMapper.querycheck(pageParam);
	    }
	
}
