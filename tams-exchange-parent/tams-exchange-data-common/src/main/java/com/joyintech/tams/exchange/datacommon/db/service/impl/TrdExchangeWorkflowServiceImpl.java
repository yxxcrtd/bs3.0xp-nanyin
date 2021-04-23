package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.bean.WorkFlowDto;
import com.joyintech.tams.exchange.datacommon.db.mapper.TrdExchangeWorkflowMapper;
import com.joyintech.tams.exchange.datacommon.db.service.TrdExchangeWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TrdExchangeWorkflowServiceImpl
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/14 16:07
 * @Version 1.0
 **/
@Service
public class TrdExchangeWorkflowServiceImpl implements TrdExchangeWorkflowService {

    @Autowired
    private TrdExchangeWorkflowMapper trdExchangeWorkflowMapper;

    @Override
    public List<WorkFlowDto> selectWorkflow(Map<String, Object> param) {
        return trdExchangeWorkflowMapper.selectWorkflow(param);
    }

    @Override
    public String qureyStateById(String seqNo) {
        return trdExchangeWorkflowMapper.qureyStateById(seqNo);
    }

    @Override
    public String qureyStateByEntrustId(String entrustId) {
        return trdExchangeWorkflowMapper.qureyStateByEntrustId(entrustId);
    }
}
