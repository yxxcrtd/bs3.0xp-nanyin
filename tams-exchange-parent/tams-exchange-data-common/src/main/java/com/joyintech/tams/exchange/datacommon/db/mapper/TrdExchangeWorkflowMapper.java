package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.bean.WorkFlowDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TrdExchangeWorkflowMapper
 * @Description TODO
 * @Author swwan
 * @Date 2020/8/7 14:52
 * @Version 1.0
 **/
@Repository
public interface TrdExchangeWorkflowMapper  {

    List<WorkFlowDto> selectWorkflow(Map<String, Object> param);

    String qureyStateById(String seqNo);

    String qureyStateByEntrustId(String entrustId);

}
