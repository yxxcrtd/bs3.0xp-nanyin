package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.ServiceNode;

import java.util.List;

public interface ServiceNodeService {
    List<ServiceNode> findAll();

    ServiceNode findByNodeName(String nodeName);

    int insertServiceNode(ServiceNode serviceNode);

    int updateServiceNode(ServiceNode serviceNode);

    int deleteServiceNodeByNodeName(String nodeName);
}
