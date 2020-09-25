package com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see FlowRiskSceneManage003Service
 * @since 1.0
 */
@Service
public class FlowRiskSceneManage003Service extends CommonExportService<FlowRiskSceneManage001Dto> 
        implements BaseProcessService<FlowRiskSceneManage001Dto>{
	  @Autowired
	    private FlowRiskSceneManage001Service flowRiskSceneManage001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FlowRiskSceneManage001Dto entity, OperationType type) {
    	flowRiskSceneManage001Service.updateScenesEffStatus(entity);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public FlowRiskSceneManage001Dto checkIsExistDetail(String workflowId) {
          ProcessMark processMark = getBusinessInfo(workflowId);
        return getBussinessEntity(processMark);
    }

}
