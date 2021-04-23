package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.common.db.domain.ServiceNodeExample;
import com.joyintech.tams.exchange.datacommon.db.mapper.ServiceNodeMapper;
import com.joyintech.tams.exchange.datacommon.db.service.ServiceNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNodeServiceImpl implements ServiceNodeService {
    @Autowired
    private ServiceNodeMapper serviceNodeMapper;

    @Override
    public List<ServiceNode> findAll() {
        return serviceNodeMapper.selectByExample(null);
    }

    @Override
    public ServiceNode findByNodeName(String nodeName) {
        return serviceNodeMapper.selectByPrimaryKey(nodeName);
    }

    @Override
    public int insertServiceNode(ServiceNode serviceNode) {
        return serviceNodeMapper.insertSelective(serviceNode);
    }

    @Override
    public int updateServiceNode(ServiceNode serviceNode) {
        ServiceNodeExample serviceNodeExample = new ServiceNodeExample();
        ServiceNodeExample.Criteria criteria = serviceNodeExample.createCriteria();
        criteria.andNodeNameEqualTo(serviceNode.getNodeName());
        return serviceNodeMapper.updateByExampleSelective(serviceNode, serviceNodeExample);
    }

    @Override
    public int deleteServiceNodeByNodeName(String nodeName) {
        return serviceNodeMapper.deleteByPrimaryKey(nodeName);
    }
}
