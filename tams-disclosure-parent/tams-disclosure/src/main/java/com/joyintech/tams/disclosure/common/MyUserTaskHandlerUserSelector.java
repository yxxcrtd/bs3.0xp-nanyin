package com.joyintech.tams.disclosure.common;

import com.joyintech.workflow.engine.dto.WflDefaultUser;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.entity.WorkflowEntity;
import com.joyintech.workflow.engine.service.UserTaskHandlerUserSelector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author 何阳阳
 * @date 2022/9/2 17:30
 */
@Component
public class MyUserTaskHandlerUserSelector implements UserTaskHandlerUserSelector {
    @Override
    public List<String> selectHandlerUser(String templateKey, String nodeId, WorkflowEntity entity, List<String> allStatedUsers) {
        return allStatedUsers;
    }

    @Override
    public List<WflDefaultUser> defaultSelected(String processKey, String taskId, FlowEntity entity, List<WflDefaultUser> defaultUsers) {
        // 流程中
        if(entity != null) {
            // 信批合规管理，最后一级业务经办默认选中流程发起人
            if(StringUtils.equals(entity.getProcessKey(), "fiveprocess")) {
                // 业务经办节点前一个节点
                if(StringUtils.equals(entity.getCurNodeId(), "review4")) {
                    WflDefaultUser defaultUser = new WflDefaultUser();
                    defaultUser.setModuleId(processKey);
                    defaultUser.setNodeId(entity.getCurNodeId());
                    defaultUser.setValue(entity.getCreateUser());
                    return Arrays.asList(defaultUser);
                }
            }
            // 模板启用管理页面，每一级退回 都需要默认勾线经办人
            // TODO
        }
        return defaultUsers;
    }
}
