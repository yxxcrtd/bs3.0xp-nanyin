package com.joyintech.tams.invest.entrust.common.service;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;

public interface AuthProcessService<T extends ProcessEntity> extends BaseProcessService<T> {


    /**
     * 批量审批时检查 所有指令的授权审批人是否是同一档
     * @param
     * @return
     */
    default String checkAuthUser(WorkflowEntity workflowEntity, FlowEntity entity){
        EtrCommonService etrCommonService = (EtrCommonService) SpringContextUtil.getBean("etrCommonService");
        if (etrCommonService != null){
            return etrCommonService.checkAuthUser(workflowEntity,entity);
        }
        return null;
    }

    /**
     * 清理授权审批用户校验缓存
     * @param
     * @return
     */
    default void removeCheckAuthUser(FlowEntity flowEntity){
        // 清理授权审批用户校验缓存
        EtrCommonService etrCommonService = (EtrCommonService) SpringContextUtil.getBean("etrCommonService");
        if (etrCommonService != null){
            etrCommonService.removeCheckAuthUser(flowEntity.getNextUser()+flowEntity.getBusinessType());
        }
    }

}
