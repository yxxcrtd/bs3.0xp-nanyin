package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysTypeMapping;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysTypeMappingMapper {
    int deleteByPrimaryKey(String seqno);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysTypeMapping record);

    int insertSelective(ChbSysTypeMapping record);

    int insertBasicByBasicInfo(ChbSysTypeMapping record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysTypeMapping selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbSysTypeMapping record);

    int updateByWorkflowIdSelective(ChbSysTypeMapping record);

    int updateByWorkflowId(ChbSysTypeMapping record);

    int updateByPrimaryKey(ChbSysTypeMapping record);
}