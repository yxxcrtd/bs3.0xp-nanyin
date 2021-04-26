package com.joyintech.tams.valuation.component.business.dao;

import com.joyintech.framework.base.entity.ProcessMark;
import org.springframework.stereotype.Repository;

/**
 * @author yanya
 * @create 2021-03-03 9:58
 */
@Repository
public interface ComponentWorkflowCommonMapper {
    /**
     * 根据工作ID获取提交流程数据
     * @param workFlowId
     * @return
     */
    ProcessMark getBusinessInfo(String workFlowId);
}
