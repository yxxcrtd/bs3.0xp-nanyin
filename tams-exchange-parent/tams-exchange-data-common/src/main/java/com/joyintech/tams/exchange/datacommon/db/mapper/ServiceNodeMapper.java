package com.joyintech.tams.exchange.datacommon.db.mapper;

import com.joyintech.tams.exchange.common.db.domain.ServiceNode;
import com.joyintech.tams.exchange.common.db.domain.ServiceNodeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceNodeMapper extends IMybatisMapper<ServiceNode, String, ServiceNodeExample> {
    int countByExample(ServiceNodeExample example);

    int deleteByExample(ServiceNodeExample example);

    int deleteByPrimaryKey(String nodeName);

    int insert(ServiceNode record);

    int insertSelective(ServiceNode record);

    List<ServiceNode> selectByExample(ServiceNodeExample example);

    ServiceNode selectByPrimaryKey(String nodeName);

    int updateByExampleSelective(@Param("record") ServiceNode record, @Param("example") ServiceNodeExample example);

    int updateByExample(@Param("record") ServiceNode record, @Param("example") ServiceNodeExample example);

    int updateByPrimaryKeySelective(ServiceNode record);

    int updateByPrimaryKey(ServiceNode record);
}