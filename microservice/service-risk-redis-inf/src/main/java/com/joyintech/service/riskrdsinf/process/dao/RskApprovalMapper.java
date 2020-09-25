package com.joyintech.service.riskrdsinf.process.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.base.entity.ProcessMark;

@Repository
public interface RskApprovalMapper {

	/**
	 * 取得最大的工作流ID
	 * 
	 * @param mark 查询条件
	 * @return 最大的工作流ID
	 */
	String getLastWorkflowId(ProcessMark mark);
}
