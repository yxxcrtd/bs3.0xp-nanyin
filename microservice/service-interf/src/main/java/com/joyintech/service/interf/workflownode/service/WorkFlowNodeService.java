/*
 * 文件名：WorkFlowNodeService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.workflownode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillOutEntity;
import com.joyintech.service.interf.workflownode.dao.WorkFlowNodeMapper;

/**
 * 查询流程节点处理人——详细处理
 * 
 * @author 杨静敏
 * @version 1.0 2018年12月20日
 * @see WorkFlowNodeService
 * @since 1.0
 */
@Service
public class WorkFlowNodeService extends InterfBaseService {

    /**
     * 查询流程节点处理人信息
     */
    @Autowired
    private WorkFlowNodeMapper workFlowNodeMapper;

    /**
     * 查询流程节点处理人
     * 
     * @param mark 流程信息
     * @throws Exception 异常
     * @return 程节点处理人信息
     * @see
     * @since 1.0
     */
    public InterfBillOutEntity selectWorkFlowNode(ProcessMark mark)
        throws Exception {

        InterfBillOutEntity interfEntity = new InterfBillOutEntity();
        // List<WflStatusDto> list = WorkFlowUtil.getNodeInfo(mark, 2);
        // for (WflStatusDto wflStatusDto : list) {
        // String nodeName = wflStatusDto.getNodeName();
        // // 查询用户表真实姓名
        // String userName = wflStatusDto.getHiUserName();
        // if (StringUtil.isBlank(userName)) {
        // getLogger().info("{}节点的处理人为空", nodeName);
        // continue;
        // }
        //
        // String realName = workFlowNodeMapper.selectRealname(userName);
        // // 经办节点
        // if (checkNodeName(InterfConstant.AGENTCY, nodeName)) {
        // interfEntity.setAgencyClientId(userName);
        // interfEntity.setAgencyClientName(realName);
        // }
        // // 审批1节点
        // else if (checkNodeName(InterfConstant.CHECK1, nodeName)) {
        // interfEntity.setApprUserId(userName);
        // interfEntity.setApprTellerName(realName);
        // }
        // // 审批2节点
        // else if (checkNodeName(InterfConstant.CHECK2, nodeName)) {
        // interfEntity.setGrantTellerNo(userName);
        // interfEntity.setOwnerName(realName);
        // }
        // }
        interfEntity.setAgencyClientId("user1");
        interfEntity.setAgencyClientName("交易员甲");
        interfEntity.setApprUserId("user2");
        interfEntity.setApprTellerName("投资经理乙");
        return interfEntity;
    }

    /**
     * 校验节点名称是否设置在系统参数中且和指定节点的名称一致
     * 
     * @param key 系统参数KEY
     * @param nodeName 指定节点的名称
     * @return true:校验成功，false：校验失败
     * @see
     * @since 1.0
     */
    private boolean checkNodeName(String key, String nodeName) {
        String node = InterfCacheUtil.getSysParam(key);
        if (StringUtil.isEmpty(node)) {
            getLogger().info("未设置系统参数{}的值.", key);
            getLogger().info("请设置系统参数{}的值并重启接口服务！", key);
        }
        return StringUtil.equals(node, nodeName);
    }
}
