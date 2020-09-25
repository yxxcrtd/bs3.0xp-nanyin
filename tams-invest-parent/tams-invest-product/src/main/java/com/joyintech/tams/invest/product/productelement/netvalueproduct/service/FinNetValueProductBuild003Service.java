/*
 * 文件名：FinProductBuild003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.netvalueproduct.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication; 
import com.joyintech.tams.invest.product.productelement.netvalueproduct.dto.FinNetValueProductBuild003Dto; 
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.entity.FlowEntity;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.service.ProcessService;
import com.joyintech.workflow.engine.service.WorkFlowService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 发行状态管理
 * 
 * @author zhangliang
 * @version 1.0 2018年10月29日
 * @see FinNetValueProductBuild003Service
 * @since 1.0
 */
@Service
public class FinNetValueProductBuild003Service extends BaseService<FinNetValueProductBuild003Dto> implements BaseProcessService<FinNetValueProductBuild003Dto> {

    /**
     * 业务流程表service
     */
    @Autowired(required = false)
    private WorkFlowService workflowService;
    
    
    /**
     * 查询发行状态管理对象
     * 
     * @param finprodIdStr 金融产品代码拼接字符串
     * @return 行状态管理对象
     * @see
     * @since 1.0
     */
    public List<FinNetValueProductBuild003Dto> selectForList(String finprodIdStr) {
        List<String> finprodId = StringUtil.splitToList(null, finprodIdStr);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("userId", SecurityUtil.getLoginUser().getUserName());

        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductBuild/selectForList", map,
            FinNetValueProductBuild003Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinNetValueProductBuild003Dto entity, OperationType type) {
        if (!StringUtil.isEmptyList(entity.getSelectedItem())) {
            for (FinNetValueProductBuild003Dto FinNetValueProductBuild003Dto : entity.getSelectedItem()) {
            	restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                           + "/FinProductBuild/updateIssueStatusByFinprodId",
                    FinNetValueProductBuild003Dto, Integer.class);
                
            }
        }
        else {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                       + "/FinProductBuild/updateIssueStatusByFinprodId",
                entity, Integer.class);
        }
    }

    @Override
    public void doDelete(String workflowId) {
    }

    /**
     * 代办中提交流程启动时
     */
    @Override
    public void doUpdate(FinNetValueProductBuild003Dto entity) { 
        try {   
            Map<String, Object> map = Maps.newConcurrentMap();
            map.put("title", WorkFlowUtil.concatComment(getMessage("FinProductBuild001.table.prodId"),
              GlobalConstant.OPEN_PARENTHESIS, entity.getFinprodMarketId(),
              GlobalConstant.CLOSE_PARENTHESIS,
              getMessage("FinProductBuild003.table.newIssueStatus"),
              GlobalConstant.OPEN_PARENTHESIS,
              SysDataUtil.getNameFromDataDict(DictCode.发行状态,
                  entity.getSelectedItem().get(0).getNewIssueStatus()),
              GlobalConstant.CLOSE_PARENTHESIS));
             map.put("workFlowId",entity.getWorkflowId());
             map.put("updateUser",LoginUserUtil.getUserName());
             map.put("updateTime",DateUtil.now());
             workflowService.updateTitle(map);
          }
          catch (Exception e) {
              e.printStackTrace();
          }
//        ProcessMark newEntity;
//        try {
//            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(FinNetValueProductBuild003Dto.class, true);
//            // 工作流ID
//            newEntity.setWorkflowId(entity.getWorkflowId());
//            newEntity.setStatus(entity.getStatus());
//            newEntity.setBusinessData(JsonUtils.object2Json(entity));
//            // 更新业务表数据
//            // 操作留痕表
//            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (StringUtil.equals(entity.getOperateType(), "submit")) {
//           Map<String, Object> map = Maps.newConcurrentMap();
//           map.put("title", WorkFlowUtil.concatComment(getMessage("FinProductBuild001.table.prodId"),
//               GlobalConstant.OPEN_PARENTHESIS, entity.getFinprodMarketId(),
//               GlobalConstant.CLOSE_PARENTHESIS,
//               getMessage("FinProductBuild003.table.newIssueStatus"),
//               GlobalConstant.OPEN_PARENTHESIS,
//               SysDataUtil.getNameFromDataDict(DictCode.发行状态,
//                   entity.getSelectedItem().get(0).getNewIssueStatus()),
//               GlobalConstant.CLOSE_PARENTHESIS));
//           map.put("seqNo",entity.getWorkflowId());
//           restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
//                  + "/FinProductBuild/updateWflTitleByWorkflowId", map, Integer.class);
//        }   
        System.out.println("方法完成");
    } 
}
