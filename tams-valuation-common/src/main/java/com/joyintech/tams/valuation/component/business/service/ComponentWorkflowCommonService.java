package com.joyintech.tams.valuation.component.business.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.component.business.dao.ComponentWorkflowCommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ComponentWorkflowCommonService
 * @Description: 业务组件工作流相关service
 * @Author yanya
 * @Date 2021/3/4 0004 
 * @Version V1.0
 **/
@Service("valComponentWorkflowCommonService")
@Transactional(rollbackFor = Exception.class)
public class ComponentWorkflowCommonService {

    @Autowired
    private ComponentWorkflowCommonMapper componentWorkflowCommonMapper;


    /**
     * 根据工作流ID获取工作流提交信息
     * @param workflowId
     * @return
     */
    public String getBusinessEntityByWorkFlowId(String workflowId) {
        ProcessMark entity = componentWorkflowCommonMapper.getBusinessInfo(workflowId);
        return StringUtil.isNotEmpty(entity)?entity.getBusinessData():null;
    }

    /**
     * 根据工作流ID返回实体信息
     * @param workflowId
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBusinessEntityByWorkFlowId(String workflowId, String beanName, Class<T> clazz) {
        // 获取业务entity
        String temp = this.getBusinessEntityByWorkFlowId(workflowId);
        Map<String,Object> all = JsonUtils.json2Object(temp,Map.class);
        try {
            if (all.get(beanName) != null){
                return (T)JsonUtils.map2entity((Map<String,Object>)all.get(beanName), clazz);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
