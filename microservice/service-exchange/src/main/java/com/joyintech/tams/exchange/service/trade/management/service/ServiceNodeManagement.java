package com.joyintech.tams.exchange.service.trade.management.service;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.datacommon.db.service.ServiceNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ServiceNodeManagement {
    @Autowired
    private ServiceNodeService serviceNodeService;

    public ResultBean queryServiceNode(String nodeName) {
        if (!Strings.isNullOrEmpty(nodeName)) {
            return new ResultBean(ErrorCode.SUCCESS, serviceNodeService.findByNodeName(nodeName));
        } else {
            return new ResultBean(ErrorCode.SUCCESS, serviceNodeService.findAll());
        }
    }

    public ResultBean addServiceNode(ServiceNode serviceNode) {
        if (Objects.isNull(serviceNode)) return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        return new ResultBean(ErrorCode.SUCCESS, serviceNodeService.insertServiceNode(serviceNode));
    }

    public ResultBean updateServiceNode(ServiceNode serviceNode) {
        if (Objects.isNull(serviceNode)) return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        return new ResultBean(ErrorCode.SUCCESS, serviceNodeService.updateServiceNode(serviceNode));
    }

    public ResultBean deleteServiceNode(String nodeName) {
        if (Strings.isNullOrEmpty(nodeName)) return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        return new ResultBean(ErrorCode.SUCCESS, serviceNodeService.deleteServiceNodeByNodeName(nodeName));
    }
}
