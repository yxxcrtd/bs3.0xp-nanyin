package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.bean.WorkFlowDto;

import java.util.List;
import java.util.Map;

public interface TrdExchangeWorkflowService {

    List<WorkFlowDto> selectWorkflow(Map<String, Object> param);

    String qureyStateById(String seqNo);

    String qureyStateByEntrustId(String entrustId);
}
